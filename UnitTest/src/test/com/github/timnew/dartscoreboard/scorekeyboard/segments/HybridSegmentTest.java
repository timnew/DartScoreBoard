package com.github.timnew.dartscoreboard.scorekeyboard.segments;

import com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag;

import org.junit.Before;
import org.junit.Test;

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
        assertSegment(0, NORMAL);
    }

    private void assertSegment(int baseScore, ScoreFlag scoreFlag) {
        assertThat(segment.getBaseScore()).isEqualTo(baseScore);
        assertThat(segment.getScoreFlag()).isEqualTo(scoreFlag);
    }

    @Test
    public void should_handle_digit_input() {
        assertSegment(0, NORMAL);

        assertThat(segment.onDigits(1)).isEqualTo(UPDATE);
        assertSegment(1, NORMAL);

        assertThat(segment.onDigits(5)).isEqualTo(UPDATE);
        assertSegment(15, NORMAL);

        assertThat(segment.onDigits(8)).isEqualTo(UPDATE);
        assertSegment(158, NORMAL);
    }

    @Test
    public void should_handle_times_input() {
        assertThat(segment.getScoreFlag()).isEqualTo(NORMAL);

        assertThat(segment.onTimes(DOUBLE)).isEqualTo(NEXT);
        assertSegment(0, DOUBLE);

        assertThat(segment.onTimes(TRIPLE)).isEqualTo(NEXT);
        assertSegment(0, TRIPLE);
    }

    @Test
    public void should_handle_enter() {
        assertThat(segment.onEnter()).isEqualTo(COMMIT);

        assertSegment(0, NORMAL);
    }

    @Test
    public void should_handle_plus() {
        assertThat(segment.onPlus()).isEqualTo(NEXT);

        assertSegment(0, NORMAL);
    }

    @Test
    public void should_handle_bust() {
        assertThat(segment.isBusted()).isFalse();

        assertThat(segment.onBust()).isEqualTo(BUST);

        assertSegment(0, BUSTED);

        assertThat(segment.isBusted()).isTrue();
    }

    @Test
    public void should_handle_backspace() {
        segment.setBaseScore(20);
        segment.setScoreFlag(DOUBLE);

        assertThat(segment.onBackspace()).isEqualTo(UPDATE);
        assertSegment(20, NORMAL);

        assertThat(segment.onBackspace()).isEqualTo(UPDATE);
        assertSegment(2, NORMAL);

        assertThat(segment.onBackspace()).isEqualTo(DELETE);
        assertSegment(0, NORMAL);
    }

    @Test
    public void should_handle_backspace_on_busted() {
        segment.onBust();

        assertThat(segment.onBackspace()).isEqualTo(UPDATE);
        assertSegment(0, NORMAL);
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
