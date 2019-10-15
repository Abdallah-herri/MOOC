<?php
namespace App\ServiceDependency\User\Parent;

use App\Entity\UserStudent;

use Doctrine\ORM\Query;

class ChildService extends AbstractParentService
{
	private $rep;
	private $request;
	private $result;
	
	public function init()
	{
		$user = $this->user_service->getUser();
		$this->rep = $this->em->getRepository(UserStudent::class);
		
		$this->request = $this->rep->createQueryBuilder("child");
		$this->request->innerJoin("child.parents", "p");
		$this->request->where("p.id = :parent_id");
		$this->request->setParameter("parent_id", $user->getId());
	}
	
	public function getParent()
	{
		$this->request->addSelect("p");
		
		return $this;
	}
	
	public function getNote()
	{
		$this->request->leftJoin("child.notes", "n");
		$this->request->addSelect("n");
		
		return $this;
	}
	
	public function getSubjectAssignment()
	{
		$this->request->innerJoin("child.assignedCourses", "sy");
		$this->request->addSelect("sy");
		
		$this->request->join("sy.schoolSubject", "subject");
		$this->request->addSelect("subject");
		
		$this->request->join("sy.schoolYear", "year");
		$this->request->addSelect("year");
		
		return $this;
	}
	
	public function getCourses()
	{
		$this->request->leftJoin("sy.courses", "course");
		$this->request->addSelect("course");
		
		return $this;
	}
	
	public function findChildById($id)
	{
		$this->request->andWhere("child.id = :id");
		$this->request->setParameter("id", $id);
		
		return $this;
	}
	
	public function getResult($is_lazy_loading = false)
	{
		if ($this->result === null)
		{
			$query = $this->request->getQuery();
			if (!$is_lazy_loading) $query->setHint(Query::HINT_FORCE_PARTIAL_LOAD, true);
			$this->result = $query->getResult();
		}
		
		return $this->result;
	}
}
