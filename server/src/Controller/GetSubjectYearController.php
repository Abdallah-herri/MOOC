<?php
namespace App\Controller;

use App\Service\SerializerService;
use App\Entity\SchoolSubjectYear;

use Doctrine\ORM\EntityManagerInterface;
use Doctrine\ORM\Query;

use Symfony\Component\Routing\Annotation\Route;

class GetSubjectYearController extends AbstractMoocController
{
	private $em;

    /**
     * @Route("/get/subject_year", methods={"GET"})
     */
	public function action(SerializerService $serializer, EntityManagerInterface $em)
	{
		$this->em = $em;

		return parent::abstractAction($serializer);
	}
	
	public function data()
	{
		$rep = $this->em->getRepository(SchoolSubjectYear::class);
		$request = $rep->createQueryBuilder("sy");
		$request->join("sy.schoolSubject", "sy_s");
		$request->addSelect("sy_s");
		$request->join("sy.schoolYear", "sy_y");
		$request->addSelect("sy_y");
		$request->addOrderBy('sy_y.name', 'ASC');
		$request->addOrderBy('sy_s.name', 'ASC');
		
		$query = $request->getQuery();
		$query->setHint(Query::HINT_FORCE_PARTIAL_LOAD, true);
		$data = $query->getResult();
		
		return $data;
	}
}
