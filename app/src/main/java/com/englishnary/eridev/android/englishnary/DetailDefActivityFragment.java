package com.englishnary.eridev.android.englishnary;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailDefActivityFragment extends Fragment {

    public DetailDefActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_def, container, false);

                  // The detail Activity called via intent.  Inspect the intent for definitions data.
                     Intent intent = getActivity().getIntent();
                        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                            String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                             ((TextView) rootView.findViewById(R.id.tvDefinicion))
                               .setText(forecastStr);
                 }

        return rootView;


    }
}
