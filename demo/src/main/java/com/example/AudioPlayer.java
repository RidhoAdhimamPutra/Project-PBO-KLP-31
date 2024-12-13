package com.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip;

    public void play(String audioFilePath) {
        try {
            // Load file audio
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Muat audio ke Clip
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Atur agar lagu diputar terus-menerus
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Mulai pemutaran
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
