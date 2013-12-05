package com.github.timnew.dartscoreboard.scorekeyboard;

import android.graphics.Color;
import android.text.Spanned;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.HybridSegment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.BUSTED;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.DOUBLE;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.NORMAL;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.SpannedContentTextCondition.contentText;
import static org.fest.assertions.conditions.android.SpannedTextColorCondition.textColor;

@RunWith(DartScoreBoardTestRunner.class)
public class SegmentRenderTest {

    private SegmentRender render;

    @Before
    public void setUp() throws Exception {
        render = new SegmentRender(Robolectric.application);
    }

    private HybridSegment newSegment(int baseScore, ScoreFlag flag) {
        HybridSegment segment = new HybridSegment();

        segment.setBaseScore(baseScore);
        segment.setScoreFlag(flag);

        return segment;
    }

    @Test
    public void should_render_busted() {
        Spanned rendered = render.render(asList(newSegment(0, BUSTED)));

        assertThat(rendered)
                .has(contentText("Busted"))
                .has(textColor(Color.RED));
    }

    @Test
    public void should_render_normal_score() {
        Spanned renedered = render.render(asList(newSegment(20, NORMAL)));

        assertThat(renedered)
                .has(contentText("20"))
                .has(textColor(0xFFCCCCCC));
    }

    @Test
    public void should_render_timed_score() {
        Spanned renedered = render.render(asList(newSegment(20, DOUBLE)));

        assertThat(renedered)
                .has(contentText("20x2"))
                .has(textColor(0xFFCCCCCC, 0, 1))
                .has(textColor(Color.YELLOW, 2, 3));
    }
}
