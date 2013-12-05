package com.github.timnew.dartscoreboard.builders;

import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegment;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegmentList;

public class InputSegmentListBuilder {
    public static InputSegmentList inputSegmentList(InputSegment... segments) {
        InputSegmentList list = new InputSegmentList();

        for (InputSegment segment : segments) {
            list.push(segment);
        }

        return list;
    }
}
