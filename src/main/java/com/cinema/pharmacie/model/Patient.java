package com.cinema.pharmacie.model;

public class Patient {
    private String code;
    private String nom;
    private String numTel;

    public Patient(String code, String nom, String numTel) {
        this.code = code;
        this.nom = nom;
        this.numTel = numTel;
    }
    // Getters
    public String getCode() {
        return code;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}