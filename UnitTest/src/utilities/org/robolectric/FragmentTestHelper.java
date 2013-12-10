package org.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.robolectric.util.ActivityController;

public class FragmentTestHelper {
    public static void startFragment(Fragment fragment) {
        ActivityController
                .of(FragmentActivity.class)
                .create()
                .start()
                .resume()
                .visible()
                .get()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment, null)
                .commit();
    }
}
