package com.github.timnew.dartscoreboard.scoregrid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.timnew.dartscoreboard.R;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.score_view)
public class ScoreView extends FrameLayout {
    public ScoreView(Context context) {
        super(context);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @ViewById(R.id.player_name)
    protected TextView playerName;

    @ViewById(R.id.player_game_score)
    protected TextView playGameScore;


    @ViewById(R.id.total_score)
    protected TextView totalScore;

    @ViewById(R.id.hint)
    protected TextView hint;

    @ViewById(R.id.statistics)
    protected TextView statistics;


    public void updateView(PlayerScoreInfo scoreInfo) {
        Context context = getContext();

        playerName.setText(scoreInfo.getPlayerName());

        playGameScore.setText(context.getString(R.string.player_game_score_template, scoreInfo.getSets(), scoreInfo.getLegs()));

        totalScore.setText(Integer.toString(scoreInfo.getTotalScore()));

        hint.setText(scoreInfo.getHint());

        statistics.setText(getContext().getString(R.string.player_statistics_template, scoreInfo.getBest(), scoreInfo.getAverage()));
    }
}
