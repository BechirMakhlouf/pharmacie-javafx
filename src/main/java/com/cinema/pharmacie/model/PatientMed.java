package com.cinema.pharmacie.model;

import java.util.Date;

public class PatientMed {
    private int Qte;
    private Date Date;
    private Medicament med;
    private Patient patient;

    public PatientMed(int Qte, Date Date, Medicament med, Patient patient) throws IllegalArgumentException {
        if (Qte < 0) {
            throw new IllegalArgumentException("Qte must be positive");
        }
        this.Qte = Qte;
        this.Date = Date;
        this.med = med;
        this.patient = patient;
    }

    public int getQte() {
        return Qte;
    }

    public Date getDate() {
        return Date;
    }

    public Medicament getMed() {
        return this.med;
    }

    public Patient getPatient() {
        return this.patient;
    }

    // Setters
    public void setQte(int qte) {
        Qte = qte;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public void setMed(Medicament med) {
        this.med = med;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
