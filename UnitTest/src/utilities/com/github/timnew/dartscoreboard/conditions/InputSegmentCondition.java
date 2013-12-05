package com.github.timnew.dartscoreboard.conditions;

import com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegment;

import org.fest.assertions.core.Condition;

public class InputSegmentCondition extends Condition<InputSegment> {

    public static final String DESCRIPTION_TEMPLATE = "Input Segment with score %d, and flag %s";
    private int expectedBaseScore;
    private ScoreFlag expectedFlag;

    public InputSegmentCondition(int expectedBaseScore, ScoreFlag expectedFlag) {
        super(String.format(DESCRIPTION_TEMPLATE, expectedBaseScore, expectedFlag));

        this.expectedBaseScore = expectedBaseScore;
        this.expectedFlag = expectedFlag;
    }

    @Override
    public boolean matches(InputSegment segment) {
        return segment.getBaseScore() == expectedBaseScore &&
                segment.getScoreFlag() == expectedFlag;
    }

    public static InputSegmentCondition inputSegmentWith(int baseScore, ScoreFlag flag) {
        return new InputSegmentCondition(baseScore, flag);
    }
}
