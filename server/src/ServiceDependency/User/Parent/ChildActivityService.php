<?php
namespace App\ServiceDependency\User\Parent;

use App\Entity\Session;
use App\Entity\UserStudent;

use Doctrine\ORM\Query;

class ChildActivityService extends AbstractParentService
{
	public function init()
	{
	}
	
	public function getLastActivity(UserStudent $child)
	{
		$user_id = $child->getId();
		$rep = $this->em->getRepository(Session::class);
		
		$request = $rep->createQueryBuilder("s");
		$request->where("IDENTITY(s.user) = :user_id");
		$request->setParameter("user_id", $user_id);
		$request->orderBy('s.time', 'DESC');
		$request->setMaxResults(1);
		
		$query = $request->getQuery();
		$result = $query->getOneOrNullResult();
		
		return $result;
	}
}
