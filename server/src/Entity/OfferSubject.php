<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SubjectOffer
 *
 * @ORM\Table(name="offer_subject")
 * @ORM\Entity
 */
class SubjectOffer
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
     * @ORM\Column(name="price", type="float", precision=10, scale=0, nullable=false)
     */
    private $price;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_school_subject", type="integer", nullable=false)
     */
    private $nbSchoolSubject;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrice(): ?float
    {
        return $this->price;
    }

    public function setPrice(float $price): self
    {
        $this->price = $price;

        return $this;
    }

    public function getNbSchoolSubject()
    {
        return $this->nbSchoolSubject;
    }

    public function setNbSchoolSubject($nbSchoolSubject): self
    {
        $this->nbSchoolSubject = $nbSchoolSubject;

        return $this;
    }

}
