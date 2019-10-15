<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * FileDeposit
 *
 * @ORM\Table(name="view_file_deposit", indexes={@ORM\Index(name="IDX_FILE_DEPOSIT_DEPOSIT", columns={"id_deposit"})})
 * @ORM\Entity
 */
class FileDeposit extends File
{
    /**
     * @var \Deposit
     *
     * @ORM\ManyToOne(targetEntity="Deposit", inversedBy="files")
     * @ORM\JoinColumn(name="id_deposit", referencedColumnName="id")
     */
    private $deposit;

    public function getDeposit(): ?Deposit
    {
        return $this->deposit;
    }

    public function setDeposit(?Deposit $deposit): self
    {
        $this->deposit = $deposit;

        return $this;
    }

}
