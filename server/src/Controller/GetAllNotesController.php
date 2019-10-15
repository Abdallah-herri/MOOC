<?php
namespace App\Controller;

use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;

class GetAllNotesController extends AbstractMoocController
{
	private $user_service;

    /**
     * @Route("/student/get/notes", methods={"GET"})
     */
	public function action(SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}

	public function data()
	{
		$data = $this->user_service
			->getStudentService()
			->getNoteService()
			->getCourse()
			->getSubjectYear()
			->orderBySubjectYear()
			->getResult();

		return $data;
	}
}
