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
import com.esprit.entity.Reclamation;
import com.esprit.service.EvenementService;
import com.esprit.service.ReclamationService;
import com.esprit.utils.Session;
import java.io.IOException;
import java.util.Date;


/**
 *
 * @author 21628
 */
public class ReclamationClientForm extends Form{
    
          String file ;
          Resources theme;


    ReclamationService es = new ReclamationService();
    
     public ReclamationClientForm(Form previous) throws IOException {
                super("Reclamation", BoxLayout.y());
        this.getToolbar();
         Label logi = new Label("Mes Reclamation");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilClientForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Reclamation", null, ev->{
        Form addEvent = new Form("Ajouter Reclamation",BoxLayout.y());
            Label AJOUT = new Label("ADD Reclamation");
            addEvent.add(AJOUT);
            TextField TYPE = new TextField("", "Type", 20, TextArea.TEXT_CURSOR);
;
        TextField DISCRIPTION = new TextField("", "Discription", 20, TextArea.TEXT_CURSOR);

        Button upload = new Button("Upload Image");
        Button save = new Button("Ajouter");
        addEvent.add("Discription : ").add(DISCRIPTION);
        addEvent.add("Type : ").add(TYPE);

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

                            if (TYPE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);

                           } else if (DISCRIPTION.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } 
                             else {
                           
                                try {

                         
                                    Reclamation e = new Reclamation(Session.get().getCin(), file, TYPE.getText(), DISCRIPTION.getText(), null);
                                                     System.out.println("forms.addEvet.addItem()"+e);

                                    if (es.addReclamation(e,Session.get().getCin()) == true) {
                                        Dialog.show("Ajouter Reclamation", "Ajouter Reclamation aves success ", "OK", null);
                                         new ReclamationClientForm(this).show();
                                        
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
              for(Reclamation c: es.getReclamationUser(Session.get().getCin())){
           
 
          this.add(addItem(c));    
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Reclamation e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label type=new Label(e.getType());
          Label libelle_type = new Label("LIBELLE");


        Label lieu=new Label(e.getDescription());
        Label libelle_description = new Label("Descrption");

        Label dd=new Label(String.valueOf(e.getDate()));
        Label libelle_dd = new Label("Date :");
          
        String image = new String("http://localhost/Upload/");
            EncodedImage enc = EncodedImage.create("/sep.jpg");
            Image im = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       

        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_type).add(type);
        
        cn2.add(libelle_description).add(lieu);

        cn2.add(libelle_dd).add(dd);
        
        cn2.add(im);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getType());

        Label discriptionm=new Label(e.getDescription());
        Label ddm=new Label(String.valueOf(e.getDate()));
        
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
         
         Form fmodifier = new Form("Modifier Reclamation", BoxLayout.y());
         
           Label modif = new Label("EDIT Reclamation");
                modif.setUIID("login");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getType());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getDescription());
         lieu2.setMinimumElementsShownInPopup(1);
         lieu2.setUIID("txtn");


         Picker datedebut = new Picker();
         Picker dateFin = new Picker();
          Label lib_titre = new Label("Type");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);
              Label lib_Lieu = new Label("Description");
                lib_Lieu.setUIID("pass");
         fmodifier.add(lib_Lieu).add(lieu2);
             
      

         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             try {

                 Reclamation r = new Reclamation(e.getIdrec(), "", "",titre2.getText(), lieu2.getText(), null);
                 
                 
                                    if ( es.ModifierReclamation(r) == true) {
                                        Dialog.show("Modifier Reclamation", "Reclamation Modifier aves success ", "OK", null);
                                        
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                 
                 new ReclamationClientForm(this).show();
             } catch (IOException ex) {
             }
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (es.DeleteReclamation(e.getIdrec())) {
                                        Dialog.show("Supprimer Reclamation", "Reclamation Supprimer aves success ", "OK", null);
                                        
                                             try {
                new ReclamationClientForm(this).show();
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
   

 

                
            f2.add(im1).add(lib_titre).add(titrem).add(lib_Lieu).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   
     
     
}
