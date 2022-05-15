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
public class Reclamation {


private int idrec ;
private String cin ,image,type,description;
private Date date ;

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Reclamation() {
    }

    public Reclamation(int idrec, String cin, String image, String type, String description, Date date) {
        this.idrec = idrec;
        this.cin = cin;
        this.image = image;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public Reclamation(String cin, String image, String type, String description, Date date) {
        this.cin = cin;
        this.image = image;
        this.type = type;
        this.description = description;
        this.date = date;
    }


    
}
