package com.github.timnew.dartscoreboard.scoregrid;

public class SimplePlayer implements PlayerScoreInfo {
    private String playerName;
    private int totalScore;

    public SimplePlayer() {
    }

    @Override
    public CharSequence getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int subtractScore(int score) {
        if (score > totalScore)
            return totalScore;

        return totalScore -= score;
    }

    @Override
    public int getSets() {
        return NOT_AVAILABLE;
    }

    @Override
    public int getLegs() {
        return NOT_AVAILABLE;
    }

    @Override
    public CharSequence getHint() {
        return "";
    }

    @Override
    public float getAverage() {
        return NOT_AVAILABLE;
    }

    @Override
    public int getBest() {
        return NOT_AVAILABLE;
    }
}
