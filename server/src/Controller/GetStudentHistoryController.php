<?php
namespace App\Controller;

use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;

class GetStudentHistoryController extends AbstractMoocController
{
	private $user_service;

    /**
     * @Route("/student/get/history", methods={"GET"})
     */
	public function action(SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}
	
	public function data()
	{
		$student_service = $this->user_service->getStudentService();
		
		$data = $student_service
			->getHistoryService()
			->getCourse()
			->limit()
			->getResult();
			
		return $data;
	}
}
