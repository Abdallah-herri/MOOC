<?php
namespace App\Controller;

use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;

class GetNoteController extends AbstractMoocController
{
	private $user_service;
	private $subject_year_id;

    /**
     * @Route("/student/get/note/{subject_year_id}", methods={"GET"})
     */
	public function action($subject_year_id, SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;
		$this->subject_year_id = $subject_year_id;

		return parent::abstractAction($serializer);
	}

	public function data()
	{
		$data = $this->user_service
			->getStudentService()
			->getNoteService()
			->getCourse()
			->getTeacher()
			->findBySubjectYear($this->subject_year_id)
			->getResult();

		return $data;
	}
}
