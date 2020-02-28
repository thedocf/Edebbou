<?php

namespace test\Bundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Product
 *
 * @ORM\Table(name="product")
 * @ORM\Entity(repositoryClass="test\Bundle\Repository\ProductRepository")
 */
class Product
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idp;

    /**
     * @var float
     *@Assert\NotBlank()
     * @Assert\Regex(pattern="/^-?(?:\d+|\d*\.\d+)$/")
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;

    /**
     * @var int
     *@Assert\NotBlank()
     * @ORM\Column(name="qte", type="integer")
     */
    private $qte;

    /**
     * @var string
     * @Assert\Length(min="4")
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;



    /**
     * @var string
     *@Assert\Date
     *
     * @ORM\Column(name="date_expiration", type="string", length=255)
     */
    private $dateExpiration;

    /**
     * @var string

     *@Assert\NotBlank()
     * @Assert\Length(min="5")
     * @Assert\Regex(pattern="/^[a-zA-Z\s]+$/")
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

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
     * @ORM\ManyToOne(targetEntity="Category")
     *  @Assert\NotBlank(message="Category Required")
     * @ORM\JoinColumn(name="cat_id",referencedColumnName="id")
     */
    private $category;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     */
    private $image;


    /**
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param string $image
     */
    public function setImage($image)
    {
        $this->image = $image;
    }

    /**
     *
     * @return mixed
     */
    public function getCategory()
    {
        return $this->category;
    }

    /**
     * @param mixed $category
     */
    public function setCategory($category)
    {
        $this->category = $category;
    }

    /**
     * Get id
     *
     * @return int
     */
    public function getIdp()
    {
        return $this->idp;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Product
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set qte
     *
     * @param integer $qte
     *
     * @return Product
     */
    public function setQte($qte)
    {
        $this->qte = $qte;

        return $this;
    }

    /**
     * Get qte
     *
     * @return int
     */
    public function getQte()
    {
        return $this->qte;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Product
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    

    /**
     * Set dateExpiration
     *
     * @param string $dateExpiration
     *
     * @return Product
     */
    public function setDateExpiration($dateExpiration)
    {
        $this->dateExpiration = $dateExpiration;

        return $this;
    }

    /**
     * Get dateExpiration
     *
     * @return string
     */
    public function getDateExpiration()
    {
        return $this->dateExpiration;
    }
}

