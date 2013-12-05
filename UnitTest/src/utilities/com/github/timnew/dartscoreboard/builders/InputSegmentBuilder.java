package com.github.timnew.dartscoreboard.builders;

import com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.HybridSegment;

import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.BUSTED;

public class InputSegmentBuilder {
    public static HybridSegment newSegment(int baseScore, ScoreFlag flag) {
        HybridSegment segment = new HybridSegment();

        segment.setBaseScore(baseScore);
        segment.setScoreFlag(flag);

        return segment;
    }

    public static HybridSegment newSegment() {
        return new HybridSegment();
    }

    public static HybridSegment newBustedSegment() {
        return newSegment(0, BUSTED);
    }
}
