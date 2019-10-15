<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * History
 *
 * @ORM\Table(name="history", indexes={@ORM\Index(name="IDX_HISTORY_STUDENT", columns={"id_student"}), @ORM\Index(name="IDX_HISTORY_COURSE", columns={"id_course"})})
 * @ORM\Entity
 */
class History
{
	const READ_COURSE = "L_READ_COURSE";
	
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     *@ORM\Column(name="message", type="string", length=50, nullable=false)
     */
    private $message;
	
    /**
     * @var int
     *
     * @ORM\Column(name="time", type="bigint", nullable=false)
     */
    private $time;

    /**
     * @var \UserStudent
     *
     * @ORM\ManyToOne(targetEntity="UserStudent", inversedBy="history")
     * @ORM\JoinColumn(name="id_student", referencedColumnName="id")
     */
    private $student;

    /**
     * @var \Course
     *
     * @ORM\ManyToOne(targetEntity="Course", inversedBy="history")
     * @ORM\JoinColumn(name="id_course", referencedColumnName="id")
     */
    private $course;
	

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMessage(): ?string
    {
        return $this->message;
    }
	
	public function setMessage(string $message): self
    {
        $this->message = $message;

        return $this;
    }

    public function getTime(): ?int
    {
        return $this->time;
    }

    public function setTime(int $time): self
    {
        $this->time = $time;

        return $this;
    }
	
    public function getCourse(): ?Course
    {
        return $this->course;
    }

    public function setCourse(?Course $course): self
    {
        $this->course = $course;

        return $this;
    }

    public function getStudent(): ?UserStudent
    {
        return $this->student;
    }

    public function setStudent(?UserStudent $student): self
    {
        $this->student = $student;

        return $this;
    }

}
