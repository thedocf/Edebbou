/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionEvent.Type;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.services.FournisseurService;
import com.mycompany.myapp.services.UploadServices;
import java.io.IOException;

/**
 *
 * @author M-YAHYAOUI
 */
public class AddFournisseurForm extends BaseForm {

    FournisseurService ad = new FournisseurService();

    public AddFournisseurForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public AddFournisseurForm(com.codename1.ui.util.Resources resourceObjectInstance) {

        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajout Fournisseur ", "Title")
                )
        );

        installSidemenu(resourceObjectInstance);

        //getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("contact-b.png"), e -> {
        //});

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("SignUpCenter");

        Container row1 = new Container(new GridLayout(1, 2));

        TextField depot_id = new TextField();
        depot_id.setUIID("SignUpField");
        depot_id.setHint("depot_id");
        depot_id.getHintLabel().setUIID("SignupFieldHint");

        TextField nom = new TextField();
        nom.setUIID("SignUpField");
        nom.setHint("nom");
        nom.getHintLabel().setUIID("SignupFieldHint");

        TextField prenom = new TextField();
        prenom.setUIID("SignUpField");
        prenom.setHint("prenom");
        prenom.getHintLabel().setUIID("SignupFieldHint");
row1.add(new Label("nom")).add(nom);
this.add(row1);
        //  row1.addComponent(depot_id);
        //  row1.addComponent(nom);
        //  row1.addComponent(prenom);
        //  center.addComponent(row1);
        //  center.setScrollableY(true);
         Container row3 = new Container(new GridLayout(1, 2));
        row3.add(new Label("prenom :")).add(prenom);
this.add(row3);
        
        Container row2 = new Container(new GridLayout(1, 2));

        TextField numTel = new TextField();
        numTel.setUIID("SignUpField");
        numTel.setHint("numTel");
        
        row2.add(new Label("numéro Tel :")).addComponent(numTel);
        numTel.getHintLabel().setUIID("SignupFieldHint");

        TextField disponible = new TextField();
        disponible.setUIID("SignUpField");
        Container row4 = new Container(new GridLayout(1, 2));
        row4.add(new Label("disponible :")).addComponent(disponible);
        disponible.setHint("disponible");
        disponible.getHintLabel().setUIID("SignupFieldHint");
this.add(row4);
 Container row5 = new Container(new GridLayout(1, 2));
  row5.add(new Label("depot ID :")).addComponent(depot_id);
        Button Ajouter = new Button("Ajouter Fournisseur");

        Container btnC = new Container();
        btnC.add(Ajouter);
        this.add(row2);
        this.add(row5);
        this.add(btnC);

        Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //  System.out.println(FilenameInserver);

                ad.addFournisseur(nom.getText(), prenom.getText(), Integer.parseInt(numTel.getText()), Integer.parseInt(disponible.getText()),Integer.parseInt(depot_id.getText()));
            }
        });
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);

    }
}
