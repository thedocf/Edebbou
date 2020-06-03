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


import com.codename1.components.MultiButton;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.CommandeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple details form
 *
 * @author Shai Almog
 */
public class commandes extends com.codename1.ui.Form {

    public commandes() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public commandes(com.codename1.ui.util.Resources resourceObjectInstance) {
        setToolbar(new Toolbar(true));;
        setTitle("Commandes");
        Container c = new Container(BoxLayout.y());
                    //Form myCommandes = new Form("Mes Commandes", BoxLayout.y());
            CommandeService cs = new CommandeService();
            List<Commande> lc = new ArrayList<Commande>();
                try {
                    lc = cs.getListProducts();
                } catch (ParseException ex) {
                   // Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            for (Commande entry : lc) {
                MultiButton bb = new MultiButton(""+entry.getId());
                bb.setTextLine2("Date : "+entry.getDate());
                bb.setTextLine3("Etat : "+entry.getEtatLivraison());
            //Label ll = new Label("'"+entry.getId()+"'  Date : '"+entry.getDate()+"' etat : '"+entry.getEtatLivraison());
            c.add(bb);
        }Button p = new Button("Produits");
            p.addActionListener(ls->{
                new produits(resourceObjectInstance).show();
            });
            
                // Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

   // Message message = Message.creator(new PhoneNumber("+21653853529"),new PhoneNumber("+12074264959"), "This is the ship that made the Kessel Run in fourteen parsecs?").create();
       
        

    c.add(p);
            
         add(c);
            
            
    }


}
