package com.github.timnew.dartscoreboard;

import android.support.v4.app.Fragment;

public interface FragmentBuilder {
    Fragment buildFragment();

    CharSequence getDisplayName();
}
