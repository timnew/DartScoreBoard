package com.github.timnew.dartscoreboard.settings;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.github.timnew.dartscoreboard.R;
import com.github.timnew.shared.ActivityResultManager;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.settings_activity)
public class SettingsActivity extends Activity {

    @Bean
    protected ActivityResultManager resultManager;

    @AfterViews
    protected void configBlueTooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            resultManager.startActivityForResult(enableBtIntent, new ActivityResultManager.ActivityResultHandler() {
                @Override
                public void onResult(int resultCode, Intent data) {
                    onBlueToothDialogResult(resultCode == RESULT_OK);
                }
            });
        }
    }

    private void onBlueToothDialogResult(boolean isEnabled) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        resultManager.onActivityResult(requestCode, resultCode, data);
    }
}