package com.example;

public class LeaderboardEntry {
    private String playerName;
    private String nim;
    private int score;

    public LeaderboardEntry(String playerName, String nim, int score) {
        this.playerName = playerName;
        this.nim = nim;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getNim() {
        return nim;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s : %5d", playerName, nim, score);
    }
}
