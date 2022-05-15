/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.form;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author 21628
 */
public class AcceuilAdmin extends  Form{

    Resources theme;

    public AcceuilAdmin() {

        super("Admin",BoxLayout.y());
      //  theme = UIManager.initFirstTheme("/theme");
        Button b,btn_responsable_commande, btn_responsable_Equipment, btn_responsable_evenment, btn_Responsable_offre, btn_responsable_reservation, btn_responsable_Zone;

        btn_responsable_Equipment = new Button("RESPONSABLE EQUIPMENT"); // hedha fih produit(abonnment,piece,accessoirte,velo) , categorie
        btn_responsable_evenment = new Button("RESPONSABLE EVENEMENT");
        btn_Responsable_offre = new Button("RESPONSABLE OFFRE");
        btn_responsable_Zone = new Button("RESPONSABLE ZONECAMPING");
        btn_responsable_commande = new Button("RESPONSABLE Commmande");

   
        this.add(btn_responsable_Equipment);
        this.add(btn_Responsable_offre);
        this.add(btn_responsable_Zone);
        this.add(btn_responsable_evenment);
        this.add(btn_responsable_commande);

       btn_responsable_evenment.addActionListener(pro->   
    {

           try{
            new EvenementAdmin(this).show();
            } catch (IOException ex) {}

    }
           
    );
      btn_responsable_commande.addActionListener(pro->   
    {

           try{
            new CommandeAdmin(this).show();
            } catch (IOException ex) {}

    }
           
    );
        btn_responsable_Zone.addActionListener(pro
                -> {
             try{
            new ZoneAdmin(this).show();
            } catch (IOException ex) {} 
        }
        );

        btn_responsable_Equipment.addActionListener(pro
                -> {
             try{

            new EquipementForm(this).show();
            } catch (IOException ex) {} 

        }
        );

        btn_Responsable_offre.addActionListener(l
                -> {
             try{

            new OffreAdmin(this).show();
            } catch (IOException ex) {} 

        }
        );

        this.getToolbar().setUIID("tb");
       
         this.getToolbar().addCommandToOverflowMenu("Users", null, ev -> {
          try{
            new UsersAfficheForm().show();
            } catch (IOException ex) {} 
 
        });
        

        this.getToolbar().addCommandToOverflowMenu("Logout", null, ev -> {
            new Login().show();
        });

    }
    
}
