<?php
namespace App\ServiceDependency\User;

use App\ServiceDependency\User\Parent\StudentAssignmentService;
use App\ServiceDependency\User\Parent\ChildService;
use App\ServiceDependency\User\Parent\ChildHistoryService;
use App\ServiceDependency\User\Parent\ChildActivityService;

class ParentService extends AbstractUserService
{
	private $childs = array();
	private $not_childs = array();

	protected function init()
	{
	}

	/* --------- BEGIN dependencies -------- */

	public function getStudentAssignmentService()
	{
		$student_assignment_service = new StudentAssignmentService($this->em, $this->user_service, $this);
		
		return $student_assignment_service;
	}
	
	public function getChildService()
	{
		$child_service = new ChildService($this->em, $this->user_service, $this);
		
		return $child_service;
	}
	
	public function getChildHistoryService()
	{
		$child_history_service = new ChildHistoryService($this->em, $this->user_service, $this);
		
		return $child_history_service;
	}
	
	public function getChildActivityService()
	{
		$child_activity_service = new ChildActivityService($this->em, $this->user_service, $this);
		
		return $child_activity_service;
	}

	/* --------- END dependencies -------- */
	
	public function isChild($id, &$child = null, $is_lazy_loading = false)
	{
		$i = 0;
		$count = count($this->childs);
		$found_child = false;
		
		while ($i < $count && !$found_child)
		{
			$e = $this->childs[$i];
			
			if ($e->getId() == $id)
			{
				$found_child = true;
			}
			
			++$i;
		}
		
		if ($found_child)
		{
			return true;
		}
		
		$i = 0;
		$count = count($this->not_childs);
		$found_not_child = false;
		
		while ($i < $count && !$found_not_child)
		{
			$e = $this->not_childs[$i];
			
			if ($e == $id)
			{
				$found_not_child = true;
			}
			
			++$i;
		}
		
		if ($found_not_child)
		{
			return false;
		}
		
		$child_result = $this
			->getChildService()
			->findChildById($id)
			->getResult($is_lazy_loading);
			
		if (empty($child_result))
		{
			$this->not_childs[] = $id;
			
			return false;
		}
		else
		{
			$child = $child_result[0];
			$this->childs[] = $child;
			
			return true;
		}
	}
}
