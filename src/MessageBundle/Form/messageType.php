<?php

namespace MessageBundle\Form;

use MessageBundle\Entity\categ;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class messageType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('category', EntityType::class, [
                // looks for choices from this entity
                'class' => categ::class,
                // uses the User.username property as the visible option string
                'choice_label' => 'label',
            ])
            ->add('title')
             ->add('description', TextareaType::class)
            ->add('photo', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('Submit', SubmitType::class);
    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'MessageBundle\Entity\message'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'messagebundle_message';
    }


}
