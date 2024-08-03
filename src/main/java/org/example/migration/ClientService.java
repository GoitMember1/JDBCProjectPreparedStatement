package org.example.migration;

import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public long create(String name) {
        if (name == null || name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Name must be between 2 and 1000 characters.");
        }

        String sql = "INSERT INTO client (NAME) VALUES (?)";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                } else {
                    throw new SQLException("Client creation failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating client", e);
        }
    }

    public String getById(long id) {
        String sql = "SELECT NAME FROM client WHERE ID = ?";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("NAME");
                } else {
                    throw new SQLException("Client with ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving client by ID", e);
        }
    }

    public void setName(long id, String name) {
        if (name == null || name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Name must be between 2 and 1000 characters.");
        }

        String sql = "UPDATE client SET NAME = ? WHERE ID = ?";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setLong(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Client with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating client name", e);
        }
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM client WHERE ID = ?";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Client with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting client", e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM client";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                clients.add(new Client(rs.getLong("ID"), rs.getString("NAME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error listing all clients", e);
        }

        return clients;
    }
}