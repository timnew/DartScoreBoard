package com.github.timnew.dartscoreboard.scoregrid;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SimplePlayerTest {

    private SimplePlayer player;

    @Before
    public void setUp() throws Exception {
        player = new SimplePlayer();

        player.setPlayerName("Player");
        player.setTotalScore(501);
    }

    @Test
    public void should_subtract_score() {
        assertThat(player.getTotalScore()).isEqualTo(501);
        assertThat(player.subtractScore(1)).isEqualTo(500);
        assertThat(player.getTotalScore()).isEqualTo(500);
    }

    @Test
    public void should_not_substract_score_when_busted() {
        player.setTotalScore(5);
        assertThat(player.getTotalScore()).isEqualTo(5);
        assertThat(player.subtractScore(20)).isEqualTo(5);
        assertThat(player.getTotalScore()).isEqualTo(5);
    }
}
