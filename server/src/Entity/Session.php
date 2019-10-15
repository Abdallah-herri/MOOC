<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Session
 *
 * @ORM\Table(name="session", indexes={@ORM\Index(name="IDX_SESSION_USER", columns={"id_user"})})
 * @ORM\Entity
 */
class Session
{
    const C_SESSION_LIFESPAN = 604800; // 1 week
    const C_SESSION_KEY_LENGTH = 16; // random key length
    
    /**
     * @var string
     *
     * @ORM\Column(name="session_key", type="string", length=16, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $sessionKey;

    /**
     * @var int
     *
     * @ORM\Column(name="time", type="bigint", nullable=false)
     */
    private $time;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User", inversedBy="session")
     * @ORM\JoinColumn(name="id_user", referencedColumnName="id")
     */
    private $user;

    public function getSessionKey(): ?string
    {
        return $this->sessionKey;
    }
	
	public function setSessionKey(string $sessionKey): self
	{
        $this->sessionKey = $sessionKey;

        return $this;
	}

    public function getTime(): ?int
    {
        return $this->time;
    }

    public function setTime(int $time): self
    {
        $this->time = $time;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }


}
