<?php
namespace App\Controller;

use App\Entity\UserType;
use App\Exception\MoocException;
use App\Service\SerializerService;
use App\Service\UserService;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class SignUpController extends AbstractMoocController
{
	// message
	const SUCCESS_RESITER = "L_SUCCESS_REGISTER";
	
	// parameters name:
	const P_LOGIN = "login";
	const P_PASSWORD = "password";
	const P_LAST_NAME = "last_name";
	const P_FIRST_NAME = "first_name";
	const P_USER_TYPE = "user_type";
	
	// other const
	const C_PASSWORD_MIN_LEN = 7;

	private $user_service;

    /**
	 * @Route("/sign_up", methods={"POST"})
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

		if (
			!$post->has(self::P_LOGIN)
			|| !$post->has(self::P_PASSWORD)
			|| !$post->has(self::P_LAST_NAME)
			|| !$post->has(self::P_FIRST_NAME)
			|| !$post->has(self::P_USER_TYPE)
		) {
			throw new MoocException(MoocException::INVALID_FORM);
		}
		
		$login = $post->get(self::P_LOGIN);
		$password = $post->get(self::P_PASSWORD);
		$last_name = $post->get(self::P_LAST_NAME);
		$first_name = $post->get(self::P_FIRST_NAME);
		$user_type = $post->get(self::P_USER_TYPE);
		
		if ($user_type != UserType::PARENT && $user_type != UserType::STUDENT)
		{
			throw new MoocException(MoocException::INVALID_USER_TYPE);
		}
		
		if (empty($login) || empty($password) || empty($last_name) || empty($first_name))
		{
			throw new MoocException(MoocException::EMPTY_FIELD);
		}
		
		if (filter_var($login, FILTER_VALIDATE_EMAIL) === false)
		{
			throw new MoocException(MoocException::INVALID_EMAIL);
		}
		
		if (strlen($password) < self::C_PASSWORD_MIN_LEN)
		{
			throw new MoocException(MoocException::INVALID_PASSWORD);
		}
		
		$search_user = $this->user_service->findUser($login);
		
		if ($search_user !== null)
		{
			throw new MoocException(MoocException::USER_ALREADY_EXIST);
		}
		
		$user = $guest_service->newUser($login, $password, $last_name, $first_name, $user_type);
		$this->result->setMessage(self::SUCCESS_RESITER);

		return null;
	}
}