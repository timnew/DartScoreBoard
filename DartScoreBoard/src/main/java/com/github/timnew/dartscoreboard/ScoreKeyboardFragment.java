package com.github.timnew.dartscoreboard;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;

@EFragment(R.layout.score_keyboard)
public class ScoreKeyboardFragment extends Fragment {

    @Click({
            R.id.d1, R.id.d2, R.id.d3,
            R.id.d4, R.id.d5, R.id.d6,
            R.id.d7, R.id.d8, R.id.d9,
            R.id.d0
    })
    public void digitClicked(View button) {
        char digit = ((Button) button).getText().toString().charAt(0);
    }

    @Click(R.id.doubleTimes)
    public void doubleClicked() {

    }

    @Click(R.id.tripleTimes)
    public void tripleClicked() {

    }
}