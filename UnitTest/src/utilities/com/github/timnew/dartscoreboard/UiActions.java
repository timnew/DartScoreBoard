package com.github.timnew.dartscoreboard;

import android.view.View;

import org.robolectric.Robolectric;

public class UiActions {

    public static void clickOn(View... views) {
        for (View view : views) {
            Robolectric.clickOn(view);
        }
    }
}
