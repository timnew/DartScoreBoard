package com.github.timnew.dartscoreboard.remotescoreboard;

import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultString;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.APPLICATION_DEFAULT)
public interface RemoteScorePreference {

    @DefaultBoolean(false)
    boolean remoteScoreBoardEnabled();

    @DefaultString("TimNew's MacBook Pro")
    String remoteScoreBoardName();
}
