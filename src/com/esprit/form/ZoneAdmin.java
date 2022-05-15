/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.entity.Evenement;
import com.esprit.entity.Zone;
import com.esprit.service.EvenementService;
import com.esprit.service.ZoneService;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author 21628
 */
public class ZoneAdmin extends Form{
       
       
                 String file ;
          Resources theme;


    ZoneService es = new ZoneService();
    
     public ZoneAdmin(Form previous) throws IOException {
                super("Zone", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("LES Zone");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilAdmin().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Zone", null, ev->{
        Form addEvent = new Form("Ajouter Zone",BoxLayout.y());
            Label AJOUT = new Label("ADD Zone");
            AJOUT.setUIID("login");
            addEvent.add(AJOUT);
            TextField CENTRE = new TextField("", "Nom", 20, TextArea.TEXT_CURSOR);
           CENTRE.setUIID("txtn");
;
        TextField DISCRIPTION = new TextField("", "Discription", 20, TextArea.TEXT_CURSOR);
         DISCRIPTION.setUIID("txtn");

            TextField REGION = new TextField("", "Region", 20, TextArea.TEXT_CURSOR);
           REGION.setUIID("txtn");
;
        TextField DELEGATION = new TextField("", "Delegation", 20, TextArea.TEXT_CURSOR);
         DISCRIPTION.setUIID("txtn");
     
        Button save = new Button("Ajouter");
        save.setUIID("vtnvalid");
        addEvent.add("Centre : ").add(CENTRE);
        addEvent.add("Discription : ").add(DISCRIPTION);
        addEvent.add("Region : ").add(REGION);
        addEvent.add("Delegation : ").add(DELEGATION);
        addEvent.add(save);
        
    
        save.addActionListener(l
                                -> {

                            if (CENTRE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Centre ", "OK", null);

                           } else if (DISCRIPTION.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Discription ", "OK", null);

                             
                           } else if (DELEGATION.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Delegation ", "OK", null);

                            
                           } else if (REGION.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de REgion ", "OK", null);

                            } 
                             else {
                           
                                try {

                                    
                                    Zone e = new Zone(DISCRIPTION.getText(), REGION.getText(), CENTRE.getText(), DELEGATION.getText());

                                    if (es.addZone(e) == true) {
                                        Dialog.show("Ajouter Zone", "Ajouter Zone aves success ", "OK", null);
                                         new ZoneAdmin(this).show();
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                                } catch (IOException ex) {
                                }

                            }

                        }
                        );
          addEvent.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
        
        addEvent.show();
 });
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


        



     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
       Modifier.setUIID("vtnvalid");
         Supprimer.setUIID("vtnvalid");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Evenement", BoxLayout.y());
         
           Label modif = new Label("EDIT Accessoire");
                modif.setUIID("login");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getNoncentre());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getDescription());
         lieu2.setMinimumElementsShownInPopup(1);
         lieu2.setUIID("txtn");

         AutoCompleteTextField region2 =  new AutoCompleteTextField(e.getRegion());
          
         region2.setMinimumElementsShownInPopup(1);
         region2.setUIID("txtn");
         AutoCompleteTextField delegation2=  new AutoCompleteTextField(e.getDelegation());
         delegation2.setMinimumElementsShownInPopup(1);
         delegation2.setUIID("txtn");


        
          Label lib_titre = new Label("Centre");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);


              Label lib_Lieu = new Label("Description");
                lib_Lieu.setUIID("pass");
         fmodifier.add(lib_Lieu).add(lieu2);


              Label lib_Description = new Label("Delegation");
                lib_Description.setUIID("pass");

         fmodifier.add(lib_Description).add(delegation2);

              Label lib_region = new Label("Region");
                lib_Description.setUIID("pass");

         fmodifier.add(lib_region).add(region2);
    

         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             try {

                 Zone ze = new Zone(e.getId(), lieu2.getText(), region2.getText(), titre2.getText(), delegation2.getText());
                 
                 
                                    if ( es.ModifierZone(ze) == true) {
                                        Dialog.show("Modifier Zone", "Zone Modifier aves success ", "OK", null);
                                        
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                 
                 new ZoneAdmin(this).show();
             } catch (IOException ex) {
             }
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (es.DeleteZone(e.getId())) {
                                        Dialog.show("Supprimer Zone", "Zone Supprimer aves success ", "OK", null);
                                        
                                             try {
                new ZoneAdmin(this).show();
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
               Label lib_titre = new Label("CNTRE");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Descrption");
                lib_Lieu.setUIID("pass");
             Label lib_region = new Label("Region");
                lib_titre.setUIID("pass");
      
              Label lib_deligation = new Label("Delegation");
                lib_Lieu.setUIID("pass");

 
                
            f2.add(lib_titre).add(titrem).add(lib_Lieu).add(discriptionm).add(lib_region).add(regionm).add(lib_deligation).add(delegationm).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   


}
