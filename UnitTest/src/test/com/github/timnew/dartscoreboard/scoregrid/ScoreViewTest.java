package com.github.timnew.dartscoreboard.scoregrid;

import android.widget.TextView;
import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.ContainsTextCondition.text;

@RunWith(DartScoreBoardTestRunner.class)
public class ScoreViewTest {

    private ScoreView view;

    @Before
    public void setUp() throws Exception {
        view = ScoreView_.build(Robolectric.application);
    }

    @Test
    public void should_set_player_name() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public CharSequence getPlayerName() {
                return "Player 01";
            }
        });


        TextView textView = (TextView) this.view.findViewById(R.id.player_name);

        assertThat(textView).has(text("Player 01"));
    }


    private static class PlayerScoreInfoStub implements PlayerScoreInfo {

        @Override
        public CharSequence getPlayerName() {
            return null;
        }

        @Override
        public int getTotalScore() {
            return 0;
        }

        @Override
        public int getSets() {
            return 0;
        }

        @Override
        public int getLegs() {
            return 0;
        }

        @Override
        public CharSequence getHint() {
            return null;
        }

        @Override
        public float getAverage() {
            return 0;
        }

        @Override
        public int getBest() {
            return 0;
        }
    }
}
