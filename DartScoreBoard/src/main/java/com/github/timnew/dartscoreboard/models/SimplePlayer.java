package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

public class SimplePlayer implements PlayerScoreInfo {

    private String playerName;
    private int totalScore;
    private boolean activated;

    public SimplePlayer(String playerName, int totalScore) {
        this.playerName = playerName;
        this.totalScore = totalScore;
        activated = false;
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

    @Override
    public boolean submitScore(int score) {
        if (score > totalScore)
            return false;

        totalScore -= score;

        return true;
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

    @Override
    public void win() {
        // Do nothing
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public PlayerScoreInfo activate() {
        activated = true;
        return this;
    }

    @Override
    public PlayerScoreInfo deactivate() {
        activated = false;
        return this;
    }
}
