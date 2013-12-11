package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

import java.util.ArrayList;

public class CompositeGameWatcher extends ArrayList<GameWatcher> implements GameWatcher {
    @Override
    public void gameFinish(PlayerScoreInfo player) {
        for (GameWatcher watcher : this)
            watcher.gameFinish(player);
    }

    @Override
    public void scoreChanged(Game game) {
        for (GameWatcher watcher : this)
            watcher.scoreChanged(game);
    }

    @Override
    public void currentPlayerChanged(PlayerScoreInfo currentPlayer, int currentPlayerIndex) {
        for (GameWatcher watcher : this)
            watcher.currentPlayerChanged(currentPlayer, currentPlayerIndex);
    }
}
