<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/** @ORM\Entity */
class UserStudent extends User
{
    const C_CHILD_KEY_LENGHT = 8;

    /**
     * @var string
     *
     * @ORM\Column(name="student_key", type="string", length=32, nullable=false)
     */
    private $studentKey;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Deposit", mappedBy="student")
     */
    private $completedExercise;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Note", mappedBy="student")
     */
    private $notes;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="StudentSupport", mappedBy="student")
     */
	private $assignedTeacher;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="UserParent", mappedBy="childs")
     */
    private $parents;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="SchoolSubjectYear", inversedBy="assignedStudents")
     * @ORM\JoinTable(name="student_assignment",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_student", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_subject_year", referencedColumnName="id")
     *   }
     * )
     */
    private $assignedCourses;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="History", mappedBy="student")
     */
    private $history;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->completedExercise = new ArrayCollection();
		$this->notes = new ArrayCollection();
		$this->assignedTeacher = new ArrayCollection();
		$this->parents = new ArrayCollection();
		$this->assignedCourses = new ArrayCollection();
		$this->history = new ArrayCollection();

    }

    public function getStudentKey(): ?string
    {
        return $this->studentKey;
    }

    public function setStudentKey(string $studentKey): self
    {
        $this->studentKey = $studentKey;

        return $this;
    }

    /**
     * @return Collection|Deposit[]
     */
    public function getCompletedExercise(): Collection
    {
        return $this->completedExercise;
    }

    public function addCompletedExercise(Deposit $completedExercise): self
    {
        if (!$this->completedExercise->contains($completedExercise)) {
            $this->completedExercise[] = $completedExercise;
            $completedExercise->setStudent($this);
        }

        return $this;
    }

    public function removeCompletedExercise(Deposit $completedExercise): self
    {
        if ($this->completedExercise->contains($completedExercise)) {
            $this->completedExercise->removeElement($completedExercise);
            // set the owning side to null (unless already changed)
            if ($completedExercise->getStudent() === $this) {
                $completedExercise->setStudent(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Note[]
     */
    public function getNotes(): Collection
    {
        return $this->notes;
    }

    public function addNote(Note $note): self
    {
        if (!$this->notes->contains($note)) {
            $this->notes[] = $note;
            $note->setStudent($this);
        }

        return $this;
    }

    public function removeNote(Note $note): self
    {
        if ($this->notes->contains($note)) {
            $this->notes->removeElement($note);
            // set the owning side to null (unless already changed)
            if ($note->getStudent() === $this) {
                $note->setStudent(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|StudentSupport[]
     */
    public function getAssignedTeacher(): Collection
    {
        return $this->assignedTeacher;
    }

    public function addAssignedTeacher(StudentSupport $assignedTeacher): self
    {
        if (!$this->assignedTeacher->contains($assignedTeacher)) {
            $this->assignedTeacher[] = $assignedTeacher;
            $assignedTeacher->setStudent($this);
        }

        return $this;
    }

    public function removeAssignedTeacher(StudentSupport $assignedTeacher): self
    {
        if ($this->assignedTeacher->contains($assignedTeacher)) {
            $this->assignedTeacher->removeElement($assignedTeacher);
            // set the owning side to null (unless already changed)
            if ($assignedTeacher->getStudent() === $this) {
                $assignedTeacher->setStudent(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|UserParent[]
     */
    public function getParents(): Collection
    {
        return $this->parents;
    }

    public function addParent(UserParent $parent): self
    {
        if (!$this->parents->contains($parent)) {
            $this->parents[] = $parent;
            $parent->addChild($this);
        }

        return $this;
    }

    public function removeParent(UserParent $parent): self
    {
        if ($this->parents->contains($parent)) {
            $this->parents->removeElement($parent);
            $parent->removeChild($this);
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

    /**
     * @return Collection|History[]
     */
    public function getHistory(): Collection
    {
        return $this->history;
    }

    public function addHistory(History $history): self
    {
        if (!$this->history->contains($history)) {
            $this->history[] = $history;
            $history->setStudent($this);
        }

        return $this;
    }

    public function removeHistory(History $history): self
    {
        if ($this->history->contains($history)) {
            $this->history->removeElement($history);
            // set the owning side to null (unless already changed)
            if ($history->getStudent() === $this) {
                $history->setStudent(null);
            }
        }

        return $this;
    }

}
