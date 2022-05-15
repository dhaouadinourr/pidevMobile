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
import com.esprit.entity.Equipement;
import com.esprit.entity.Offre;
import com.esprit.service.EquipementService;
import com.esprit.service.OffreService;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author 21628
 */
public class EquipementFront extends Form{


       String file ;
          Resources theme;


    EquipementService es = new EquipementService();
    OffreService os = new OffreService();
     public EquipementFront(Form previous) throws IOException {
                super("Equipement", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("LES Equipement");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilClientForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
 
              for(Equipement c: es.getAllEquipement()){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Equipement e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label titre=new Label(e.getNom());
          Label libelle_titre = new Label("LIBELLE");
          libelle_titre.setUIID("type1");


        Label lieu=new Label(e.getDescription());
        Label libelle_lieu = new Label("Descrption");
          libelle_lieu.setUIID("type1");
    
         Label prix=new Label(String.valueOf(e.getPrix()));
        Label libelle_prix = new Label("Descrption");
         libelle_prix.setUIID("type1");

  
  
        String image = new String("http://localhost/Upload/");
            EncodedImage enc = EncodedImage.create("/sep.jpg");
            Image im = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       


        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);

        cn2.add(libelle_prix).add(prix);
        
        cn2.add(im);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      

        return cn1;
                
    }
    
   
    
}
