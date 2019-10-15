<?php
namespace App\Service;

use App\Serializer\DoctrineSubscriberCollection;
use App\Serializer\DoctrineSubscriberProxy;
use App\Serializer\DoctrineHandlerSerializer;

use JMS\Serializer\EventDispatcher\EventDispatcher;
use JMS\Serializer\EventDispatcher\Subscriber\DoctrineProxySubscriber;
use JMS\Serializer\Handler\HandlerRegistry;
use JMS\Serializer\SerializerBuilder;

class SerializerService
{
	const FORMAT = "json";
	
	private $serializer;
	
	public function __construct()
	{
		$builder = SerializerBuilder::create();		
		
		$builder
		    ->configureListeners(function(EventDispatcher $dispatcher) {
		        $dispatcher->addSubscriber(new DoctrineSubscriberProxy());
				$dispatcher->addSubscriber(new DoctrineProxySubscriber());
				$dispatcher->addSubscriber(new DoctrineSubscriberCollection());
		    });
		
		$builder
			->configureHandlers(function(HandlerRegistry $registry) {
				$registry->registerSubscribingHandler(new DoctrineHandlerSerializer());
			});
		
		$builder->addDefaultHandlers();
		
		$this->serializer = $builder->build();
	}
	
	public function serialize($data)
	{
		$json = $this->serializer->serialize($data, self::FORMAT);
		
		return $json;
	}
}
