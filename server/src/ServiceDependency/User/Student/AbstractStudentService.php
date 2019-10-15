<?php
namespace App\ServiceDependency\User\Student;

use App\Service\UserService;
use App\ServiceDependency\User\AbstractUserService;
use App\ServiceDependency\User\StudentService;

use Doctrine\ORM\EntityManagerInterface;

abstract class AbstractStudentService extends AbstractUserService
{
	protected $student_service;

	public function __construct(EntityManagerInterface $em, UserService $user_service, StudentService $student_service)
	{
		$this->student_service = $student_service;

		parent::__construct($em, $user_service);
	}
}
