/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.entity.Equipement;
import com.esprit.entity.Evenement;
import com.esprit.entity.Zone;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21628
 */
public class EquipementService {



    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Equipement> Evenets;

    public EquipementService() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addEsuipement(Equipement e) {
     
        String url = Statics.BASE_URL + "/api/equipmeent?image=" + e.getImage()+ "&description=" + e.getDescription()+ "&image=" + e.getImage()+ "&nom=" + e.getImage()+ "&prix=" + e.getPrix();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
           NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
        public ArrayList<Equipement> getAllEquipement() {
        String url = Statics.BASE_URL + "/api/allEquipement";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Evenets = parseEquipement(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
        
          public ArrayList<Equipement> parseEquipement(String jsonText) throws ParseException{
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("refEquipement").toString());
            String nom = obj.get("nomEquipement").toString();
            String Description = obj.get("descriptionEquipement").toString();
            float prix =(float)Float.parseFloat( obj.get("prixEquipement").toString());
            String image = obj.get("image").toString();



           
                  
                Evenets.add(new Equipement(id, prix, nom, Description, image));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

       public boolean ModifierEquipement(Equipement e) {
     
        String url = Statics.BASE_URL + "/updateEquipememnt?image=" + e.getImage()+ "&description=" + e.getDescription()+ "&image=" + e.getImage()+ "&nom=" + e.getImage()+ "&prix=" + e.getPrix()+"&id="+e.getId();
//http://localhost/bike/web/app_dev.php/api/Events/update?titre=aymen&discription=hello&lieu=cc&image=xx&dated=2020-04-08&dateF=2020-04-08&nbrparticipant=50&nbrplace=20&id=10
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
          public boolean DeleteEquipemnt(int id) {
     
        String url = Statics.BASE_URL + "/api/equipemtdelete/"+id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     
}

    

