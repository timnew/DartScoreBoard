package com.github.timnew.dartscoreboard.scorekeyboard.segments;

import com.github.timnew.dartscoreboard.scorekeyboard.KeyPressHandler;
import com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag;

public interface InputSegment extends KeyPressHandler {

    int getBaseScore();

    ScoreFlag getScoreFlag();

    boolean isBusted();

    int getTotalScore();
}


