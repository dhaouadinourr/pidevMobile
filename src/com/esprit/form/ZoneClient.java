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
public class ZoneClient extends Form{


                    String file ;
          Resources theme;


    ZoneService es = new ZoneService();
    ServiceReservation sr = new ServiceReservation();
    
     public ZoneClient(Form previous) throws IOException {
            
            super("Zone", BoxLayout.y());
            this.getToolbar();
            Label logi = new Label("LES Zone");
            this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilClientForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");

              for(Zone c: es.getAllZones()){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Zone e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label cnetre=new Label(e.getNoncentre());
          Label libelle_titre = new Label("LIBELLE");
          libelle_titre.setUIID("type1");


        Label description=new Label(e.getDescription());
        Label libelle_lieu = new Label("Descrption");
          libelle_lieu.setUIID("type1");

        Label deligation=new Label(e.getDelegation());
          Label libelle_delegation = new Label("Delegation");
          libelle_titre.setUIID("type1");


        Label region=new Label(e.getDescription());
        Label libelle_region = new Label("Region");
          libelle_lieu.setUIID("type1");



        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_titre).add(cnetre);
        
        cn2.add(libelle_lieu).add(description);

        cn2.add(libelle_region).add(region);
        cn2.add(libelle_delegation).add(deligation);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getNoncentre());

        Label discriptionm=new Label(e.getDescription());
        Label regionm=new Label(e.getRegion());

        Label delegationm=new Label(e.getDelegation());


        



     Button Modifier = new Button("Reserver");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Reservation", BoxLayout.y());
         
           
         fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
      TextField CENTRE = new TextField("", "Nombre De Place", 20, TextArea.TEXT_CURSOR);
;

     
        Button save = new Button("Ajouter");
        fmodifier.add("Nombre de Place : ").add(CENTRE);

        fmodifier.add(save);
        
    
        save.addActionListener(l
                                -> {

                            if (CENTRE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Centre ", "OK", null);

                           }  
                             else {
                           
                                try {

                                    
                                    Reservation r = new Reservation(Integer.parseInt(CENTRE.getText()), e.getId(), null, Session.get().getCin());

                                    if (sr.addReservation(r,e.getId()) == true) {
                                        Dialog.show("Ajouter Reservation", "Ajouter Reservation aves success ", "OK", null);
                                        new ReservationForm(this).show();
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                                } catch (IOException ex) {
                                }

                            }

                        }
                        );
          fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
        
        fmodifier.show();
 });
     
      
               Label lib_titre = new Label("CNTRE");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Descrption");
                lib_Lieu.setUIID("pass");
             Label lib_region = new Label("Region");
                lib_titre.setUIID("pass");
      
              Label lib_deligation = new Label("Delegation");
                lib_Lieu.setUIID("pass");

 
                
            f2.add(lib_titre).add(titrem).add(lib_Lieu).add(discriptionm).add(lib_region).add(regionm).add(lib_deligation).add(delegationm).add(Modifier);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   

    
}
