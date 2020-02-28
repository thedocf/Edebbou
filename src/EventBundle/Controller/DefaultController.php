<?php

namespace EventBundle\Controller;

use EventBundle\Entity\participant;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Swift_Mailer;
use Swift_SmtpTransport;
use Symfony\Bundle\SwiftmailerBundle\SwiftmailerBundle;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('EventBundle:Default:index.html.twig');
    }
    public function affichefrontAction()
    {
        return $this->render('EventBundle:Front:index.html.twig');
    }
    public function testAction(Request $request ,$id)

    {   $participant = new Participant();

        $em=$this->getDoctrine()->getManager();
    $event=$em->getRepository('EventBundle:evenement')->find($id);
        $form = $this->createForm('EventBundle\Form\participantType', $participant);
        $form->handleRequest($request);

        //  die('aaa');
        if ($form->isSubmitted() && $form->isValid()) {
            $participant->setEvenement($event);
            $em = $this->getDoctrine()->getManager();
            $em->persist($participant);
            $em->flush();
            $mails=$participant->getMail();
            $transport = Swift_SmtpTransport::newInstance('smtp.googlemail.com',465, 'ssl')
                ->setUsername('alaa.guissouma@esprit.tn')
                ->setPassword('Skotinka00_');

            $mailer = Swift_Mailer::newInstance($transport);

            $message = (new \Swift_Message('ajouter evenement'))
                ->setFrom('lina.thraya@esprit.tn')
                ->setTo($mails)
                ->setBody(' bonjour mr/mme votre evenement a été bien ajouté');
            $mailer->send($message);
            $this->get('mailer')->send($message);

            return $this->redirectToRoute('affichefront');
        }

        return $this->render('@Event/Front/inscrit.html.twig', array(
            'participant' => $participant,
            'form' => $form->createView(),
        ));

    }
}
