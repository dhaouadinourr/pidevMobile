/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.entity.Client;
import com.esprit.service.ClientService;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author 21628
 */
public class Registre extends Form{
    
    Resources theme;
    String file ;

    ClientService cs = new ClientService();

    public Registre() {
        super( BoxLayout.y());
         theme = UIManager.initFirstTheme("/theme");
        TextField FIRSTNAME = new TextField("", "First Name", 20, TextArea.TEXT_CURSOR);
        TextField LASTNAME = new TextField("", "Last Name", 20, TextArea.ANY);
        Label logi = new Label("ACCOUNT REGISTRATION ");
        logi.setUIID("logi");
        TextField Cin = new TextField("", "Cin", 20, TextArea.PHONENUMBER);
        TextField ADRESSE = new TextField("", "Adresse", 4, TextArea.NUMERIC);
        TextField SEXE = new TextField("", "Sexe", 20, TextArea.ANY);
        TextField EMAIL = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField Password = new TextField("", "Password", 20, TextArea.PASSWORD);
        Picker  DATEN = new Picker ();
        DATEN.setUIID("txtn");
        FIRSTNAME.setUIID("txtn");
        LASTNAME.setUIID("txtn");
        Cin.setUIID("txtn");
        ADRESSE.setUIID("txtn");
        SEXE.setUIID("txtn");
        EMAIL.setUIID("txtn");
        Password.setUIID("txtn");
        
        Label labFIRSTNAME = new Label("FIRSTNAME");
        Label labLASTNAME = new Label("LASTNAME");
        Label labEMAIL = new Label("EMAIL");
        Label labUSERAGE = new Label("USERAGE");
        Label labUSERNAME = new Label("USERNAME");
        Label labPASSWORD = new Label("PASSWORD");
        Label labUSERNUMBER = new Label("USERNUMBER");
        Label labAdresse = new Label("Adresse");

        labFIRSTNAME.setUIID("pass");
        labLASTNAME.setUIID("pass");
        labEMAIL.setUIID("pass");
        labUSERAGE.setUIID("pass");
        labPASSWORD.setUIID("pass");
        labUSERNUMBER.setUIID("pass");
        labUSERNAME.setUIID("pass");
        labUSERNAME.setUIID("pass");
        this.add(logi);
        this.add(labFIRSTNAME).add(FIRSTNAME);
        this.add(labLASTNAME).add(LASTNAME);
        this.add(labEMAIL).add(EMAIL);
        this.add(labUSERAGE).add(Cin);
        this.add(labUSERNAME).add(SEXE);
        this.add(labPASSWORD).add(Password);
        this.add(labUSERNUMBER).add(DATEN);
        this.add(labAdresse).add(ADRESSE); 
          
         

                        Button btn = new Button("Submit");
                        btn.setUIID("vtnvalid");
 
                        Button upload = new Button("Upload Image");
                        upload.setUIID("vtnvalid");

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
                        this.add(upload);
                        this.add(btn);

                        this.revalidate();

                        btn.addActionListener(l
                                -> {

                            // val firstname       
                            Validator val_firstname = new Validator();
                            val_firstname.addConstraint(FIRSTNAME, new LengthConstraint(8));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_firstname.addConstraint(FIRSTNAME, new RegexConstraint(text_saisir_des_caracteres, ""));
                            // val lastname   
                            Validator val_lastname = new Validator();
                            val_lastname.addConstraint(LASTNAME, new LengthConstraint(8));
                            val_lastname.addConstraint(LASTNAME, new RegexConstraint(text_saisir_des_caracteres, ""));

                            String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                            
                             // val mail   
                            Validator val_mail = new Validator();
                            val_mail.addConstraint(EMAIL, new LengthConstraint(8));
                            val_mail.addConstraint(EMAIL, new RegexConstraint(text_mail, ""));
                              // val age   

                            if (FIRSTNAME.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);

                            }else if (LASTNAME.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de lastname ", "OK", null);

                            }
                            else if (EMAIL.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            } else if (!val_mail.isValid()) {
                                Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);

                            } 
                            
                            else if (SEXE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de age ", "OK", null);

                            }
                            
                            else if (SEXE.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Username ", "OK", null);

                            } else if (Password.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de password ", "OK", null);
                            }
            
                            
                        
                      
                            else {

                                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    Date dfin = DATEN.getDate();
                                    String d = df.format(dfin);
                                   Date  datefin;
                                try {
                                    datefin = df.parse(d);
                                  Client u = new Client(FIRSTNAME.getText(), LASTNAME.getText(), SEXE.getText(), EMAIL.getText(), Password.getText(), ADRESSE.getText(), file, "", Cin.getText(), datefin);
                                if (cs.addUser(u) == true) {
//                                    Dialog.show("Sign UP", "Sign Up aves success ", "OK", null);
//                                    ConnectionRequest cnreq = new ConnectionRequest();
//                                    cnreq.setPost(false);
//                                    cnreq.addArgument("name", USERNAME.getText());
//                                    cnreq.addArgument("password", Password.getText());
//                                    cnreq.setUrl(url);
//                                    cnreq.addResponseListener(ev -> {
//                                        String chaine = new String(cnreq.getResponseData());
//
//                                        int id = Integer.valueOf(chaine);
//                                        Session.start(id);
//                                        System.out.println(Session.getCurrentSession());
//
//                                        //new LoginForm().showBack();
//
//                                    });
//                                    NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                        new Login().show();
                                } else {
                                    Dialog.show("Erreur", "Compte existe ", "OK", null);
                                }
                                } catch (ParseException ex) {
                                }
                       


                            }

                        }
                        );

                   

                

        
this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {

           // previous.showBack();
        });

    }

}
