<?php

namespace VenteBundle\Controller;

use VenteBundle\Entity\LigneCommande;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;


/**
 * Lignecommande controller.
 *
 */
class LigneCommandeController extends Controller
{
    /**
     * Lists all ligneCommande entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $ligneCommandes = $em->getRepository('VenteBundle:LigneCommande')->findAll();

        return $this->render('lignecommande/index.html.twig', array(
            'ligneCommandes' => $ligneCommandes,
        ));
    }

    /**
     * Finds and displays a ligneCommande entity.
     *
     */
    public function showAction(LigneCommande $ligneCommande)
    {

        return $this->render('lignecommande/show.html.twig', array(
            'ligneCommande' => $ligneCommande,
        ));
    }
}
