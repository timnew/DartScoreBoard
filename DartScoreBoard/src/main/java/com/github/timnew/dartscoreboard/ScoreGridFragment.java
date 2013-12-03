package com.github.timnew.dartscoreboard;

import android.support.v4.app.Fragment;
import android.widget.GridView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.score_grid)
public class ScoreGridFragment extends Fragment {

    @ViewById(R.id.gridView)
    protected GridView gridView;

    @AfterViews
    protected void initGridView() {
    }
}