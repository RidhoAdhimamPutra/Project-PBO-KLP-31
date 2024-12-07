package com.example;

public class LeaderboardEntry {
    private String playerName;
    private int score;

    public LeaderboardEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%-20s : %5d", playerName, score);
    }
}
