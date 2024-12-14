package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Leaderboard extends JPanel {
    private Image backgroundImage;

    public Leaderboard(List<LeaderboardEntry> entries) {
        backgroundImage = new ImageIcon("demo/src/main/resources/iqscore.png").getImage();

        setLayout(null);

        JPanel leaderboardPanel = new JPanel(new GridLayout(entries.size(), 1, 10, 10));
        leaderboardPanel.setBounds(520, 300, 400, 200);
        leaderboardPanel.setBackground(Color.white);
        leaderboardPanel.setOpaque(false);

        // Adding entries
        for (LeaderboardEntry entry : entries) {
            JLabel entryLabel = new JLabel(entry.toString());
            entryLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            entryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            entryLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            leaderboardPanel.add(entryLabel);
        }

        add(leaderboardPanel);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(520, 750, 150, 40);
        restartButton.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();

            Appquiz appquiz = new Appquiz();
            appquiz.startQuiz(); // Kembali ke layar awal
        });

        add(restartButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(770, 750, 150, 40);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
