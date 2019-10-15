<?php
namespace App\ServiceDependency\User\Teacher;

use App\Service\UserService;
use App\ServiceDependency\User\AbstractUserService;
use App\ServiceDependency\User\TeacherService;

use Doctrine\ORM\EntityManagerInterface;

abstract class AbstractTeacherService extends AbstractUserService
{
	protected $teacher_service;

	public function __construct(EntityManagerInterface $em, UserService $user_service, TeacherService $teacher_service)
	{
		$this->teacher_service = $teacher_service;

		parent::__construct($em, $user_service);
	}
}
