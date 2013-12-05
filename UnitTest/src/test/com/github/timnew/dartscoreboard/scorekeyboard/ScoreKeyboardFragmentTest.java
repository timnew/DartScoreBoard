package com.github.timnew.dartscoreboard.scorekeyboard;

import android.view.View;
import android.widget.TextView;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.R;
import com.google.common.base.Function;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.primitives.Ints.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.ContainsTextCondition.text;
import static org.robolectric.UiActions.clickOn;
import static org.robolectric.util.FragmentTestUtil.startFragment;

@RunWith(DartScoreBoardTestRunner.class)
public class ScoreKeyboardFragmentTest {

    private ScoreKeyboardFragment_ fragment;
    private TextView currentInput;

    @Before
    public void setUp() throws Exception {
        fragment = new ScoreKeyboardFragment_();
        startFragment(fragment);

        currentInput = (TextView) fragment.findViewById(R.id.current_input);
    }

    private View[] buttons(int... ids) {
        final View rootView = fragment.getView();

        Iterable<View> viewInterable = transform(asList(ids), new Function<Integer, View>() {
            @Override
            public View apply(Integer id) {
                return rootView.findViewById(id);
            }
        });

        return toArray(viewInterable, View.class);
    }

    @Ignore("Failed when loading theme @style/Theme_Sherlock, fix later")
    @Test
    public void should_handle_user_input() {
        clickOn(buttons(R.id.d2, R.id.d0, R.id.x2));

        assertThat(currentInput).has(text("20 x 2"));
    }
}
