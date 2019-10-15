<?php
namespace App\Controller;

use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;

class IsConnectedController extends AbstractMoocController
{
	private $course_id;
	private $user_service;

    /**
     * @Route("/is_connected", methods={"GET"})
     */
	public function action(SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}

	public function data()
	{
		return $this->user_service->isConnected();
	}
}
