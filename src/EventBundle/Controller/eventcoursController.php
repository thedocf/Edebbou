<?php

namespace EventBundle\Controller;

use EventBundle\Entity\evenement;
use EventBundle\Entity\eventcours;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;

use Swift_Mailer;
use Swift_SmtpTransport;
use Symfony\Bundle\SwiftmailerBundle\SwiftmailerBundle;

/**
 * Eventcour controller.
 *
 * @Route("eventcours")
 */
class eventcoursController extends Controller
{
    /**
     * Lists all eventcour entities.
     *
     * @Route("/", name="eventcours_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $eventcours = $em->getRepository('EventBundle:eventcours')->findAll();

        return $this->render('eventcours/index.html.twig', array(
            'eventcours' => $eventcours,
        ));
    }

    /**
     * Creates a new eventcour entity.
     *
     * @Route("/new", name="eventcours_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $eventcour = new Eventcours();
        $form = $this->createForm('EventBundle\Form\eventcoursType', $eventcour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            /** @var UploadedFile $file */
            $file = $eventcour->getImage();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $eventcour->setImage($filename);
            $em = $this->getDoctrine()->getManager();
            $em->persist($eventcour);
            $em->flush();

           // return $this->redirectToRoute('eventcours_show', array('id' => $eventcour->getId()));
        }

        return $this->render('@Event/Back/ajout.html.twig', array(
            'eventcour' => $eventcour,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a eventcour entity.
     *
     * @Route("/{id}", name="eventcours_show")
     * @Method("GET")
     */
    public function showAction(eventcours $eventcour)
    {
        $deleteForm = $this->createDeleteForm($eventcour);

        return $this->render('eventcours/show.html.twig', array(
            'eventcour' => $eventcour,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing eventcour entity.
     *
     * @Route("/{id}/edit", name="eventcours_edit")
     * @Method({"GET", "POST"})
     */
    public function modifiereventcoursAction(Request $request, eventcours $eventcour)
    {
       // $deleteForm = $this->createDeleteForm($eventcour);
        $editForm = $this->createForm('EventBundle\Form\eventcoursType', $eventcour);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

           // return $this->redirectToRoute('modifierevtncours', array('id' => $eventcour->getId()));
        }

        return $this->render('@/Event/Back/modifiereventcours.html.twig', array(
                'eventcour' => $eventcour,
            'edit_form' => $editForm->createView(),

        ));
    }

    /**
     * Deletes a eventcour entity.
     *
     * @Route("/{id}", name="eventcours_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, eventcours $eventcour)
    {
        $form = $this->createDeleteForm($eventcour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($eventcour);
            $em->flush();
        }

        return $this->redirectToRoute('eventcours_index');
    }

    /**
     * Creates a form to delete a eventcour entity.
     *
     * @param eventcours $eventcour The eventcour entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(eventcours $eventcour)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('eventcours_delete', array('id' => $eventcour->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function supprimereventencoursAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $eventcours =$em->getRepository('EventBundle:eventcours')->find($id);
        $em->remove($eventcours);
        $em->flush();
        return $this->redirectToRoute('afficherevent');
    }

    public function confirmerAction(Request $request ,$id)
    {
        $transport = Swift_SmtpTransport::newInstance('smtp.googlemail.com',465, 'ssl')
            ->setUsername('wifek.ouerghemmi@esprit.tn')
            ->setPassword('esprit123.');

        $mailer = Swift_Mailer::newInstance($transport);


        $message = (new \Swift_Message('ajouter evenement'))
            ->setFrom('lina.thraya@esprit.tn')
            ->setTo('lina.thraya@esprit.tn')
            ->setBody(' bonjour, votre événement a été ajouté.');
        $mailer->send($message);
        $this->get('mailer')->send($message);
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository('EventBundle:eventcours')->find($id);
        $Eventr=new evenement();

        $Eventr->setDateDebut($event->getDateDebut());
        $Eventr->setDateFin($event->getDateFin());
        $Eventr->setDescription($event->getDescription());
        $Eventr->setImage($event->getImage());
        $Eventr->setNbPlaces($event->getNbPlaces());
        $Eventr->setNomEvent($event->getNomEvent());




        $em=$this->getDoctrine()->getManager();
        $em->persist($Eventr);
        $em->remove($event);
        $em->flush();
        return $this->redirectToRoute('afficherevent');

    }





    public function ajoutereventencoursaction(\Symfony\Component\HttpFoundation\Request $request)
    {
        $eventencours= new eventcours();
        $form =$this->createFormBuilder($eventencours)
            ->add('nomEvent')
            ->add('nbPlaces')
            ->add('description')
            ->add('dateDebut')
            ->add('image',FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('dateFin')

            ->getForm();
        $form->handleRequest($request);
        if ($form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            /** @var UploadedFile $file */
            $file = $eventencours->getImage();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $eventencours->setImage($filename);
            $em->persist($eventencours);
            $em->flush();
            return $this->redirectToRoute('afficherevent');
        }
        return $this->render('@Event/Back/ajout.html.twig',array('form'=>$form->createView()));
    }
}
