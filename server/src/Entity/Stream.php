<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Stream
 *
 * @ORM\Table(name="course_stream")
 * @ORM\Entity
 */
class Stream
{
    /**
     * @var \CourseStream
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="CourseStream", inversedBy="stream")
     * @ORM\JoinColumn(name="id_course", referencedColumnName="id")
     */
    private $course;

    /**
     * @var bool
     *
     * @ORM\Column(name="state", type="boolean", nullable=false)
     */
    private $state;

    public function getState(): ?bool
    {
        return $this->state;
    }

    public function setState(bool $state): self
    {
        $this->state = $state;

        return $this;
    }

    public function getCourse(): ?CourseStream
    {
        return $this->course;
    }

    public function setCourse(?CourseStream $course): self
    {
        $this->course = $course;

        return $this;
    }
}
