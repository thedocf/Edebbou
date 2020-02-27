<?php

namespace VenteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity(repositoryClass="VenteBundle\Repository\CommandeRepository")
 */
class Commande
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
     * @var
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="id_u",referencedColumnName="id", onDelete="CASCADE")
     */
    private $user;


    /**
     * @var
     * @ORM\ManyToOne(targetEntity="VenteBundle\Entity\Livreur")
     * @ORM\JoinColumn(name="id_livreur",referencedColumnName="id", onDelete="CASCADE")
     */
    private $livreur;


    /**
     * @ORM\OneToMany(targetEntity="VenteBundle\Entity\LigneCommande",mappedBy="commande")
     */
    private $lignes;



    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="etatLivraison", type="string", options={"default": "en attente"})
     */
    private $etatLivraion ="en attente";


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
     * Constructor
     */
    public function __construct()
    {
        $this->lignes = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return Commande
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Set etatLivraion
     *
     * @param string $etatLivraion
     *
     * @return Commande
     */
    public function setEtatLivraion($etatLivraion)
    {
        $this->etatLivraion = $etatLivraion;

        return $this;
    }

    /**
     * Get etatLivraion
     *
     * @return string
     */
    public function getEtatLivraion()
    {
        return $this->etatLivraion;
    }

    /**
     * Set user
     *
     * @param \AppBundle\Entity\User $user
     *
     * @return Commande
     */
    public function setUser(\AppBundle\Entity\User $user = null)
    {
        $this->user = $user;

        return $this;
    }

    /**
     * Get user
     *
     * @return \AppBundle\Entity\User
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * Add ligne
     *
     * @param \VenteBundle\Entity\LigneCommande $ligne
     *
     * @return Commande
     */
    public function addLigne(\VenteBundle\Entity\LigneCommande $ligne)
    {
        $this->lignes[] = $ligne;

        return $this;
    }

    /**
     * Remove ligne
     *
     * @param \VenteBundle\Entity\LigneCommande $ligne
     */
    public function removeLigne(\VenteBundle\Entity\LigneCommande $ligne)
    {
        $this->lignes->removeElement($ligne);
    }

    /**
     * Get lignes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getLignes()
    {
        return $this->lignes;
    }

    /**
     * Set livreur2
     *
     * @param \VenteBundle\Entity\Livreur $livreur
     *
     * @return Commande
     */
    public function setLivreur(\VenteBundle\Entity\Livreur $livreur = null)
    {
        $this->livreur = $livreur;

        return $this;
    }

    /**
     * Get livreur2
     *
     * @return \VenteBundle\Entity\Livreur
     */
    public function getLivreur()
    {
        return $this->livreur;
    }
}
