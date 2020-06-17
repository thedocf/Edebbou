/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Fos_user;
import com.mycompany.myapp.services.ServiceUser;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignUp extends com.codename1.ui.Form {
  Form current;
    static Fos_user currentUser = new Fos_user();

    public Fos_user getcurrentUser() {
        return currentUser;
    }

    public void setcurrentUser(Fos_user u) {
        currentUser.setId(u.getId());
        currentUser.setUsername(u.getUsername());
        currentUser.setEmail(u.getEmail());
          currentUser.setRoles(u.getRoles());
    }
    public SignUp() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignUp(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
    }

//////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    protected com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    protected com.codename1.ui.TextField username  = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField email = new com.codename1.ui.TextField();
     protected com.codename1.ui.TextComponentPassword password = new com.codename1.ui.TextComponentPassword();
     
     protected com.codename1.ui.TextComponentPassword confirmPassword= new com.codename1.ui.TextComponentPassword();
    protected com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        username.addActionListener(callback);
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == username) {
                onText_Field_1ActionEvent(ev);
            }
            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
             
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Sign In");
        setName("SignInForm");
        gui_Container_1.setScrollableY(true);
                gui_Container_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_1.setName("Container_1");
       
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Label_1.setUIID("CenterLabel");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("signup.png"));
                gui_Component_Group_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
                gui_Button_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_2.setName("Button_2");
        
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        username.setText("username");
                username.setInlineStylesTheme(resourceObjectInstance);
        username.setName("username");
        email.setText("email");
                email.setInlineStylesTheme(resourceObjectInstance);
        email.setName("email");
        password.text("pwd");
                password.setInlineStylesTheme(resourceObjectInstance);
        password.setName("password");
        confirmPassword.text("pwd");
          confirmPassword.setInlineStylesTheme(resourceObjectInstance);
        confirmPassword.setName("password");
        gui_Component_Group_1.addComponent(username);
        gui_Component_Group_1.addComponent(email);
        
        gui_Component_Group_1.addComponent(password);
        gui_Component_Group_1.addComponent(confirmPassword);
        gui_Container_1.addComponent(gui_Button_2);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
         ServiceUser ser = new ServiceUser();
            if (username.getText().isEmpty()) {
                Dialog dlg = new Dialog(" saisir votre username ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (email.getText().isEmpty()) {

                Dialog dlg = new Dialog(" saisir votre adresse mail ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (password.getText().isEmpty()) {

                Dialog dlg = new Dialog("saisir votre mot de passe ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            }

            Fos_user t = new Fos_user (username.getText(), email.getText(), password.getText());
            System.out.println("a" + t.toString());

            ArrayList<Fos_user > listTasks = new ArrayList<>();

            ser.ajoutTask(t);
            Button ok = new Button(new Command("OK"));

            Dialog dlg = new Dialog("Votre compte a ete cree" + " " + t.getUsername());

            TextArea taa = new TextArea("Bienvenue  ");
            taa.setEditable(false);
            taa.setUIID("DialogBody");
            taa.getAllStyles().setFgColor(0);
            dlg.add(taa);

            ok.getAllStyles().setBorder(Border.createEmpty());
            ok.getAllStyles().setFgColor(0);
            ok.getUnselectedStyle().setFgColor(100000);

            dlg.add(ok);
            dlg.showDialog(); 
    }
    
 public void onText_Field_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        
        
    }
  public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
       
    }
}