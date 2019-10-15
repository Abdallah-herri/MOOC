<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Deposit
 *
 * @ORM\Table(name="deposit", indexes={@ORM\Index(name="IDX_DEPOSIT_STUDENT", columns={"id_student"}), @ORM\Index(name="IDX_DEPOSIT_COURSE", columns={"id_course"})})
 * @ORM\Entity
 */
class Deposit
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
     * @var \CourseExercise
     *
     * @ORM\ManyToOne(targetEntity="CourseExercise", inversedBy="completedExercises")
     * @ORM\JoinColumn(name="id_course", referencedColumnName="id")
     */
    private $course;

    /**
     * @var \UserStudent
     *
     * @ORM\ManyToOne(targetEntity="UserStudent", inversedBy="completedExercise")
     * @ORM\JoinColumn(name="id_student", referencedColumnName="id")
     */
    private $student;

    /**
     * \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="FileDeposit", mappedBy="deposit")
     */
	private $files;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->files = new ArrayCollection();

    }

    public function getId(): ?int
    {
        return $this->id;
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

    /**
     * @return Collection|FileDeposit[]
     */
    public function getFiles(): Collection
    {
        return $this->files;
    }

    public function addFile(FileDeposit $file): self
    {
        if (!$this->files->contains($file)) {
            $this->files[] = $file;
            $file->setDeposit($this);
        }

        return $this;
    }

    public function removeFile(FileDeposit $file): self
    {
        if ($this->files->contains($file)) {
            $this->files->removeElement($file);
            // set the owning side to null (unless already changed)
            if ($file->getDeposit() === $this) {
                $file->setDeposit(null);
            }
        }

        return $this;
    }
}
