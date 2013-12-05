package com.github.timnew.dartscoreboard.scorekeyboard;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegment;

public class SegmentRender {
    private Context context;

    public SegmentRender(Context context) {
        this.context = context;
    }

    public <T extends InputSegment> Spanned render(Iterable<T> segments) {
        SpannableStringBuilder buffer = new SpannableStringBuilder();

        for (InputSegment segment : segments) {
            renderSegment(buffer, segment);
        }

        return buffer;
    }

    private void renderSegment(SpannableStringBuilder buffer, InputSegment segment) {
        switch (segment.getScoreFlag()) {
            case NORMAL:
                appendTextWithColor(buffer, Integer.toString(segment.getBaseScore()), getColor(R.color.score_base_color));
                break;
            case DOUBLE:
                appendTextWithColor(buffer, Integer.toString(segment.getBaseScore()), getColor(R.color.score_base_color));
                appendTextWithColor(buffer, String.format("x%d", 2), getColor(R.color.score_times_color));
                break;
            case TRIPLE:
                appendTextWithColor(buffer, Integer.toString(segment.getBaseScore()), getColor(R.color.score_base_color));
                appendTextWithColor(buffer, String.format("x%d", 3), getColor(R.color.score_times_color));
                break;
            case BUSTED:
                appendTextWithColor(buffer, "Busted", getColor(R.color.score_busted_color));
                break;
        }
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
