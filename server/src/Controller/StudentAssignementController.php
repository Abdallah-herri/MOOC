<?php
namespace App\Controller;

use App\Exception\MoocException;
use App\Service\SerializerService;
use App\Service\UserService;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class StudentAssignementController extends AbstractMoocController
{
	const P_STUDENT_KEY = "student_key";
	
	private $user_service;

    /**
	 * @Route("/parent/student_assignment", methods={"POST"})
     */
    public function action(SerializerService $serializer, UserService $user_service)
    {
		$this->user_service = $user_service;

		return parent::abstractAction($serializer);
    }

    public function data()
    {
		$request = Request::createFromGlobals();
		$post = $request->request;
		
		if (!$post->has(self::P_STUDENT_KEY))
		{
			throw new MoocException(MoocException::INVALID_FORM);
		}
		
		$student_key = $post->get(self::P_STUDENT_KEY);
		
    	$parent_service = $this->user_service->getParentService();
        $student_assignment_service = $parent_service->getStudentAssignmentService();
        $student = $student_assignment_service->assign($student_key);

        return $student;
    }
}
