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
public class EvenementAdmin extends Form{
    
          String file ;
          Resources theme;


    EvenementService es = new EvenementService();
    
     public EvenementAdmin(Form previous) throws IOException {
                super("Evenements", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("LES Evenements");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilAdmin().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Evenement", null, ev->{
        Form addEvent = new Form("Ajouter Evenement",BoxLayout.y());
Label AJOUT = new Label("ADD Evenements");
            AJOUT.setUIID("login");
            addEvent.add(AJOUT);
            TextField TITRE = new TextField("", "Nom", 20, TextArea.TEXT_CURSOR);
           TITRE.setUIID("txtn");
;
        TextField DISCRIPTION = new TextField("", "Discription", 20, TextArea.TEXT_CURSOR);
         DISCRIPTION.setUIID("txtn");
        Picker  DATEDEBUT = new Picker ();
        DATEDEBUT.setUIID("txtn");
            Picker  DATEFIN = new Picker ();
        DATEFIN.setUIID("txtn");

            Button upload = new Button("Upload Image");
        upload.setUIID("vtnvalid");
        Button save = new Button("Ajouter");
        save.setUIID("vtnvalid");
        addEvent.add("Titre : ").add(TITRE);
        addEvent.add("Discription : ").add(DISCRIPTION);
        addEvent.add("Date De Début : ").add(DATEDEBUT);
        addEvent.add("Date de Fin : ").add(DATEFIN);
        addEvent.add(upload);
        addEvent.add(save);
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1/Upload.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
    
        save.addActionListener(l
                                -> {

                            if (TITRE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);

                           } else if (DISCRIPTION.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } 
                             else {
                           
                                try {
                                    System.out.println("datedd"+DATEDEBUT.getDate());
                                    System.out.println("datefff"+DATEFIN.getDate());
                                    
                                    DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                                    Date ddebut = DATEDEBUT.getDate();
                                    String dated = dd.format(ddebut);
                                    
                                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    Date dfin = DATEFIN.getDate();
                                    String datef = df.format(dfin);
                                    
                                    Date datedebut = new Date();
                                    Date datefin = new Date();
                                    datedebut = df.parse(dated);
                                    datefin = df.parse(datef);
                                    
                                    Evenement e = new Evenement(TITRE.getText(),DISCRIPTION.getText() , file, datefin, datefin);
                                                     System.out.println("forms.addEvet.addItem()"+e);

                                    if (es.addEvent(e) == true) {
                                        Dialog.show("Ajouter Evenemet", "Ajouter Evenement aves success ", "OK", null);
                                         new EvenementAdmin(this).show();
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                                } catch (ParseException ex) {
                                       System.out.println("hekllllo");
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
       

        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);

        cn2.add(libelle_dd).add(dd);
        cn2.add(libelle_df).add(df);
              cn2.add(im);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getNom());

        Label discriptionm=new Label(e.getDescrption());
        Label ddm=new Label(String.valueOf(e.getDated()));
        Label dfm=new Label(String.valueOf(e.getDatef()));
        
        String imaga1 = new String("http://localhost/Upload/");
            try {
                EncodedImage enc1 = EncodedImage.create("/sep.jpg");
            } catch (IOException ex) {
            }
        Image im1 = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       


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
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getNom());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getDescrption());
         lieu2.setMinimumElementsShownInPopup(1);
         lieu2.setUIID("txtn");


         Picker datedebut = new Picker();
         Picker dateFin = new Picker();
          Label lib_titre = new Label("Titre");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);
              Label lib_Lieu = new Label("Lieu");
                lib_Lieu.setUIID("pass");
         fmodifier.add(lib_Lieu).add(lieu2);
              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");

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
                 Evenement events = new Evenement(e.getId(), titre2.getText(),lieu2.getText(), e.getImage(), datedebute, datefinitt);
                 System.out.println("forms.addEvet.addItem()"+events);
                 
                 
                                    if ( es.ModifierEvent(events) == true) {
                                        Dialog.show("Modifier Evenemet", "Evenement Modifier aves success ", "OK", null);
                                        
                                        
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
           
            if (es.DeleteEvenement(e.getId())) {
                                        Dialog.show("Supprimer Evenemet", "Evenement Supprimer aves success ", "OK", null);
                                        
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
      
              Label lib_Lieu = new Label("Descrption");
                lib_Lieu.setUIID("pass");
   

 

          Label lib_dd = new Label("Date Debut : ");
                lib_dd.setUIID("pass");
       
          Label lib_df = new Label("Date Fin : ");
                lib_df.setUIID("pass");
                
            f2.add(im1).add(lib_titre).add(titrem).add(lib_Lieu).add(discriptionm).add(lib_dd).add(ddm).add(lib_df).add(dfm).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   
     
     
}
