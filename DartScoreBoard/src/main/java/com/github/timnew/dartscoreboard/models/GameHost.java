package com.github.timnew.dartscoreboard.models;

public interface GameHost {

    Game newGame();

    void registerGameWatcher(GameWatcher watcher);

    void removeGameWatcher(GameWatcher watcher);

    Game getGame();
}
