<?php
namespace App\ServiceDependency\User;

use App\Service\UserService;

use Doctrine\ORM\EntityManagerInterface;

abstract class AbstractUserService
{
	protected $em;
	protected $user_service;

	public function __construct(EntityManagerInterface $em, UserService $user_service)
	{
		$this->em = $em;
		$this->user_service = $user_service;

		$this->init();
	}

	protected abstract function init();
}
