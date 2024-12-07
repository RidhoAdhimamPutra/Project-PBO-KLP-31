package com.example;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class RoundedPanel extends JPanel {
    private int cornerRadius;
    private float opacity = 1.0f; // Default 100% opasitas

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    public void setOpacity(float opacity) {
        this.opacity = Math.max(0.0f, Math.min(opacity, 1.0f)); // Batas antara 0.0 - 1.0
        repaint(); // Render ulang agar perubahan terlihat
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Aktifkan anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Terapkan opasitas menggunakan AlphaComposite
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        // Gambar panel bundar
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Kembalikan komposisi ke default
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        super.paintComponent(g);
    }
}
