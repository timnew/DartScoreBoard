package com.github.timnew.dartscoreboard.scorekeyboard;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegment;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegmentList;

import java.util.ArrayList;

import static com.google.common.collect.Iterables.addAll;

public class SegmentRender {
    public static final String PLUS = " + ";
    public static final String TIMES_2 = " x 2";
    public static final String TIMES_3 = " x 3";
    public static final String EQUALS = " = ";
    public static final String BUSTED = "Busted";
    private Context context;

    public SegmentRender(Context context) {
        this.context = context;
    }

    public Spanned render(InputSegmentList segments) {
        SpannableStringBuilder buffer = new SpannableStringBuilder();

        ArrayList<InputSegment> ts = new ArrayList<InputSegment>();
        addAll(ts, segments);

        if (ts.size() == 0)
            return renderResult(buffer, segments);

        InputSegment first = ts.remove(0);
        if (first.isNew())
            return buffer;

        renderSegment(buffer, first);

        if (ts.size() == 0)
            return renderResult(buffer, segments);

        int lastIndex = ts.size() - 1;
        InputSegment last = ts.get(lastIndex);
        if (last.isNew()) {
            ts.remove(lastIndex);
        }

        for (InputSegment segment : ts) {
            renderPlus(buffer, R.color.score_symbol);
            renderSegment(buffer, segment);
        }

        if (last.isNew()) {
            renderPlus(buffer, R.color.score_hint);
        }

        return renderResult(buffer, segments);
    }

    private void renderSegment(SpannableStringBuilder buffer, InputSegment segment) {
        switch (segment.getScoreFlag()) {
            case NORMAL:
                renderBaseScore(buffer, segment.getBaseScore());
                break;
            case DOUBLE:
                renderBaseScore(buffer, segment.getBaseScore());
                renderTimes(buffer, TIMES_2);
                break;
            case TRIPLE:
                renderBaseScore(buffer, segment.getBaseScore());
                renderTimes(buffer, TIMES_3);
                break;
            case BUSTED:
                renderBusted(buffer);
                break;
        }
    }

    private Spanned renderResult(SpannableStringBuilder buffer, InputSegmentList segments) {
        appendTextWithColor(buffer, EQUALS, getColor(R.color.score_symbol));
        if (segments.isBusted())
            appendTextWithColor(buffer, BUSTED, getColor(R.color.score_busted_color));
        else
            appendTextWithColor(buffer, Integer.toString(segments.getTotalScore()), getColor(R.color.score_result));

        return buffer;
    }

    private void renderPlus(SpannableStringBuilder buffer, int color) {
        appendTextWithColor(buffer, PLUS, getColor(color));
    }

    private void renderBaseScore(SpannableStringBuilder buffer, int baseScore) {
        appendTextWithColor(buffer, Integer.toString(baseScore), getColor(R.color.score_base_color));
    }

    private void renderTimes(SpannableStringBuilder buffer, String text) {
        appendTextWithColor(buffer, text, getColor(R.color.score_times_color));
    }

    private void renderBusted(SpannableStringBuilder buffer) {
        appendTextWithColor(buffer, BUSTED, getColor(R.color.score_busted_color));
    }

    private int getColor(int resourceId) {
        return context.getResources().getColor(resourceId);
    }

    private void appendTextWithColor(SpannableStringBuilder buffer, CharSequence text, int color) {
        int start = buffer.length();
        int end = start + text.length();
        buffer.append(text);
        buffer.setSpan(new ForegroundColorSpan(color), start, end, 0);
    }
}
