<?php
namespace App\Entity;

use App\Exception\MoocException;
use Exception;

class MessageResponse
{
	const SUCCESS = "L_SUCCESS";

	private $error = false;
	private $message = self::SUCCESS;
	private $data = null;

	public function isError()
	{
		return $this->error;
	}

	public function getMessage()
	{
		return $this->message;
	}

	public function getData()
	{
		return $this->data;
	}

	public function error(Exception $e)
	{
		$this->error = true;
		$this->message = ($e instanceof MoocException) ? $e->getMessage() : MoocException::UNEXPECTED_ERROR;
	}

	public function setData($data)
	{
		$this->data = $data;
	}

	public function setMessage($message)
	{
		return $this->message;
	}
}
