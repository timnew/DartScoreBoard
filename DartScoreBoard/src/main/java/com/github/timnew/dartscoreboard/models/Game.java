package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

import java.util.List;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;
import static java.lang.String.format;

public class Game {

    private List<PlayerScoreInfo> players;
    private int currentPlayerIndex;
    private GameWatcher gameWatcher = new GameWatcherAdapter();

    public Game(List<PlayerScoreInfo> players) {
        this.players = players;
        currentPlayerIndex = 0;
        getCurrentPlayer().activate();
    }

    public List<PlayerScoreInfo> getPlayers() {
        return players;
    }

    public PlayerScoreInfo getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public PlayerScoreInfo nextPlayer() {
        getCurrentPlayer().deactivate();

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

        PlayerScoreInfo currentPlayer = getCurrentPlayer().activate();

        gameWatcher.currentPlayerChanged(currentPlayer, currentPlayerIndex);

        return currentPlayer;
    }

    public void submitScore(int totalScore) {
        PlayerScoreInfo currentPlayer = getCurrentPlayer();

        currentPlayer.submitScore(totalScore);

        gameWatcher.scoreChanged(this);

        nextPlayer();

        if (currentPlayer.getTotalScore() == 0)
            gameFinish(currentPlayer);
    }

    private void gameFinish(PlayerScoreInfo player) {
        player.win();

        gameWatcher.gameFinish(player);
    }

    public static Game newSimpleGame(int playerCount, int totalScore) {
        List<PlayerScoreInfo> players = newArrayListWithExpectedSize(playerCount);

        for (int i = 0; i < playerCount; i++) {
            SimplePlayer player = new SimplePlayer(format("Player %d", i + 1), totalScore);

            players.add(i, player);
        }

        return new Game(players);
    }

    public void setGameWatcher(GameWatcher gameWatcher) {
        if (gameWatcher == null)
            throw new IllegalArgumentException("gameWatcher is null");

        this.gameWatcher = gameWatcher;
    }

}
