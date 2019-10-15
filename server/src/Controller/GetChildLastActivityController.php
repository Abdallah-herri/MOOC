<?php
namespace App\Controller;

use App\Exception\MoocException;
use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;

class GetChildLastActivityController extends AbstractMoocController
{
	private $id;
	private $user_service;

    /**
     * @Route("/parent/get/lastactivity/{id}", methods={"GET"})
     */
	public function action($id, SerializerService $serializer, UserService $user_service)
	{
		$this->id = $id;
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}
	
	public function data()
	{
		$parent_service = $this->user_service->getParentService();
		$child_history_service = $parent_service->getChildActivityService();
		$child = null;
		
		if (!$parent_service->isChild($this->id, $child))
		{
			throw new MoocException(MoocException::FORBIDDEN_ACTION);
		}
		
		$data = $child_history_service->getLastActivity($child);
			
		return $data->getTime();
	}
}
