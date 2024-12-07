package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends JPanel {
    public Leaderboard(List<LeaderboardEntry> entries) {
        setLayout(null);

        // Title
        JLabel title = new JLabel("Leaderboard");
        title.setFont(new Font("Arial", Font.BOLD, 80));
        title.setForeground(Color.ORANGE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 50, 1440, 100);
        add(title);

        // Panel for leaderboard entries
        JPanel leaderboardPanel = new JPanel(new GridLayout(entries.size(), 1, 10, 10));
        leaderboardPanel.setBounds(320, 200, 800, 500);
        leaderboardPanel.setBackground(Color.WHITE);

        // Adding entries
        for (LeaderboardEntry entry : entries) {
            JLabel entryLabel = new JLabel(entry.toString());
            entryLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            entryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            entryLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            leaderboardPanel.add(entryLabel);
        }

        add(leaderboardPanel);

        // Restart and Exit buttons
        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(520, 750, 150, 40);
        restartButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Restarting...", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        add(restartButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(770, 750, 150, 40);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440, 900);
        frame.setLayout(new BorderLayout());

        // Layar ucapan terima kasih
        JPanel thankYouPanel = new JPanel(null);

        // Tambahkan background ucapan terima kasih
        ImageIcon bgIcon = new ImageIcon("demo/src/main/resources/terimakasih.png");
        Image bgImage = bgIcon.getImage().getScaledInstance(1440, 900, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
        bgLabel.setBounds(0, 0, 1440, 900);
        thankYouPanel.add(bgLabel);

        // Tambahkan tombol untuk menuju leaderboard
        JButton leaderboardButton = new JButton(new ImageIcon("demo/src/main/resources/End.png"));
        leaderboardButton.setBorderPainted(false);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.setFocusPainted(false);
        leaderboardButton.setBounds(620, 500, 200, 200);
        leaderboardButton.addActionListener(e -> {
            // Ganti ke layar leaderboard
            frame.getContentPane().removeAll();
            List<LeaderboardEntry> entries = new ArrayList<>();
            entries.add(new LeaderboardEntry("Alice", 95));
            entries.add(new LeaderboardEntry("Bob", 88));
            entries.add(new LeaderboardEntry("Charlie", 76));
            entries.add(new LeaderboardEntry("Diana", 60));
            frame.add(new Leaderboard(entries));
            frame.revalidate();
            frame.repaint();
        });
        bgLabel.add(leaderboardButton);

        // Tambahkan panel ucapan terima kasih ke frame
        frame.add(thankYouPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
