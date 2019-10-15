<?php
namespace App\Exception;

use Exception;

class MoocException extends Exception
{
	const ALREADY_CONNECTED = "L_ALREADY_CONNECTED";
	
	const EMPTY_FIELD = "L_EMPTY_FIELD";

	const FILE_NOT_FOUND = "L_FILE_NOT_FOUND";

	const FORBIDDEN_ACTION = "L_FORBIDDEN_ACTION";
	
	const INVALID_EMAIL = "L_INVALID_EMAIL";
	const INVALID_FORM = "L_INVALID_FORM";
	const INVALID_LOGIN = "L_INVALID_LOGIN";
	const INVALID_PASSWORD = "L_INVALID_PASSWORD";
	const INVALID_USER_TYPE = "L_INVALID_USER_TYPE";
	
	const SUBJECT_YEAR_NOT_EXIST = "L_SUBJECT_YEAR_NOT_EXIST";
	
	const USER_ALREADY_EXIST = "L_USER_ALREADY_EXIST";
	const USER_NOT_EXIST = "L_USER_NOT_EXIST";

	const UNEXPECTED_ERROR = "L_UNEXPECTED_ERROR";
	
	public function __construct($message)
	{
		parent::__construct($message);
	}
}
