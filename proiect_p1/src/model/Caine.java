package model;

public class Caine extends Animal {
    private String rasa;
    private boolean esteDresat;

    public Caine(String id, String nume, int varsta, String rasa) {
        super(id, nume, varsta);
        this.rasa = rasa;
        this.esteDresat = false;
    }

    // Metode de acces
    public String getRasa() { return rasa; }
    public boolean isEsteDresat() { return esteDresat; }

    public void setEsteDresat(boolean esteDresat) { this.esteDresat = esteDresat; }

    @Override
    public String getSpecie() {
        return "Caine";
    }
}