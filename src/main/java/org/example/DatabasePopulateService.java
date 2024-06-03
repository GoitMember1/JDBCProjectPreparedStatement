package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            List<String> sqlLines = Files.readAllLines(Paths.get("sql/populate_db.sql"));
            for (String sql : sqlLines) {
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.execute();
                }
            }
            System.out.println("Database populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}