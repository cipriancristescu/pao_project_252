package service;

import database.DatabaseConnection;
import model.Adoptant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptantDAO {

    public void createTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS adoptant (
                    id TEXT PRIMARY KEY,
                    nume TEXT NOT NULL,
                    telefon TEXT,
                    adresa TEXT
                )
            """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exista(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM adoptant WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void adauga(Adoptant a) {
        if (exista(a.getId())) {
            System.out.println("Adoptantul cu ID-ul " + a.getId() + " există deja.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO adoptant(id, nume, telefon, adresa) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, a.getId());
            stmt.setString(2, a.getNume());
            stmt.setString(3, a.getTelefon());
            stmt.setString(4, a.getAdresa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Adoptant getById(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM adoptant WHERE id = ?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Adoptant(
                        rs.getString("id"),
                        rs.getString("nume"),
                        rs.getString("telefon"),
                        rs.getString("adresa")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Adoptant> getAll() {
        List<Adoptant> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM adoptant")) {
            while (rs.next()) {
                list.add(new Adoptant(
                        rs.getString("id"),
                        rs.getString("nume"),
                        rs.getString("telefon"),
                        rs.getString("adresa")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void sterge(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM adoptant WHERE id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Adoptant a) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE adoptant SET nume = ?, telefon = ?, adresa = ? WHERE id = ?")) {
            stmt.setString(1, a.getNume());
            stmt.setString(2, a.getTelefon());
            stmt.setString(3, a.getAdresa());
            stmt.setString(4, a.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stergeTot() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM adoptant");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
