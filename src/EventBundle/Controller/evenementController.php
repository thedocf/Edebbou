<?php

namespace EventBundle\Controller;

use EventBundle\Entity\evenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Evenement controller.
 *
 * @Route("evenement")
 */
class evenementController extends Controller
{
    /**
     * Lists all evenement entities.
     *
     * @Route("/", name="evenement_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('EventBundle:evenement')->findAll();
        $eventcours = $em->getRepository('EventBundle:eventcours')->findAll();

        return $this->render('@Event/Back/afficher.html.twig', array(
            'evenements' => $evenements,
            'eventcours' => $eventcours,
        ));
    }

    /**
     * Creates a new evenement entity.
     *
     * @Route("/new", name="evenement_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $evenement = new Evenement();
        $form = $this->createForm('EventBundle\Form\evenementType', $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->flush();

            return $this->redirectToRoute('evenement_show', array('id' => $evenement->getId()));
        }

        return $this->render('evenement/new.html.twig', array(
            'evenement' => $evenement,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a evenement entity.
     *
     * @Route("/{id}", name="evenement_show")
     * @Method("GET")
     */
    public function showAction(evenement $evenement)
    {
        $deleteForm = $this->createDeleteForm($evenement);

        return $this->render('evenement/show.html.twig', array(
            'evenement' => $evenement,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing evenement entity.
     *
     * @Route("/{id}/edit", name="evenement_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, evenement $evenement)
    {
        $deleteForm = $this->createDeleteForm($evenement);
        $editForm = $this->createForm('EventBundle\Form\evenementType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('afficherevent', array('id' => $evenement->getId()));
        }

        return $this->render('@Event/Back/modifierevent.html.twig', array(
            'evenement' => $evenement,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }



    /**
     * Deletes a evenement entity.
     *
     * @Route("/{id}", name="evenement_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, evenement $evenement)
    {
        $form = $this->createDeleteForm($evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($evenement);
            $em->flush();
        }

        return $this->redirectToRoute('evenement_index');
    }

    /**
     * Creates a form to delete a evenement entity.
     *
     * @param evenement $evenement The evenement entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(evenement $evenement)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('evenement_delete', array('id' => $evenement->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

    public function supprimereventAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $eventcours =$em->getRepository('EventBundle:evenement')->find($id);
            $em->remove($eventcours);
        $em->flush();
        return $this->redirectToRoute('afficherevent');
    }

    public function affichefrontAction()
    {
        $em = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('EventBundle:evenement')->findAll();
        $eventcours = $em->getRepository('EventBundle:eventcours')->findAll();

        return $this->render('@Event/Front/afficherfront.html.twig', array(
            'evenements' => $evenements,
            'eventcours' => $eventcours,
        ));
    }

    public function DetailsAction(\Symfony\Component\HttpFoundation\Request $request,$id)
    {
        $em= $this->getDoctrine()->getManager();
        $event=$em->getRepository('EventBundle:evenement')->find($id);
        return $this->render('@Event/Front/detailevent.html.twig', array(
            'id'=>$event->getId(),
            'image'=>$event->getImage(),
            'nomEvent'=>$event->getNomEvent(),
            'description'=>$event->getDescription(),
            'prix'=>$event->getPrix(),
            'adresse'=>$event->getPrix()
        ));
    }
}
