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
public class ZoneService {
    

    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Zone> Evenets;

    public ZoneService() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addZone(Zone z) {
     
        String url = Statics.BASE_URL + "/api/Zonenew?description=" + z.getDescription()+ "&delegation=" + z.getDelegation()+ "&centre=" + z.getNoncentre()+ "&region=" + z.getRegion();
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

    
        public ArrayList<Zone> getAllZones() {
        String url = Statics.BASE_URL + "/api/allZone";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Evenets = parseZone(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
        
          public ArrayList<Zone> parseZone(String jsonText) throws ParseException{
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String region = obj.get("region").toString();
            String delegation = obj.get("delegation").toString();
            String centre = obj.get("nomCentre").toString();
            String description = obj.get("description").toString();
        

           
                  
                Evenets.add(new Zone(id, description, region, centre, delegation));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

       public boolean ModifierZone(Zone z) {
     
        String url = Statics.BASE_URL + "/updateZone?description=" + z.getDescription()+ "&delegation=" + z.getDelegation()+ "&centre=" + z.getNoncentre()+ "&region=" + z.getRegion()+"&id="+z.getId();
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
          public boolean DeleteZone(int id) {
     
        String url = Statics.BASE_URL + "/api/zonedelete/"+id;

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

