package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/iq_test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static String currentName;
    private static String currentNIM;

    public static void setCurrentUser(String name, String nim) {
        currentName = name;
        currentNIM = nim;
    }

    public static String getCurrentName() {
        return currentName;
    }

    public static String getCurrentNIM() {
        return currentNIM;
    }

    private List<Question> fetchQuestionsFromDatabase() {
        List<Question> questions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soal");
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Question question = new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("bunyi_soal"),
                        resultSet.getString("opsi_a"),
                        resultSet.getString("opsi_b"),
                        resultSet.getString("opsi_c"),
                        resultSet.getString("opsi_d"),
                        resultSet.getString("opsi_e"),
                        resultSet.getString("jawaban"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    public void showLoginScreen() {
        // Membuat frame utama
        JFrame frame = new JFrame("Login Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440, 900);
        frame.setLayout(new BorderLayout());

        // Panel latar belakang dengan gambar
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("demo/src/main/resources/4.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        frame.add(backgroundPanel, BorderLayout.CENTER);

        // Layout untuk form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false); // Transparan agar latar belakang terlihat

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Label "WHO ARE YOU?"
        JLabel titleLabel = new JLabel("WHO ARE YOU?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Label Nama
        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setForeground(Color.BLACK);
        formPanel.add(nameLabel, gbc);

        // Field Nama
        gbc.gridx++;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Label NIM
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nimLabel = new JLabel("NIM:");
        nimLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nimLabel.setForeground(Color.BLACK);
        formPanel.add(nimLabel, gbc);

        // Field NIM
        gbc.gridx++;
        JTextField nimField = new JTextField(20);
        formPanel.add(nimField, gbc);

        // Tombol Login
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("ENTER");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(Color.YELLOW);
        loginButton.setForeground(Color.BLACK);
        formPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String nim = nimField.getText();

                if (name.isEmpty() || nim.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Silahkan Masukkan Nama dan NIM.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simpan nama pengguna
                    Login.setCurrentUser(name, nim);

                    // Tutup frame Login
                    JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(loginButton);
                    loginFrame.dispose();

                    // Lanjutkan ke frame kuis
                    JFrame quizFrame = new JFrame("Quiz Panel");
                    quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    quizFrame.setSize(1440, 900);

                    List<Question> questions = fetchQuestionsFromDatabase(); // Ambil data soal dari DB
                    QuizPanel quizPanel = new QuizPanel(questions);
                    quizFrame.add(quizPanel);

                    quizFrame.setVisible(true);
                }
            }
        });

        // Tambahkan formPanel ke backgroundPanel
        backgroundPanel.add(formPanel);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
