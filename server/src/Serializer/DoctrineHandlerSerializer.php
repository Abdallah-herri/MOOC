<?php
namespace App\Serializer;

use JMS\Serializer\Handler\SubscribingHandlerInterface;
use JMS\Serializer\GraphNavigator;
use JMS\Serializer\JsonSerializationVisitor;
use JMS\Serializer\Context;

use stdClass;

class DoctrineHandlerSerializer implements SubscribingHandlerInterface
{
	public static function getSubscribingMethods()
	{
	    return array(
	        array(
	            'direction' => GraphNavigator::DIRECTION_SERIALIZATION,
	            'format' => 'json',
	            'type' => DoctrineTypeLazyCollection::class,
	            'method' => 'serializeLazyCollection',
	        ),
	        array(
	            'direction' => GraphNavigator::DIRECTION_SERIALIZATION,
	            'format' => 'json',
	            'type' => SerializerTypeProxy::class,
	            'method' => 'serializeLazyObject',
	        ),
	    );
	}

	public function serializeLazyCollection(JsonSerializationVisitor $visitor, $lazy_collection, array $type, Context $context)
	{
		$r = array();
		
		return $r;
	}

	public function serializeLazyObject(JsonSerializationVisitor $visitor, $proxy, array $type, Context $context)
	{
		$r = new stdClass();
		
		return $r;
	}
}
