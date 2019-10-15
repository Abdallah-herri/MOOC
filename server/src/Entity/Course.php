<?php
namespace App\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Course
 *
 * @ORM\Table(name="course", indexes={@ORM\Index(name="IDX_COURSE_SUBJECT_YEAR", columns={"id_subject_year"}), @ORM\Index(name="IDX_COURSE_TYPE", columns={"id_course_type"}), @ORM\Index(name="IDX_COURSE_TEACHER", columns={"id_teacher"})})
 * @ORM\Entity
 * @ORM\InheritanceType("SINGLE_TABLE")
 * @ORM\DiscriminatorColumn(name="id_course_type", type="bigint")
 * @ORM\DiscriminatorMap({1 = "CourseLesson", 2 = "CourseExercise", 3 = "CourseStream"})
 */

abstract class Course
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
     * @ORM\Column(name="title", type="string", length=50, nullable=false)
     */
    private $title;
	
    /**
     * @var int
     *
     * @ORM\Column(name="time", type="bigint", nullable=false)
     */
	private $time;

    /**
     * @var \UserTeacher
     *
     * @ORM\ManyToOne(targetEntity="UserTeacher", inversedBy="courses")
     * @ORM\JoinColumn(name="id_teacher", referencedColumnName="id")
     */
    private $teacher;

    /**
     * @var \SchoolSubjectYear
     *
     * @ORM\ManyToOne(targetEntity="SchoolSubjectYear", inversedBy="courses")
     * @ORM\JoinColumn(name="id_subject_year", referencedColumnName="id")
     */
    private $subjectYear;
	
    /**
     * @var \CourseType
     *
     * @ORM\ManyToOne(targetEntity="CourseType", inversedBy="courses", fetch="EAGER")
     * @ORM\JoinColumn(name="id_course_type", referencedColumnName="id")
     */
    private $type;
	
    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="FileCourse", mappedBy="course")
     */
	private $files;

    /**
     * @var \Planning
     *
     * @ORM\OneToOne(targetEntity="Planning", mappedBy="course")
     */
    private $planning;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\OneToMany(targetEntity="History", mappedBy="course")
     */
    private $history;

    /**
     * Constructor
     */
    public function __construct()
    {
		$this->files = new ArrayCollection();
  $this->history = new ArrayCollection();

    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): self
    {
        $this->title = $title;

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

    public function getTeacher(): ?UserTeacher
    {
        return $this->teacher;
    }

    public function setTeacher(?UserTeacher $teacher): self
    {
        $this->teacher = $teacher;

        return $this;
    }

    public function getSubjectYear(): ?SchoolSubjectYear
    {
        return $this->subjectYear;
    }

    public function setSubjectYear(?SchoolSubjectYear $subjectYear): self
    {
        $this->subjectYear = $subjectYear;

        return $this;
    }

    public function getType(): ?CourseType
    {
        return $this->type;
    }

    public function setType(?CourseType $type): self
    {
        $this->type = $type;

        return $this;
    }

    /**
     * @return Collection|FileCourse[]
     */
    public function getFiles(): Collection
    {
        return $this->files;
    }

    public function addFile(FileCourse $file): self
    {
        if (!$this->files->contains($file)) {
            $this->files[] = $file;
            $file->setCourse($this);
        }

        return $this;
    }

    public function removeFile(FileCourse $file): self
    {
        if ($this->files->contains($file)) {
            $this->files->removeElement($file);
            // set the owning side to null (unless already changed)
            if ($file->getCourse() === $this) {
                $file->setCourse(null);
            }
        }

        return $this;
    }

    public function getPlanning(): ?Planning
    {
        return $this->planning;
    }

    public function setPlanning(?Planning $planning): self
    {
        $this->planning = $planning;

        // set (or unset) the owning side of the relation if necessary
        $newCourse = $planning === null ? null : $this;
        if ($newCourse !== $planning->getCourse()) {
            $planning->setCourse($newCourse);
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
            $history->setCourse($this);
        }

        return $this;
    }

    public function removeHistory(History $history): self
    {
        if ($this->history->contains($history)) {
            $this->history->removeElement($history);
            // set the owning side to null (unless already changed)
            if ($history->getCourse() === $this) {
                $history->setCourse(null);
            }
        }

        return $this;
    }
}
