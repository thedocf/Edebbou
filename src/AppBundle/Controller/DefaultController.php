<?php

namespace AppBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class DefaultController extends Controller
{
    /**
     * @Route("/", name="homepage")
     */
    public function indexAction(Request $request)
    {

        $authChecker = $this->container->get('security.authorization_checker');
        if($authChecker->isGranted('ROLE_ADMIN'))
        {
            return $this->redirectToRoute('show2');
        }
        else if ($authChecker->isGranted('ROLE_USER'))
        {
            return $this->redirectToRoute('fronthome');
        }
        return $this->render('baseok.html.twig');
    }
}
