<?php
namespace App\Serializer;

use Doctrine\Common\Collections\AbstractLazyCollection;

class DoctrineTypeLazyCollection extends AbstractLazyCollection
{
	public function __construct()
	{
		$this->collection = array();
		$this->initialize = true;
	}

	protected function doInitialize()
	{
		// do nothing
		return;
	}
}
