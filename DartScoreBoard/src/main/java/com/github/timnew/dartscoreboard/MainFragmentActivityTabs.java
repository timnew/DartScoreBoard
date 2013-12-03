package com.github.timnew.dartscoreboard;

import android.support.v4.app.Fragment;

public enum MainFragmentActivityTabs implements FragmentBuilder {
    SimpleScore {
        @Override
        public Fragment buildFragment() {
            return new SimpleScoreFragment_();
        }

        @Override
        public CharSequence getDisplayName() {
            return "Simple Score Board";
        }
    };

    @Override
    public abstract Fragment buildFragment();

    @Override
    public abstract CharSequence getDisplayName();
}
