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
public class Offre {

private int id ,ideq ;
private String pourcentage ;
private Date dated , datef ;

    public Offre(int id, int ideq, String pourcentage, Date dated, Date datef) {
        this.id = id;
        this.ideq = ideq;
        this.pourcentage = pourcentage;
        this.dated = dated;
        this.datef = datef;
    }

    public Offre() {
    }

    public Offre(int ideq, String pourcentage, Date dated, Date datef) {
        this.ideq = ideq;
        this.pourcentage = pourcentage;
        this.dated = dated;
        this.datef = datef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdeq() {
        return ideq;
    }

    public void setIdeq(int ideq) {
        this.ideq = ideq;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
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


    
}
