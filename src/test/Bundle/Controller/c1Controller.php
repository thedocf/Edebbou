<?php

namespace test\Bundle\Controller;

use Doctrine\ORM\EntityManager;
use Doctrine\ORM\QueryBuilder;
use Doctrine\ORM\EntityRepository;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use test\Bundle\Entity\Category;
use test\Bundle\Entity\PlopRepository;
use test\Bundle\Entity\Product;
use test\Bundle\Form\CategoryType;
use test\Bundle\Form\ProductType;
use Doctrine\ORM\Query;

class c1Controller extends Controller
{
    public function show1Action()
    {
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findAll();
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/show.html.twig',array(
            'car'=> $car,'cat'=>$car1));

    }

    public function showByCatAction($id)
    {

        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMe($id);
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/showCat.html.twig',array(
            'car'=> $car,'cat'=>$car1 ,'idc'=>$id));

    }
    public function show2Action()
    {
        return $this->render('@test/c1/show2.html.twig', array(

        ));
    }
    public function addPAction(Request $request)
    {
        $car = new Product();
        $form = $this->createForm(ProductType::class, $car);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('manP');
        }


        return $this->render('@test/c1/addP.html.twig',array(
            'Form'=> $form->createView()));
    }
    public function addCatAction(Request $request)
    {
        $car = new Category();
        $form = $this->createForm(CategoryType::class, $car);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('manCat');
        }


        return $this->render('@test/c1/addCat.html.twig',array(
            'Form'=> $form->createView()));
    }
    public function manCatAction(){


        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/manCat.html.twig',array(
            'car'=> $car));
    }
    public function editCatAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $car= $em->getRepository('testBundle:Category')->find($id);
        $form=$this->createForm(CategoryType::class,$car);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('manCat');
        }


        return $this->render('@test/c1/editCat.html.twig',array(
            'Form1'=> $form->createView()));




    }
    public function deleteCatAction($qdt)
    {


        $em= $this->getDoctrine()->getManager();

        $carP =$em->getRepository('testBundle:Product')->findbyMe($qdt);
        $car =$em->getRepository('testBundle:Category')->find($qdt);

        if (!$carP)
        {
        $em->remove($car);
        $em->flush();
        }


        return $this->redirectToRoute('manCat');

    }
    public function manPAction(){


        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findAll();
        return $this->render('@test/c1/manP.html.twig',array(
            'car'=> $car));
    }
    public function editPAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $car= $em->getRepository('testBundle:Product')->find($id);
        $form=$this->createForm(ProductType::class,$car);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($car);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('manP');
        }


        return $this->render('@test/c1/editP.html.twig',array(
            'Form'=> $form->createView()));




    }
    public function deletePAction($qdt)
    {

        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->find($qdt);
        $em->remove($car);
        $em->flush();
        return $this->redirectToRoute('manP');


    }
}
