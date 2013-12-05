package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

import java.util.List;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;
import static java.lang.String.format;

public class Game {

    private List<PlayerScoreInfo> players;
    private int currentPlayerIndex;

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

        return getCurrentPlayer().activate();
    }

    public void submitScore(int totalScore) {
        PlayerScoreInfo currentPlayer = getCurrentPlayer();

        currentPlayer.submitScore(totalScore);

        nextPlayer();

        if (currentPlayer.getTotalScore() == 0)
            playerWin(currentPlayer);
    }

    private void playerWin(PlayerScoreInfo currentSimplePlayer) {
        currentSimplePlayer.win();
    }

    public static Game newSimpleGame(int playerCount, int totalScore) {
        List<PlayerScoreInfo> players = newArrayListWithExpectedSize(playerCount);

        for (int i = 0; i < playerCount; i++) {
            SimplePlayer player = new SimplePlayer(format("Player %d", i + 1), totalScore);

            players.add(i, player);
        }

        return new Game(players);
    }
}
