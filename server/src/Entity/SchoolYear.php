<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * SchoolYear
 *
 * @ORM\Table(name="school_year", uniqueConstraints={@ORM\UniqueConstraint(name="name", columns={"name"})})
 * @ORM\Entity
 */
class SchoolYear
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
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=50, nullable=false)
     */
    private $name;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="SchoolSubjectYear", mappedBy="schoolYear")
     */
    private $assignations;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->assignations = new ArrayCollection();

    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    /**
     * @return Collection|SchoolSubjectYear[]
     */
    public function getAssignations(): Collection
    {
        return $this->assignations;
    }

    public function addAssignation(SchoolSubjectYear $assignation): self
    {
        if (!$this->assignations->contains($assignation)) {
            $this->assignations[] = $assignation;
            $assignation->setSchoolYear($this);
        }

        return $this;
    }

    public function removeAssignation(SchoolSubjectYear $assignation): self
    {
        if ($this->assignations->contains($assignation)) {
            $this->assignations->removeElement($assignation);
            // set the owning side to null (unless already changed)
            if ($assignation->getSchoolYear() === $this) {
                $assignation->setSchoolYear(null);
            }
        }

        return $this;
    }
}
