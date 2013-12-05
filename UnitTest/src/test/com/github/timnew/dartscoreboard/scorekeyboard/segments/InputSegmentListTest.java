package com.github.timnew.dartscoreboard.scorekeyboard.segments;

import com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult;

import org.junit.Before;
import org.junit.Test;

import static com.github.timnew.dartscoreboard.conditions.InputSegmentCondition.inputSegmentWith;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.BUSTED;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.DOUBLE;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.NORMAL;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.TRIPLE;
import static com.github.timnew.dartscoreboard.scorekeyboard.builders.InputSegmentBuilder.newSegment;
import static org.fest.assertions.api.Assertions.assertThat;

public class InputSegmentListTest {

    private InputSegmentList list;

    @Before
    public void setUp() throws Exception {
        list = new InputSegmentList();
    }

    @Test
    public void should_add_segment() {
        assertThat(list).hasSize(0);

        InputSegment segment = list.addSegment();

        assertThat(list).hasSize(1);

        assertThat(segment)
                .isNotNull()
                .isSameAs(list.firstElement())
                .is(inputSegmentWith(0, NORMAL));

        list.addSegment();

        assertThat(list).hasSize(2);
    }

    @Test
    public void should_get_current_segment() {
        assertThat(list).hasSize(0);

        InputSegment first = list.getCurrentSegment();

        assertThat(list).hasSize(1);

        assertThat(first)
                .isNotNull()
                .is(inputSegmentWith(0, NORMAL));

        InputSegment second = list.getCurrentSegment();

        assertThat(list).hasSize(1);

        assertThat(first).isSameAs(second);
    }

    @Test
    public void should_get_total_score() {
        list.push(newSegment(20, TRIPLE));
        list.push(newSegment(10, DOUBLE));
        list.push(newSegment(15, NORMAL));

        assertThat(list.getTotalScore()).isEqualTo(20 * 3 + 10 * 2 + 15);
    }

    @Test
    public void should_get_total_score_0_for_busted() {
        list.push(newSegment(20, TRIPLE));
        list.push(newSegment(10, DOUBLE));
        list.push(newSegment(0, BUSTED));

        assertThat(list.getTotalScore()).isEqualTo(0);
    }

    @Test
    public void should_commit_total_score() {
        list.push(newSegment(20, NORMAL));
        list.push(newSegment(20, NORMAL));
        list.push(newSegment(20, NORMAL));

        final int[] result = {0};

        list.setInputResultHandler(new InputSegmentList.InputResultHandler() {
            @Override
            public void onCommit(int totalScore) {
                result[0] = totalScore;
            }
        });

        list.onResult(SegmentKeyResult.COMMIT);

        assertThat(result[0]).isEqualTo(60);
    }

    @Test
    public void should_commit_0_when_busted() {
        list.push(newSegment(20, NORMAL));
        list.push(newSegment(20, NORMAL));

        final int[] result = {0};

        list.setInputResultHandler(new InputSegmentList.InputResultHandler() {
            @Override
            public void onCommit(int totalScore) {
                result[0] = totalScore;
            }
        });

        list.onResult(SegmentKeyResult.BUST);

        assertThat(result[0]).isEqualTo(0);
    }

    @Test
    public void should_add_new_when_next() {
        assertThat(list).hasSize(0);

        list.onResult(SegmentKeyResult.NEXT);

        assertThat(list).hasSize(1);

        list.onResult(SegmentKeyResult.NEXT);

        assertThat(list).hasSize(2);
    }

    @Test
    public void should_delete() {
        list.push(newSegment(0, NORMAL));
        list.push(newSegment(0, NORMAL));

        assertThat(list).hasSize(2);

        list.onResult(SegmentKeyResult.DELETE);

        assertThat(list).hasSize(1);

        list.onResult(SegmentKeyResult.DELETE);

        assertThat(list).hasSize(0);
    }
}
