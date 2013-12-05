package com.github.timnew.dartscoreboard.scorekeyboard.segments;

import com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult;

import java.util.Stack;

public class InputSegmentList extends Stack<InputSegment> {
    private InputResultHandler inputResultHandler;

    public InputSegment addSegment() {
        HybridSegment segment = new HybridSegment();

        return push(segment);
    }

    public void setInputResultHandler(InputResultHandler inputResultHandler) {
        this.inputResultHandler = inputResultHandler;
    }

    public InputSegment getCurrentSegment() {
        if (size() == 0)
            return addSegment();

        return peek();
    }

    public void onResult(SegmentKeyResult result) {
        switch (result) {
            case NEXT:
                addSegment();
                break;
            case UPDATE:
                break;
            case DELETE:
                pop();
                break;
            case COMMIT:
                commit(getTotalScore());
                break;
            case BUST:
                commit(0);
                clear();
                break;
        }
    }

    private void commit(int totalScore) {
        if (inputResultHandler != null)
            inputResultHandler.onCommit(totalScore);
    }

    public boolean isBusted() {
        for (InputSegment segment : this) {
            if (segment.isBusted())
                return true;
        }

        return false;
    }

    public int getTotalScore() {
        if (isBusted())
            return 0;

        int totalScore = 0;

        for (InputSegment segment : this) {
            totalScore += segment.getTotalScore();
        }

        return totalScore;
    }

    public static interface InputResultHandler {
        void onCommit(int totalScore);
    }
}
