<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * StudentSupport
 *
 * @ORM\Table(name="student_support", indexes={@ORM\Index(name="IDX_STUDENT_SUPPORT_TEACHER", columns={"id_teacher"}), @ORM\Index(name="IDX_STUDENT_SUPPORT_STUDENT", columns={"id_student"})})
 * @ORM\Entity
 */
class StudentSupport
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
     * @var int
     *
     * @ORM\Column(name="year", type="bigint", nullable=false)
     */
    private $year;

    /**
     * @var \UserTeacher
     *
     * @ORM\ManyToOne(targetEntity="UserTeacher", inversedBy="supportedStudents")
     * @ORM\JoinColumn(name="id_teacher", referencedColumnName="id")
     */
    private $teacher;

    /**
     * @var \UserStudent
     *
     * @ORM\ManyToOne(targetEntity="UserStudent", inversedBy="assignedTeacher")
     * @ORM\JoinColumn(name="id_student", referencedColumnName="id")
     */
    private $student;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getYear(): ?int
    {
        return $this->year;
    }

    public function setYear(int $year): self
    {
        $this->year = $year;

        return $this;
    }

    public function getTeacher(): ?UserTeacher
    {
        return $this->teacher;
    }

    public function setTeacher(?UserTeacher $teacher): self
    {
        $this->teacher = $teacher;

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
