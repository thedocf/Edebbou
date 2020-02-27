<?php

namespace FournisseurBundle\Form;

use blackknight467\StarRatingBundle\Form\RatingType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Gregwar\CaptchaBundle\Type\CaptchaType;


class DepotType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('entreprise')
                ->add('surface')
                ->add('ville')
                ->add('capacite')
                ->add('description')
                ->add('image',FileType::class,array('label'=>'Insert image','data_class'=>null))
                ->add('captcha', CaptchaType::class, array(
                    'width' => 400,
                    'height' => 100,
                    'length' => 3,
                    'background_color' => [255, 255, 255] ,
                ))
                ->add('Valider',SubmitType::class);
                //->add('captcha', CaptchaType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'FournisseurBundle\Entity\Depot'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'fournisseurbundle_depot';
    }


}
