public class Angajat extends Persoana implements Comparable<Angajat> {
    private String functie;
    private double salariu;

    public Angajat(String id, String nume, String telefon, String functie, double salariu) {
        super(id, nume, telefon);
        this.functie = functie;
        this.salariu = salariu;
    }

    // Metode de acces
    public String getFunctie() { return functie; }
    public double getSalariu() { return salariu; }

    public void setSalariu(double salariu) { this.salariu = salariu; }

    @Override
    public int compareTo(Angajat other) {
        return this.getNume().compareTo(other.getNume());
    }
}