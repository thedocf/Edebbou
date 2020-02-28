<?php

namespace AppBundle\Repository;

use Doctrine\ORM\EntityRepository;
use AppBundle\Entity\Post;
use AppBundle\Entity\PostLike;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Symfony\Bridge\Doctrine\RegistryInterface;

class PostLikeRepository extends ServiceEntityRepository
{
    /**
     * PostLikeRepository constructor.
     * @param RegistryInterface $registry
     */
    public function __construct(RegistryInterface $registry)
    {
        parent::__construct($registry, PostLike::class);
    }
    // /**
    //  * @return PostLike[] Returns an array of PostLike objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */
    /*
    public function findOneBySomeField($value): ?PostLike
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function count(array $array)
    {

    }
    public function findtopBylikes()
    {

        $qb = $this->createQueryBuilder('postLike')
            ->select('partial order.{id, orderId} as order, count(post.id) as total_products_in_order')
            ->leftJoin('AppBundle:Product', 'product', 'WITH', 'product.order = order.id')
            ->groupBy('order.id')
            ->orderBy('total_products_in_order', 'DESC')
        ;

        return $qb->getQuery()->execute();
    }

}