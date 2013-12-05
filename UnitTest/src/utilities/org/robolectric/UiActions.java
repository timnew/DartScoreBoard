package org.robolectric;

import android.view.View;

public class UiActions {

    public static void clickOn(View... views) {
        for (View view : views) {
            Robolectric.clickOn(view);
        }
    }
}
