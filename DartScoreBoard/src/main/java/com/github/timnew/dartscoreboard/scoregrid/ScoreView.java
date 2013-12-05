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
    protected TextView playerGameScore;


    @ViewById(R.id.total_score)
    protected TextView totalScore;

    @ViewById(R.id.hint)
    protected TextView hint;

    @ViewById(R.id.statistics)
    protected TextView statistics;


    public void updateView(PlayerScoreInfo scoreInfo) {
        Context context = getContext();

        playerName.setText(scoreInfo.getPlayerName());

        StringBuilder gameScoreText = new StringBuilder();

        if (scoreInfo.getSets() >= 0)
            gameScoreText.append(context.getString(R.string.player_game_sets_template, scoreInfo.getSets()));

        if (scoreInfo.getLegs() >= 0) {
            if (gameScoreText.length() > 0)
                gameScoreText.append(" ");

            gameScoreText.append(context.getString(R.string.player_game_legs_template, scoreInfo.getLegs()));
        }

        playerGameScore.setText(gameScoreText);

        totalScore.setText(Integer.toString(scoreInfo.getTotalScore()));

        hint.setText(scoreInfo.getHint());

        StringBuilder statisticsText = new StringBuilder();

        if (scoreInfo.getBest() >= 0) {
            statisticsText.append(context.getString(R.string.player_statistics_best_template, scoreInfo.getBest()));
        }

        if (scoreInfo.getAverage() >= 0) {
            if (statisticsText.length() > 0)
                statisticsText.append(" ");

            statisticsText.append(context.getString(R.string.player_statistics_average_template, scoreInfo.getAverage()));
        }

        statistics.setText(statisticsText);
    }
}
