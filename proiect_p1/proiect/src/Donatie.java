public class Donatie {
    private String id;
    private String tipDonatie; // "bani", "hrana", "alte materiale"
    private double valoare;
    private String donatorNume;

    public Donatie(String id, String tipDonatie, double valoare, String donatorNume) {
        this.id = id;
        this.tipDonatie = tipDonatie;
        this.valoare = valoare;
        this.donatorNume = donatorNume;
    }

    // Metode de acces
    public String getId() { return id; }
    public String getTipDonatie() { return tipDonatie; }
    public double getValoare() { return valoare; }
    public String getDonatorNume() { return donatorNume; }
}