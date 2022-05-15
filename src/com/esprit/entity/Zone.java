/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entity;

/**
 *
 * @author 21628
 */
public class Zone {

  private int id ;
  private String description,region,noncentre,delegation ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNoncentre() {
        return noncentre;
    }

    public void setNoncentre(String noncentre) {
        this.noncentre = noncentre;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public Zone() {
    }

    public Zone(int id, String description, String region, String noncentre, String delegation) {
        this.id = id;
        this.description = description;
        this.region = region;
        this.noncentre = noncentre;
        this.delegation = delegation;
    }

    public Zone(String description, String region, String noncentre, String delegation) {
        this.description = description;
        this.region = region;
        this.noncentre = noncentre;
        this.delegation = delegation;
    }



    
}
