<?php

namespace VenteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use test\Bundle\Entity\Product;
use VenteBundle\Entity\Commande;
use VenteBundle\Entity\LigneCommande;

class DefaultController extends Controller
{
    public function indexAction(SessionInterface $session)
    {
        //$session->start();
        /*if ($session->get('panier') == null)
            $session->set('panier',[]);

        $panier = $session->get('panier');

        $panier['ww'] += 88;

        $session->set('panier',$panier);
        dump(isset($panier['111']));*/

        $panier = $session->get('panier');
        dump(sizeof($panier));
        //dump($session->get('panier'));

        //$session->clear();

        return $this->render('@Vente/Default/index.html.twig');
    }


    public function addProduitAction(SessionInterface $session,$idp, $qte){

        $session->start();
        if ($session->get('panier') == null)
            $session->set('panier',[]);
        $panier = $session->get('panier');
        if(isset($panier[$idp])){
            if($qte + $panier[$idp]<0)
                $panier[$idp] = 0;
            else
                $panier[$idp] += $qte;
        }
        else {
            $panier[$idp] = $qte;
        }
        $session->set('panier',$panier);
        return new JsonResponse(sizeof($panier));
    }



    public function PanierAction(SessionInterface $session){
        $session->start();
        if ($session->get('panier') == null)
            $session->set('panier',[]);
        $em = $this->getDoctrine()->getManager();
        $panier = $session->get('panier');
        $produits = [];
        foreach ($panier as $p=>$qte){
          $produits[$p] = $em->getRepository(Product::class)->find($p);
        }


        return $this->render('@Vente/cart.html.twig', ["panier"=>$panier, "produits"=>$produits]);
    }




    public function setProduitAction(SessionInterface $session,$idp, $qte){

        $em = $this->getDoctrine()->getManager();
        $session->start();
        if ($session->get('panier') == null)
            $session->set('panier',[]);
        $panier = $session->get('panier');

            $panier[$idp] = $qte;

        $session->set('panier',$panier);
        $total = 0;
        foreach ($panier as $k=>$v){
            $total += $v * $em->getRepository(Product::class)->find($k)->getPrix();
        }
        return new JsonResponse($total);
    }

    public function CheckOutAction(SessionInterface $session){

        $em = $this->getDoctrine()->getManager();
        $session->start();
        if ($session->get('panier') == null)
            $session->set('panier',[]);
        $panier = $session->get('panier');

        $commande = new Commande();
        $commande->setDate(new \DateTime());
        $commande->setUser($this->getUser());

        foreach ($panier as $k=>$v){
             $prod = $em->getRepository(Product::class)->find($k);
             $ligne = new LigneCommande();
             $ligne->setCommande($commande);
             $ligne->setProduct($prod);
             $ligne->setQte($v);
             $em->persist($ligne);
             $commande->addLigne($ligne);
        }
        $em->persist($commande);
        $em->flush();
        $session->clear();

        return $this->redirectToRoute('commande_index_front');
    }

}
