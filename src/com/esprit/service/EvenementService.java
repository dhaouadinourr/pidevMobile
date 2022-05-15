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
import com.esprit.entity.Evenement;
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
public class EvenementService {
    

    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Evenement> Evenets;

    public EvenementService() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addEvent(Evenement event) {
     
        String url = Statics.BASE_URL + "/api/eventnew?titre=" + event.getNom()+ "&description=" + event.getDescrption()+ "&image=" + event.getImage()+ "&dated=" + event.getDated()+ "&dateF=" + event.getDatef()+ "&image=" + event.getImage();
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

    
        public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/api/allEvent";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Evenets = parseEvenement(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
        
          public ArrayList<Evenement> parseEvenement(String jsonText) throws ParseException{
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String nom = obj.get("nom").toString();
            String Description = obj.get("description").toString();
            String dated = obj.get("dateDeb").toString();
            String datef = obj.get("dateFin").toString();
            String image = obj.get("image").toString();

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(dated);
            String s1 = formatter.format(datef);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date datedebut = new Date();
            Date datefin = new Date();
            datedebut = df.parse(s);
            datefin = df.parse(s1);
        

           
                  
                Evenets.add(new Evenement(id, nom, Description, image, datefin, datefin));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

       public boolean ModifierEvent(Evenement event) {
     
        String url = Statics.BASE_URL + "/updateEvent?nom=" + event.getNom()+ "&description=" + event.getDescrption()+"&dated=" + event.getDated()+ "&datef=" + event.getDatef()+ "&image=" + event.getImage()+"&id="+event.getId();
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
          public boolean DeleteEvenement(int id) {
     
        String url = Statics.BASE_URL + "/api/eventdelete/"+id;

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

