package com.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Appquiz {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/iq_test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

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

    public void startQuiz() {
        JFrame quizFrame = new JFrame("Kuis IQ");
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.setSize(1440, 900);
        quizFrame.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("demo/src/main/resources/1 (2).png");
        Image image = imageIcon.getImage().getScaledInstance(quizFrame.getWidth(), quizFrame.getHeight(),
                Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setLayout(new BorderLayout());

        JButton startButton = new JButton(new ImageIcon("demo/src/main/resources/playbutton.png"));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(200, 100));
        startButton.setBorder(new EmptyBorder(80, 40, 10, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(startButton);

        imageLabel.add(buttonPanel, BorderLayout.CENTER);

        quizFrame.add(imageLabel, BorderLayout.CENTER);
        quizFrame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizFrame.remove(imageLabel);
                List<Question> questions = fetchQuestionsFromDatabase();
                quizFrame.add(new QuizPanel(questions), BorderLayout.CENTER);
                quizFrame.revalidate();
                quizFrame.repaint();
            }
        });
    }

    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel();
        return quizPanel;
    }
}
