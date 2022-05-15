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
import com.esprit.service.EvenementService;
import java.io.IOException;
import java.util.Date;


/**
 *
 * @author 21628
 */
public class EvenementUser extends Form{
    
          String file ;
          Resources theme;


    EvenementService es = new EvenementService();
    
     public EvenementUser(Form previous) throws IOException {
                super("Evenements", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("LES Evenements");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilClientForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
              for(Evenement c: es.getAllEvents()){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Evenement e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label titre=new Label(e.getNom());
          Label libelle_titre = new Label("LIBELLE");
          libelle_titre.setUIID("type1");


        Label lieu=new Label(e.getDescrption());
        Label libelle_lieu = new Label("Descrption");
          libelle_lieu.setUIID("type1");

        Label dd=new Label(String.valueOf(e.getDated()));
        Label libelle_dd = new Label("Date debut");
          libelle_dd.setUIID("type1");
        Label df=new Label(String.valueOf(e.getDatef()));
        Label libelle_df = new Label("Date Fin");
          libelle_df.setUIID("type1");
        String image = new String("http://localhost/Upload/");
            EncodedImage enc = EncodedImage.create("/sep.jpg");
            Image im = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       


        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);

        cn2.add(libelle_dd).add(dd);
        cn2.add(libelle_df).add(df);
              cn2.add(im);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);

        return cn1;
                
    }
    
   
     
     
}
