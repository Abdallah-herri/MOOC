<?php
namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/** @ORM\Entity */
class CourseExercise extends Course
{
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Deposit", mappedBy="course")
     */
    private $completedExercises;

       /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Note", mappedBy="course")
     */
    private $notes;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->completedExercises = new ArrayCollection();
		$this->notes = new ArrayCollection();

    }

    /**
     * @return Collection|Deposit[]
     */
    public function getCompletedExercises(): Collection
    {
        return $this->completedExercises;
    }

    public function addCompletedExercise(Deposit $completedExercise): self
    {
        if (!$this->completedExercises->contains($completedExercise)) {
            $this->completedExercises[] = $completedExercise;
            $completedExercise->setCourse($this);
        }

        return $this;
    }

    public function removeCompletedExercise(Deposit $completedExercise): self
    {
        if ($this->completedExercises->contains($completedExercise)) {
            $this->completedExercises->removeElement($completedExercise);
            // set the owning side to null (unless already changed)
            if ($completedExercise->getCourse() === $this) {
                $completedExercise->setCourse(null);
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
            $note->setCourse($this);
        }

        return $this;
    }

    public function removeNote(Note $note): self
    {
        if ($this->notes->contains($note)) {
            $this->notes->removeElement($note);
            // set the owning side to null (unless already changed)
            if ($note->getCourse() === $this) {
                $note->setCourse(null);
            }
        }

        return $this;
    }
}
