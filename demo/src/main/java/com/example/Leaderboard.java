package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

        JPanel leaderboardPanel = new JPanel(new GridBagLayout());
        leaderboardPanel.setBounds(520, 300, 400, 300);
        leaderboardPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.gridx = 0;

        // Header row
        JLabel headerLabel = new JLabel("Nama | NIM | IQ", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);
        gbc.gridy = 0;
        leaderboardPanel.add(headerLabel, gbc);

        // Adding entries
        for (int i = 0; i < entries.size(); i++) {
            LeaderboardEntry entry = entries.get(i);
            JLabel entryLabel = new JLabel(entry.toString(), SwingConstants.CENTER);
            entryLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            entryLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));
            entryLabel.setOpaque(true);
            entryLabel.setBackground(i % 2 == 0 ? Color.WHITE : new Color(240, 240, 240)); // Alternating row colors
            gbc.gridy = i + 1;
            leaderboardPanel.add(entryLabel, gbc);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
