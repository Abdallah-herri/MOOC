<?php
declare(strict_types=1);
namespace App\Serializer;

use Doctrine\Common\Persistence\Proxy;
use Doctrine\Common\Collections\AbstractLazyCollection;

use JMS\Serializer\EventDispatcher\EventSubscriberInterface;
use JMS\Serializer\EventDispatcher\PreSerializeEvent;

class DoctrineSubscriberCollection implements EventSubscriberInterface
{
    public function onPreSerialize(PreSerializeEvent $event)
    {
        $object = $event->getObject();
        
		if ($object instanceof AbstractLazyCollection && !$object->isInitialized())
		{
			$event->setType(DoctrineTypeLazyCollection::class);
		}
    }
	
    public static function getSubscribedEvents()
    {
        return array(
            array('event' => 'serializer.pre_serialize', 'method' => 'onPreSerialize'),
        );
    }
}
