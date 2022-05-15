/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entity.Commande;
import com.esprit.service.CommandeService;
import com.esprit.utils.Session;
import java.io.IOException;

/**
 *
 * @author 21628
 */
public class CommandeAdmin extends Form{




     
                 String file ;
          Resources theme;


    CommandeService es = new CommandeService();
    
     public CommandeAdmin(Form previous) throws IOException {
                super("Commande", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("LES Commande");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
try{
            new CommandeAdmin(this).showBack();
}catch(IOException ex){}
        });
    
     this.getToolbar().setUIID("tb");
      for(Commande c: es.getAllCommande()){
     
          this.add(addItem(c));    
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Commande e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label mode=new Label(e.getMode());
          Label libelle_mode = new Label("Mode");


        Label codePostal=new Label(String.valueOf(e.getCodePostal()));
        Label CodePostal = new Label("code Postal");

        Label numTel=new Label(String.valueOf(e.getNum()));
          Label libelle_numTel = new Label("numTel");





        cn2.add(libelle_mode).add(mode);
        
        cn2.add(CodePostal).add(codePostal);

        cn2.add(libelle_numTel).add(numTel);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      

        return cn1;
                
    }

    
}
