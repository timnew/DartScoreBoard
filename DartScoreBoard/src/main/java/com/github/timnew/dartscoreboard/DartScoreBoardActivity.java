
package com.github.timnew.dartscoreboard;

import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
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
        getSupportMenuInflater();
        return true;
    }
}
