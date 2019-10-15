<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Planning
 *
 * @ORM\Table(name="planning", indexes={@ORM\Index(name="IDX_PLANNING_COURSE", columns={"id_course"})})
 * @ORM\Entity
 */
class Planning
{
    /**
     * @var int
     *
     * @ORM\Column(name="begin", type="bigint", nullable=false)
     */
    private $begin;

    /**
     * @var int
     *
     * @ORM\Column(name="end", type="bigint", nullable=false)
     */
    private $end;

    /**
     * @var \Course
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Course", inversedBy="planning")
     * @ORM\JoinColumn(name="id_course", referencedColumnName="id")
     */
    private $course;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="User", mappedBy="planning")
     */
    private $userAssigned;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->userAssigned = new ArrayCollection();

    }

    public function getBegin(): ?int
    {
        return $this->begin;
    }

    public function setBegin(int $begin): self
    {
        $this->begin = $begin;

        return $this;
    }

    public function getEnd(): ?int
    {
        return $this->end;
    }

    public function setEnd(int $end): self
    {
        $this->end = $end;

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

    /**
     * @return Collection|User[]
     */
    public function getUserAssigned(): Collection
    {
        return $this->userAssigned;
    }

    public function addUserAssigned(User $userAssigned): self
    {
        if (!$this->userAssigned->contains($userAssigned)) {
            $this->userAssigned[] = $userAssigned;
            $userAssigned->addPlanning($this);
        }

        return $this;
    }

    public function removeUserAssigned(User $userAssigned): self
    {
        if ($this->userAssigned->contains($userAssigned)) {
            $this->userAssigned->removeElement($userAssigned);
            $userAssigned->removePlanning($this);
        }

        return $this;
    }
}
