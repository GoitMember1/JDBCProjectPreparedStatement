package org.example;

import java.sql.Connection;


public class Database {
    private static Database instance;
    private Connection connection;

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}