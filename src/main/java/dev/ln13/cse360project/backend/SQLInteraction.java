package dev.ln13.cse360project.backend;

import java.sql.*;

public class SQLInteraction {
    static Connection conn;

    public static void main(String[] args) throws SQLException {
        conn = setupConnection();
    }

    private static Connection setupConnection() throws SQLException {
        // Check if database exists
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:medical.db");
        } catch (Exception e) {
            System.out.println("Database does not exist. Creating database...");
            conn = createDatabase();
        }
        return conn;
    }

    private static Connection createDatabase() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:medical.db");
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE patients (id INTEGER PRIMARY KEY, name TEXT NOT NULL, dob TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            // MORE SQL STUFF HERE
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
            throw e;
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        conn.close();
    }
}