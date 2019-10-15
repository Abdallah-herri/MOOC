<?php
namespace App\Controller;

use App\Exception\MoocException;
use App\Service\SerializerService;
use App\Service\UserService;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class SignInController extends AbstractMoocController
{
	// message
	const SUCCESS_LOGIN = "L_SUCCESS_LOGIN";

	// parameters name:
	const P_LOGIN = "login";
	const P_PASSWORD = "password";

	private $user_service;

    /**
	 * @Route("/sign_in", methods={"POST"})
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
		$guest_service = $this->user_service->getGuestService();

		if (!$post->has(self::P_LOGIN) || !$post->has(self::P_PASSWORD))
		{
			throw new MoocException(MoocException::INVALID_FORM);
		}
		
		$login = $post->get(self::P_LOGIN);
		$password = $post->get(self::P_PASSWORD);
		
		if (empty($login) || empty($password))
		{
			throw new MoocException(MoocException::EMPTY_FIELD);
		}
		
		$user = $this->user_service->findUser($login);
		
		if ($user === null || !$user->checkPassword($password))
		{
			throw new MoocException(MoocException::INVALID_LOGIN);
		}
		
		$guest_service->connexion($user);
		$this->result->setMessage(self::SUCCESS_LOGIN);

		return $user;
	}
}
