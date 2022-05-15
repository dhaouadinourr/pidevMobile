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
import com.esprit.entity.Zone;
import com.esprit.service.CommandeService;
import com.esprit.service.ZoneService;
import com.esprit.utils.Session;
import java.io.IOException;

/**
 *
 * @author 21628
 */
public class CommandeUser extends Form{
    
       
                 String file ;
          Resources theme;


    CommandeService es = new CommandeService();
    
     public CommandeUser(Form previous) throws IOException {
                super("Commande", BoxLayout.y());
        this.getToolbar().setUIID("tb");
         Label logi = new Label("LES Commande");
           this.add(logi);
            this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AcceuilClientForm().showBack();
        });
    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Commande", null, ev->{
        Form addEvent = new Form("Ajouter Commande",BoxLayout.y());
            Label AJOUT = new Label("ADD Commande");
            addEvent.add(AJOUT);
            TextField MODE = new TextField("", "Mode de Payment", 20, TextArea.TEXT_CURSOR);
;
        TextField CODEPOSTAL = new TextField("", "CodePostal", 20, TextArea.TEXT_CURSOR);

            TextField Num_Tel = new TextField("", "Num_Tel", 20, TextArea.TEXT_CURSOR);
;

     
        Button save = new Button("Ajouter");
        save.setUIID("vtnvalid");
        addEvent.add("Mode Payment : ").add(MODE);
        addEvent.add("CodePostal : ").add(CODEPOSTAL);
        addEvent.add("NumTel : ").add(Num_Tel);
        addEvent.add(save);
        
    
        save.addActionListener(l
                                -> {

                            if (MODE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Centre ", "OK", null);

                           } else if (CODEPOSTAL.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Discription ", "OK", null);

                             
                           } else if (Num_Tel.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Delegation ", "OK", null);

                            } 
                             else {
                           
                                try {

                                    
                                    Commande e = new Commande(Integer.parseInt(Num_Tel.getText()), Integer.parseInt(CODEPOSTAL.getText()), MODE.getText()) ;

                                    if (es.addCommande(e, Session.get().getCin()) == true) {
                                        Dialog.show("Ajouter Commande", "Ajouter Commande aves success ", "OK", null);
                                         new CommandeUser(this).show();
                                        
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
              for(Commande c: es.getCommandeUser( Session.get().getCin())){
           
 
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




        Button btn=new Button("Details");

        cn2.add(libelle_mode).add(mode);
        
        cn2.add(CodePostal).add(codePostal);

        cn2.add(libelle_numTel).add(numTel);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label Mode=new Label(e.getMode());

        Label CODE=new Label(String.valueOf(e.getCodePostal()));
        Label NumTel=new Label(String.valueOf(e.getNum()));



        



     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
       Modifier.setUIID("vtnvalid");
         Supprimer.setUIID("vtnvalid");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Commande", BoxLayout.y());
         
           Label modif = new Label("EDIT Commande");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
            fmodifier.getToolbar();
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField Mode2 =  new AutoCompleteTextField(e.getMode());
          
         Mode2.setMinimumElementsShownInPopup(1);
         AutoCompleteTextField CodePostal2=  new AutoCompleteTextField(String.valueOf(e.getCodePostal()));
         CodePostal2.setMinimumElementsShownInPopup(1);

         AutoCompleteTextField NUM2 =  new AutoCompleteTextField(String.valueOf(e.getNum()));
          
         NUM2.setMinimumElementsShownInPopup(1);


        
          Label lib_titre = new Label("Mode");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(Mode2);


              Label lib_Lieu = new Label("CODE Postal");
                lib_Lieu.setUIID("pass");
         fmodifier.add(lib_Lieu).add(CodePostal2);


              Label lib_Description = new Label("Num");
                lib_Description.setUIID("pass");

         fmodifier.add(lib_Description).add(NUM2);


         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar("back", null, evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             try {

                 Commande ze = new Commande(e.getId(), Integer.valueOf(NUM2.getText()), Integer.valueOf(CodePostal2.getText()), Mode2.getText());
                 
                 
                                    if ( es.ModifierCommande(ze) == true) {
                                        Dialog.show("Modifier Commande", "Commande Modifier aves success ", "OK", null);
                                        
                                        
                                    } else {
                                        Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                    }
                 
                 new CommandeUser(this).show();
             } catch (IOException ex) {
             }
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (es.DeleteCommande(e.getId())) {
                                        Dialog.show("Supprimer Commande", "Commande Supprimer aves success ", "OK", null);
                                        
                                             try {
                new CommandeUser(this).show();
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
               Label lib_titre = new Label("Mode");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Code Postal");
                lib_Lieu.setUIID("pass");
             Label lib_num = new Label("Num Tel");
                lib_titre.setUIID("pass");
      

 
                
            f2.add(lib_titre).add(Mode).add(lib_Lieu).add(CODE).add(lib_num).add(NumTel).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }

}
    
   

