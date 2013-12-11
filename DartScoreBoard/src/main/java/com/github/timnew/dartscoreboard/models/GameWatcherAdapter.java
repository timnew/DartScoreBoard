package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

public class GameWatcherAdapter implements GameWatcher {

    @Override
    public void gameFinish(PlayerScoreInfo player) {
    }

    @Override
    public void scoreChanged(Game game) {
    }

    @Override
    public void currentPlayerChanged(PlayerScoreInfo currentPlayer, int currentPlayerIndex) {
    }
}
