<?php
namespace App\ServiceDependency\User\Parent;

use App\Entity\UserStudent;
use App\Entity\SchoolSubjectYear;
use App\Exception\MoocException;

class StudentAssignmentService extends AbstractParentService
{
	public function init()
	{

	}

	public function assign($student_key)
	{
		$parent = $this->user_service->getUser();

		$rep = $this->em->getRepository(UserStudent::class);
		$request = $rep->createQueryBuilder("c");
		$request->leftJoin("c.parents", "p");
		$request->addSelect("p");
		$request->where("c.studentKey = :student_key");
		$request->setParameter("student_key", $student_key);
		$query = $request->getQuery();
		$student = $query->getOneOrNullResult();

		if ($student === null)
		{
			throw new MoocException(MoocException::USER_NOT_EXIST);
		}
		
		// no lazy loading (it's secure because we check if assignation already exist in child)
		$parent->getChilds()->setInitialized(true);

		$student->addParent($parent);
		$this->em->persist($student);
		$this->em->flush();

		return $student;
	}
	
	public function assignSubjectYear(UserStudent $child, $subject_year_id)
	{
		$rep = $this->em->getRepository(SchoolSubjectYear::class);
		$request = $rep->createQueryBuilder("sy");
		$request->leftJoin("sy.assignedStudents", "u");
		$request->addSelect("u");
		$request->where("sy.id = :subject_year_id");
		$request->setParameter("subject_year_id", $subject_year_id);
		$query = $request->getQuery();
		$data = $query->getOneOrNullResult();
		
		if ($data === null)
		{
			throw new MoocException(MoocException::SUBJECT_YEAR_NOT_EXIST);
		}
		
		$child->getAssignedCourses()->setInitialized(true);
		$data->addAssignedStudent($child);
		$child->getAssignedCourses()->setInitialized(false);
		
		$this->em->persist($data);
		$this->em->flush();		
		
	}
}
