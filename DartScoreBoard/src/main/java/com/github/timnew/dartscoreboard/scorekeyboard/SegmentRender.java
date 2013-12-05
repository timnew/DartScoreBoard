package com.github.timnew.dartscoreboard.scorekeyboard;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegment;

import java.util.ArrayList;

import static com.google.common.collect.Iterables.addAll;

public class SegmentRender {
    public static final String PLUS = " + ";
    public static final String TIMES_2 = " x 2";
    public static final String TIMES_3 = " x 3";
    public static final String BUSTED = "Busted";
    private Context context;

    public SegmentRender(Context context) {
        this.context = context;
    }

    public <T extends InputSegment> Spanned render(Iterable<T> segments) {
        SpannableStringBuilder buffer = new SpannableStringBuilder();

        ArrayList<T> ts = new ArrayList<T>();
        addAll(ts, segments);

        if (ts.size() == 0)
            return buffer;

        T first = ts.remove(0);
        if (!first.isNew())
            renderSegment(buffer, first);

        if (ts.size() == 0)
            return buffer;

        int lastIndex = ts.size() - 1;
        T last = ts.get(lastIndex);
        if (last.isNew()) {
            ts.remove(lastIndex);
        }

        for (T segment : ts) {
            renderPlus(buffer, R.color.score_plus);
            renderSegment(buffer, segment);
        }

        if (last.isNew()) {
            renderPlus(buffer, R.color.score_hint);
        }

        return buffer;
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
