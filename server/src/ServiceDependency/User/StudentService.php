<?php
namespace App\ServiceDependency\User;

use App\ServiceDependency\User\Student\CourseService;
use App\ServiceDependency\User\Student\NoteService;
use App\ServiceDependency\User\Student\FileCourseService;
use App\ServiceDependency\User\Student\HistoryService;

class StudentService extends AbstractUserService
{
	protected function init()
	{

	}

	/* --------- BEGIN dependencies -------- */

	public function getCourseService()
	{
		$course_service = new CourseService($this->em, $this->user_service, $this);
		
		return $course_service;
	}

	public function getNoteService()
	{
		$note_service = new NoteService($this->em, $this->user_service, $this);
		
		return $note_service;
	}

	public function getFileService()
	{
		$file_service = new FileCourseService($this->em, $this->user_service, $this);
		
		return $file_service;
	}

	public function getHistoryService()
	{
		$history_service = new HistoryService($this->em, $this->user_service, $this);
		
		return $history_service;
	}
	/* --------- END dependencies -------- */
}
