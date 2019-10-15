<?php

namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

use JMS\Serializer\Annotation as JMS;

/**
 * User
 *
 * @ORM\Table(name="user", uniqueConstraints={@ORM\UniqueConstraint(name="private_key", columns={"private_key"}), @ORM\UniqueConstraint(name="password", columns={"password"}), @ORM\UniqueConstraint(name="login", columns={"login"})}, indexes={@ORM\Index(name="IDX_USER_LOGIN", columns={"login"}), @ORM\Index(name="IDX_USER_TYPE", columns={"id_user_type"})})
 * @ORM\Entity
 * @ORM\InheritanceType("SINGLE_TABLE")
 * @ORM\DiscriminatorColumn(name="id_user_type", type="bigint")
 * @ORM\DiscriminatorMap({1 = "UserAdmin", 2 = "UserTeacher", 3 = "UserParent", 4 = "UserStudent"})
 */
abstract class User
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
     * @ORM\Column(name="login", type="string", length=255, nullable=false)
     */
    private $login;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=60, nullable=false)
     * @JMS\Exclude()
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="first_name", type="string", length=50, nullable=false)
     */
    private $firstName;

    /**
     * @var string
     *
     * @ORM\Column(name="last_name", type="string", length=50, nullable=false)
     */
    private $lastName;

    /**
     * @var \UserType
     *
     * @ORM\ManyToOne(targetEntity="UserType", inversedBy="users", fetch="EAGER")
     * @ORM\JoinColumn(name="id_user_type", referencedColumnName="id")
     */
    private $type;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="Session", mappedBy="user")
     */
	private $session;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Planning", inversedBy="userAssigned")
     * @ORM\JoinTable(name="planning_assignment",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_user", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_planning", referencedColumnName="id_course")
     *   }
     * )
     */
    private $planning;
	
    /**
     * Constructor
     */
    public function __construct()
    {
		$this->session = new ArrayCollection();
		$this->planning = new ArrayCollection();

    }

    /* Generated methods (by Doctrine) */

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(string $login): self
    {
        $this->login = $login;

        return $this;
    }

    public function checkPassword(string $password): ?bool
    {
        return password_verify($password, $this->password);
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }
	
    public function getFirstName(): ?string
    {
        return $this->firstName;
    }

    public function setFirstName(string $firstName): self
    {
        $this->firstName = $firstName;

        return $this;
    }

    public function getLastName(): ?string
    {
        return $this->lastName;
    }

    public function setLastName(string $lastName): self
    {
        $this->lastName = $lastName;

        return $this;
    }

    public function getType(): ?UserType
    {
        return $this->type;
    }

    public function setType(?UserType $type): self
    {
        $this->type = $type;

        return $this;
    }

    /**
     * @return Collection|Session[]
     */
    public function getSession(): Collection
    {
        return $this->session;
    }

    public function addSession(Session $session): self
    {
        if (!$this->session->contains($session)) {
            $this->session[] = $session;
            $session->setUser($this);
        }

        return $this;
    }

    public function removeSession(Session $session): self
    {
        if ($this->session->contains($session)) {
            $this->session->removeElement($session);
            // set the owning side to null (unless already changed)
            if ($session->getUser() === $this) {
                $session->setUser(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Planning[]
     */
    public function getPlanning(): Collection
    {
        return $this->planning;
    }

    public function addPlanning(Planning $planning): self
    {
        if (!$this->planning->contains($planning)) {
            $this->planning[] = $planning;
        }

        return $this;
    }

    public function removePlanning(Planning $planning): self
    {
        if ($this->planning->contains($planning)) {
            $this->planning->removeElement($planning);
        }

        return $this;
    }

    /* Custom methods */

    public function isAdmin()
    {
        return ($this instanceof UserAdmin);
    }

    public function isTeacher()
    {
        return ($this instanceof UserTeacher);
    }

    public function isParent()
    {
        return ($this instanceof UserParent);
    }

    public function isStudent()
    {
        return ($this instanceof UserStudent);
    }
}
