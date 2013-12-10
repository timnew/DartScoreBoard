package org.robolectric;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.common.base.Function;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.primitives.Ints.asList;

public class UiActions {

    public static void clickOn(View... views) {
        for (View view : views) {
            Robolectric.clickOn(view);
        }
    }

    public static View[] buttons(Fragment fragment, int... ids) {
        final View rootView = fragment.getView();

        return buttons(rootView, ids);
    }

    public static View[] buttons(final View rootView, int... ids) {
        Iterable<View> viewInterable = transform(asList(ids), new Function<Integer, View>() {
            @Override
            public View apply(Integer id) {
                return rootView.findViewById(id);
            }
        });

        return toArray(viewInterable, View.class);
    }


    public static View[] buttons(final Activity activity, int... ids) {

        Iterable<View> viewInterable = transform(asList(ids), new Function<Integer, View>() {
            @Override
            public View apply(Integer id) {
                return activity.findViewById(id);
            }
        });

        return toArray(viewInterable, View.class);
    }
}
