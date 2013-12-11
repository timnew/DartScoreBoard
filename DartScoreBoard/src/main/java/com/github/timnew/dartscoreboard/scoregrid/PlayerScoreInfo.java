package com.github.timnew.dartscoreboard.scoregrid;

public interface PlayerScoreInfo {

    public static int NOT_AVAILABLE = -1;

    CharSequence getPlayerName();

    int getTotalScore();

    boolean submitScore(int score);

    int getSets();

    int getLegs();

    CharSequence getHint();

    float getAverage();

    int getBest();

    void win();

    boolean isActivated();

    PlayerScoreInfo deactivate();

    PlayerScoreInfo activate();
}
