package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class User implements Storable {
    private String name;
    private String nim;
    private int iq;

    public User(String name, String nim, int iq) {
        this.name = name;
        this.nim = nim;
        this.iq = iq;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public int getIq() {
        return iq;
    }

    @Override
    public void saveToDatabase() {
        String dbUrl = "jdbc:mysql://localhost:3306/iq_test";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "INSERT INTO users (name, nim, iq) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, nim);
                preparedStatement.setInt(3, iq);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
