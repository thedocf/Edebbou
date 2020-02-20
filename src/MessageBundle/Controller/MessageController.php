<?php

namespace MessageBundle\Controller;

use MessageBundle\Entity\categ;
use MessageBundle\Form\categType;
use Symfony\Component\HttpFoundation\Request;
use MessageBundle\Entity\message;
use MessageBundle\Form\messageType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\User\UserInterface;


class MessageController extends Controller
{



    public function addmessageAction(Request $request)
    {

        $message = new Message();
        $form= $this->createForm(messageType::class, $message);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $file = $message->getPhoto();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $message->setPhoto($filename);
            $message->setCreateur($this->getUser());
            $message->setPostdate(new \DateTime('now'));

            $em->persist($message);
            $em->flush();

            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('listerMessage');
        }

        return $this->render('@Message/message/addMessage.html.twig', array(
            "form"=> $form->createView()
        ));
    }


    public function listmessagesAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $message=$em->getRepository('MessageBundle:message')->findAll();
        return $this->render('@Message/message/showMsg.html.twig', array(
            "message" =>$message
        ));

    }


    public function updatemessageAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('MessageBundle:message')->find($id);
        $form=$this->createForm(messageType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $file = $p->getPhoto();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $p->setPhoto($filename);
            $p->setPostdate(new \DateTime('now'));
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
            return $this->redirectToRoute('listerMessage');

        }
        return $this->render('@Message/message/update.html.twig', array(
            "form"=> $form->createView()
        ));
    }

    public function deletemessageAction(Request $request)
    {
        $id = $request->get('id');
        $em= $this->getDoctrine()->getManager();
        $message=$em->getRepository('MessageBundle:message')->find($id);
        $em->remove($message);
        $em->flush();
        return $this->redirectToRoute('listerMessage');
    }


    public function addCatAction(Request $request)
    {
        $car = new categ();
        $form = $this->createForm(categType::class, $car);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('showCateg');
        }


        return $this->render('@Message/message/addCateg.html.twig',array(
            'Form'=> $form->createView()));
    }

    public function manCatAction(){


        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('MessageBundle:categ')->findAll();
        return $this->render('@Message/message/showCateg.html.twig',array(
            'car'=> $car));
    }

    public function editCatAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('MessageBundle:categ')->find($id);
        $form=$this->createForm(categType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
            return $this->redirectToRoute('showCateg');

        }
        return $this->render('@Message/message/editCateg.html.twig', array(
            "form"=> $form->createView()
        ));
    }

    public function deleteCatAction(Request $request, $qdt)
    {


        $em = $this->getDoctrine()->getManager();

        $carP = $em->getRepository('MessageBundle:message')->find($qdt);
        $car = $em->getRepository('MessageBundle:categ')->find($qdt);

        if (!$carP) {
            $em->remove($car);
            $em->flush();
        }


        return $this->redirectToRoute('showCateg');
    }

    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $posts =  $em->getRepository('MessageBundle:message')->findEntitiesByString($requestString);
        if(!$posts) {
            $result['message']['error'] = "Post Not found :( ";
        } else {
            $result['message'] = $this->getRealEntities($posts);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($message){
        foreach ($message as $message){
            $realEntities[$message->getId()] = [$message->getPhoto(),$message->getTitle()];

        }
        return $realEntities;
    }

    }

