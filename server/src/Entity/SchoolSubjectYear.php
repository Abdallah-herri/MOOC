<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * SchoolSubjectYear
 *
 * @ORM\Table(name="school_subject_year", indexes={@ORM\Index(name="IDX_SUBJECT_YEAR_SCHOOL_YEAR", columns={"id_school_year"}), @ORM\Index(name="IDX_SUBJECT_YEAR_SCHOOL_SUBJECT", columns={"id_school_subject"})})
 * @ORM\Entity
 */
class SchoolSubjectYear
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
     * @var \SchoolSubject
     *
     * @ORM\ManyToOne(targetEntity="SchoolSubject", inversedBy="assignations")
     * @ORM\JoinColumn(name="id_school_subject", referencedColumnName="id")
     */
    private $schoolSubject;

    /**
     * @var \SchoolYear
     *
     * @ORM\ManyToOne(targetEntity="SchoolYear", inversedBy="assignations")
     * @ORM\JoinColumn(name="id_school_year", referencedColumnName="id")
     */
    private $schoolYear;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Course", mappedBy="subjectYear")
     */
    private $courses;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="UserStudent", mappedBy="assignedCourses")
     */
    private $assignedStudents;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="UserTeacher", mappedBy="assignedCourses")
     */
    private $assignedTeachers;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->courses = new ArrayCollection();
		$this->assignedStudents = new ArrayCollection();
		$this->assignedTeachers = new ArrayCollection();

    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getSchoolSubject(): ?SchoolSubject
    {
        return $this->schoolSubject;
    }

    public function setSchoolSubject(?SchoolSubject $schoolSubject): self
    {
        $this->schoolSubject = $schoolSubject;

        return $this;
    }

    public function getSchoolYear(): ?SchoolYear
    {
        return $this->schoolYear;
    }

    public function setSchoolYear(?SchoolYear $schoolYear): self
    {
        $this->schoolYear = $schoolYear;

        return $this;
    }

    /**
     * @return Collection|Course[]
     */
    public function getCourses(): Collection
    {
        return $this->courses;
    }

    public function addCourse(Course $course): self
    {
        if (!$this->courses->contains($course)) {
            $this->courses[] = $course;
            $course->setSubjectYear($this);
        }

        return $this;
    }

    public function removeCourse(Course $course): self
    {
        if ($this->courses->contains($course)) {
            $this->courses->removeElement($course);
            // set the owning side to null (unless already changed)
            if ($course->getSubjectYear() === $this) {
                $course->setSubjectYear(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|UserStudent[]
     */
    public function getAssignedStudents(): Collection
    {
        return $this->assignedStudents;
    }

    public function addAssignedStudent(UserStudent $assignedStudent): self
    {
        if (!$this->assignedStudents->contains($assignedStudent)) {
            $this->assignedStudents[] = $assignedStudent;
            $assignedStudent->addAssignedCourse($this);
        }

        return $this;
    }

    public function removeAssignedStudent(UserStudent $assignedStudent): self
    {
        if ($this->assignedStudents->contains($assignedStudent)) {
            $this->assignedStudents->removeElement($assignedStudent);
            $assignedStudent->removeAssignedCourse($this);
        }

        return $this;
    }

    /**
     * @return Collection|UserTeacher[]
     */
    public function getAssignedTeachers(): Collection
    {
        return $this->assignedTeachers;
    }

    public function addAssignedTeacher(UserTeacher $assignedTeacher): self
    {
        if (!$this->assignedTeachers->contains($assignedTeacher)) {
            $this->assignedTeachers[] = $assignedTeacher;
            $assignedTeacher->addAssignedCourse($this);
        }

        return $this;
    }

    public function removeAssignedTeacher(UserTeacher $assignedTeacher): self
    {
        if ($this->assignedTeachers->contains($assignedTeacher)) {
            $this->assignedTeachers->removeElement($assignedTeacher);
            $assignedTeacher->removeAssignedCourse($this);
        }

        return $this;
    }
}
