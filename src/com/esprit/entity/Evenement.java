/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entity;

import java.util.Date;

/**
 *
 * @author 21628
 */
public class Evenement {
    
      private int id ;
      private String nom,descrption,image ;
      private Date dated , datef ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public Date getDatef() {
        return datef;
    }

    public void setDatef(Date datef) {
        this.datef = datef;
    }

    public Evenement(int id, String nom, String descrption, String image, Date dated, Date datef) {
        this.id = id;
        this.nom = nom;
        this.descrption = descrption;
        this.image = image;
        this.dated = dated;
        this.datef = datef;
    }

    public Evenement(String nom, String descrption, String image, Date dated, Date datef) {
        this.nom = nom;
        this.descrption = descrption;
        this.image = image;
        this.dated = dated;
        this.datef = datef;
    }

    public Evenement() {
    }
       

      




}
