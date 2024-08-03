package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public static class MaxProjectCountClient {
        private String name;
        private int projectCount;

        public MaxProjectCountClient(String name, int projectCount) {
            this.name = name;
            this.projectCount = projectCount;
        }

        public String getName() {
            return name;
        }

        public int getProjectCount() {
            return projectCount;
        }

        @Override
        public String toString() {
            return "MaxProjectCountClient{name='" + name + "', projectCount=" + projectCount + '}';
        }
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sql = readSqlFile("sql/find_max_projects_client.sql");

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(new MaxProjectCountClient(rs.getString("NAME"), rs.getInt("PROJECT_COUNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private String readSqlFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read SQL file", e);
        }
    }


}