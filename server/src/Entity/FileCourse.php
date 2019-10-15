<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * FileCourse
 *
 * @ORM\Table(name="view_file_course", indexes={@ORM\Index(name="IDX_FILE_COURSE_COURSE", columns={"id_course"})})
 * @ORM\Entity
 */
class FileCourse extends File
{
    /**
     * @var \Course
     *
     * @ORM\ManyToOne(targetEntity="Course", inversedBy="files")
     * @ORM\JoinColumn(name="id_course", referencedColumnName="id")
     */
    private $course;

    public function getCourse(): ?Course
    {
        return $this->course;
    }

    public function setCourse(?Course $course): self
    {
        $this->course = $course;

        return $this;
    }


}
