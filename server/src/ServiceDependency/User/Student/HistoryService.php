<?php
namespace App\ServiceDependency\User\Student;

use App\Entity\Course;
use App\Entity\History;
use App\Entity\UserStudent;

use Doctrine\ORM\Query;

class HistoryService extends AbstractStudentService
{
	private $rep;
	private $request;
	private $result;

	protected function init()
	{
		$this->rep = $this->em->getRepository(History::class);
		$this->result = null;

		$this->resetRequest();
	}

	public function resetRequest()
	{
		$user_id = $this->user_service->getUser()->getId();
		
		$this->request = $this->rep->createQueryBuilder("h");
		
		$this->request->where("IDENTITY(h.student) = :user_id");
		$this->request->setParameter("user_id", $user_id);
		
		$this->request->orderBy('h.time', 'DESC');
		
		return $this;
	}
	
	public function getCourse()
	{
		$this->request->join("h.course", "c");
		$this->request->addSelect("c");
		
		$this->request->join("c.type", "ct");
		$this->request->addSelect("ct");
		
		return $this;
	}
	
	public function limit()
	{
		$this->request->setMaxResults(30);
		
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
	
	public function addHistory($message, UserStudent $student, Course $course)
	{
		$history = new History();
		$history->setMessage($message);
		$history->setTime(time());
		$history->setStudent($student);
		$history->setCourse($course);
		
		$this->em->persist($history);
		$this->em->flush();
	}
}
