package com.github.timnew.dartscoreboard.scoregrid;

import android.content.Context;
import android.view.ViewGroup;

import com.github.timnew.shared.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScoreGridAdapter extends ListAdapter<PlayerScoreInfo, ScoreView> {

    public ScoreGridAdapter(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    protected ScoreView createView(int index, ViewGroup viewGroup) {
        return ScoreView_.build(context);
    }

    @Override
    protected void renderView(ScoreView view, int index) {
        view.updateView(getItem(index));
    }

    public <T extends PlayerScoreInfo> void setList(List<T> list) {
        this.list = new ArrayList<PlayerScoreInfo>(list);
        notifyDataSetChanged();
    }
}
