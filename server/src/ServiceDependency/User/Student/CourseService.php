<?php
namespace App\ServiceDependency\User\Student;

use App\Entity\Course;

use Doctrine\ORM\Query;

class CourseService extends AbstractStudentService
{
	private $rep;
	private $request;
	private $result;

	protected function init()
	{
		$this->rep = $this->em->getRepository(Course::class);
		$this->result = null;

		$this->resetRequest();
	}

	public function resetRequest()
	{
		$user_id = $this->user_service->getUser()->getId();
		
		$this->request = $this->rep->createQueryBuilder("c");

		$this->request->join("c.type", "ct");
		$this->request->addSelect("ct");

		$this->request->innerJoin("c.subjectYear", "sy");
		$this->request->addSelect("sy");

		$this->request->innerJoin("sy.assignedStudents", "u");

		$this->request->where("u.id = :user_id");
		$this->request->setParameter("user_id", $user_id);
		
		return $this;
	}
	
	public function getTeacher()
	{
		$this->request->join("c.teacher", "t");
		$this->request->join("t.type", "tt");

		$this->request->addSelect("t");
		$this->request->addSelect("tt");
		
		return $this;
	}
	
	public function getFiles()
	{
		$this->request->join("c.files", "f");
		$this->request->addSelect("f");
		
		return $this;
	}
	
	public function getPlanning()
	{
		$this->request->join("c.planning", "p");
		$this->request->addSelect("p");
		
		return $this;
	}
	
	public function findById($id)
	{
		$this->request->andWhere("c.id = :course_id");
		$this->request->setParameter("course_id", $id);
		
		return $this;
	}
	
	public function getResult()
	{
		if ($this->result === null)
		{
			$query = $this->request->getQuery();
			$query->setHint(Query::HINT_FORCE_PARTIAL_LOAD, true);
			$this->result = $query->getResult();
		}
		
		return $this->result;
	}
}
