<?php
namespace App\ServiceDependency\User\Student;

use App\Entity\FileCourse;

use Doctrine\ORM\Query;

class FileCourseService extends AbstractStudentService
{
	private $rep;
	private $request;
	private $result;
	private $filename;
	private $file;

	protected function init()
	{
		$this->rep = $this->em->getRepository(FileCourse::class);
		$this->result = null;

		$this->resetRequest();
	}

	public function resetRequest()
	{
		$user_id = $this->user_service->getUser()->getId();

		$this->request = $this->rep->createQueryBuilder("f");


		$this->request->join("f.course", "co");

		$this->request->innerJoin("co.subjectYear", "sub");

		$this->request->innerJoin("sub.assignedStudents", "stds");
		
		$this->request->andWhere("stds.id = :std_id");
		$this->request->setParameter("std_id", $user_id);

		return $this;
	}

	public function findById($id)
	{
		$this->request->andWhere("f.id = :file_id");
		$this->request->setParameter("file_id", $id);

		return $this;
	}

	public function getCourse()
	{
		$this->request->addSelect("co");
		
		return $this;
	}

	public function getSubjectYear()
	{
		$this->request->addSelect("sub");
		
		return $this;
	}

	public function getAssignedStudents()
	{
		$this->request->addSelect("stds");
		
		return $this;
	}

	public function getResult()
	{
		if ($this->result === null)
		{
			$query = $this->request->getQuery();
			$query->setHint(Query::HINT_FORCE_PARTIAL_LOAD, true);
			$this->result = $query->getOneOrNullResult();
		}
		
		return $this->result;
	}

}