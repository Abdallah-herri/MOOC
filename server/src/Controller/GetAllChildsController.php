<?php
namespace App\Controller;

use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\Routing\Annotation\Route;

class GetAllChildsController extends AbstractMoocController
{
	private $user_service;

    /**
     * @Route("/parent/get/childs", methods={"GET"})
     */
	public function action(SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}
	
	public function data()
	{
		$parent_service = $this->user_service->getParentService();
		$child_service = $parent_service->getChildService();
		
		$data = $child_service
			->getResult();
			
		return $data;
	}
}