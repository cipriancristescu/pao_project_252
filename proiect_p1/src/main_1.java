import model.*;
import service.AdapostService;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class main_1 {
    public static void main(String[] args) {
        System.out.println("=== Sistem de Gestiune a Adapostului de Animale ===");

        // Initializare serviciu
        AdapostService adapostService = new AdapostService();

        // 1. Adaugare animale
        System.out.println("\n1. Adaugare animale în sistem:");
        Caine caine1 = new Caine("C1", "Rex", 3, "Ciobanesc German");
        Caine caine2 = new Caine("C2", "Azor", 5, "Labrador");
        Pisica pisica1 = new Pisica("P1", "Miti", 2, "Europeana");
        Pisica pisica2 = new Pisica("P2", "Tom", 1, "Siameza");

        adapostService.adaugaAnimal(caine1);
        adapostService.adaugaAnimal(caine2);
        adapostService.adaugaAnimal(pisica1);
        adapostService.adaugaAnimal(pisica2);

        // Setam vaccinarea pentru unele animale
        caine1.setEsteVaccinat(true);
        pisica2.setEsteVaccinat(true);

        System.out.println("Au fost adaugate " + adapostService.getAnimale().size() + " animale:");
        afiseazaAnimale(adapostService.getAnimale());

        // 2. Adaugare angajati
        System.out.println("\n2. Adaugare angajati:");
        Angajat angajat1 = new Angajat("A1", "Ionescu Maria", "0722123456", "Veterinar", 4500);
        Angajat angajat2 = new Angajat("A2", "Popescu Ion", "0721123456", "Ingrijitor", 3000);
        Angajat angajat3 = new Angajat("A3", "Georgescu Ana", "0733123456", "Asistent", 3500);

        adapostService.adaugaAngajat(angajat1);
        adapostService.adaugaAngajat(angajat2);
        adapostService.adaugaAngajat(angajat3);

        System.out.println("Angajati (sortati dupa nume):");
        afiseazaAngajati(adapostService.getAngajati());

        // 3. Înregistrare adopție
        System.out.println("\n3. inregistrare adoptie:");
        Adoptant adoptant1 = new Adoptant("AD1", "Vasilescu Dan", "0744123456", "Str. Libertatii, nr. 10");
        adapostService.inregistreazaAdoptie(caine1, adoptant1);

        System.out.println("Adoptie înregistrata pentru: " + caine1.getNume());
        System.out.println("Animale ramase în adapost: " + adapostService.getAnimale().size());

        // 4. Înregistrare donatii
        System.out.println("\n4. Înregistrare donatii:");
        Donatie donatie1 = new Donatie("D1", "bani", 500, "Fundatia Prietenii Animalelor");
        Donatie donatie2 = new Donatie("D2", "hrana", 0, "Magazinul Animax");

        adapostService.inregistreazaDonatie(donatie1);
        adapostService.inregistreazaDonatie(donatie2);

        System.out.println("Donatii inregistrate:");
        afiseazaDonatii(adapostService.getDonatii());

        // 5. Interogări diverse
        System.out.println("\n5. Interogari:");

        // Animale după specie
        System.out.println("Caini disponibili:");
        afiseazaAnimale(adapostService.getAnimaleDupaSpecie("Caine"));

        System.out.println("Pisici disponibile:");
        afiseazaAnimale(adapostService.getAnimaleDupaSpecie("Pisica"));

        // Animale nevaccinate
        System.out.println("Animale nevaccinate:");
        afiseazaAnimale(adapostService.getAnimaleNevaccinate());

        // Istoric adopții
        System.out.println("Istoric adoptii:");
        afiseazaAdoptii(adapostService.getAdoptii());
    }

    // Metode auxiliare pentru afișare
    private static void afiseazaAnimale(List<Animal> animale) {
        if (animale.isEmpty()) {
            System.out.println("Nu exista animale în lista.");
            return;
        }
        for (Animal animal : animale) {
            System.out.println("- " + animal.getSpecie() + ": " + animal.getNume() +
                    ", vârstă: " + animal.getVarsta() +
                    ", vaccinat: " + (animal.isEsteVaccinat() ? "da" : "nu"));
        }
    }

    private static void afiseazaAngajati(Set<Angajat> angajati) {
        for (Angajat angajat : angajati) {
            System.out.println("- " + angajat.getNume() +
                    ", funcție: " + angajat.getFunctie() +
                    ", salariu: " + angajat.getSalariu() + " RON");
        }
    }

    private static void afiseazaDonatii(List<Donatie> donatii) {
        for (Donatie donatie : donatii) {
            String valoare = donatie.getTipDonatie().equals("hrana") ?
                    "nu aplicabil" : donatie.getValoare() + " RON";
            System.out.println("- " + donatie.getTipDonatie() +
                    ", valoare: " + valoare +
                    ", donator: " + donatie.getDonatorNume());
        }
    }

    private static void afiseazaAdoptii(Map<String, Adoptie> adoptii) {
        if (adoptii.isEmpty()) {
            System.out.println("Nu există adopții înregistrate.");
            return;
        }
        for (Map.Entry<String, Adoptie> entry : adoptii.entrySet()) {
            Adoptie adoptie = entry.getValue();
            System.out.println("- Animal: " + adoptie.getAnimal().getNume() +
                    ", adoptant: " + adoptie.getAdoptant().getNume() +
                    ", data: " + adoptie.getDataAdoptie());
        }
    }
}