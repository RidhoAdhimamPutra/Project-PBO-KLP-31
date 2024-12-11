package com.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Appquiz {
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
                // Mengganti tampilan ke layar login
                quizFrame.dispose(); // Menutup frame yang ada
                Login login = new Login();
                login.showLoginScreen(); // Menampilkan layar login
            }
        });
    }
}
