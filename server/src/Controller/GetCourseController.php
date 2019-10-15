<?php
namespace App\Controller;

use App\Service\UserService;
use App\Service\SerializerService;
use App\Exception\MoocException;

use Symfony\Component\Routing\Annotation\Route;

class GetCourseController extends AbstractMoocController
{
	private $course_id;
	private $user_service;

    /**
     * @Route("/student/get/course/{id}", methods={"GET"})
     */
	public function action($id, SerializerService $serializer, UserService $user_service)
	{
		$this->course_id = $id;
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}

	public function data()
	{
		$data = $this->user_service
			->getStudentService()
			->getCourseService()
			->getFiles()
			->findById($this->course_id)
			->getResult();
		
		if (empty($data))
		{
			throw new MoocException(MoocException::FORBIDDEN_ACTION);
		}

		return $data[0];
	}
}
