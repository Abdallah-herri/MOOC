<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Note
 *
 * @ORM\Table(name="note", indexes={@ORM\Index(name="IDX_NOTE_STUDENT", columns={"id_student"}), @ORM\Index(name="IDX_NOTE_COURSE", columns={"id_course"})})
 * @ORM\Entity
 */
class Note
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="bigint", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var float
     *
     * @ORM\Column(name="note", type="float", precision=10, scale=0, nullable=false)
     */
    private $note;

    /**
     * @var \CourseExercise
     *
     * @ORM\ManyToOne(targetEntity="CourseExercise", inversedBy="notes")
     * @ORM\JoinColumn(name="id_course", referencedColumnName="id")
     */
    private $course;

    /**
     * @var \UserStudent
     *
     * @ORM\ManyToOne(targetEntity="UserStudent", inversedBy="notes")
     * @ORM\JoinColumn(name="id_student", referencedColumnName="id")
     */
    private $student;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNote(): ?float
    {
        return $this->note;
    }

    public function setNote(float $note): self
    {
        $this->note = $note;

        return $this;
    }

    public function getCourse(): ?CourseExercise
    {
        return $this->course;
    }

    public function setCourse(?CourseExercise $course): self
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
