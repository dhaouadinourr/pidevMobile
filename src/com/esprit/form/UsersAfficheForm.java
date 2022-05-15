package com.esprit.form;


import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entity.Client;
import com.esprit.entity.Zone;
import com.esprit.form.AcceuilAdmin;
import com.esprit.form.ZoneAdmin;
import com.esprit.service.ClientService;
import com.esprit.service.ZoneService;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 21628
 */
public class UsersAfficheForm  extends Form{


       
                 String file ;
          Resources theme;


    ClientService es = new ClientService();
    
     public UsersAfficheForm() throws IOException {
                super("Users", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("Users");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilAdmin().showBack();
        });

              for(Client c: es.getAllusers()){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Client e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label Cin=new Label(e.getCin());
          Label libelle_cin = new Label("Cin");


        Label NOM=new Label(e.getNom());
        Label libelle_nOM = new Label("Nom");

        Label PRENOM=new Label(e.getPrenom());
          Label libelle_PRENOM = new Label("Prenom");


        Label email=new Label(e.getEmail());
        Label libelle_email = new Label("Region");

            String imaga1 = new String("http://localhost/Upload/");
            try {
                EncodedImage enc1 = EncodedImage.create("/sep.jpg");

            } catch (IOException ex) {
            }            
               EncodedImage enc = EncodedImage.create("/sep.jpg");

               Image im1 = URLImage.createToStorage(enc, "local"+e.getImage() , imaga1+e.getImage());



        Button btn=new Button("Details");

        cn2.add(libelle_cin).add(Cin);
        
        cn2.add(libelle_nOM).add(PRENOM);

        cn2.add(libelle_email).add(email);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label cin1=new Label(e.getCin());

        Label nom2=new Label(e.getNom());
        Label prenom2=new Label(e.getPrenom());

        Label adresse2=new Label(e.getAdresse());

        Image im2 = URLImage.createToStorage(enc, "local"+e.getImage() , imaga1+e.getImage());

        



     Button Supprimer = new Button("Supprimer");

       Supprimer.addActionListener(sup ->  
       
       {
           
            if (es.DeleteClient(e.getCin())) {
                                        Dialog.show("Supprimer Client", "Client Supprimer aves success ", "OK", null);
                                        
                                             try {
                new UsersAfficheForm().show();
            } catch (IOException ex) {
            }
                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar("back",null, evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Nom");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Prenom");
                lib_Lieu.setUIID("pass");
             Label lib_region = new Label("Cin");
                lib_titre.setUIID("pass");
      
              Label lib_deligation = new Label("Adresse");
                lib_Lieu.setUIID("pass");

 
                
            f2.add(im2).add(lib_titre).add(nom2).add(lib_Lieu).add(prenom2).add(lib_region).add(cin1).add(lib_deligation).add(adresse2).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
    
}
