/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author 21628
 */
public class AcceuilClientForm extends Form{


    Resources theme;

    public AcceuilClientForm() {

        super("Welcome",BoxLayout.y());
      //  theme = UIManager.initFirstTheme("/theme");
        Button b, btn_responsable_evenement, btn_responsable_Equipment, btn_responsable_Commande, btn_Responsable_reclamation, btn_responsable_reservation, btn_responsable_Zone;

        btn_responsable_Equipment = new Button("NOS EQUIPMENT"); // hedha fih produit(abonnment,piece,accessoirte,velo) , categorie
        btn_responsable_Commande = new Button("Passer Commande");
        btn_Responsable_reclamation = new Button("Votre Reclamation");
        btn_responsable_Zone = new Button("NOS ZONECAMPING");
        btn_responsable_evenement = new Button("NOS Evenement");

   
        this.add(btn_responsable_Equipment);
        this.add(btn_Responsable_reclamation);
        this.add(btn_responsable_Zone);
        this.add(btn_responsable_Commande);
        this.add(btn_responsable_evenement);
       btn_responsable_Commande.addActionListener(pro->   
    {

           try{
            new CommandeUser(this).show();
            } catch (IOException ex) {}

    }
           
    );

       btn_responsable_evenement.addActionListener(pro->   
    {

           try{
            new EvenementUser(this).show();
            } catch (IOException ex) {}

    }
           
    );
        btn_responsable_Zone.addActionListener(pro
                -> {
             try{
            new ZoneClient(this).show();
            } catch (IOException ex) {} 
        }
        );

        btn_responsable_Equipment.addActionListener(pro
                -> {
             try{

            new EquipementFront(this).show();
            } catch (IOException ex) {} 

        }
        );

        btn_Responsable_reclamation.addActionListener(l
                -> {
             try{

            new ReclamationClientForm(this).show();
            } catch (IOException ex) {} 

        }
        );

        this.getToolbar().setUIID("tb");
       
        this.getToolbar().addCommandToOverflowMenu("Reservation", null, ev -> {
                 try{

                    new ReservationForm(this).show();
            } catch (IOException ex) {} 

        });
        

        this.getToolbar().addCommandToOverflowMenu("Logout", null, ev -> {
            new Login().show();
        });

    }
    


    
}
