package org.example.migration;


import org.flywaydb.core.Flyway;

public class DatabaseMigrationService {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/test;DB_CLOSE_DELAY=-1", "sa", "")
                .load();

        flyway.migrate();

        System.out.println("Migrations executed successfully.");
    }
}