package com.github.timnew.dartscoreboard;

import android.support.v4.app.Fragment;

public enum MainFragmentActivityTabs implements FragmentBuilder {
    ScoreBoard {
        @Override
        public Fragment buildFragment() {
            return new ScoreBoardFragment_();
        }

        @Override
        public CharSequence getDisplayName() {
            return "Score Board";
        }
    };

    @Override
    public abstract Fragment buildFragment();

    @Override
    public abstract CharSequence getDisplayName();
}
