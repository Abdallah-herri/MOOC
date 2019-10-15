<?php
namespace App\Service;

use App\Entity\User;
use App\Entity\Session;
use App\Exception\MoocException;
use App\ServiceDependency\User\AbstractUserService;
use App\ServiceDependency\User\AdminService;
use App\ServiceDependency\User\GuestService;
use App\ServiceDependency\User\ParentService;
use App\ServiceDependency\User\StudentService;
use App\ServiceDependency\User\TeacherService;

use Doctrine\ORM\EntityManagerInterface;
use Doctrine\ORM\Query;

use Symfony\Component\HttpFoundation\Request;

class UserService
{
	// parameters name
	const P_SESSION_KEY = "session-key"; // header
	
	private $em;
	private $ip;
	private $user = null;

	// dependencies
	private $admin_service = null;
	private $teacher_service = null;
	private $parent_service = null;
	private $student_service = null;
	private $guest_service = null;

	public function __construct(EntityManagerInterface $em)
	{
		$request = Request::createFromGlobals();
		$headers = $request->headers;

		$this->ip = $request->getClientIp();
		$this->em = $em;
		
		/* --------- BEGIN is auth -------- */

		if ($headers->has(self::P_SESSION_KEY))
		{
			$rep = $this->em->getRepository(User::class);

			$session_key = $headers->get(self::P_SESSION_KEY);
			$current_time = time();
			$expired_time = $current_time - Session::C_SESSION_LIFESPAN;

			$request = $rep->createQueryBuilder("u");
			$request->innerJoin("u.session", "s");
			$request->addSelect("s");
			$request->where("s.sessionKey = :session_key");
			$request->andWhere("s.time > :expired_time");
			$request->setParameter("session_key", $session_key);
			$request->setParameter("expired_time", $expired_time);
			$query = $request->getQuery();
			//$query->setHint(Query::HINT_FORCE_PARTIAL_LOAD, true);

			$this->user = $query->getOneOrNullResult();
		}
		
		if ($this->isConnected())
		{
			// update session time
			$session = $this->user->getSession()->toArray()[0];
			$session->setTime($current_time);
			
			$this->em->persist($session);
			$this->em->flush();
		}

		/* --------- END is auth -------- */

		/* --------- BEGIN dependencies -------- */

		if ($this->isConnected())
		{
			if ($this->user->isAdmin())
			{
				$this->admin_service = new AdminService($this->em, $this);
			}
			else if($this->user->isTeacher())
			{
				$this->teacher_service = new TeacherService($this->em, $this);
			}
			else if ($this->user->isParent())
			{
				$this->parent_service = new ParentService($this->em, $this);
			}
			else
			{
				$this->student_service = new StudentService($this->em, $this);
			}
		}
		else
		{
			$this->guest_service = new GuestService($this->em, $this);
		}
		
		/* --------- END dependencies -------- */
	}

	public function getIP()
	{
		return $this->ip;
	}

	public function isConnected()
	{
		return ($this->user !== null);
	}

	public function getUser()
	{
		return $this->user;
	}
	
	public function findUser($login)
	{
		$rep = $this->em->getRepository(User::class);
		
		return $rep->findOneByLogin($login);
	}

	/* --------- BEGIN dependencies -------- */

	private function getServiceDependency(AbstractUserService $service = null)
	{
		if ($service === null)
		{
			throw new MoocException(MoocException::FORBIDDEN_ACTION);
		}

		return $service;
	}

	public function getAdminService()
	{
		return $this->getServiceDependency($this->admin_service);
	}

	public function getTeacherService()
	{
		return $this->getServiceDependency($this->teacher_service);
	}

	public function getParentService()
	{
		return $this->getServiceDependency($this->parent_service);
	}

	public function getStudentService()
	{
		return $this->getServiceDependency($this->student_service);
	}

	public function getGuestService()
	{
		return $this->getServiceDependency($this->guest_service);
	}

	/* --------- END dependencies -------- */
}
