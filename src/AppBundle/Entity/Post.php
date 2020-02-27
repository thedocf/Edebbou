<?php


namespace AppBundle\Entity;

use SBC\NotificationsBundle\Builder\NotificationBuilder;
use SBC\NotificationsBundle\Model\NotifiableInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;


/**
 * @ORM\Entity(repositoryClass="AppBundle\Repository\PostRepository")
 * @ORM\Table(name="post")
 * @ORM\HasLifecycleCallbacks()
 */


class Post implements NotifiableInterface
{


    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */

    protected $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Veuillez remplir ce champ")
     */
    private $title;


    /**
     * @ORM\Column(type="string", length=255)
     *  * *@Assert\NotBlank(message="Le Champ Description est obligatoire")
     * @Assert\Length(
     *     min=20,
     *     minMessage="au moins 20 cacartères  "
     * )
     */
    private $description;

    /**
     * @ORM\Column(name="photo", type="string", length=500)
     * @Assert\File(maxSize="500k",
     *     mimeTypes={"image/jpeg", "image/jpg", "image/png"},
     *     maxSizeMessage="vous n'avez pas le droit qu'a 500k",
     *     mimeTypesMessage="Vous pouvez uploader que jpg,jpeg et png" )
     *
     */
    private $photo;

    /**
     * @return mixed
     */
    public function getCreator()
    {
        return $this->creator;
    }

    /**
     * @param mixed $creator
     */
    public function setCreator($creator)
    {
        $this->creator = $creator;
    }

    /**
     * @return mixed
     */
    public function getComments()
    {
        return $this->comments;
    }

    /**
     * @param mixed $comments
     */
    public function setComments($comments)
    {
        $this->comments = $comments;
    }


    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="creator", referencedColumnName="id")
     */
    private $creator;

    /**
     * @param \DateTime $postdate
     */
    public function setPostdate($postdate)
    {
        $this->postdate = $postdate;
    }

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="postdate", type="date")
     */
    private $postdate;

    /**
     * @ORM\OneToMany(targetEntity="AppBundle\Entity\Postcomment", mappedBy="post",cascade={"remove"}, orphanRemoval=true)
     */
    private $comments;

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
    public function getTitle()
    {
        return $this->title;
    }

    /**
     * @param mixed $title
     */
    public function setTitle($title)
    {
        $this->title = $title;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param mixed $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return mixed
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param mixed $photo
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }

    /**
     * @return \DateTime
     */
    public function getPostdate()
    {
        return $this->postdate;
    }

    /**
     * @ORM\OneToMany(targetEntity="AppBundle\Entity\PostLike", mappedBy="post",cascade={"remove"}, orphanRemoval=true)
     */
    private $likes;

    /**
     * @return mixed
     */
    public function getLikes()
    {
        return $this->likes;
    }

    /**
     * @param mixed $likes
     */
    public function setLikes($likes)
    {
        $this->likes = $likes;
    }

    /**
     * @param User $user
     * @return bool
     */
    public function isLikeByUser(User $user): bool
    {
        foreach ($this->likes as $like) {
            if ($like->getUser() === $user) return true;
        }
        return false;
    }

    public function notificationsOnCreate(NotificationBuilder $builder)
    {
        $notification=new Notification();
        $notification
            ->setTitle('New post')
            ->setDescription($this->title)
            ->setRoute('list_post')
            ->setParameters(array('id'=>$this->id))
        ;
        $builder->addNotification($notification);

        return $builder;
    }

    public function notificationsOnUpdate(NotificationBuilder $builder)
    {
        $notification=new Notification();
        $notification
            ->setTitle('Update post')
            ->setDescription($this->title)
            ->setRoute('list_post')
            ->setParameters(array('id'=>$this->id))
        ;
        $builder->addNotification($notification);

        return $builder;
    }

    public function notificationsOnDelete(NotificationBuilder $builder)
    {
        return $builder;
    }

}