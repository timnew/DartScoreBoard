package com.github.timnew.dartscoreboard.scorekeyboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegmentList;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import static com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegmentList.InputResultHandler;

@EFragment(R.layout.score_keyboard)
public class ScoreKeyboardFragment extends Fragment {

    private InputSegmentList segments;
    private SegmentRender render;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        segments = new InputSegmentList();
        render = new SegmentRender(getActivity().getApplicationContext());
    }

    @ViewById(R.id.current_input)
    protected EditText currentInput;

    @Click({
            R.id.d1, R.id.d2, R.id.d3, R.id.backspace,
            R.id.d4, R.id.d5, R.id.d6, R.id.bust,
            R.id.d7, R.id.d8, R.id.d9, R.id.plus,
            R.id.x2, R.id.d0, R.id.x3, R.id.enter
    })
    public void onKeyPressed(View button) {
        String tag = (String) button.getTag();

        ScoreKeys scoreKey = ScoreKeys.valueOf(tag);

        SegmentKeyResult result = scoreKey.applyTo(segments.getCurrentSegment());

        segments.onResult(result);

        refreshView();
    }

    public void refreshView() {
        currentInput.setText(render.render(segments), TextView.BufferType.SPANNABLE);
    }

    public void setKeyboardResultHandler(InputResultHandler handler) {
        segments.setInputResultHandler(handler);
    }
}