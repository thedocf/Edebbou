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
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Product2;
import com.mycompany.myapp.services.PanierService;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple details form
 *
 * @author Shai Almog
 */
public class produits extends BaseForm {

    public produits() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public produits(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        setToolbar(new Toolbar(true));
        installSidemenu(resourceObjectInstance);
        //Form last =  Display.getInstance().getCurrent();
        //getToolbar().setBackCommand("", e -> last.show());
        setTitle("Produits");
       // PanierService.panier =  new HashMap<Product2, Integer>();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
 
       Container hi = new Container(BoxLayout.y());
        Label ll = new Label("  ");
        Label ll1 = new Label("  ");
        Label ll2 = new Label("  ");
        hi.add(ll);
        hi.add(ll1);
        hi.add(ll2);
         ProduitService ps = new ProduitService();
        List<Product2> lp = new ArrayList<Product2>();
        try {
            lp = ps.getListProducts();
        } catch (ParseException ex) {
           // Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Product2 p : lp) { 		      
           //System.out.println(p.getNom());
           
                MultiButton b = new MultiButton(p.getNom());
           //Button b = new Button(p.getNom());
           b.addActionListener(l->{
               PanierService pss = new PanierService();
         //      pss.ajouterProduit(p,1);
               ToastBar.showMessage( p.getNom()+" ajouté", FontImage.MATERIAL_THUMB_UP);


           });
           hi.add(b);
      }
        add(hi);
        getToolbar().addCommandToRightBar("Panier", null, evv -> {
            new panier().show();
        });
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          


//-- DON'T EDIT ABOVE THIS LINE!!!
}
