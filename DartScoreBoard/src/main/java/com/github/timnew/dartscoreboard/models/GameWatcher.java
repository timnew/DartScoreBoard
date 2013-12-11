package com.github.timnew.dartscoreboard.models;

import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;

public interface GameWatcher {
    void gameFinish(PlayerScoreInfo player);

    void scoreChanged(Game game);

    void currentPlayerChanged(PlayerScoreInfo currentPlayer, int currentPlayerIndex);
}

