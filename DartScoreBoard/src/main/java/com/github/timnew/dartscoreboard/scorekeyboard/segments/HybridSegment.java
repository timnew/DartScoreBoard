package com.github.timnew.dartscoreboard.scorekeyboard.segments;

import com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag;
import com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult;

import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.BUSTED;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.NORMAL;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.BUST;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.COMMIT;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.DELETE;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.NEXT;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.UPDATE;

public class HybridSegment implements InputSegment {

    private boolean isNew;
    private int baseScore;
    private ScoreFlag scoreFlag;

    public HybridSegment() {
        isNew = true;
        baseScore = 0;
        scoreFlag = NORMAL;
    }

    public void setBaseScore(int baseScore) {
        isNew = false;
        this.baseScore = baseScore;
    }

    @Override
    public int getBaseScore() {
        return baseScore;
    }

    public void setScoreFlag(ScoreFlag scoreFlag) {
        isNew = false;

        this.scoreFlag = scoreFlag;

        if (scoreFlag == BUSTED)
            baseScore = 0;
    }

    @Override
    public ScoreFlag getScoreFlag() {
        return scoreFlag;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public boolean isBusted() {
        return scoreFlag == BUSTED;
    }

    @Override
    public int getTotalScore() {
        return scoreFlag.applyToBaseScore(baseScore);
    }

    @Override
    public SegmentKeyResult onDigit(int digit) {
        isNew = false;
        baseScore = baseScore * 10 + digit;
        return UPDATE;
    }

    @Override
    public SegmentKeyResult onTimes(ScoreFlag flag) {
        isNew = false;
        scoreFlag = flag;
        return NEXT;
    }

    @Override
    public SegmentKeyResult onPlus() {
        isNew = false;
        return NEXT;
    }

    @Override
    public SegmentKeyResult onEnter() {
        isNew = false;
        return COMMIT;
    }

    @Override
    public SegmentKeyResult onBackspace() {
        if (scoreFlag != NORMAL) {
            scoreFlag = NORMAL;
            return UPDATE;
        }

        if (baseScore > 9) {
            baseScore = baseScore / 10;
            return UPDATE;
        }

        baseScore = 0;
        return DELETE;
    }

    @Override
    public SegmentKeyResult onBust() {
        isNew = false;

        setScoreFlag(BUSTED);

        return BUST;
    }
}
