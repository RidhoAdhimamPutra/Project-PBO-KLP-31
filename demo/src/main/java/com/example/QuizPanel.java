package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuizPanel extends JPanel {
    private List<Question> questions;
    private int currentQuestionIndex = 0;

    public QuizPanel(List<Question> questions) {
        this.questions = questions;

        // Nonaktifkan layout manager
        setLayout(null);

        // Background image
        ImageIcon background = new ImageIcon("demo/src/main/resources/4.png");
        Image bg = background.getImage().getScaledInstance(1440, 900, Image.SCALE_SMOOTH);
        background = new ImageIcon(bg);

        // Label untuk gambar latar
        JLabel imgLabel = new JLabel(background);
        imgLabel.setBounds(0, 0, 1440, 900); // Atur ukuran dan posisi gambar latar
        this.add(imgLabel); // Tambahkan ke panel utama

        // Panel profil di pojok kanan atas
        RoundedPanel profilePanel = new RoundedPanel(20);
        profilePanel.setBackground(Color.white);
        profilePanel.setOpacity(0.7f); // Opasitas panel profil
        profilePanel.setBounds(1200, 20, 200, 50); // Posisi di pojok kanan atas
        profilePanel.setLayout(null);

        // Label untuk nama
        JLabel nameLabel = new JLabel("Shandy Ananta");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBounds(10, 5, 180, 20); // Atur posisi nama
        profilePanel.add(nameLabel);

        // Label untuk NIM
        JLabel nimLabel = new JLabel("F1D022099");
        nimLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nimLabel.setBounds(10, 25, 180, 20); // Atur posisi NIM
        profilePanel.add(nimLabel);

        // Tambahkan profilePanel ke imgLabel
        imgLabel.add(profilePanel);

        // Title untuk pertanyaan
        JLabel questionTitle = new JLabel("Question");
        questionTitle.setFont(new Font("Arial", Font.BOLD, 60)); // Atur font dan ukuran teks
        questionTitle.setForeground(Color.orange); // Atur warna teks
        questionTitle.setBounds(600, 150, 300, 50); // Posisi dan ukuran label
        imgLabel.add(questionTitle); // Tambahkan ke imgLabel

        // Panel untuk pertanyaan
        RoundedPanel questionPanel = new RoundedPanel(50);
        questionPanel.setBackground(Color.white);
        questionPanel.setOpacity(0.7f); // Atur opasitas ke 70%
        questionPanel.setBounds(320, 250, 800, 400);
        questionPanel.setLayout(null);

        // Tampilkan soal pertama
        Question currentQuestion = questions.get(currentQuestionIndex);
        JLabel questionText = new JLabel(currentQuestion.getBunyiSoal());
        questionText.setFont(new Font("Serif", Font.PLAIN, 30));
        questionText.setBounds(10, 10, 780, 50); // Atur posisi dan ukuran teks soal
        questionPanel.add(questionText);

        // Menampilkan opsi
        JLabel opsiA = new JLabel("A. " + currentQuestion.getOpsiA());
        JLabel opsiB = new JLabel("B. " + currentQuestion.getOpsiB());
        JLabel opsiC = new JLabel("C. " + currentQuestion.getOpsiC());
        JLabel opsiD = new JLabel("D. " + currentQuestion.getOpsiD());
        JLabel opsiE = new JLabel("E. " + currentQuestion.getOpsiE());

        // Atur posisi dan ukuran teks opsi
        opsiA.setBounds(10, 70, 780, 30);
        opsiB.setBounds(10, 110, 780, 30);
        opsiC.setBounds(10, 150, 780, 30);
        opsiD.setBounds(10, 190, 780, 30);
        opsiE.setBounds(10, 230, 780, 30);

        opsiA.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiB.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiC.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiD.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiE.setFont(new Font("Serif", Font.PLAIN, 20));

        // Tambahkan opsi ke panel
        questionPanel.add(opsiA);
        questionPanel.add(opsiB);
        questionPanel.add(opsiC);
        questionPanel.add(opsiD);
        questionPanel.add(opsiE);

        // Tambahkan questionPanel ke imgLabel
        imgLabel.add(questionPanel);

        // Gambar untuk tombol next
        ImageIcon nextIcon = new ImageIcon("demo/src/main/resources/next.png");

        // Tombol next untuk melanjutkan ke soal berikutnya
        JButton nextButton = new JButton(nextIcon);
        nextButton.setBounds(650, 680, 120, 120);
        nextButton.setContentAreaFilled(false); // Hilangkan background tombol
        nextButton.setBorderPainted(false);     // Hilangkan border tombol
        nextButton.setFocusPainted(false);      // Hilangkan efek fokus tombol
        nextButton.setOpaque(false);            // Buat tombol transparan

        nextButton.addActionListener(e -> {
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;
                updateQuestion(questionPanel);
            }
        });
        imgLabel.add(nextButton);
    }

    private void updateQuestion(JPanel questionPanel) {
        // Ambil soal berikutnya
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Hapus komponen soal lama
        questionPanel.removeAll();

        // Tambahkan soal baru
        JLabel questionText = new JLabel(currentQuestion.getBunyiSoal());
        questionText.setFont(new Font("Serif", Font.PLAIN, 30));
        questionText.setBounds(10, 10, 780, 50);
        questionPanel.add(questionText);

        // Menampilkan opsi baru
        JLabel opsiA = new JLabel("A. " + currentQuestion.getOpsiA());
        JLabel opsiB = new JLabel("B. " + currentQuestion.getOpsiB());
        JLabel opsiC = new JLabel("C. " + currentQuestion.getOpsiC());
        JLabel opsiD = new JLabel("D. " + currentQuestion.getOpsiD());
        JLabel opsiE = new JLabel("E. " + currentQuestion.getOpsiE());

        // Atur posisi dan ukuran teks opsi
        opsiA.setBounds(10, 70, 780, 30);
        opsiB.setBounds(10, 110, 780, 30);
        opsiC.setBounds(10, 150, 780, 30);
        opsiD.setBounds(10, 190, 780, 30);
        opsiE.setBounds(10, 230, 780, 30);

        opsiA.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiB.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiC.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiD.setFont(new Font("Serif", Font.PLAIN, 20));
        opsiE.setFont(new Font("Serif", Font.PLAIN, 20));

        // Tambahkan opsi ke panel
        questionPanel.add(opsiA);
        questionPanel.add(opsiB);
        questionPanel.add(opsiC);
        questionPanel.add(opsiD);
        questionPanel.add(opsiE);

        // Update panel
        questionPanel.revalidate();
        questionPanel.repaint();
    }
}
