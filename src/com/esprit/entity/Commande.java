/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entity;

/**
 *
 * @author 21628
 */
public class Commande {
 
    private int id ;
    private int num ,codePostal ;
    private String mode ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Commande(int id, int num, int codePostal, String mode) {
        this.id = id;
        this.num = num;
        this.codePostal = codePostal;
        this.mode = mode;
    }

    public Commande() {
    }

    public Commande(int num, int codePostal, String mode) {
        this.num = num;
        this.codePostal = codePostal;
        this.mode = mode;
    }

   
    
}
