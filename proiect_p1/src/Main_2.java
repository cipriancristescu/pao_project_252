import model.*;
import service.*;
import exceptii.ExceptieAnimal;
import java.util.*;

public class Main_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AdoptantDAO adoptantDAO = new AdoptantDAO();
        AnimalDAO animalDAO = new AnimalDAO();
        DonatieDAO donatieDAO = new DonatieDAO();
        AdoptieDAO adoptieDAO = new AdoptieDAO();
        AngajatDAO angajatDAO = new AngajatDAO();
        AuditService audit = AuditService.getInstance();

        adoptantDAO.createTable();
        animalDAO.createTable();
        angajatDAO.createTable();
        donatieDAO.createTable();
        adoptieDAO.createTable();

        while (true) {
            System.out.println("\n1. Adaugă adoptant\n2. Afișează adoptanți\n3. Adaugă animal\n4. Afișează animale\n5. Adaugă angajat\n6. Afișează angajați\n7. Adaugă donație\n8. Afișează donații\n9. Adaugă adopție\n10. Șterge toți\n11. Afișează animale după specie\n0. Ieșire");
            System.out.print("Opțiune: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1" -> {
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Nume: "); String nume = sc.nextLine();
                    System.out.print("Telefon: "); String tel = sc.nextLine();
                    System.out.print("Adresa: "); String adr = sc.nextLine();
                    adoptantDAO.adauga(new Adoptant(id, nume, tel, adr));
                    audit.log("Adaugă adoptant");
                }
                case "2" -> {
                    var lista = adoptantDAO.getAll();
                    if (lista.isEmpty()) System.out.println("Nu există adoptanți.");
                    else lista.forEach(a -> System.out.println("ID: " + a.getId() + ", Nume: " + a.getNume() + ", Telefon: " + a.getTelefon() + ", Adresă: " + a.getAdresa()));
                    audit.log("Afișează adoptanți");
                }
                case "3" -> {
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Nume: "); String nume = sc.nextLine();
                    System.out.print("Vârstă: "); int varsta = Integer.parseInt(sc.nextLine());
                    System.out.print("Specie (caine/pisica): "); String specie = sc.nextLine().toLowerCase();
                    System.out.print("Rasă: "); String rasa = sc.nextLine();
                    System.out.print("Vaccinat (true/false): "); boolean vaccinat = Boolean.parseBoolean(sc.nextLine());

                    Animal animal;
                    if (specie.equals("caine")) {
                        animal = new Caine(id, nume, varsta, rasa);
                    } else if (specie.equals("pisica")) {
                        animal = new Pisica(id, nume, varsta, rasa);
                    } else {
                        System.out.println("Specie invalidă.");
                        break;
                    }
                    animal.setEsteVaccinat(vaccinat);
                    try {
                        animalDAO.adauga(animal);
                        audit.log("Adaugă animal");
                    } catch (ExceptieAnimal e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }

                }
                case "4" -> {
                    var lista = animalDAO.getAll();
                    if (lista.isEmpty()) System.out.println("Nu există animale.");
                    else lista.forEach(a -> System.out.println("ID: " + a.getId() + ", Nume: " + a.getNume() + ", Vârstă: " + a.getVarsta() + ", Specie: " + a.getSpecie() + ", Vaccinat: " + a.isEsteVaccinat()));
                    audit.log("Afișează animale");
                }
                case "5" -> {
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Nume: "); String nume = sc.nextLine();
                    System.out.print("Telefon: "); String tel = sc.nextLine();
                    System.out.print("Funcție: "); String f = sc.nextLine();
                    System.out.print("Salariu: "); double s = Double.parseDouble(sc.nextLine());
                    angajatDAO.adauga(new Angajat(id, nume, tel, f, s));
                    audit.log("Adaugă angajat");
                }
                case "6" -> {
                    var lista = angajatDAO.getAll();
                    if (lista.isEmpty()) System.out.println("Nu există angajați.");
                    else lista.forEach(a -> System.out.println("ID: " + a.getId() + ", Nume: " + a.getNume() + ", Telefon: " + a.getTelefon() + ", Funcție: " + a.getFunctie() + ", Salariu: " + a.getSalariu()));
                    audit.log("Afișează angajați");
                }
                case "7" -> {
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Tip: "); String tip = sc.nextLine();
                    System.out.print("Valoare: "); double val = Double.parseDouble(sc.nextLine());
                    System.out.print("Donator: "); String donator = sc.nextLine();
                    donatieDAO.adauga(new Donatie(id, tip, val, donator));
                    audit.log("Adaugă donație");
                }
                case "8" -> {
                    var lista = donatieDAO.getAll();
                    if (lista.isEmpty()) System.out.println("Nu există donații.");
                    else lista.forEach(d -> System.out.println("ID: " + d.getId() + ", Tip: " + d.getTipDonatie() + ", Valoare: " + d.getValoare() + ", Donator: " + d.getDonatorNume()));
                    audit.log("Afișează donații");
                }
                case "9" -> {
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("ID Animal: "); String idA = sc.nextLine();
                    System.out.print("ID Adoptant: "); String idAd = sc.nextLine();
                    Animal an = animalDAO.getAll().stream().filter(x -> x.getId().equals(idA)).findFirst().orElse(null);
                    Adoptant ad = adoptantDAO.getById(idAd);
                    if (an != null && ad != null) {
                        adoptieDAO.adauga(new Adoptie(id, an, ad));
                        animalDAO.sterge(an.getId());
                        audit.log("Adaugă adopție și șterge animal adoptat");
                    } else System.out.println("Date invalide.");
                }
                case "10" -> {
                    adoptantDAO.stergeTot();
                    animalDAO.stergeTot();
                    angajatDAO.stergeTot();
                    donatieDAO.stergeTot();
                    adoptieDAO.stergeTot();
                    audit.log("Șterge toate datele");
                    System.out.println("Toate datele au fost șterse.");
                }
                case "11" -> {
                    System.out.print("Specie (caine/pisica): ");
                    String specie = sc.nextLine().toLowerCase();
                    var lista = animalDAO.getAll();
                    var filtrate = lista.stream().filter(a -> a.getSpecie().equalsIgnoreCase(specie)).toList();
                    if (filtrate.isEmpty()) System.out.println("Nu există animale din această specie.");
                    else filtrate.forEach(a -> System.out.println("ID: " + a.getId() + ", Nume: " + a.getNume() + ", Vârstă: " + a.getVarsta() + ", Rasă: " + ((a instanceof Caine c) ? c.getRasa() : (a instanceof Pisica p) ? p.getRasa() : "necunoscută") + ", Vaccinat: " + a.isEsteVaccinat()));
                    audit.log("Afișează animale după specie");
                }
                case "0" -> {
                    audit.log("Ieșire program");
                    System.out.println("Ieșire...");
                    return;
                }
                default -> System.out.println("Opțiune invalidă.");
            }
        }
    }
}
