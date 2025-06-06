package service;

import database.DatabaseConnection;
import model.Donatie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonatieDAO {

    public void createTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS donatie (
                    id TEXT PRIMARY KEY,
                    tipDonatie TEXT,
                    valoare REAL,
                    donatorNume TEXT
                )
            """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exista(String id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM donatie WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void adauga(Donatie d) {
        if (exista(d.getId())) {
            System.out.println("Donatia cu ID-ul " + d.getId() + " există deja.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO donatie(id, tipDonatie, valoare, donatorNume) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, d.getId());
            stmt.setString(2, d.getTipDonatie());
            stmt.setDouble(3, d.getValoare());
            stmt.setString(4, d.getDonatorNume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Donatie> getAll() {
        List<Donatie> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM donatie")) {

            while (rs.next()) {
                list.add(new Donatie(
                        rs.getString("id"),
                        rs.getString("tipDonatie"),
                        rs.getDouble("valoare"),
                        rs.getString("donatorNume")
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
            stmt.executeUpdate("DELETE FROM donatie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
