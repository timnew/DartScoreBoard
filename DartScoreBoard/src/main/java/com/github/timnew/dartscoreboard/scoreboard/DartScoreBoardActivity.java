
package com.github.timnew.dartscoreboard.scoreboard;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.timnew.bluetooth.BluetoothDeviceManager;
import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.models.CompositeGameWatcher;
import com.github.timnew.dartscoreboard.models.Game;
import com.github.timnew.dartscoreboard.models.GameHost;
import com.github.timnew.dartscoreboard.models.GameWatcher;
import com.github.timnew.dartscoreboard.remotescoreboard.RemoteScoreBoard;
import com.github.timnew.dartscoreboard.remotescoreboard.RemoteScorePreference_;
import com.github.timnew.dartscoreboard.settings.SettingsActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.scoreboard_activity)
public class DartScoreBoardActivity
        extends SherlockFragmentActivity
        implements GameHost {

    @ViewById
    protected ViewPager pager;

    @Bean
    protected BluetoothDeviceManager deviceManager;

    @Bean
    protected RemoteScoreBoard remoteScoreBoard;

    @Pref
    protected RemoteScorePreference_ settings;

    private Game game;

    private CompositeGameWatcher gameWatchers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newGame();
    }

    @Override
    public Game newGame() {
        game = Game.newSimpleGame(2, 501);

        gameWatchers = new CompositeGameWatcher();

        game.setGameWatcher(gameWatchers);

        return game;
    }

    @AfterViews
    void afterViews() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        configureViewPager();
    }

    private void configureViewPager() {
        new PagerActionBarAdapter(getSupportFragmentManager(), getSupportActionBar(), pager, MainFragmentActivityTabs.values());
    }

    @Override
    public void registerGameWatcher(GameWatcher watcher) {
        gameWatchers.add(watcher);
    }

    @Override
    public void removeGameWatcher(GameWatcher watcher) {
        gameWatchers.remove(watcher);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.dart_score_board_activity, menu);
        return true;
    }

    public void onSettingMenuItemClicked(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void onConnectBluetoothItemClicked(MenuItem item) {
        if (remoteScoreBoard.isConnected()) {
            remoteScoreBoard.disconnect();
            removeGameWatcher(remoteScoreBoard);
        } else {
            deviceManager.pickDevice(new BluetoothDeviceManager.BluetoothDevicePickResultHandler() {
                @Override
                public void onDevicePicked(BluetoothDevice device) {
                    remoteScoreBoard.connect(device);
                    registerGameWatcher(remoteScoreBoard);
                }
            });
        }


    }
}
