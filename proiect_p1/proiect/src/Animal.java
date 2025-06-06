public abstract class Animal {
    private String id;
    private String nume;
    private int varsta;
    private String stareSanatate;
    private boolean esteVaccinat;

    public Animal(String id, String nume, int varsta) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.stareSanatate = "Necunoscuta";
        this.esteVaccinat = false;
    }

    // Metode de acces
    public String getId() { return id; }
    public String getNume() { return nume; }
    public int getVarsta() { return varsta; }
    public String getStareSanatate() { return stareSanatate; }
    public boolean isEsteVaccinat() { return esteVaccinat; }

    public void setStareSanatate(String stareSanatate) { this.stareSanatate = stareSanatate; }
    public void setEsteVaccinat(boolean esteVaccinat) { this.esteVaccinat = esteVaccinat; }

    public abstract String getSpecie();
}