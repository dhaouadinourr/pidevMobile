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
import com.esprit.entity.Offre;
import com.esprit.service.EvenementService;
import com.esprit.service.OffreService;
import java.io.IOException;
import java.util.Date;


/**
 *
 * @author 21628
 */
public class OffreAdmin extends Form{
    
          String file ;
          Resources theme;


    OffreService es = new OffreService();
    
     public OffreAdmin(Form previous) throws IOException {
                super("Offre", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("Les Offres");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilAdmin().showBack();
        });
    
            this.getToolbar();

              for(Offre c: es.getAllOffre()){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Offre e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label POURCENTAGE=new Label(e.getPourcentage());
          Label libelle_pourcentage = new Label("Pourventage :");


  

        Label dd=new Label(String.valueOf(e.getDated()));
        Label libelle_dd = new Label("Date debut");
          libelle_dd.setUIID("type1");
        Label df=new Label(String.valueOf(e.getDatef()));
        Label libelle_df = new Label("Date Fin");
          libelle_df.setUIID("type1");

        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_pourcentage).add(POURCENTAGE);
        
        cn2.add(libelle_dd).add(dd);
        cn2.add(libelle_df).add(df);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getPourcentage());

        Label ddm=new Label(String.valueOf(e.getDated()));
        Label dfm=new Label(String.valueOf(e.getDatef()));



     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
       Modifier.setUIID("vtnvalid");
         Supprimer.setUIID("vtnvalid");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Offre", BoxLayout.y());
         
           Label modif = new Label("EDIT Offre");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getPourcentage());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");



         Picker datedebut = new Picker();
         Picker dateFin = new Picker();
          Label lib_titre = new Label("Titre");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);
          
              
          Label lib_dd = new Label("Date Debut : ");
                lib_dd.setUIID("pass");
         fmodifier.add(lib_dd).add(datedebut);
          Label lib_df = new Label("Date Fin : ");
                lib_df.setUIID("pass");
         fmodifier.add(lib_df).add(dateFin);
         datedebut.setDate(e.getDated());
         dateFin.setDate(e.getDatef());
         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             try {
                 DateFormat dde = new SimpleDateFormat("yyyy-MM-dd");
                 Date ddebutt = datedebut.getDate();
                 String dateddd = dde.format(ddebutt);
                 
                 DateFormat dfi = new SimpleDateFormat("yyyy-MM-dd");
                 Date dfinii = dateFin.getDate();
                 String datefff = dfi.format(dfinii);
                 
                 Date datedebute = new Date();
                 Date datefinitt = new Date();
                 
                 datedebute = dfi.parse(dateddd);
                 datefinitt = dfi.parse(datefff);
                 Offre o = new Offre(e.getId(), e.getIdeq(), titre2.getText(), datedebute, datefinitt);
                 
                 
                                    if ( es.ModifierOffre(o) == true) {
                                        Dialog.show("Modifier Offre", "Offre Modifier aves success ", "OK", null);
                                        
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                 
                 new EvenementAdmin(this).show();
             } catch (ParseException ex) {

             } catch (IOException ex) {
             }
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (es.DeleteOffre(e.getId())) {
                                        Dialog.show("Supprimer Offre", "Offre Supprimer aves success ", "OK", null);
                                        
                                             try {
                new EvenementAdmin(this).show();
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
               Label lib_titre = new Label("Titre");
                lib_titre.setUIID("pass");
      
            

 

          Label lib_dd = new Label("Date Debut : ");
                lib_dd.setUIID("pass");
       
          Label lib_df = new Label("Date Fin : ");
                lib_df.setUIID("pass");
                
            f2.add(lib_titre).add(titrem).add(lib_dd).add(ddm).add(lib_df).add(dfm).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   
     
     
}
