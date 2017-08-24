package com.example.android.booklisting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pedro on 8/23/17.
 */

public class ResultsFragment extends Fragment implements Serializable {

    @BindView(R.id.results_list_view)
    ListView resultsListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_results, container, false);

        ButterKnife.bind(this, rootView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ResultsListAdapter resultsListAdapter = (ResultsListAdapter) bundle.get("resultsListAdapter");

            resultsListView.setAdapter(resultsListAdapter);
        }

        return rootView;
    }
}
