package com.github.timnew.dartscoreboard.scorekeyboard;

public interface KeyPressHandler {

    SegmentKeyResult onDigit(int digit);

    SegmentKeyResult onTimes(ScoreFlag flag);

    SegmentKeyResult onPlus();

    SegmentKeyResult onEnter();

    SegmentKeyResult onBackspace();

    SegmentKeyResult onBust();
}
