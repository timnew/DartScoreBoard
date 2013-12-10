package com.github.timnew.shared;

import android.app.Activity;
import android.content.Intent;

import com.github.timnew.dartscoreboard.DartScoreBoardTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Activity.RESULT_OK;
import static com.github.timnew.shared.ActivityResultManager.ActivityResultHandler;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(DartScoreBoardTestRunner.class)
public class ActivityResultManagerTest {

    private ActivityResultManager_ manager;
    private Activity activity;
    private ActivityResultHandler handler;
    private Intent requestIntent;
    private Intent respondIntent;

    @Before
    public void setUp() throws Exception {
        activity = mock(Activity.class);
        handler = mock(ActivityResultHandler.class);
        manager = ActivityResultManager_.getInstance_(activity);
        requestIntent = new Intent();
        respondIntent = new Intent();
    }

    @Test
    public void should_work() {
        manager.startActivityForResult(requestIntent, handler);
        verify(activity).startActivityForResult(requestIntent, manager.currentRequestCode());

        manager.onActivityResult(manager.currentRequestCode(), RESULT_OK, respondIntent);
        verify(handler).onResult(RESULT_OK, respondIntent);
    }

    @Test
    public void should_route_to_right_handler() {
        manager.startActivityForResult(requestIntent, handler);
        int requestCode = manager.currentRequestCode();

        ActivityResultHandler secondHandler = mock(ActivityResultHandler.class);
        manager.startActivityForResult(requestIntent, secondHandler);

        manager.onActivityResult(requestCode, RESULT_OK, respondIntent);
        verify(handler, only()).onResult(RESULT_OK, respondIntent);
        verify(secondHandler, never()).onResult(any(Integer.class), any(Intent.class));
    }
}
