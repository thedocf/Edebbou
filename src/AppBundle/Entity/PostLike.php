<?php

namespace AppBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;
/**
 * PostLike
 *
 * @ORM\Table(name="postlike")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\PostLikeRepository")
 */
class PostLike
{


    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $id;
    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\Post", inversedBy="likes")
     */
    private $post;
    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User", inversedBy="likes")
     */
    private $user;

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getPost()
    {
        return $this->post;
    }

    /**
     * @param mixed $post
     */
    public function setPost($post)
    {
        $this->post = $post;
    }

    /**
     * @return mixed
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * @param mixed $user
     */
    public function setUser($user)
    {
        $this->user = $user;
    }

}