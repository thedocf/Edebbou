<?php

namespace FournisseurBundle\Form;

use FournisseurBundle\Entity\Depot;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Gregwar\CaptchaBundle\Type\CaptchaType;

class FournisseurType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')
                ->add('prenom')
                ->add('numTel')
                ->add('disponible',ChoiceType::class, [
            'choices'  => [
                'Oui' => true,
                'Non' => false,
            ]])

            ->add('Valider',SubmitType::class)
            ->add('captcha', CaptchaType::class, array(
                'width' => 400,
                'height' => 100,
                'length' => 3,
                'background_color' => [255, 255, 255] ,
            ));

    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'FournisseurBundle\Entity\Fournisseur'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'fournisseurbundle_fournisseur';
    }


}
