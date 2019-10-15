<?php
namespace App\ServiceDependency\User;

use App\Entity\Session;
use App\Entity\User;
use App\Entity\UserAdmin;
use App\Entity\UserTeacher;
use App\Entity\UserParent;
use App\Entity\UserStudent;
use App\Entity\UserType;

class GuestService extends AbstractUserService
{
	protected function init()
	{
		
	}

	public function connexion(User $user)
	{
		$key = bin2hex(random_bytes(Session::C_SESSION_KEY_LENGTH));
		$time = time();
		
		$session = new Session();
		$session->setSessionKey($key);
		$session->setTime($time);
		
		$user->getSession()->setInitialized(true); // no lazy loading
		$user->addSession($session);
		
		$this->em->persist($session);
		$this->em->flush();
	}
	
	public function newUser($login, $password, $last_name, $first_name, $user_type)
	{
		switch ($user_type)
		{
			case UserType::ADMIN:
				$user = new UserAdmin();
			break;
			
			case UserType::TEACHER:
				$user = new UserTeacher();
			break;
			
			case UserType::PARENT:
				$user = new UserParent();
			break;

			case UserType::STUDENT:
				$user = new UserStudent();
				$child_key = bin2hex(random_bytes(UserStudent::C_CHILD_KEY_LENGHT));

				$user->setStudentKey($child_key);
			break;
			
			default:
				throw new MoocException(MoocException::INVALID_USER_TYPE);
			break;
		}
		
		$user->setLogin($login);
		$user->setPassword(password_hash($password, PASSWORD_DEFAULT));
		$user->setLastName($last_name);
		$user->setFirstName($first_name);
		
		$this->em->persist($user);
		$this->em->flush();
		
		return $user;
	}
}
