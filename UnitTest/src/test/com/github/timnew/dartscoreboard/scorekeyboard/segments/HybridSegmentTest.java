package com.github.timnew.dartscoreboard.scorekeyboard.segments;

import org.junit.Before;
import org.junit.Test;

import static com.github.timnew.dartscoreboard.conditions.InputSegmentCondition.inputSegmentWith;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.BUSTED;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.DOUBLE;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.NORMAL;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.TRIPLE;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.BUST;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.COMMIT;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.DELETE;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.NEXT;
import static com.github.timnew.dartscoreboard.scorekeyboard.SegmentKeyResult.UPDATE;
import static org.fest.assertions.api.Assertions.assertThat;

public class HybridSegmentTest {

    private HybridSegment segment;

    @Before
    public void setUp() throws Exception {
        segment = new HybridSegment();
    }

    @Test
    public void should_initialize_segment() {
        assertThat(segment).is(inputSegmentWith(0, NORMAL));
    }

    @Test
    public void should_handle_digit_input() {
        assertThat(segment).is(inputSegmentWith(0, NORMAL));

        assertThat(segment.onDigit(1)).isEqualTo(UPDATE);
        assertThat(segment).is(inputSegmentWith(1, NORMAL));

        assertThat(segment.onDigit(5)).isEqualTo(UPDATE);
        assertThat(segment).is(inputSegmentWith(15, NORMAL));

        assertThat(segment.onDigit(8)).isEqualTo(UPDATE);
        assertThat(segment).is(inputSegmentWith(158, NORMAL));
    }

    @Test
    public void should_handle_times_input() {
        assertThat(segment.getScoreFlag()).isEqualTo(NORMAL);

        assertThat(segment.onTimes(DOUBLE)).isEqualTo(NEXT);
        assertThat(segment).is(inputSegmentWith(0, DOUBLE));

        assertThat(segment.onTimes(TRIPLE)).isEqualTo(NEXT);
        assertThat(segment).is(inputSegmentWith(0, TRIPLE));
    }

    @Test
    public void should_handle_enter() {
        assertThat(segment.onEnter()).isEqualTo(COMMIT);

        assertThat(segment).is(inputSegmentWith(0, NORMAL));
    }

    @Test
    public void should_handle_plus() {
        assertThat(segment.onPlus()).isEqualTo(NEXT);

        assertThat(segment).is(inputSegmentWith(0, NORMAL));
    }

    @Test
    public void should_handle_bust() {
        assertThat(segment.isBusted()).isFalse();

        assertThat(segment.onBust()).isEqualTo(BUST);

        assertThat(segment).is(inputSegmentWith(0, BUSTED));

        assertThat(segment.isBusted()).isTrue();
    }

    @Test
    public void should_handle_backspace() {
        segment.setBaseScore(20);
        segment.setScoreFlag(DOUBLE);

        assertThat(segment.onBackspace()).isEqualTo(UPDATE);
        assertThat(segment).is(inputSegmentWith(20, NORMAL));

        assertThat(segment.onBackspace()).isEqualTo(UPDATE);
        assertThat(segment).is(inputSegmentWith(2, NORMAL));

        assertThat(segment.onBackspace()).isEqualTo(DELETE);
        assertThat(segment).is(inputSegmentWith(0, NORMAL));
    }

    @Test
    public void should_handle_backspace_on_busted() {
        segment.onBust();

        assertThat(segment.onBackspace()).isEqualTo(UPDATE);
        assertThat(segment).is(inputSegmentWith(0, NORMAL));
    }

    @Test
    public void should_calculate_total_score() {
        segment.setBaseScore(20);
        assertThat(segment.getTotalScore()).isEqualTo(20);

        segment.setScoreFlag(DOUBLE);
        assertThat(segment.getTotalScore()).isEqualTo(40);

        segment.setScoreFlag(TRIPLE);
        assertThat(segment.getTotalScore()).isEqualTo(60);

        segment.setScoreFlag(BUSTED);
        assertThat(segment.getTotalScore()).isEqualTo(0);
    }
}
