package com.github.timnew.dartscoreboard.scoregrid;

import android.support.v4.app.Fragment;
import android.widget.GridView;

import com.github.timnew.dartscoreboard.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.score_grid)
public class ScoreGridFragment extends Fragment {

    @ViewById(R.id.gridView)
    protected GridView gridView;

    private ScoreGridAdapter adapter;

    @AfterViews
    protected void initGridView() {
        adapter = new ScoreGridAdapter(getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
    }

    public <T extends PlayerScoreInfo> void setPlayerInfos(List<T> simplePlayers) {
        adapter.setList(simplePlayers);
    }

    public void refreshView() {
        adapter.notifyDataSetChanged();
    }
}