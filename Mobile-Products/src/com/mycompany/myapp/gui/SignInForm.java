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

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.myapp.entities.Fos_user;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.gui.SignUp;
import com.codename1.ui.util.Resources;
/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignInForm extends com.codename1.ui.Form {
  Form current;
    static Fos_user currentUser = new Fos_user();

   
    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
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
    protected com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    protected com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    protected com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    
    protected com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Text_Field_1.addActionListener(callback);
        gui_Button_2.addActionListener(callback);
        
        gui_Button_1.addActionListener(callback);
        
        gui_Button_4.addActionListener(callback);
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

            if(sourceComponent == gui_Text_Field_1) {
                onText_Field_1ActionEvent(ev);
            }
            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
             if(sourceComponent == gui_Button_1) {
                onButton_1ActionEvent(ev);
            }
             if(sourceComponent == gui_Button_4) {
                onButton_4ActionEvent(ev);
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
        gui_Button_1.setText("Create New Account");
        gui_Button_1.setUIID("CenterLabel");
                gui_Button_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_1.setName("Button_1");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Label_1.setUIID("CenterLabel");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
                gui_Component_Group_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
                gui_Button_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_2.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
                gui_Button_3.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_3.setName("Button_3");
         gui_Button_4.setText("Fb login");
        gui_Button_4.setUIID("CenterLabelSmall");
                gui_Button_4.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_4.setName("Fb login");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Text_Field_2.setText("TextField");
                gui_Text_Field_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setText("TextField");
                gui_Text_Field_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        
        gui_Container_1.addComponent(gui_Button_4);
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
         String usr=gui_Text_Field_2.getText();
               String pwd=gui_Text_Field_1.getText();
               try{
               Fos_user s = ServiceUser.getInstance().getConnectedUser(usr, pwd);
               if (gui_Text_Field_2.getText().contentEquals(s.getUsername())){
            
                        SessionManager.setId(s.getId());
//                        SessionManager.se(password.getText());
                        SessionManager.setUserName(s.getUsername());
                        SessionManager.setEmail(s.getEmail());
                        SessionManager.setPhoto("anonimo.jpg");
                     
        new InboxForm().show();
        
                    
                    }
                    
                  
          
        }
               catch (Exception ex) {
                    Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
       
    }
    }
 public void onText_Field_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        
        
    }
  public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
         new SignUp().show();
    }
  public void onButton_4ActionEvent(com.codename1.ui.events.ActionEvent ev) {
                UserForm hi = new UserForm();
        hi.show();
    }
}