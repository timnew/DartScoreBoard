package com.github.timnew.dartscoreboard.scoregrid;

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
    public void should_render_player_name() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public CharSequence getPlayerName() {
                return "Player 01";
            }
        });

        assertThat(view.findViewById(R.id.player_name)).has(text("Player 01"));
    }

    @Test
    public void should_render_total_score() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public int getTotalScore() {
                return 501;
            }
        });

        assertThat(view.findViewById(R.id.total_score)).has(text("501"));
    }

    @Test
    public void should_render_player_game_scores() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public int getSets() {
                return 1;
            }

            @Override
            public int getLegs() {
                return 3;
            }
        });

        assertThat(view.findViewById(R.id.player_game_score)).has(text(R.string.player_game_score_template, 1, 3));
    }

    @Test
    public void should_render_sets() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public int getTotalScore() {
                return 501;
            }
        });

        assertThat(view.findViewById(R.id.total_score)).has(text("501"));
    }

    @Test
    public void should_render_hint() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public CharSequence getHint() {
                return "T20 T20 D20";
            }
        });

        assertThat(view.findViewById(R.id.hint)).has(text("T20 T20 D20"));
    }

    @Test
    public void should_render_statistics() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public float getAverage() {
                return 120.25f;
            }

            @Override
            public int getBest() {
                return 180;
            }
        });

        assertThat(view.findViewById(R.id.statistics)).has(text(R.string.player_statistics_template, 180, 120.25f));
    }


    private static class PlayerScoreInfoStub implements PlayerScoreInfo {

        @Override
        public CharSequence getPlayerName() {
            return "";
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
            return "";
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
