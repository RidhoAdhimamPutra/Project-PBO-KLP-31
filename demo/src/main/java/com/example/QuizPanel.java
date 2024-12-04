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

        // Title untuk pertanyaan
        JLabel questionTitle = new JLabel("Question");
        questionTitle.setFont(new Font("Arial", Font.BOLD, 60)); // Atur font dan ukuran teks
        questionTitle.setForeground(Color.orange); // Atur warna teks
        questionTitle.setBounds(600, 150, 300, 50); // Posisi dan ukuran label
        imgLabel.add(questionTitle); // Tambahkan ke imgLabel

        // Panel untuk pertanyaan
        RoundedPanel questionPanel = new RoundedPanel(20);
        questionPanel.setBackground(Color.LIGHT_GRAY);
        questionPanel.setOpaque(true); // Pastikan panel terlihat di atas latar belakang
        questionPanel.setBounds(320, 250, 800, 400); // Atur posisi dan ukuran panel
        questionPanel.setLayout(null); // Nonaktifkan layout manager panel soal

        // Tampilkan soal pertama
        Question currentQuestion = questions.get(currentQuestionIndex);
        JLabel questionText = new JLabel(currentQuestion.getBunyiSoal());
        questionText.setFont(new Font("Arial", Font.PLAIN, 30));
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

        // Tambahkan opsi ke panel
        questionPanel.add(opsiA);
        questionPanel.add(opsiB);
        questionPanel.add(opsiC);
        questionPanel.add(opsiD);
        questionPanel.add(opsiE);

        // Tambahkan questionPanel ke imgLabel
        imgLabel.add(questionPanel);

        // Tombol next untuk melanjutkan ke soal berikutnya
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(650, 680, 120, 40);
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
        questionText.setFont(new Font("Arial", Font.PLAIN, 30));
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
