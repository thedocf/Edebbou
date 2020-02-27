<?php

namespace test\Bundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use test\Bundle\Entity\Category;

class ProductType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')->add('prix')->add('qte')->add('description')
            ->add('dateExpiration')

            ->add('Category', EntityType::class, [
                // looks for choices from this entity

                'required' => true,
                'class' => Category::class,

                // uses the User.username property as the visible option string
                'choice_label' => 'label',


            ])
            ->add('image',FileType::class,array('data_class'=>null));


    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'test\Bundle\Entity\Product'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'test_bundle_product';
    }


}
