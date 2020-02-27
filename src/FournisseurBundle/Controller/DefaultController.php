<?php

namespace FournisseurBundle\Controller;

use FournisseurBundle\Entity\Depot;
use FournisseurBundle\Entity\Fournisseur;
use FournisseurBundle\Form\DepotType;
use FournisseurBundle\Form\FournisseurType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Fournisseur/default/index.html.twig');
    }
    public function showFournisseurAction ()
    {
        $em= $this->getDoctrine()->getManager();
        $fournisseurs =$em->getRepository('FournisseurBundle:Fournisseur')->findAll();
        return $this->render('@Fournisseur/fournisseur/showback.html.twig',compact("fournisseurs"));
    }

    public function addFournisseurAction(Request $request)
    {

        $car = new Fournisseur();
        $form = $this->createForm(FournisseurType::class, $car);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            return $this->redirectToRoute('fournisseur_homepage');
        }
        $string = (string) $form->getErrors(true, false);
        return $this->render('@Fournisseur/fournisseur/addF.html.twig',array(
            'form'=> $form->createView(),'error'=>$string));
    }

    public function editFournissuerAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $car= $em->getRepository('FournisseurBundle:Fournisseur')->find($id);
        $form=$this->createForm(FournisseurType::class,$car);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('fournisseur_homepage');
        }

        return $this->render('@Fournisseur/fournisseur/addF.html.twig',array(
            'form'=> $form->createView()));
    }
    public function deleteFournisseurAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('FournisseurBundle:Fournisseur')->find($id);
            $em->remove($car);
            $em->flush();
        return $this->redirectToRoute('fournisseur_homepage');
    }

    public function detailsAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $depot =$em->getRepository('FournisseurBundle:Depot')->find($id);
        $fournisseurs =$em->getRepository('FournisseurBundle:Fournisseur')->findBy(array(
            'depot'=>$depot
        ));
        return $this->render('@Fournisseur/depot/detailback.html.twig',compact("depot","fournisseurs"));

    }

    //////////DEPOT CRUD ////////////
    public function showDepotAction ()
    {
        $em= $this->getDoctrine()->getManager();
        $depots =$em->getRepository('FournisseurBundle:Depot')->findAll();
        return $this->render('@Fournisseur/depot/showback.html.twig',compact("depots"));
    }

    public function showDepotAffectAction ($id)
    {
        $em= $this->getDoctrine()->getManager();
        $fournisseur =$em->getRepository('FournisseurBundle:Fournisseur')->find($id);
        $depots =$em->getRepository('FournisseurBundle:Depot')->findAll();

        return $this->render('@Fournisseur/fournisseur/affect.html.twig',compact("depots","fournisseur"));
    }

    public function affecterDepotAction($id,$idd)
    {
        $depot=new Depot();
         $this->getDoctrine()->getManager()->getRepository('FournisseurBundle:Fournisseur')->affecter($id,$idd);
        $em= $this->getDoctrine()->getManager();
        $depot=$em->getRepository(Depot::class)->find($idd);
        if($depot->getCapacite() >0) /// avoid negatif capacity
        $depot->setCapacite($depot->getCapacite()-1);
        $em->persist($depot);
        $em->flush();
        return $this->redirectToRoute('fournisseur_homepage');
    }

    public function addDepotAction(Request $request)
    {
        $car = new Depot();
        $form = $this->createForm(DepotType::class, $car);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $image = $car->getImage();
            $imageName = md5(uniqid()).'.'.$image->guessExtension();

            $image->move(
                $this->getParameter('imagedepot_directory'),$imageName
            );

            $car->setImage($imageName);

            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            return $this->redirectToRoute('depot_homepage');
        }

        return $this->render('@Fournisseur/depot/addF.html.twig',array(
            'form'=> $form->createView()));
    }

    public function editDepotAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $car= $em->getRepository('FournisseurBundle:Depot')->find($id);
        $form=$this->createForm(DepotType::class,$car);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $image = $car->getImage();
            $imageName = md5(uniqid()).'.'.$image->guessExtension();

            $image->move(
                $this->getParameter('imagedepot_directory'),$imageName
            );

            $car->setImage($imageName);

            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('depot_homepage');
        }

        return $this->render('@Fournisseur/depot/addF.html.twig',array(
            'form'=> $form->createView()));
    }

    public function deleteDepotAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('FournisseurBundle:Depot')->find($id);
        $em->remove($car);
        $em->flush();
        return $this->redirectToRoute('depot_homepage');
    }

    public function afficherAction()
    {
        $em= $this->getDoctrine()->getManager();
        $depots =$em->getRepository('FournisseurBundle:Depot')->findAll();
        return $this->render('@Fournisseur/depot/showfront.html.twig',compact("depots"));
    }

}
