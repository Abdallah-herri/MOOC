<?php
namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/** @ORM\Entity */
class CourseStream extends Course
{
    /**
     * @var \Course
     *
     * @ORM\OneToOne(targetEntity="Stream", mappedBy="course")
     */
    private $stream;

    public function getStream(): ?Stream
    {
        return $this->stream;
    }

    public function setStream(?Stream $stream): self
    {
        $this->stream = $stream;

        // set (or unset) the owning side of the relation if necessary
        $newCourse = $stream === null ? null : $this;
        if ($newCourse !== $stream->getCourse()) {
            $stream->setCourse($newCourse);
        }

        return $this;
    }
	
}
