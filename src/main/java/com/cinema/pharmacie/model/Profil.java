package com.cinema.pharmacie.model;

public class Profil {
    private User user;

    public Profil(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}