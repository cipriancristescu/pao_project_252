package model;

public abstract class Persoana {
    private String id;
    private String nume;
    private String telefon;

    public Persoana(String id, String nume, String telefon) {
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
    }

    // Metode de acces
    public String getId() { return id; }
    public String getNume() { return nume; }
    public String getTelefon() { return telefon; }

    public void setTelefon(String telefon) { this.telefon = telefon; }
}