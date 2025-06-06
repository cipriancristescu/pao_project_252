public class Pisica extends Animal {
    private String rasa;
    private boolean esteCastrata;

    public Pisica(String id, String nume, int varsta, String rasa) {
        super(id, nume, varsta);
        this.rasa = rasa;
        this.esteCastrata = false;
    }

    // Metode de acces
    public String getRasa() { return rasa; }
    public boolean isEsteCastrata() { return esteCastrata; }

    public void setEsteCastrata(boolean esteCastrata) { this.esteCastrata = esteCastrata; }

    @Override
    public String getSpecie() {
        return "Pisica";
    }
}