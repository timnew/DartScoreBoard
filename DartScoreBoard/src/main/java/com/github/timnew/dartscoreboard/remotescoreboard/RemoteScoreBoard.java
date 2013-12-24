package com.github.timnew.dartscoreboard.remotescoreboard;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.v4.app.FragmentActivity;

import com.github.timnew.dartscoreboard.models.Game;
import com.github.timnew.dartscoreboard.models.GameWatcher;
import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.UUID;

@EBean
public class RemoteScoreBoard implements GameWatcher {

    public static final String SERIALPORT_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    @RootContext
    protected FragmentActivity context;

    private BluetoothSocket socket;
    private InputStreamReader reader;
    private OutputStreamWriter writer;

    public boolean connect(BluetoothDevice device) {
        try {
            socket = device.createRfcommSocketToServiceRecord(UUID.fromString(SERIALPORT_UUID));

            socket.connect();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            reader = new InputStreamReader(inputStream);
            writer = new OutputStreamWriter(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isConnected();
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public void disconnect() {
        if (socket == null)
            return;

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reader = null;
        writer = null;
        socket = null;
    }

    @Override
    public void gameFinish(PlayerScoreInfo player) {
    }

    @Override
    public void scoreChanged(Game game) {
        if (!isConnected())
            return;

        List<PlayerScoreInfo> players = game.getPlayers();

        int[] scores = {
                players.get(0).getTotalScore(),
                players.get(1).getTotalScore()
        };

        try {
            writer.write(String.format("%4d%4d\n", scores[0], scores[1]));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void currentPlayerChanged(PlayerScoreInfo currentPlayer, int currentPlayerIndex) {

    }
}
