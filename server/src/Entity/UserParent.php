<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/** @ORM\Entity */
class UserParent extends User
{
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="UserStudent", inversedBy="parents")
     * @ORM\JoinTable(name="parent_assignment",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_parent", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_student", referencedColumnName="id")
     *   }
     * )
     */
    private $childs;
	
    /**
     * Constructor
     */
    public function __construct()
    {
        parent::__construct();

		$this->childs = new ArrayCollection();
    }

    /**
     * @return Collection|UserStudent[]
     */
    public function getChilds(): Collection
    {
        return $this->childs;
    }

    public function addChild(UserStudent $child): self
    {
        if (!$this->childs->contains($child)) {
            $this->childs[] = $child;
        }

        return $this;
    }

    public function removeChild(UserStudent $child): self
    {
        if ($this->childs->contains($child)) {
            $this->childs->removeElement($child);
        }

        return $this;
    }
}
