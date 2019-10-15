<?php

declare(strict_types=1);

namespace App\Serializer;

use Doctrine\Common\Persistence\Proxy;
use JMS\Serializer\EventDispatcher\EventSubscriberInterface;
use JMS\Serializer\EventDispatcher\PreSerializeEvent;

class DoctrineSubscriberProxy implements EventSubscriberInterface
{
    public function onPreSerialize(PreSerializeEvent $event)
    {
        $object = $event->getObject();
        
		if ($object instanceof Proxy && !$object->__isInitialized())
		{
			$event->setType(SerializerTypeProxy::class);
		}

    }
	
    public static function getSubscribedEvents()
    {
        return array(
            array('event' => 'serializer.pre_serialize', 'method' => 'onPreSerialize'),
        );
    }
}
