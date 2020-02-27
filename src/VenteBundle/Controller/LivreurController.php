<?php

namespace VenteBundle\Controller;

use Symfony\Component\HttpFoundation\JsonResponse;
use VenteBundle\Entity\Livreur;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Livreur controller.
 *
 */
class LivreurController extends Controller
{
    /**
     * Lists all livreur entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $livreurs = $em->getRepository('VenteBundle:Livreur')->findAll();

        return $this->render('livreur/index.html.twig', array(
            'livreurs' => $livreurs,
        ));
    }

    /**
     * Creates a new livreur entity.
     *
     */
    public function newAction(Request $request)
    {
        $livreur = new Livreur();
        $form = $this->createForm('VenteBundle\Form\LivreurType', $livreur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($livreur);
            $em->flush();

            return $this->redirectToRoute('livreur_show', array('id' => $livreur->getId()));
        }

        return $this->render('livreur/new.html.twig', array(
            'livreur' => $livreur,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a livreur entity.
     *
     */
    public function showAction(Livreur $livreur)
    {
        $deleteForm = $this->createDeleteForm($livreur);

        return $this->render('livreur/show.html.twig', array(
            'livreur' => $livreur,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing livreur entity.
     *
     */
    public function editAction(Request $request, Livreur $livreur)
    {
        $deleteForm = $this->createDeleteForm($livreur);
        $editForm = $this->createForm('VenteBundle\Form\LivreurType', $livreur);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('livreur_edit', array('id' => $livreur->getId()));
        }

        return $this->render('livreur/edit.html.twig', array(
            'livreur' => $livreur,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a livreur entity.
     *
     */
    public function deleteAction(Request $request, Livreur $livreur)
    {
        $form = $this->createDeleteForm($livreur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($livreur);
            $em->flush();
        }

        return $this->redirectToRoute('livreur_index');
    }

    /**
     * Creates a form to delete a livreur entity.
     *
     * @param Livreur $livreur The livreur entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Livreur $livreur)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('livreur_delete', array('id' => $livreur->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }



    public function rechercheLivreurAction(Request $request)
    {
        $event=$request->get('liv');

        $events=$this->getDoctrine()->getManager()->createQuery("select e from VenteBundle:Livreur e where e.nom like '%".$event."%'")
            ->getResult();


        $jsonData=array();
        $idx=0;
        foreach ($events as $liv) {
            $temp=array(
                'id'=>$liv->getId(),
                'nom'=>$liv->getNom(),

            );
            $jsonData[$idx++]=$temp;

        }

        return new JsonResponse($jsonData);

        //return
    }
}
