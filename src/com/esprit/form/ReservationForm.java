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
import com.esprit.entity.Reservation;
import com.esprit.entity.Zone;
import com.esprit.service.ServiceReservation;
import com.esprit.service.ZoneService;
import com.esprit.utils.Session;
import java.io.IOException;

/**
 *
 * @author 21628
 */
public class ReservationForm extends Form {


                   String file ;
          Resources theme;


    ServiceReservation es = new ServiceReservation();
    
     public ReservationForm(Form previous) throws IOException {
                super("Reservation", BoxLayout.y());
        this.getToolbar();
         Label logi = new Label("Mes Reservation");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilClientForm().showBack();
        });
    

              for(Reservation c: es.getAllResercation( Session.get().getCin())){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Reservation e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label cnetre=new Label(String.valueOf(e.getNbrPlace()));
          Label libelle_titre = new Label("Nombre de Place :");


        Label description=new Label(String.valueOf(e.getDatec()));
        Label libelle_lieu = new Label("Date");

    



        Button btn=new Button("Details");

        cn2.add(libelle_titre).add(cnetre);
        
        cn2.add(libelle_lieu).add(description);

 
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(String.valueOf(e.getNbrPlace()));

        Label discriptionm=new Label(String.valueOf(e.getDatec()));

        



     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");

     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Reservation", BoxLayout.y());
         
       
         fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(String.valueOf(e.getNbrPlace()));
          
    

        
          Label lib_titre = new Label("Nombre De Place");
          fmodifier.add(lib_titre).add(titre2);



         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             try {

                 Reservation ze = new Reservation(e.getId(), Integer.parseInt(titre2.getText()), 0, null, null);
                 
                 
                                    if ( es.ModifierReservation(ze) == true) {
                                        Dialog.show("Modifier Reservation", "Zone Modifier aves success ", "OK", null);
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                 
                                        new ReservationForm(this).show();
             } catch (IOException ex) {
             }
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (es.DeleteReservation(e.getId())) {
                                        Dialog.show("Supprimer Reservation", "Reservation Supprimer aves success ", "OK", null);
                                        
                                             try {
                new ReservationForm(this).show();
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
               Label lib_titre = new Label("Nombre de Place");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Date");
                lib_Lieu.setUIID("pass");


 
                
            f2.add(lib_titre).add(titrem).add(lib_Lieu).add(discriptionm).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   


}

