package com.github.timnew.dartscoreboard.scorekeyboard.builders;

import com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.HybridSegment;

public class InputSegmentBuilder {
    public static HybridSegment newSegment(int baseScore, ScoreFlag flag) {
        HybridSegment segment = new HybridSegment();

        segment.setBaseScore(baseScore);
        segment.setScoreFlag(flag);

        return segment;
    }
}
