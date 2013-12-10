package com.github.timnew.shared;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

@EBean
public class ActivityResultManager {
    private final SparseArray<ActivityResultHandler> handlers = new SparseArray<ActivityResultHandler>();

    @RootContext
    protected Activity activity;

    private int requestCode = 0;

    public int currentRequestCode() {
        return requestCode;
    }

    private int registerHandler(ActivityResultHandler handler) {
        requestCode++;

        handlers.put(requestCode, handler);

        return requestCode;
    }

    public void startActivityForResult(Intent itent, ActivityResultHandler handler) {
        int requestId = registerHandler(handler);

        activity.startActivityForResult(itent, requestId);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int index = handlers.indexOfKey(requestCode);

        if (index < 0)
            return;

        ActivityResultHandler handler = handlers.valueAt(index);
        handlers.removeAt(index);

        handler.onResult(resultCode, data);
    }

    public static interface ActivityResultHandler {
        void onResult(int resultCode, Intent data);
    }
}
