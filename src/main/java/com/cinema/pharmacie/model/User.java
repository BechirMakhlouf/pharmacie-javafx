package com.cinema.pharmacie.model;

public class User {
    private String code;
    private String email;
    private String mp;
    private String nom;
    private String numTel;

    public User(String code, String email, String mp, String nom, String numTel) {
        this.code = code;
        this.email = email;
        this.mp = mp;
        this.nom = nom;
        this.numTel = numTel;
    }
    public String getCode() {
        return code;
    }
    public String getEmail() {
        return email;
    }

    public String getMp() {
        return mp;
    }

    public String getNom() {
        return nom;
    }

    public String getNumTel() {
        return numTel;
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}