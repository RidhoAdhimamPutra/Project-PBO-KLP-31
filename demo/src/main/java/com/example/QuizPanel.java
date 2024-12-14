package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class QuizPanel extends JPanel {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/iq_test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private final List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private int unansweredQuestions = 0;
    private final ButtonGroup buttonGroup;
    private final JLabel questionText;
    private final AudioPlayer audioPlayer;
    private int remainingTime = 60; // Waktu dalam detik 
    private final JLabel timerLabel;

    public QuizPanel(List<Question> questions) {
        this.questions = questions;
        audioPlayer = new AudioPlayer();
        audioPlayer.play("demo/src/main/resources/brain.wav"); // Mulai memutar lagu

        setLayout(null);

        // Background image
        ImageIcon background = new ImageIcon("demo/src/main/resources/4.png");
        Image bg = background.getImage().getScaledInstance(1440, 900, Image.SCALE_SMOOTH);
        background = new ImageIcon(bg);

        JLabel imgLabel = new JLabel(background);
        imgLabel.setBounds(0, 0, 1440, 900); // Atur ukuran dan posisi gambar latar
        this.add(imgLabel);

        // Timer Label
        timerLabel = new JLabel(); // Format waktu awal
        timerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        timerLabel.setForeground(Color.RED);
        timerLabel.setBounds(20, 20, 500, 50); // Posisi timer
        imgLabel.add(timerLabel);

        // Thread untuk hitung mundur waktu
        startTimerThread();

        // Title "Question"
        JLabel titleLabel = new JLabel("Question");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 80)); // Memperbesar ukuran font
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setBounds(570, 150, 500, 60); // Atur posisi dan ukuran title
        imgLabel.add(titleLabel);

        // Panel untuk pertanyaan
        RoundedPanel questionPanel = new RoundedPanel(50);
        questionPanel.setBackground(Color.white);
        questionPanel.setOpacity(0.7f);
        questionPanel.setBounds(320, 250, 800, 400);
        questionPanel.setLayout(null);
        imgLabel.add(questionPanel);

        // Tambahkan komponen ke panel
        questionText = new JLabel();
        questionText.setFont(new Font("Serif", Font.PLAIN, 30));
        questionText.setBounds(10, 10, 780, 50);
        questionPanel.add(questionText);

        // RadioButton untuk opsi
        buttonGroup = new ButtonGroup();
        JRadioButton opsiA = createOptionButton();
        JRadioButton opsiB = createOptionButton();
        JRadioButton opsiC = createOptionButton();
        JRadioButton opsiD = createOptionButton();
        JRadioButton opsiE = createOptionButton();

        opsiA.setBounds(10, 70, 780, 30);
        opsiB.setBounds(10, 110, 780, 30);
        opsiC.setBounds(10, 150, 780, 30);
        opsiD.setBounds(10, 190, 780, 30);
        opsiE.setBounds(10, 230, 780, 30);

        buttonGroup.add(opsiA);
        buttonGroup.add(opsiB);
        buttonGroup.add(opsiC);
        buttonGroup.add(opsiD);
        buttonGroup.add(opsiE);

        questionPanel.add(opsiA);
        questionPanel.add(opsiB);
        questionPanel.add(opsiC);
        questionPanel.add(opsiD);
        questionPanel.add(opsiE);

        // Tombol Next
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(730, 680, 120, 40);
        imgLabel.add(nextButton);

        // Tombol Previous
        JButton prevButton = new JButton("Previous");
        prevButton.setBounds(550, 680, 120, 40);
        imgLabel.add(prevButton);

        // Action Listener untuk tombol next
        nextButton.addActionListener(e -> handleNextButton(opsiA, opsiB, opsiC, opsiD, opsiE, questionPanel));

        // Action Listener untuk tombol previous
        prevButton.addActionListener(e -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                updateQuestion(questionPanel, opsiA, opsiB, opsiC, opsiD, opsiE);
            }
        });

        // Tampilkan soal pertama
        updateQuestion(questionPanel, opsiA, opsiB, opsiC, opsiD, opsiE);
    }

    private JRadioButton createOptionButton() {
        JRadioButton optionButton = new JRadioButton();
        optionButton.setFont(new Font("Serif", Font.PLAIN, 20));
        optionButton.setOpaque(false);
        return optionButton;
    }

    private void startTimerThread() {
        Thread timerThread = new Thread(() -> {
            try {
                while (remainingTime > 0) {
                    SwingUtilities.invokeLater(() -> timerLabel.setText("Time Left: " + formatTime(remainingTime)));
                    Thread.sleep(1000);
                    remainingTime--;
                }
                SwingUtilities.invokeLater(this::showResult);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        timerThread.start();
    }
    
    private void handleNextButton(JRadioButton opsiA, JRadioButton opsiB, JRadioButton opsiC, JRadioButton opsiD,
                                  JRadioButton opsiE, JPanel questionPanel) {
        String selectedAnswer = "";
        if (opsiA.isSelected()) selectedAnswer = "A";
        else if (opsiB.isSelected()) selectedAnswer = "B";
        else if (opsiC.isSelected()) selectedAnswer = "C";
        else if (opsiD.isSelected()) selectedAnswer = "D";
        else if (opsiE.isSelected()) selectedAnswer = "E";
        else unansweredQuestions++;
    
        if (!selectedAnswer.isEmpty()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (selectedAnswer.equalsIgnoreCase(currentQuestion.getJawaban())) {
                correctAnswers++;
            } else {
                incorrectAnswers++;
            }
        }
    
        buttonGroup.clearSelection();
    
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            updateQuestion(questionPanel, opsiA, opsiB, opsiC, opsiD, opsiE);
        } else {
            showResult();
        }
    }
    
    private void updateQuestion(JPanel questionPanel, JRadioButton opsiA, JRadioButton opsiB, JRadioButton opsiC,
                                JRadioButton opsiD, JRadioButton opsiE) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionText.setText(currentQuestion.getBunyiSoal());
        opsiA.setText("A. " + currentQuestion.getOpsiA());
        opsiB.setText("B. " + currentQuestion.getOpsiB());
        opsiC.setText("C. " + currentQuestion.getOpsiC());
        opsiD.setText("D. " + currentQuestion.getOpsiD());
        opsiE.setText("E. " + currentQuestion.getOpsiE());
    
        questionPanel.revalidate();
        questionPanel.repaint();
    }
    
    private void showResult() {
    int finalIqScore = calculateIqScore(correctAnswers, questions.size()); // Hitung skor IQ berdasarkan jawaban benar
    updateUserIqInDatabase(Login.getCurrentName(), Login.getCurrentNIM(), finalIqScore);

    JOptionPane.showMessageDialog(this,
            String.format("Hasil Kuis:\nBenar: %d\nSalah: %d\nTidak Dijawab: %d", 
                    correctAnswers, incorrectAnswers, unansweredQuestions),
            "Hasil Akhir", JOptionPane.INFORMATION_MESSAGE);
    audioPlayer.stop();

    JFrame topLevelFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    if (topLevelFrame != null) {
        topLevelFrame.dispose(); 
    }

    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440, 900);
        frame.setLayout(new BorderLayout());

        List<LeaderboardEntry> entries = fetchEntriesFromDatabase(); // Panggil fetchEntriesFromDatabase untuk mendapatkan data

        frame.add(new Leaderboard(entries));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); 
    });
}

private List<LeaderboardEntry> fetchEntriesFromDatabase() {
    List<LeaderboardEntry> entries = new ArrayList<>();
    String dbUrl = "jdbc:mysql://localhost:3306/iq_test";
    String dbUser = "root";
    String dbPassword = "";

    String query = "SELECT name, nim, iq FROM users ORDER BY iq DESC";
    try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String nim = resultSet.getString("nim");
            int iq = resultSet.getInt("iq");
            entries.add(new LeaderboardEntry(name, nim, iq));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return entries;
}

    
    private int calculateIqScore(int correctAnswers, int totalQuestions) {
        // Misal perhitungan sederhana: (100 / totalQuestions) * correctAnswers
        // Anda bisa menyesuaikan formula ini sesuai kebutuhan
        return (int) (((double) correctAnswers / totalQuestions) * 100);
    }
    
    private void updateUserIqInDatabase(String name, String nim, int iq) {
        String query = "UPDATE users SET iq = ? WHERE name = ? AND nim = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, iq);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, nim);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    }
    