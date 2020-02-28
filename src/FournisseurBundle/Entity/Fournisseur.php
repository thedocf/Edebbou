<?php

namespace FournisseurBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Fournisseur
 *
 * @ORM\Table(name="fournisseur")
 * @ORM\Entity(repositoryClass="FournisseurBundle\Repository\FournisseurRepository")
 */
class Fournisseur
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
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255)
     */
    private $prenom;

    /**
     * @var int
     *
     * @ORM\Column(name="numTel", type="integer")
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     *      minMessage = "Numero 8 chiffre seulement",
     *      maxMessage = "Numero 8 chiffre seulement"
     * )
     */
    private $numTel;

    /**
     * @var int
     *
     * @ORM\Column(name="disponible", type="boolean")
     */
    private $disponible;

    /**
     * @ORM\ManyToOne(targetEntity="FournisseurBundle\Entity\Depot")
     * @ORM\JoinColumn(name="depot_id", referencedColumnName="id", nullable=true)
     */
    private $depot;

    /**
     * @return mixed
     */
    public function getDepot()
    {
        return $this->depot;
    }

    /**
     * @param mixed $depot
     */
    public function setDepot($depot)
    {
        $this->depot = $depot;
    }



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
     * Set nom
     *
     * @param string $nom
     *
     * @return Fournisseur
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set prenom
     *
     * @param string $prenom
     *
     * @return Fournisseur
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;

        return $this;
    }

    /**
     * Get prenom
     *
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * Set numTel
     *
     * @param integer $numTel
     *
     * @return Fournisseur
     */
    public function setNumTel($numTel)
    {
        $this->numTel = $numTel;

        return $this;
    }

    /**
     * Get numTel
     *
     * @return int
     */
    public function getNumTel()
    {
        return $this->numTel;
    }

    /**
     * Set disponible
     *
     * @param integer $disponible
     *
     * @return Fournisseur
     */
    public function setDisponible($disponible)
    {
        $this->disponible = $disponible;

        return $this;
    }

    /**
     * Get disponible
     *
     * @return int
     */
    public function getDisponible()
    {
        return $this->disponible;
    }

}

