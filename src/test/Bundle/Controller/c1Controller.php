<?php

namespace test\Bundle\Controller;

use Doctrine\ORM\EntityManager;
use Doctrine\ORM\QueryBuilder;
use Doctrine\ORM\EntityRepository;
use http\Env\Response;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Console\Input\Input;
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
    public function pdfAction(Request $request)
    {
        $em= $this->getDoctrine()->getManager();
        $pro =$em->getRepository('testBundle:Product')->findok();
        $cat =$em->getRepository('testBundle:Category')->findAll();
        $snappy=$this->get('knp_snappy.pdf');
        $date = date("Y:m:d");
           $html= $this->renderView(
                '@test/c1/pdf.html.twig',
                array(
                    'product'=> $pro,'category'=>$cat
                ));

        return new PdfResponse(
            $snappy->getOutputFromHtml($html),

            "$date~.pdf",
        array(
            'Content-Type' => 'application/pdf',
            'Content-Disposition' => 'attachment',

        )
        );
    }

    public function showByCatAction($id)
    {

        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMe($id);
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/showCat.html.twig',array(
            'car'=> $car,'cat'=>$car1 ,'idc'=>$id));

    }
    public function sortByPAction(Request $request)
    {

        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMe3();
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('baseSP.html.twig',array(
            'car'=> $car,'cat'=>$car1 ));

    }
    public function sortByP2Action(Request $request)
    {

        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMeAsc();
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('baseSP2.html.twig',array(
            'car'=> $car,'cat'=>$car1 ));

    }
    /*public function sortByDAction(Request $request)
    {

        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMe4();
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('manProductD.html.twig',array(
            'car'=> $car,'cat'=>$car1 ));

    }*/
    public function searchPAction(Request $request)
    {
        $id = $request->request->get('search');
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMe4($id);
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('manProductS.html.twig',array(
            'car'=> $car,'cat'=>$car1 ));

    }
    public function searchCAction(Request $request)
    {
        $id = $request->request->get('search');
        $em= $this->getDoctrine()->getManager();

        $car1 =$em->getRepository('testBundle:Category')->findbyMe5($id);
        return $this->render('manCategoryS.html.twig',array(
            'car'=>$car1 ));

    }
    public function showByPAction(Request $request)
    {
        $min = $request->request->get('min');
        $max = $request->request->get('max');
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMe2($min,$max);
        $car1 =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('baseP.html.twig',array(
            'car'=> $car,'cat'=>$car1 ));

    }
    public function show2Action()
    {
        return $this->render('@test/c1/show2.html.twig', array(

        ));
    }
    public function fronthomeAction()
    {
        return $this->render('fronthome.html.twig', array(

        ));
    }
    public function showpdfAction()
    {
        $em= $this->getDoctrine()->getManager();
        $pro =$em->getRepository('testBundle:Product')->findok();
        $cat =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/pdf.html.twig', array(
            'product'=> $pro,'category'=>$cat
        ));
    }
    public function addPAction(Request $request)
    {
        $car = new Product();
        $form = $this->createForm(ProductType::class, $car);
        $form->handleRequest($request);

        $image = $form->get('image')->getData();

        $imageName = md5(uniqid()).'.jpg';
        if ($image) {
            $image->move(
                $this->getParameter('imagedepot_directory'),$imageName
            );
        }


        $car->setImage($imageName);
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
        $cat =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/manP.html.twig',array(
            'car'=> $car,'cat'=>$cat));
    }
    public function editPAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $car= $em->getRepository('testBundle:Product')->find($id);
        $form=$this->createForm(ProductType::class,$car);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $car->getImage();
            $imageName = md5(uniqid()).'.jpg';
            if ($image) {
                $image->move(
                    $this->getParameter('imagedepot_directory'), $imageName
                );
            }
            $car->setImage($imageName);
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
    public function statAction()
    {
        $em= $this->getDoctrine()->getManager();

        $pro1 =$em->getRepository('testBundle:Product')->findbyStat();
        $pro2 =$em->getRepository('testBundle:Product')->findtotal();
        $nbcat =$em->getRepository('testBundle:Category')->findnbC();
        $ids=$em->getRepository('testBundle:Category')->findidsC();

        for ($i=0;$i < $nbcat ;$i++)
        {

            $pros[$i] =$em->getRepository('testBundle:Product')->findStatCP($ids[$i]);
            $pro[$i] =$em->getRepository('testBundle:Product')->findStatC($ids[$i]);

        }

        $cat =$em->getRepository('testBundle:Category')->findAll();
        return $this->render('@test/c1/stat.html.twig', array(
            'total'=> $pro2,'category'=>$cat ,'stat'=> $pro1,'stats' => $pro ,'ids'=>$ids,'totals'=> $pros
        ));
    }
    public function delExpAction($id){


        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('testBundle:Product')->findbyMeE($id);

        return $this->render('manProductEXP.html.twig',array(
            'car'=> $car));
    }
}
