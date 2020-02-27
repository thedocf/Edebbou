<?php

namespace VenteBundle\Controller;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use VenteBundle\Entity\Commande;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use VenteBundle\Entity\Livreur;


/**
 * Commande controller.
 *
 */
class CommandeController extends Controller
{
    /**
     * Lists all commande entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $commandes = $em->getRepository('VenteBundle:Commande')->findAll();

        foreach ($commandes as $commande) {
            if($commande->getLivreur() != null){
                if($commande->getDate() > new \DateTime())
                    $commande->setEtatLivraion("En cours de livraison");
                else $commande->setEtatLivraion("Livré");
            }
        }
        $em->flush();

        return $this->render('commande/index.html.twig', array(
            'commandes' => $commandes,
        ));
    }

    /**
     * Finds and displays a commande entity.
     *
     */
    public function showAction(Request $request,Commande $commande)
    {

        $form = $this->createFormBuilder()
            ->add('Livreur', EntityType::class,['class'=>Livreur::class])
            ->add('send', SubmitType::class)
            ->getForm();

        if($commande->getLivreur() != null){
            if($commande->getDate() > new \DateTime())
                $commande->setEtatLivraion("En cours de livraison");
            else $commande->setEtatLivraion("Livré");
        }


        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // data is an array with "name", "email", and "message" keys
            $data = $form->getData();
            $commande->setLivreur($data['Livreur']);
        }
        $this->getDoctrine()->getManager()->flush();
        return $this->render('commande/show.html.twig', array(
            'commande' => $commande,
            'form'=>$form->createView()
        ));
    }


    public function indexFrontAction()
    {
        $em = $this->getDoctrine()->getManager();

        $commandes = $em->getRepository('VenteBundle:Commande')->findBy(['user'=>$this->getUser()]);

        foreach ($commandes as $commande) {
            if($commande->getLivreur() != null){
                if($commande->getDate() > new \DateTime())
                    $commande->setEtatLivraion("En cours de livraison");
                else $commande->setEtatLivraion("Livré");
            }
        }
        $em->flush();

        return $this->render('@Vente/commandes.html.twig', array(
            'commandes' => $commandes,
        ));
    }


}
