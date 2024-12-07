package com.example;

import javax.swing.*;
import java.awt.*;

public class Login {
    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Login Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Menambahkan panel latar belakang
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("D:\\Perkuliahan\\SEMESTER5\\PraktikumPBO\\Project PBO\\Project-PBO-KLP-31\\demo\\target\\classes\\4.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 800, 600);
        backgroundPanel.setLayout(null); // Menggunakan layout manual
        frame.add(backgroundPanel);

        // Menambahkan label "Who Are You?"
        JLabel titleLabel = new JLabel("WHO ARE YOU?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(250, 100, 400, 50); // Atur posisi dan ukuran
        backgroundPanel.add(titleLabel);

        // Menambahkan label username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setBounds(200, 200, 150, 30);
        backgroundPanel.add(usernameLabel);

        // Menambahkan field input username
        JTextField usernameField = new JTextField();
        usernameField.setBounds(350, 200, 250, 30);
        backgroundPanel.add(usernameField);

        // Menambahkan label password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(200, 260, 150, 30);
        backgroundPanel.add(passwordLabel);

        // Menambahkan field input password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(350, 260, 250, 30);
        backgroundPanel.add(passwordField);

        // Menambahkan tombol login
        JButton loginButton = new JButton("ENTER");
        loginButton.setBounds(300, 350, 200, 40);
        loginButton.setBackground(Color.YELLOW);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundPanel.add(loginButton);

        // Menampilkan frame
        frame.setVisible(true);
    }
}
