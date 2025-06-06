package model;

import model.Persoana;

public class Adoptant extends Persoana {
    private String adresa;

    public Adoptant(String id, String nume, String telefon, String adresa) {
        super(id, nume, telefon);
        this.adresa = adresa;
    }

    // Metode de acces
    public String getAdresa() { return adresa; }

    public void setAdresa(String adresa) { this.adresa = adresa; }
}