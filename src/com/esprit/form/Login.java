/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.utils.Session;

/**
 *
 * @author 21628
 */
public class Login extends Form{

   Form h = this;
    Resources theme;

    public Login() {
        super( BoxLayout.y());

        String url = "http://127.0.0.1/cnx.php";

        TextField txtn, txtpass;
        Button btnvalid, btninscrire;

        Label logi = new Label("ACCOUNT LOGIN");
        logi.setUIID("login");
        Label labUser = new Label("USERNAME");
        txtn = new TextField("", "Username", 5, TextArea.ANY);
        Label labpASSWORD = new Label("PASSWORD");
        txtpass = new TextField("", "Password", 5, TextArea.PASSWORD);
        btnvalid = new Button("Valider");
        btninscrire = new Button("Sign up");
        

        Label a = new Label("                                      ");
        Label b = new Label("    ");
        a.setUIID("aa");
        //   b.setUIID("bb");

        this.add(logi).add(labUser).add(txtn).add(labpASSWORD).add(txtpass).add(b).add(btnvalid).add(a).add(btninscrire);
                //.add(new ScaleImageLabel(GifImage.decode(getResourceAsStream("accueil.gif"), 1177720)));

       

        btninscrire.addActionListener(l
                -> {
            new Registre().show();
        }
        );

        btnvalid.addActionListener(e -> {
            ConnectionRequest cnreq = new ConnectionRequest();
            cnreq.setPost(false);
            cnreq.addArgument("name", txtn.getText());
            cnreq.addArgument("password", txtpass.getText());
            cnreq.setUrl(url);
            cnreq.addResponseListener(ev -> {
                String chaine = new String(cnreq.getResponseData());
                System.out.println(chaine);
                if (chaine.equalsIgnoreCase("-1")) {

                    Dialog.show("Erreur", "Verifier votre USername and password", "OK", null);

                } else {
                    System.out.println("chaine"+chaine);


                    Session.start(chaine);
                    System.out.println("whyy"+Session.get().getRole().equals("[ROLE_ADMIN]"));


                        if (Session.get().getRole().equals("[ROLE_USER]")) {

                             new AcceuilClientForm().show();

                        } 

                        else  {

                             new AcceuilAdmin().show();

                        } 

                    

                }

            });
            NetworkManager.getInstance().addToQueueAndWait(cnreq);
        });

    }
}