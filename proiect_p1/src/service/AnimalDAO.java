package service;

import database.DatabaseConnection;
import model.Animal;
import exceptii.ExceptieAnimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public void createTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS animal (
                    id TEXT PRIMARY KEY,
                    nume TEXT NOT NULL,
                    varsta INTEGER,
                    specie TEXT NOT NULL,
                    esteVaccinat INTEGER
                )
            """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exista(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM animal WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void adauga(Animal a) throws ExceptieAnimal {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement check = conn.prepareStatement("SELECT 1 FROM animal WHERE id = ?")) {

            check.setString(1, a.getId());
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                throw new ExceptieAnimal("Animalul cu ID-ul " + a.getId() + " există deja în baza de date.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO animal(id, nume, varsta, specie, esteVaccinat) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, a.getId());
            stmt.setString(2, a.getNume());
            stmt.setInt(3, a.getVarsta());
            stmt.setString(4, a.getSpecie());
            stmt.setInt(5, a.isEsteVaccinat() ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Animal> getAll() {
        List<Animal> animale = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM animal")) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                String specie = rs.getString("specie");
                boolean esteVaccinat = rs.getInt("esteVaccinat") == 1;

                Animal a = new Animal(id, nume, varsta) {
                    @Override
                    public String getSpecie() {
                        return specie;
                    }
                };
                a.setEsteVaccinat(esteVaccinat);
                animale.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animale;
    }

    public void sterge(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM animal WHERE id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stergeTot() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM animal");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
