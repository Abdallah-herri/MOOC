<?php
namespace App\Controller;

use App\Entity\History;
use App\Exception\MoocException;
use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;

class GetFileController
{
	const C_PATH = "./files/";
	
	private $user_service;

    /**
     * @Route("/files/get/{file_id}", methods={"GET"})
     */
	public function action($file_id, SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;

		$student_service = $this->user_service->getStudentService();
		$file_service = $student_service->getFileService();
		$history_service = $student_service->getHistoryService();
		
		$data = $file_service
			->getCourse()
			->findById($file_id)
			->getResult();

		if($data === null)
		{
			throw new NotFoundHttpException(MoocException::FILE_NOT_FOUND);
		}

		$course = $data->getCourse();
		$user = $user_service->getUser();
		$history_service->addHistory(History::READ_COURSE, $user, $course);

		$filename = self::C_PATH . $data->getFileName();
		$file = file_get_contents($filename);

		$response = new Response($file);
		$response->headers->set('Content-Type', $data->getMime());

		return $response;

	}
}
