package com.github.timnew.dartscoreboard.models;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SimplePlayerTest {

    private SimplePlayer player;

    @Before
    public void setUp() throws Exception {
        player = new SimplePlayer("Player", 501);
    }

    @Test
    public void should_subtract_score() {
        assertThat(player.getTotalScore()).isEqualTo(501);
        assertThat(player.submitScore(1)).isTrue();
        assertThat(player.getTotalScore()).isEqualTo(500);
    }

    @Test
    public void should_not_substract_score_when_busted() {
        player.setTotalScore(5);
        assertThat(player.getTotalScore()).isEqualTo(5);
        assertThat(player.submitScore(20)).isFalse();
        assertThat(player.getTotalScore()).isEqualTo(5);
    }
}
