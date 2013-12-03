package com.github.timnew.dartscoreboard.scoregrid;

public interface PlayerScoreInfo {
    CharSequence getPlayerName();

    int getTotalScore();

    int getSets();

    int getLegs();

    CharSequence getHint();

    float getAverage();

    int getBest();
}
