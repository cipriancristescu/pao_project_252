package service;

import database.DatabaseConnection;
import model.Angajat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AngajatDAO {

    public void createTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS angajat (
                    id TEXT PRIMARY KEY,
                    nume TEXT NOT NULL,
                    telefon TEXT,
                    functie TEXT,
                    salariu REAL
                )
            """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exista(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM angajat WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void adauga(Angajat a) {
        if (exista(a.getId())) {
            System.out.println("Angajatul cu ID-ul " + a.getId() + " există deja.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO angajat(id, nume, telefon, functie, salariu) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, a.getId());
            stmt.setString(2, a.getNume());
            stmt.setString(3, a.getTelefon());
            stmt.setString(4, a.getFunctie());
            stmt.setDouble(5, a.getSalariu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Angajat> getAll() {
        List<Angajat> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM angajat")) {

            while (rs.next()) {
                list.add(new Angajat(
                        rs.getString("id"),
                        rs.getString("nume"),
                        rs.getString("telefon"),
                        rs.getString("functie"),
                        rs.getDouble("salariu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void stergeTot() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM angajat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
