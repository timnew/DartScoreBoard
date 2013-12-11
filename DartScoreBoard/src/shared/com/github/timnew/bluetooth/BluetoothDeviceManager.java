package com.github.timnew.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothDevicePicker;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

@EBean
public class BluetoothDeviceManager implements BluetoothDevicePicker {
    @RootContext
    protected Context context;

    public void pickDevice(BluetoothDevicePickResultHandler handler) {
        context.registerReceiver(new BluetoothDeviceManagerReceiver(handler), new IntentFilter(ACTION_DEVICE_SELECTED));

        context.startActivity(new Intent(ACTION_LAUNCH)
                .putExtra(EXTRA_NEED_AUTH, false)
                .putExtra(EXTRA_FILTER_TYPE, FILTER_TYPE_ALL)
                .setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS));
    }

    public static interface BluetoothDevicePickResultHandler {
        void onDevicePicked(BluetoothDevice device);
    }

    private static class BluetoothDeviceManagerReceiver extends BroadcastReceiver {

        private final BluetoothDevicePickResultHandler handler;

        public BluetoothDeviceManagerReceiver(BluetoothDevicePickResultHandler handler) {
            this.handler = handler;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);

            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            handler.onDevicePicked(device);
        }
    }
}
