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
import com.esprit.entity.Offre;
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
public class OffreService {
    

        
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Offre> Evenets;

    public OffreService() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addOffre(Offre e,int id) {
     
        String url = Statics.BASE_URL + "/api/offre/"+id+"?datef=" + e.getDatef()+ "&dated=" + e.getDated()+ "&pourcentage=" + e.getPourcentage();
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

    
        public ArrayList<Offre> getAllOffre() {
        String url = Statics.BASE_URL + "/api/allOffre";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Evenets = parseOffre(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenets;
    }
        
          public ArrayList<Offre> parseOffre(String jsonText) throws ParseException{
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("idPromotion").toString());
            String dated = obj.get("dateDebutpromo").toString();
            String datef = obj.get("dateFinpromo").toString();
            String pourcentage = obj.get("pourcentagepromo").toString();

            Map map = ((Map) obj.get("refEquipement"));
            Double ideq = (Double) map.get("refEquipement");

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(dated);
                String s1 = formatter.format(datef);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

           Date datedebut = df.parse(s);
           Date  datefin = df.parse(s1);
        

           
                  
                Evenets.add(new Offre(id, ideq.intValue(), pourcentage, datedebut, datefin));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

       public boolean ModifierOffre(Offre e) {
     
        String url = Statics.BASE_URL + "/updateOffre??datef=" + e.getDatef()+ "&dated=" + e.getDated()+ "&pourcentage=" + e.getPourcentage()+"&id="+e.getId();
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
          public boolean DeleteOffre(int id) {
     
        String url = Statics.BASE_URL + "/api/offredelet/"+id;

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
