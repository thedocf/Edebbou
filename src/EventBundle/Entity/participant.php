<?php

namespace EventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * participant
 *
 * @ORM\Table(name="participant")
 * @ORM\Entity(repositoryClass="EventBundle\Repository\participantRepository")
 */
class participant
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }
    /**
     * @var bool
     *
     * @ORM\Column(name="confirmation", type="boolean")
     */
    private $confirmation;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length = 255)
     */
    private $nom;

    /**
     * @return string
     */
    public function getMail()
    {
        return $this->mail;
    }

    /**
     * @param string $mail
     */
    public function setMail($mail)
    {
        $this->mail = $mail;
    }
    /**
     * @var string
     *
     * @ORM\Column(name="mail", type="string", length = 255)
     */
    private $mail;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length = 255)
     */
    private $prenom;

    /**
     * @ORM\ManyToOne(targetEntity="EventBundle\Entity\evenement")
     * @ORM\JoinColumn(name="evenement",referencedColumnName="id" ,onDelete="CASCADE")
     *
     */
    private $evenement;

    /**
     * @return bool
     */
    public function isConfirmation()
    {
        return $this->confirmation;
    }

    /**
     * @param bool $confirmation
     */
    public function setConfirmation($confirmation)
    {
        $this->confirmation = $confirmation;
    }

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @return mixed
     */
    public function getEvenement()
    {
        return $this->evenement;
    }

    /**
     * @param mixed $evenement
     */
    public function setEvenement($evenement)
    {
        $this->evenement = $evenement;
    }



    /**
     * @return \DateTime
     */
    public function getDateInscrit()
    {
        return $this->dateInscrit;
    }

    /**
     * @param \DateTime $dateInscrit
     */
    public function setDateInscrit($dateInscrit)
    {
        $this->dateInscrit = $dateInscrit;
    }

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_inscrit", type="datetime")
     */
    private $dateInscrit;

    public function __toString() {
        return (string) $this->evenement;
    }


}

