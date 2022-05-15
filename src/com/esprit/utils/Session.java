/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utils;

import com.codename1.facebook.User;
import com.esprit.entity.Client;
import com.esprit.service.ClientService;




/**
 *
 * @author aymen
 */
public class Session {
         private static String idUser;
    private static   Client userstat;

    public static void start(String currentUserID) {
        idUser = currentUserID;
           ClientService  u = new ClientService();
     
       userstat = u.get_User(idUser);
        System.out.println("com.esprit.utils.Session.start()"+u.get_User(idUser));

        System.out.println("hhhhhhhhhhhhhhhhhhhh()"+idUser);
    }

    public static String getCurrentSession() throws Exception {
        if (idUser.equals("-1")) {
            return idUser;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static void close() throws Exception {
        if (idUser.equals("-1")) {
            idUser = "-1";
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
    public static Client get()
    {
             System.out.println("usestat"+userstat);

        return userstat;
        
    }

}
