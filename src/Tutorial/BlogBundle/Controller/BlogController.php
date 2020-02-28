<?php

namespace Tutorial\BlogBundle\Controller;
use AppBundle\Entity\Notification;
use AppBundle\Entity\Post;
use AppBundle\Entity\Postcomment;
use AppBundle\Entity\PostLike;
use AppBundle\Repository\PostLikeRepository;
use AppBundle\Repository\PostRepository;
use Doctrine\Common\Persistence\ObjectManager;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\User\UserInterface;
use Tutorial\BlogBundle\Form\PostType;


class BlogController extends Controller
{

    public function addAction(Request $request)
    {

        $post = new Post();
        $form= $this->createForm(PostType::class, $post);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $file = $post->getPhoto();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $post->setPhoto($filename);
            $post->setCreator($this->getUser());
            $post->setPostdate(new \DateTime('now'));

            $em->persist($post);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('list_post');
        }
        return $this->render('@TutorialBlog/Post/add.html.twig', array(
            "Form"=> $form->createView()
        ));
    }

public function listpostAction(Request $request)
{

$em=$this->getDoctrine()->getManager();
$posts=$em->getRepository('AppBundle:Post')->findAll();
    return $this->render('@TutorialBlog/Post/list.html.twig', array(
        "posts" =>$posts
    ));

}
public function updatepostAction(Request $request, $id)
{
    $em=$this->getDoctrine()->getManager();
    $p= $em->getRepository('AppBundle:Post')->find($id);
    $form=$this->createForm(PostType::class,$p);
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
        return $this->redirectToRoute('list_post');

    }
    return $this->render('@TutorialBlog/Post/update.html.twig', array(
        "form"=> $form->createView()
    ));
}

public function deletepostAction(Request $request)
{
    $id = $request->get('id');
    $em= $this->getDoctrine()->getManager();
    $Post=$em->getRepository('AppBundle:Post')->find($id);
    $em->remove($Post);
    $em->flush();
    return $this->redirectToRoute('@TutorialBlog/Post/list.html.twig');
}
public function showdetailedAction($id)
{
    $em= $this->getDoctrine()->getManager();
    $p=$em->getRepository('AppBundle:Post')->find($id);
    return $this->render('@TutorialBlog/Post/detailedpost.html.twig', array(
        'title'=>$p->getTitle(),
        'date'=>$p->getPostdate(),
        'photo'=>$p->getPhoto(),
        'descripion'=>$p->getDescription(),
        'posts'=>$p,
        'comments'=>$p,
        'id'=>$p->getId()
    ));
}

    public function searchpostAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $posts =  $em->getRepository('AppBundle:Post')->findEntitiesByString($requestString);
        if(!$posts) {
            $result['posts']['error'] = "Post Not found :( ";
        } else {
            $result['posts'] = $this->getRealEntities2($posts);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities2($posts){
        foreach ($posts as $posts){
            $realEntities2[$posts->getId()] = array($posts->getPhoto(),$posts->getTitle());

        }
        return $realEntities2;
    }
    public function addCommentAction(Request $request, UserInterface $user)
    {

        $ref = $request->headers->get('referer');

        $post = $this->getDoctrine()
            ->getRepository(Post::class)
            ->findPostByid($request->request->get('post_id'));

        $comment = new Postcomment();

        $comment->setUser($user);
        $comment->setPost($post);
        $comment->setContent($request->request->get('comment'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($comment);
        $em->flush();

        $this->addFlash(
            'info', 'Comment published !.'
        );

        return $this->redirect($ref);

    }

public function deleteCommentAction(Request $request)
{
    $id = $request->get('id');
    $em= $this->getDoctrine()->getManager();
    $comment=$em->getRepository('AppBundle:Postcomment')->find($id);
    $em->remove($comment);
    $em->flush();
    return $this->redirectToRoute('@TutorialBlog/Post/list.html.twig');
}
public function listadAction()
    {
        //Créer une instance de l'Entity manager
        $em = $this->getDoctrine()->getManager();

        $posts = $em->getRepository("AppBundle:Post")
            ->findAll();
        return $this->render('@TutorialBlog/Admin/list.html.twig'
            ,array(
                "posts"=>$posts

            ));
    }
    public function deleteadanAction(Request $request ){
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $post = $em
            ->getRepository("AppBundle:Post")
            ->find($id);
        $em->remove($post);
        $em->flush();
        return $this->redirectToRoute('post_admin');
    }
    /**
     * @param Post $post
     * @param ObjectManager $manager
     * @param PostLikeRepository $repository
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     */
//liker ou disliker un article
    public function likeAction(
        Post $post,  ObjectManager $objectManager
        ,PostLikeRepository $postLikeRepository

    ): response{


        $user = $this->getUser();
        if (!$user) return $this->json([
            'code' => 403,
            'message' => 'Unauthorized'
        ], 403);
        if ($post->isLikeByUser($user)) {
            $like = $postLikeRepository->findOneBy([
                'post' => $post,
                'user' => $user
            ]);
            $objectManager->remove($like);
            $objectManager->flush();
            return $this->json([
                'code' => 200,
                'message' => 'Like supprimeé',
                'likes' => $postLikeRepository->count(['post' => $post])
            ], 200);

        }
        $like = new PostLike();
        $like->setPost($post);
        $like->setUser($user);
        $objectManager->persist($like);
        $objectManager->flush();
        return $this->json([
            'code' => 200,
            'message' => 'Like bien ajouté',
            'likes' => $postLikeRepository->count(['post' => $post])
        ], 200);

    }
Public function findByLikesAction()
{
    //Créer une instance de l'Entity manager
    $em = $this->getDoctrine()->getManager();

    $posts = $em->getRepository("AppBundle:Post")
        ->findAll();
    return $this->render('@TutorialBlog/Post/top.html.twig'
        ,array(
            "posts"=>$posts

        ));
}
    public function triAction(Request $request)
    {
        //Créer une instance de l'Entity manager
        $em = $this->getDoctrine()->getManager();
             $dql   = "SELECT a FROM AppBundle:Post a";
               $query = $em->createQuery($dql);
        /*
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate(
            $query,
            $request->query->getInt('page', 1), /*page number*/
            $request->query->getInt('limit', 6) /*limit per page*/
        );
        return $this->render('@TutorialBlog/Admin/tri.html.twig'
            ,array(
                "posts"=>$result

            ));
    }
    public function listAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $repository=$em->getRepository('AppBundle:Post');
$posts=$repository->finddatePosts();
        return $this->render('@TutorialBlog/Post/listD.html.twig', array(
            "posts" =>$posts
        ));

    }
    public function lisstAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $repository=$em->getRepository('AppBundle:Post');
        $posts=$repository->top();
        return $this->render('@TutorialBlog/Post/listP.html.twig', array(
            "posts" =>$posts
        ));

    }
}