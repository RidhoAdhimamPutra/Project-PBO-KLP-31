package com.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Image image;

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    public RoundedPanel(Image image) {
        this.image = image;
        this.cornerRadius = Math.min(getWidth(), getHeight()) / 2;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (image != null) {
            // Menggambar gambar bundar
            int diameter = Math.min(getWidth(), getHeight());
            g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, diameter, diameter));
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Menggambar panel bundar
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
    }
}
