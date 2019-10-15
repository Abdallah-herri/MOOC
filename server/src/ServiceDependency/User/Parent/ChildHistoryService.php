<?php
namespace App\ServiceDependency\User\Parent;

use App\Entity\History;
use App\Entity\UserStudent;

use Doctrine\ORM\Query;

class ChildHistoryService extends AbstractParentService
{
	public function init()
	{
	}
	
	public function getChildHistory(UserStudent $child)
	{
		$user_id = $child->getId();
		$rep = $this->em->getRepository(History::class);
		
		$request = $rep->createQueryBuilder("h");
		$request->join("h.course", "c");
		$request->addSelect("c");
		$request->join("c.type", "ct");
		$request->addSelect("ct");
		$request->where("IDENTITY(h.student) = :user_id");
		$request->setParameter("user_id", $user_id);
		$request->orderBy('h.time', 'DESC');
		$request->setMaxResults(30);
		
		$query = $request->getQuery();
		$query->setHint(Query::HINT_FORCE_PARTIAL_LOAD, true);
		$result = $query->getResult();
		
		
		return $result;
	}
}
