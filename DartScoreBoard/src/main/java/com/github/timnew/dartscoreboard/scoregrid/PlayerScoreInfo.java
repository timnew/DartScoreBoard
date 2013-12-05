package com.github.timnew.dartscoreboard.scoregrid;

public interface PlayerScoreInfo {
    public static int NOT_AVAILABLE = -1;

    CharSequence getPlayerName();

    int getTotalScore();

    int getSets();

    int getLegs();

    CharSequence getHint();

    float getAverage();

    int getBest();
}
