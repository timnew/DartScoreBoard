package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

import org.fest.assertions.core.Condition;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static com.github.timnew.dartscoreboard.models.Game.newSimpleGame;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.conditions.android.IterableItemCountCondition.all;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameTest {

    private Game game;

    private void startGame(Game game) {
        this.game = game;
    }

    private void startNewGame(int playerCount) {
        startGame(newSimpleGame(playerCount, 501));
    }

    private List<PlayerScoreInfo> players() {
        return game.getPlayers();
    }

    private SimplePlayer firstPlayer() {
        return (SimplePlayer) players().get(0);
    }

    private SimplePlayer secondPlayer() {
        return (SimplePlayer) players().get(1);
    }

    private PlayerScoreInfo currentPlayer() {
        return game.getCurrentPlayer();
    }

    @Test
    public void should_create_simple_game() {
        startNewGame(5);

        assertThat(players()).hasSize(5);

        assertThat(players()).has(all(new Condition<PlayerScoreInfo>() {
            @Override
            public boolean matches(PlayerScoreInfo value) {
                return value.getTotalScore() == 501;
            }
        }));
    }

    @Test
    public void should_next_player() {
        startNewGame(2);

        assertThat(currentPlayer()).isEqualTo(firstPlayer());
        assertThat(firstPlayer().isActivated()).isTrue();
        assertThat(secondPlayer().isActivated()).isFalse();

        assertThat(game.nextPlayer()).isEqualTo(secondPlayer());
        assertThat(currentPlayer()).isEqualTo(secondPlayer());
        assertThat(firstPlayer().isActivated()).isFalse();
        assertThat(secondPlayer().isActivated()).isTrue();

        assertThat(game.nextPlayer()).isEqualTo(firstPlayer());
        assertThat(currentPlayer()).isEqualTo(firstPlayer());
        assertThat(firstPlayer().isActivated()).isTrue();
        assertThat(secondPlayer().isActivated()).isFalse();
    }

    @Test
    public void should_submit_score() {
        startNewGame(2);

        game.submitScore(180);

        assertThat(firstPlayer().getTotalScore()).isEqualTo(321);
        assertThat(currentPlayer()).isEqualTo(secondPlayer());
    }

    @Test
    public void should_bust_score() {
        startNewGame(2);

        firstPlayer().setTotalScore(90);

        assertThat(currentPlayer()).isEqualTo(firstPlayer());
        game.submitScore(180);
        assertThat(currentPlayer()).isEqualTo(secondPlayer());
        assertThat(firstPlayer().getTotalScore()).isEqualTo(90);
    }

    @Test
    public void should_win_the_game() {
        startNewGame(2);

        GameWatcher gameWatcher = mock(GameWatcher.class);
        game.setGameWatcher(gameWatcher);
        firstPlayer().setTotalScore(100);
        game.submitScore(100);
        verify(gameWatcher).gameFinish(firstPlayer());
    }

    @Ignore("Not Implemented Yet")
    @Test
    public void should_notify_when_score_changed() {

    }

    @Ignore("Not Implemented Yet")
    @Test
    public void should_notify_when_current_player_changed() {

    }
}

