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
        $authChecker = $this->get('security.authorization_checker');
        if($authChecker->isGranted('ROLE_ADMIN'))
        {
            return $this->render('default/user.html.twig');
        }
        else if ($authChecker->isGranted('ROLE_USER'))
        {
            return $this->render('default/client.html.twig');
        }
        return $this->render('base.html.twig');
    }
}
