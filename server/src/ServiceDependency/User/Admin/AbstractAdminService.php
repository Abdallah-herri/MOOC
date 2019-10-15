<?php
namespace App\ServiceDependency\User\Admin;

use App\Service\UserService;
use App\ServiceDependency\User\AbstractUserService;
use App\ServiceDependency\User\AdminService;

use Doctrine\ORM\EntityManagerInterface;

abstract class AbstractAdminService extends AbstractUserService
{
	protected $admin_service;

	public function __construct(EntityManagerInterface $em, UserService $user_service, AdminService $admin_service)
	{
		$this->admin_service = $admin_service;

		parent::__construct($em, $user_service);
	}
}
