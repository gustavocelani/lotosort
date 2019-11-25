package com.gcelani.lotosort.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.tasks.SubsetsGeneratorAsyncTask;

import java.lang.ref.WeakReference;

/**
 * FragmentSubsets
 */
public class FragmentSubsets extends Fragment {

    /** Context */
    private WeakReference<Context> mContext;

    /** ResultTextView */
    private TextView mResultTextView;
    /** ResultTextView */
    private TextView mHintTextView;

    /**
     * onCreateView
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return rootView
     */
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subsets, container, false);

        Button subsetsGenerateButton = rootView.findViewById(R.id.subsets_generate_button);
        subsetsGenerateButton.setOnClickListener(onSubsetsGenerateClickListener);

        mHintTextView = rootView.findViewById(R.id.subsets_hint_text);
        mResultTextView = rootView.findViewById(R.id.subsets_text_view);

        return rootView;
    }

    /**
     * onSubsetsGenerateClickListener
     */
    private View.OnClickListener onSubsetsGenerateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SubsetsGeneratorAsyncTask subsetsGeneratorAsyncTask = new SubsetsGeneratorAsyncTask(mContext.get(), mHintTextView, mResultTextView);
            subsetsGeneratorAsyncTask.execute();
        }
    };

    /**
     * onAttach
     * @param context context
     */
    @Override
    public void onAttach(Context context) {
        this.mContext = new WeakReference<>(context);
        super.onAttach(context);
    }
}