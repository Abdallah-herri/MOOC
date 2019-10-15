<?php
namespace App\ServiceDependency\User\Parent;

use App\Service\UserService;
use App\ServiceDependency\User\AbstractUserService;
use App\ServiceDependency\User\ParentService;

use Doctrine\ORM\EntityManagerInterface;

abstract class AbstractParentService extends AbstractUserService
{
	protected $parent_service;

	public function __construct(EntityManagerInterface $em, UserService $user_service, ParentService $parent_service)
	{
		$this->parent_service = $parent_service;

		parent::__construct($em, $user_service);
	}
}
