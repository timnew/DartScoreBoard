package com.github.timnew.dartscoreboard.scoreboard;

import android.support.v4.app.Fragment;

public interface FragmentBuilder {

    Fragment buildFragment();

    CharSequence getDisplayName();
}
