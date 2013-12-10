package com.github.timnew.dartscoreboard.scorekeyboard;

import android.widget.TextView;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.ContainsTextCondition.text;
import static org.robolectric.FragmentTestHelper.startFragment;
import static org.robolectric.UiActions.buttons;
import static org.robolectric.UiActions.clickOn;

@RunWith(DartScoreBoardTestRunner.class)
public class ScoreKeyboardFragmentTest {

    private ScoreKeyboardFragment fragment;
    private TextView currentInput;

    @Before
    public void setUp() throws Exception {
        fragment = ScoreKeyboardFragment_.builder().build();

        startFragment(fragment);

        currentInput = (TextView) this.fragment.getView().findViewById(R.id.current_input);
    }

    @Test
    public void should_handle_user_input() {
        clickOn(buttons(fragment, R.id.d2, R.id.d0, R.id.x2));

        assertThat(currentInput).has(text("20 x 2 +  = 40"));
    }
}
