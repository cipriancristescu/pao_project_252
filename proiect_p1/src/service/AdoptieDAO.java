package service;

import database.DatabaseConnection;
import model.Adoptie;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptieDAO {

    public void createTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS adoptie (
                    id TEXT PRIMARY KEY,
                    animal_id TEXT,
                    adoptant_id TEXT,
                    dataAdoptie TEXT
                )
            """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exista(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM adoptie WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void adauga(Adoptie a) {
        if (exista(a.getId())) {
            System.out.println("Adoptia cu ID-ul " + a.getId() + " există deja.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO adoptie(id, animal_id, adoptant_id, dataAdoptie) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, a.getId());
            stmt.setString(2, a.getAnimal().getId());
            stmt.setString(3, a.getAdoptant().getId());
            stmt.setString(4, a.getDataAdoptie().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stergeTot() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM adoptie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
