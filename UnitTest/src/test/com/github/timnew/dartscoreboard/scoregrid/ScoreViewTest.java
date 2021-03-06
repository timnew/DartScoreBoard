package com.github.timnew.dartscoreboard.scoregrid;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;
import com.github.timnew.dartscoreboard.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.ContainsBackgroundCondition.backgroundResColor;
import static org.fest.assertions.conditions.android.ContainsTextCondition.revealText;
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
    public void should_render_sets_and_legs() {
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

        assertThat(view.findViewById(R.id.player_game_score)).has(text("Sets: 1 Legs: 3"));
    }

    @Test
    public void should_render_legs_only() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public int getSets() {
                return NOT_AVAILABLE;
            }

            @Override
            public int getLegs() {
                return 3;
            }
        });

        assertThat(view.findViewById(R.id.player_game_score)).has(text("Legs: 3"));
    }

    @Test
    public void should_render_sets_only() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public int getSets() {
                return 1;
            }

            @Override
            public int getLegs() {
                return NOT_AVAILABLE;
            }
        });

        assertThat(view.findViewById(R.id.player_game_score)).has(text("Sets: 1"));
    }

    @Test
    public void should_hide_sets_and_legs() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public int getSets() {
                return NOT_AVAILABLE;
            }

            @Override
            public int getLegs() {
                return NOT_AVAILABLE;
            }
        });

        assertThat(view.findViewById(R.id.player_game_score)).has(text(""));
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
                return 120.2514342f;
            }

            @Override
            public int getBest() {
                return 180;
            }
        });

        assertThat(view.findViewById(R.id.statistics)).has(text("Best: 180 Avg: 120.25"));
    }

    @Test
    public void should_render_best_only() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public float getAverage() {
                return NOT_AVAILABLE;
            }

            @Override
            public int getBest() {
                return 180;
            }
        });

        assertThat(view.findViewById(R.id.statistics)).has(text("Best: 180"));
    }

    @Test
    public void should_render_average_only() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public float getAverage() {
                return 125;
            }

            @Override
            public int getBest() {
                return NOT_AVAILABLE;
            }
        });

        revealText(view.findViewById(R.id.statistics));
        assertThat(view.findViewById(R.id.statistics)).has(text("Avg: 125.00"));
    }

    @Test
    public void should_hide_statistics() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public float getAverage() {
                return NOT_AVAILABLE;
            }

            @Override
            public int getBest() {
                return NOT_AVAILABLE;
            }
        });

        assertThat(view.findViewById(R.id.statistics)).has(text(""));
    }

    @Test
    public void should_highlight_active_user() {
        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public boolean isActivated() {
                return false;
            }
        });

        assertThat(view.findViewById(R.id.activate_indicator)).has(backgroundResColor(R.color.score_view_background));

        view.updateView(new PlayerScoreInfoStub() {
            @Override
            public boolean isActivated() {
                return true;
            }
        });

        assertThat(view.findViewById(R.id.activate_indicator)).has(backgroundResColor(R.color.score_view_background_activate));
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
        public boolean submitScore(int score) {
            return true;
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

        @Override
        public void win() {

        }

        @Override
        public boolean isActivated() {
            return false;
        }

        @Override
        public PlayerScoreInfo deactivate() {
            return this;
        }

        @Override
        public PlayerScoreInfo activate() {
            return this;
        }
    }
}
