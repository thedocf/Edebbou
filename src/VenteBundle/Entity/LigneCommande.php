<?php

namespace VenteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LigneCommande
 *
 * @ORM\Table(name="ligne_commande")
 * @ORM\Entity(repositoryClass="VenteBundle\Repository\LigneCommandeRepository")
 */
class LigneCommande
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
     * @ORM\ManyToOne(targetEntity="VenteBundle\Entity\Commande")
     * @ORM\JoinColumn(name="id_commande",referencedColumnName="id", onDelete="CASCADE")
     */
    private $commande;


    /**
     * @ORM\ManyToOne(targetEntity="test\Bundle\Entity\Product")
     * @ORM\JoinColumn(name="productId", referencedColumnName="id")
     */
    private $product;


    /**
     * @var int
     *
     * @ORM\Column(name="qte", type="integer")
     */
    private $qte;



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
     * Set qte
     *
     * @param integer $qte
     *
     * @return LigneCommande
     */
    public function setQte($qte)
    {
        $this->qte = $qte;

        return $this;
    }

    /**
     * Get qte
     *
     * @return integer
     */
    public function getQte()
    {
        return $this->qte;
    }

    /**
     * Set commande
     *
     * @param \VenteBundle\Entity\Commande $commande
     *
     * @return LigneCommande
     */
    public function setCommande(\VenteBundle\Entity\Commande $commande = null)
    {
        $this->commande = $commande;

        return $this;
    }

    /**
     * Get commande
     *
     * @return \VenteBundle\Entity\Commande
     */
    public function getCommande()
    {
        return $this->commande;
    }

    /**
     * Set product
     *
     * @param \test\Bundle\Entity\Product $product
     *
     * @return LigneCommande
     */
    public function setProduct(\test\Bundle\Entity\Product $product = null)
    {
        $this->product = $product;

        return $this;
    }

    /**
     * Get product
     *
     * @return \test\Bundle\Entity\Product
     */
    public function getProduct()
    {
        return $this->product;
    }
}
