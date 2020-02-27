<?php
/**
 * Created by PhpStorm.
 * User: jha
 * Date: 18/02/2020
 * Time: 20:28
 */

namespace VenteBundle\Listener;



use AncaRebeca\FullCalendarBundle\Model\Event;
use AncaRebeca\FullCalendarBundle\Model\FullCalendarEvent;
use AppBundle\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\Security\Core\Security;
use VenteBundle\Entity\Livreur;

class EventListener
{
    /**
     * @var EntityManager
     */
    private $em;
    public function __construct(EntityManagerInterface $em,Security $security)
    {
        $this->em = $em;
        $this->security=$security;
    }

    /**
     * @param CalendarEvent $calendarEvent
     *
     * @return FullCalendarEvent[]
     */
    public function loadData(\AncaRebeca\FullCalendarBundle\Event\CalendarEvent $calendarEvent)
    {
        $startDate = $calendarEvent->getStart();
        $endDate = $calendarEvent->getEnd();
        $filters = $calendarEvent->getFilters();
        if(isset($_GET['user'])) {
            $id = $_GET['user'];


            $Livreur = $this->em->getRepository(Livreur::class)->find($id);
            $plannings = $Livreur->getCommandes();
            foreach ($plannings as $p) {
                $e =  new Event("Livreur : " . $p->getLivreur()->getNom() . " \n Commande : " . $p->getId(), $p->getDate());

                $calendarEvent->addEvent($e);
            }
        }

        /*        $event  = new Event();
                $event->getColor()*/

        /*$event = new Event('Event Title 1', new \DateTime());

        $calendarEvent->addEvent($event);*/
        // $calendarEvent->addEvent(new MyCustomEvent('Event Title 2', new \DateTime()));
    }
}