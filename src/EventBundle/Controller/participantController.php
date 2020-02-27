<?php

namespace EventBundle\Controller;



use EventBundle\Entity\participant;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Participant controller.
 *
 * @Route("./participant")
 */
class participantController extends Controller
{
    /**
     * Lists all participant entities.
     *
     * @Route("/", name="._participant_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $participants = $em->getRepository('EventBundle:participant')->findAll();

        return $this->render('@Event/Back/afficherparticipant.html.twig', array(
            'participants' => $participants,
        ));
    }

    /**
     * Creates a new participant entity.
     *
     * @Route("/new", name="._participant_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {

        $participant = new Participant();
        $form = $this->createForm('EventBundle\Form\participantType', $participant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($participant);
            $em->flush();
            return $this->redirectToRoute('._participant_show', array('id' => $participant->getId()));
        }
        $mails=$participant->getMail();
        $transport = Swift_SmtpTransport::newInstance('smtp.googlemail.com',465, 'ssl')
            ->setUsername('wifek.ouerghemmi@esprit.tn')
            ->setPassword('esprit123.');

        $mailer = Swift_Mailer::newInstance($transport);

        $message = (new \Swift_Message('ajouter evenement'))
            ->setFrom('lina.thraya@esprit.tn')
            ->setTo($mails)
            ->setBody(' bonjour mr votre evenement a été bien ajouté');
        $mailer->send($message);
        $this->get('mailer')->send($message);
        return $this->render('participant/new.html.twig', array(
            'participant' => $participant,
            'form' => $form->createView(),
        ));

    }

    /**
     * Finds and displays a participant entity.
     *
     * @Route("/{id}", name="._participant_show")
     * @Method("GET")
     */
    public function showAction(participant $participant)
    {
        $deleteForm = $this->createDeleteForm($participant);

        return $this->render('participant/show.html.twig', array(
            'participant' => $participant,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing participant entity.
     *
     * @Route("/{id}/edit", name="._participant_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, participant $participant)
    {
        $deleteForm = $this->createDeleteForm($participant);
        $editForm = $this->createForm('EventBundle\Form\participantType', $participant);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            //return $this->redirectToRoute('._participant_edit', array('id' => $participant->getId()));
        }

        return $this->render('@Event/participant/modifierparticipant.html.twig', array(
            'participant' => $participant,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a participant entity.
     *
     * @Route("/{id}", name="._participant_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, participant $participant)
    {
        $form = $this->createDeleteForm($participant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($participant);
            $em->flush();
        }

        return $this->redirectToRoute('._participant_index');
    }

    /**
     * Creates a form to delete a participant entity.
     *
     * @param participant $participant The participant entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(participant $participant)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('._participant_delete', array('id' => $participant->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function supprimerparticipantAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $eventcours =$em->getRepository('EventBundle:participant')->find($id);
        $em->remove($eventcours);
        $em->flush();
        return $this->redirectToRoute('afficherparticipant');
    }
}
