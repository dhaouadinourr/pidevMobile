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
public class Reservation {

private int id ,nbrPlace,idzone ;

private Date datec ;

private String cin ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public int getIdzone() {
        return idzone;
    }

    public void setIdzone(int idzone) {
        this.idzone = idzone;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Reservation(int id, int nbrPlace, int idzone, Date datec, String cin) {
        this.id = id;
        this.nbrPlace = nbrPlace;
        this.idzone = idzone;
        this.datec = datec;
        this.cin = cin;
    }

    public Reservation(int nbrPlace, int idzone, Date datec, String cin) {
        this.nbrPlace = nbrPlace;
        this.idzone = idzone;
        this.datec = datec;
        this.cin = cin;
    }


    
}
