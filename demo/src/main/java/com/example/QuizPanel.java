package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizPanel extends JPanel {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private ButtonGroup buttonGroup;
    private JLabel questionText;

    public QuizPanel(List<Question> questions) {
        this.questions = questions;

        // Nonaktifkan layout manager
        setLayout(null);

        // Background image
        ImageIcon background = new ImageIcon("demo/src/main/resources/4.png");
        Image bg = background.getImage().getScaledInstance(1440, 900, Image.SCALE_SMOOTH);
        background = new ImageIcon(bg);

        JLabel imgLabel = new JLabel(background);
        imgLabel.setBounds(0, 0, 1440, 900); // Atur ukuran dan posisi gambar latar
        this.add(imgLabel);

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
        JRadioButton opsiA = new JRadioButton();
        JRadioButton opsiB = new JRadioButton();
        JRadioButton opsiC = new JRadioButton();
        JRadioButton opsiD = new JRadioButton();
        JRadioButton opsiE = new JRadioButton();

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
        nextButton.setBounds(650, 680, 120, 40);
        imgLabel.add(nextButton);

        // Action Listener untuk tombol next
        nextButton.addActionListener(e -> {
            // Validasi apakah user memilih jawaban
            if (buttonGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Pilih jawaban terlebih dahulu!", "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Ambil jawaban user
            String selectedAnswer = "";
            if (opsiA.isSelected()) selectedAnswer = "A";
            else if (opsiB.isSelected()) selectedAnswer = "B";
            else if (opsiC.isSelected()) selectedAnswer = "C";
            else if (opsiD.isSelected()) selectedAnswer = "D";
            else if (opsiE.isSelected()) selectedAnswer = "E";

            // Cek jawaban
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (selectedAnswer.equalsIgnoreCase(currentQuestion.getJawaban())) {
                correctAnswers++;
            } else {
                incorrectAnswers++;
            }

            // Reset pilihan
            buttonGroup.clearSelection();

            // Lanjutkan ke soal berikutnya atau tampilkan hasil
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;
                updateQuestion(questionPanel, opsiA, opsiB, opsiC, opsiD, opsiE);
            } else {
                showResult();
            }
        });

        // Tampilkan soal pertama
        updateQuestion(questionPanel, opsiA, opsiB, opsiC, opsiD, opsiE);
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

        // Refresh tampilan
        questionPanel.revalidate();
        questionPanel.repaint();
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "Hasil Kuis:\nBenar: " + correctAnswers + "\nSalah: " + incorrectAnswers,
                "Hasil Akhir", JOptionPane.INFORMATION_MESSAGE);
    }
}
