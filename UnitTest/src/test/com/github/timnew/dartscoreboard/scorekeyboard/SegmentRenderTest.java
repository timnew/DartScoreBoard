package com.github.timnew.dartscoreboard.scorekeyboard;

import android.text.Spanned;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static com.github.timnew.dartscoreboard.builders.InputSegmentBuilder.newSegment;
import static com.github.timnew.dartscoreboard.builders.InputSegmentListBuilder.inputSegmentList;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.BUSTED;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.DOUBLE;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.NORMAL;
import static com.github.timnew.dartscoreboard.scorekeyboard.ScoreFlag.TRIPLE;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.SpannedContentTextCondition.contentText;
import static org.fest.assertions.conditions.android.SpannedTextColorCondition.textResColor;

@RunWith(DartScoreBoardTestRunner.class)
public class SegmentRenderTest {

    private SegmentRender render;

    @Before
    public void setUp() throws Exception {
        render = new SegmentRender(Robolectric.application);
    }

    @Test
    public void should_render_empty() {
        Spanned rendered = render.render(inputSegmentList());

        assertThat(rendered).has(contentText(""));
    }

    @Test
    public void should_render_busted() {
        Spanned rendered = render.render(inputSegmentList(newSegment(0, BUSTED)));

        assertThat(rendered)
                .has(contentText("Busted = Busted"))
                .has(textResColor(R.color.score_busted_color, 0, 5))
                .has(textResColor(R.color.score_busted_color, 9, 14));
    }

    @Test
    public void should_render_normal_score() {
        Spanned renedered = render.render(inputSegmentList(newSegment(20, NORMAL)));

        assertThat(renedered)
                .has(contentText("20 = 20"))
                .has(textResColor(R.color.score_base_color, 0, 1));
    }

    @Test
    public void should_render_timed_score() {
        Spanned renedered = render.render(inputSegmentList(newSegment(20, DOUBLE)));

        assertThat(renedered)
                .has(contentText("20 x 2 = 40"))
                .has(textResColor(R.color.score_base_color, 0, 1))
                .has(textResColor(R.color.score_times_color, 3, 5));
    }

    @Test
    public void should_render_plus() {
        Spanned renedered = render.render(inputSegmentList(newSegment(20, DOUBLE), newSegment(20, TRIPLE), newSegment(25, NORMAL)));

        assertThat(renedered)
                .has(contentText("20 x 2 + 20 x 3 + 25 = 125"));
    }

    @Test
    public void should_not_render_new() {
        Spanned rendered = render.render(inputSegmentList(newSegment()));

        assertThat(rendered).has(contentText(""));
    }

    @Test
    public void should_render_plus_correct() {
        Spanned rendered = render.render(inputSegmentList(newSegment(20, NORMAL), newSegment(20, NORMAL), newSegment()));

        assertThat(rendered)
                .has(contentText("20 + 20 +  = 40"))
                .has(textResColor(R.color.score_symbol, 3))
                .has(textResColor(R.color.score_hint, 8));
    }

    @Test
    public void should_render_result() {
        Spanned rendered = render.render(inputSegmentList(newSegment(20, TRIPLE)));

        assertThat(rendered)
                .has(contentText("20 x 3 = 60"))
                .has(textResColor(R.color.score_symbol, 7))
                .has(textResColor(R.color.score_result, 9, 10));
    }
}
