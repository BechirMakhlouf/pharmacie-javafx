package com.cinema.pharmacie.model;

public class Medicament {
    private String codeMed;
    private String nomMed;
    private double prixMed;
    private int qte;
    private String typeMed;

    public Medicament(String codeMed, String nomMed, double prixMed, int qte, String typeMed) throws IllegalArgumentException {
        if (qte < 0) {
            throw new IllegalArgumentException("Qte must be positive");
        }
        this.codeMed = codeMed;
        this.nomMed = nomMed;
        this.prixMed = prixMed;
        this.qte = qte;
        this.typeMed = typeMed;
    }
    public String getCodeMed() {
        return this.codeMed;
    }
    public String getNomMed() {
        return this.nomMed;
    }
    public double getPrixMed() {
        return this.prixMed;
    }
    public int getQte() {
        return this.qte;
    }
    public String getTypeMed() {
        return this.typeMed;
    }

    public void setCodeMed(String codeMed) {
        this.codeMed = codeMed;
    }
    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }
    public void setPrixMed(double prixMed) {
        this.prixMed = prixMed;
    }
    public void setQte(int qte) {
        this.qte = qte;
    }
    public void setTypeMed(String typeMed) {
        this.typeMed = typeMed;
    }

}
