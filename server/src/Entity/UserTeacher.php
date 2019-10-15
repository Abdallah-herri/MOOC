<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/** @ORM\Entity */
class UserTeacher extends User
{
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Course", mappedBy="teacher")
     */
	private $courses;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="StudentSupport", mappedBy="teacher")
     */
	private $supportedStudents;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="SchoolSubjectYear", inversedBy="assignedTeachers")
     * @ORM\JoinTable(name="teacher_assignment",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_teacher", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_subject_year", referencedColumnName="id")
     *   }
     * )
     */
    private $assignedCourses;

    /* ArrayCollection */
    public function __construct()
    {
		$this->courses = new ArrayCollection();
        $this->supportedStudents = new ArrayCollection();
        $this->assignedCourses = new ArrayCollection();
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
            $course->setTeacher($this);
        }

        return $this;
    }

    public function removeCourse(Course $course): self
    {
        if ($this->courses->contains($course)) {
            $this->courses->removeElement($course);
            // set the owning side to null (unless already changed)
            if ($course->getTeacher() === $this) {
                $course->setTeacher(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|StudentSupport[]
     */
    public function getSupportedStudents(): Collection
    {
        return $this->supportedStudents;
    }

    public function addSupportedStudent(StudentSupport $supportedStudent): self
    {
        if (!$this->supportedStudents->contains($supportedStudent)) {
            $this->supportedStudents[] = $supportedStudent;
            $supportedStudent->setSupervisingTeacher($this);
        }

        return $this;
    }

    public function removeSupportedStudent(StudentSupport $supportedStudent): self
    {
        if ($this->supportedStudents->contains($supportedStudent)) {
            $this->supportedStudents->removeElement($supportedStudent);
            // set the owning side to null (unless already changed)
            if ($supportedStudent->getSupervisingTeacher() === $this) {
                $supportedStudent->setSupervisingTeacher(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|SchoolSubjectYear[]
     */
    public function getAssignedCourses(): Collection
    {
        return $this->assignedCourses;
    }

    public function addAssignedCourse(SchoolSubjectYear $assignedCourse): self
    {
        if (!$this->assignedCourses->contains($assignedCourse)) {
            $this->assignedCourses[] = $assignedCourse;
        }

        return $this;
    }

    public function removeAssignedCourse(SchoolSubjectYear $assignedCourse): self
    {
        if ($this->assignedCourses->contains($assignedCourse)) {
            $this->assignedCourses->removeElement($assignedCourse);
        }

        return $this;
    }
}
