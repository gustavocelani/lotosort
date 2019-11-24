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
import com.gcelani.lotosort.control.GeneratorController;

import java.lang.ref.WeakReference;

/**
 * FragmentGenerator
 */
public class FragmentGenerator extends Fragment {

    /** Context */
    private WeakReference<Context> mContext;

    /** ResultTextView */
    private TextView mResultTextView;

    /**
     * onCreateView
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return rootView
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generator, container, false);
        mResultTextView = rootView.findViewById(R.id.result_text);

        Button generateButton = rootView.findViewById(R.id.generate_button);
        generateButton.setOnClickListener(onGenerateButtonClickListener);

        GeneratorController generatorController = new GeneratorController(mContext.get());
        String generation = generatorController.generateAndFormat();
        mResultTextView.setText(generation.isEmpty() ? mContext.get().getResources().getString(R.string.generate_fail_text): generation);

        return rootView;
    }

    /**
     * onAttach
     * @param context context
     */
    @Override
    public void onAttach(Context context) {
        this.mContext = new WeakReference<>(context);
        super.onAttach(context);
    }

    /**
     * onGenerateButtonClickListener
     */
    private View.OnClickListener onGenerateButtonClickListener = new View.OnClickListener() {

        /**
         * onClick
         * @param v view
         */
        @Override
        public void onClick(View v) {
            GeneratorController generatorController = new GeneratorController(mContext.get());
            String generation = generatorController.generateAndFormat();
            mResultTextView.setText(generation.isEmpty() ? mContext.get().getResources().getString(R.string.generate_fail_text): generation);
        }
    };
}