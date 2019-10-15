<?php
namespace App\Controller;

use App\Exception\MoocException;
use App\Service\UserService;
use App\Service\SerializerService;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class ChildSubscribeController extends AbstractMoocController
{
	const P_CHILD_ID = "child_id";
	const P_SUBJECT_YEAR_ID = "subject_year_id";

	private $user_service;

    /**
     * @Route("/parent/subscribe/child", methods={"POST"})
     */
	public function action(SerializerService $serializer, UserService $user_service)
	{
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
	}
	
	public function data()
	{
		$parent_service = $this->user_service->getParentService();
		$assignment_service = $parent_service->getStudentAssignmentService();
		$child = null;
		
		$request = Request::createFromGlobals();
		$post = $request->request;
		
		if (
			!$post->has(self::P_CHILD_ID)
			|| !$post->has(self::P_SUBJECT_YEAR_ID)
		) {
			throw new MoocException(MoocException::INVALID_FORM);
		}
		
		$child_id = $post->get(self::P_CHILD_ID);
		$subject_years = $post->get(self::P_SUBJECT_YEAR_ID);
		
		if (!$parent_service->isChild($child_id, $child, true))
		{
			throw new MoocException(MoocException::FORBIDDEN_ACTION);
		}
		
		foreach ($subject_years as &$subject_year_id)
		{
			$data = $assignment_service->assignSubjectYear($child, $subject_year_id);
		}
		
		return null;
	}
}
