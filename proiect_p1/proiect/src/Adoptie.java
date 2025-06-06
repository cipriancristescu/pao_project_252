import java.time.LocalDate;

public class Adoptie {
    private String id;
    private Animal animal;
    private Adoptant adoptant;
    private LocalDate dataAdoptie;

    public Adoptie(String id, Animal animal, Adoptant adoptant) {
        this.id = id;
        this.animal = animal;
        this.adoptant = adoptant;
        this.dataAdoptie = LocalDate.now();
    }

    // Metode de acces
    public String getId() { return id; }
    public Animal getAnimal() { return animal; }
    public Adoptant getAdoptant() { return adoptant; }
    public LocalDate getDataAdoptie() { return dataAdoptie; }
}