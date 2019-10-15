<?php
namespace App\Controller;

use App\Entity\MessageResponse;
use App\Exception\MoocException;
use App\Service\SerializerService;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;

use Exception;

abstract class AbstractMoocController
{
	protected $result;

	public function abstractAction(SerializerService $serializer)
	{
		$this->result = new MessageResponse();

		try
		{
			$data = $this->data();
			$this->result->setData($data);
		}
		catch (MoocException $e)
		{
			$this->result->error($e);
		}

		$json = $serializer->serialize($this->result);
		$response = JsonResponse::fromJsonString($json);
		
		return $response;
	}

	protected abstract function data();
}
