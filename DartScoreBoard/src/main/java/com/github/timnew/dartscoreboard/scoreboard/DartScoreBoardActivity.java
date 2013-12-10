
package com.github.timnew.dartscoreboard.scoreboard;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.settings.SettingsActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.scoreboard_activity)
public class DartScoreBoardActivity
        extends SherlockFragmentActivity {

    @ViewById
    ViewPager pager;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.dart_score_board_activity, menu);
        return true;
    }

    public void onSettingMenuItemClicked(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}
