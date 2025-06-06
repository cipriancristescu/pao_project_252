package service;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AdapostService {
    // Colectii diferite (List și Set sortat)
    private List<Animal> animale = new ArrayList<>();
    private Set<Angajat> angajati = new TreeSet<>();
    private Map<String, Adoptie> adoptii = new HashMap<>();
    private List<Donatie> donatii = new ArrayList<>();

    // Operatii pentru animale
    public void adaugaAnimal(Animal animal) {
        animale.add(animal);
    }

    public List<Animal> getAnimale() {
        return new ArrayList<>(animale);
    }

    public List<Animal> getAnimaleDupaSpecie(String specie) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animale) {
            if (animal.getSpecie().equalsIgnoreCase(specie)) {
                result.add(animal);
            }
        }
        return result;
    }

    // Operatii pentru angajați
    public void adaugaAngajat(Angajat angajat) {
        angajati.add(angajat);
    }

    public Set<Angajat> getAngajati() {
        return new TreeSet<>(angajati);
    }

    // Operatii pentru adoptii
    public void inregistreazaAdoptie(Animal animal, Adoptant adoptant) {
        String idAdoptie = "ADP-" + System.currentTimeMillis();
        Adoptie adoptie = new Adoptie(idAdoptie, animal, adoptant);
        adoptii.put(animal.getId(), adoptie);
        animale.remove(animal);
    }

    public Map<String, Adoptie> getAdoptii() {
        return new HashMap<>(adoptii);
    }

    // Operatii pentru donatii
    public void inregistreazaDonatie(Donatie donatie) {
        donatii.add(donatie);
    }

    public List<Donatie> getDonatii() {
        return new ArrayList<>(donatii);
    }

    // Alte operatii
    public List<Animal> getAnimaleNevaccinate() {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animale) {
            if (!animal.isEsteVaccinat()) {
                result.add(animal);
            }
        }
        return result;
    }
}