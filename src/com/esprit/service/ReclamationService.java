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
import com.esprit.entity.Reclamation;
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
public class ReclamationService {
  

    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Reclamation> Evenets;

    public ReclamationService() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addReclamation(Reclamation r,String id) {
     
        String url = Statics.BASE_URL + "/api/reclamation/"+id+"?image=" + r.getImage()+ "&type=" + r.getType()+ "&Description=" + r.getDescription();
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

    
        public ArrayList<Reclamation> getReclamationUser(String id) {
        String url = Statics.BASE_URL + "/api/reclamationAll/"+id;

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
        
          public ArrayList<Reclamation> parseEvenement(String jsonText) throws ParseException{
        try {
            Evenets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("idRec").toString());
            String type = obj.get("typeRec").toString();
            String Description = obj.get("descriptionRec").toString();
            String image = obj.get("screenshot").toString();
            String dated = obj.get("dateRec").toString();

             Map map = ((Map) obj.get("cin"));
             String cin = (String) map.get("cin");

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                 String s = formatter.format(dated);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

             Date datedebut = df.parse(s);
        

           
                  
                Evenets.add(new Reclamation(id, String.valueOf(cin), image, type, Description, datedebut));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenets;
    }

       public boolean ModifierReclamation(Reclamation r) {
     
        String url = Statics.BASE_URL + "/updateRec?image=" + r.getImage()+ "&type=" + r.getType()+ "&Description=" + r.getDescription()+"&id="+r.getIdrec();
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
          public boolean DeleteReclamation(int id) {
     
        String url = Statics.BASE_URL + "/api/reclamationdelete/"+id;

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
