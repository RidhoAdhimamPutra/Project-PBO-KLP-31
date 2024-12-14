package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440, 900);
        frame.setLayout(new BorderLayout());

        JPanel thankYouPanel = new JPanel(null);

        ImageIcon bgIcon = new ImageIcon("demo/src/main/resources/terimakasih.png");
        Image bgImage = bgIcon.getImage().getScaledInstance(1440, 900, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
        bgLabel.setBounds(0, 0, 1440, 900);
        thankYouPanel.add(bgLabel);
        List<LeaderboardEntry> entries = new ArrayList<>();

        JButton leaderboardButton = new JButton(new ImageIcon("demo/src/main/resources/End.png"));
        leaderboardButton.setBorderPainted(false);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.setFocusPainted(false);
        leaderboardButton.setBounds(620, 500, 200, 200);
        leaderboardButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new Leaderboard(Appquiz.leaderboardEntries));
            frame.add(new Leaderboard(entries));
            frame.revalidate();
            frame.repaint();
        });

        bgLabel.add(leaderboardButton);

        frame.add(thankYouPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
