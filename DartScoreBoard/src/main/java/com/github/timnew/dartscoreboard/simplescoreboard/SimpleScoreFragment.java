package com.github.timnew.dartscoreboard.simplescoreboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.github.timnew.dartscoreboard.R;
import com.github.timnew.dartscoreboard.models.Game;
import com.github.timnew.dartscoreboard.models.GameHost;
import com.github.timnew.dartscoreboard.models.GameWatcherAdapter;
import com.github.timnew.dartscoreboard.scoregrid.PlayerScoreInfo;
import com.github.timnew.dartscoreboard.scoregrid.ScoreGridFragment;
import com.github.timnew.dartscoreboard.scorekeyboard.ScoreKeyboardFragment;
import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegmentList;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentById;

@EFragment(R.layout.simple_score)
public class SimpleScoreFragment extends Fragment {

    @FragmentById(R.id.score_grid)
    protected ScoreGridFragment scoreGrid;

    @FragmentById(R.id.keyboard)
    protected ScoreKeyboardFragment keyboard;

    private GameHost getGameHost() {
        return (GameHost) getActivity();
    }

    private Game getGame() {
        return getGameHost().getGame();
    }

    @AfterViews()
    protected void initialize() {
        scoreGrid.setPlayerInfos(getGame().getPlayers());
        getGameHost().registerGameWatcher(new ActivityGameWatcher());
    }

    @AfterViews
    protected void wireComponents() {
        keyboard.setKeyboardResultHandler(new InputSegmentList.InputResultHandler() {
            @Override
            public void onCommit(int totalScore) {
                getGame().submitScore(totalScore);
                scoreGrid.refreshView();
                keyboard.clear();
            }
        });
    }

    private class ActivityGameWatcher extends GameWatcherAdapter {
        @Override
        public void gameFinish(PlayerScoreInfo player) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Game Finish")
                    .setMessage(String.format("%s wins", player.getPlayerName()))
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getGameHost().newGame();
                        }
                    })
                    .create();

            alertDialog.show();
        }
    }
}