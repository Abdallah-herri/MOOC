<?php
namespace App\ServiceDependency\User\Student;

use App\Entity\Note;

use Doctrine\ORM\Query;

class NoteService extends AbstractStudentService
{
	private $rep;
	private $request;
	private $result;

	protected function init()
	{
		$this->rep = $this->em->getRepository(Note::class);
		$this->result = null;

		$this->resetRequest();
	}

	public function resetRequest()
	{
		$user_id = $this->user_service->getUser()->getId();

		$this->request = $this->rep->createQueryBuilder("n");

		$this->request->where("IDENTITY(n.student) = :user_id");
		$this->request->setParameter("user_id", $user_id);

		return $this;
	}

	public function getCourse()
	{
		$this->request->join("n.course", "co");
		$this->request->addSelect("co");

		$this->request->join("co.type", "ty");
		$this->request->addSelect("ty");

		return $this;
	}

	public function getSubjectYear()
	{
		$this->request->join("co.subjectYear", "sy");
		$this->request->addSelect("sy");
		
		$this->request->join("sy.schoolSubject", "sy_s");
		$this->request->addSelect("sy_s");
		
		$this->request->join("sy.schoolYear", "sy_y");
		$this->request->addSelect("sy_y");
		
		return $this;
	}
	
	public function getTeacher()
	{
		$this->request->join("co.teacher", "t");
		$this->request->addSelect("t");

		return $this;
	}
	
	public function findByCourse($id)
	{
		$this->request->andWhere("co.id = :course_id");
		$this->request->setParameter("course_id", $id);
		
		return $this;
	}
	
	public function findBySubjectYear($id)
	{
		$this->request->andWhere("IDENTITY(co.subjectYear) = :subject_year_id");
		$this->request->setParameter("subject_year_id", $id);
		
		return $this;
	}

	public function findByTeacher($id)
	{
		$this->request->andWhere("IDENTITY(co.teacher) = :teacher_id");
		$this->request->setParameter("teacher_id", $id);
		
		return $this;
	}
	
	public function orderBySubjectYear()
	{
		$this->request->addOrderBy('sy_y.name', 'ASC');
		$this->request->addOrderBy('sy_s.name', 'ASC');
		
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