package com.github.timnew.dartscoreboard.simplescoreboard;

import android.app.AlertDialog;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.FragmentTestHelper.startFragment;
import static org.robolectric.Robolectric.shadowOf;
import static org.robolectric.UiActions.buttons;
import static org.robolectric.UiActions.clickOn;

@RunWith(DartScoreBoardTestRunner.class)
public class SimpleScoreFragmentTest {

    private SimpleScoreFragment fragment;

    @Before
    public void setUp() throws Exception {
        fragment = SimpleScoreFragment_.builder().build();

        startFragment(fragment);
    }

    @Test
    public void should_win() {
        clickOn(buttons(fragment, R.id.d5, R.id.d0, R.id.d1, R.id.enter));

        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog).isNotNull();

        ShadowAlertDialog shadowAlertDialog = shadowOf(alertDialog);

        assertThat(shadowAlertDialog.getTitle()).isEqualTo("Game Finish");
        assertThat(shadowAlertDialog.getMessage()).isEqualTo("Player 1 wins");
    }
}
