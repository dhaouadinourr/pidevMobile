/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entity;

/**
 *
 * @author 21628
 */
public class Equipement {

private int id;
private float prix ;
private String nom ,description, image ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Equipement() {
    }

    public Equipement(int id, float prix, String nom, String description, String image) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Equipement(float prix, String nom, String description, String image) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }


    
}
