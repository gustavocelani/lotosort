package com.gcelani.lotosort.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.util.Constants;

/**
 * FragmentAbout
 */
public class FragmentAbout extends Fragment {

    /**
     * onCreateView
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return rootView
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        TextView versionTextView = rootView.findViewById(R.id.version_text);
        versionTextView.setText(getResources().getString(R.string.about_version_text, Constants.APP_VERSION));

        return rootView;
    }
}