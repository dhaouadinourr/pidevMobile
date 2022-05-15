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
import com.esprit.entity.Client;
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
public class ClientService {



    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Client> users;
    
        public ClientService() {
        request = DataSource.getInstance().getRequest();
    }
        
        
          public boolean addUser(Client user) {
     
        String url = Statics.BASE_URL + "/api/addClient?nompre=" + user.getNom()+ "&email=" + user.getEmail()+ "&surnom=" + user.getPrenom()+ "&plainPassword=" + user.getMdp()+ "&sex=" + user.getSexe()+ "&date=" + user.getDateN()+"&image=" + user.getImage()+ "&cin=" + user.getCin()+ "&adresse=" + user.getAdresse();

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
//   public boolean ModifierUser(User user,int id) {
//     
//        String url = Statics.BASE_URL + "api/users/update?username=" + user.getUsername()+ "&email=" + user.getMail()+ "&password=" + user.getPass()+ "&firstname=" + user.getFname()+ "&lastname=" + user.getLname()+ "&image=" + user.getImage()+ "&age=" + user.getAge()+ "&number=" + user.getNumber()+ "&id=" +id;
//
//        request.setUrl(url);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
//                request.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(request);
//
//        return responseResult;
//    }
//   
    public ArrayList<Client> getAllusers() {
        String url = Statics.BASE_URL + "/api/allClient";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseusers(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }

    public ArrayList<Client> parseusers(String jsonText) {
        try {
            users = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
              
            
             
                String cin = obj.get("cin").toString();
               
                String nom = obj.get("nomprenom").toString();
              
                  String prenom = obj.get("surnom").toString();
                String sexe = obj.get("sexe").toString();
                String email = obj.get("email").toString();
                 String adresse = obj.get("adresse").toString();              
                 String image = obj.get("image").toString();
                 String roles = obj.get("roles").toString();
                 String password = obj.get("password").toString();
                 
                 String daten = obj.get("datenaissance").toString();
                 

            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(daten);
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
             Date dateNai = df.parse(s);

                  
                  
                users.add(new Client(nom, prenom, sexe, email, s, adresse, image, roles, cin, dateNai));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
        }

        return users;
    }
 
      public Client  get_User(String id)
      {
          for( Client u :getAllusers()  )
          {
              if (u.getCin().equals(id))
              {
                  System.err.println("useeeer"+u);
                  return u;
              }
          }
          return null;
  
      }

          public boolean DeleteClient(String id) {
     
        String url = Statics.BASE_URL + "/api/clientdelete/"+id;

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
