package org.example.Controller;

import org.example.Controller.Users.Formateur;

public class Promotion {

    public String promoName;
    public Formateur formateur;

    public Promotion(String promoName, Formateur formateur) {
        this.promoName = promoName;
        this.formateur = formateur;
    }

    public Promotion() {

    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
}
