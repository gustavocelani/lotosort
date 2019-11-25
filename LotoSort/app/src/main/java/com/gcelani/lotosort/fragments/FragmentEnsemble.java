package com.gcelani.lotosort.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.adapters.EnsembleItemAdapter;
import com.gcelani.lotosort.control.SharedPreferencesController;
import com.gcelani.lotosort.model.EnsembleItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * FragmentEnsemble
 */
public class FragmentEnsemble extends Fragment {

    /** Context */
    private WeakReference<Context> mContext;

    /** EnsembleItemAdapter */
    private EnsembleItemAdapter mEnsembleItemAdapter;

    /**
     * onCreateView
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return rootView
     */
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ensemble, container, false);

        Button saveEnsembleButton = rootView.findViewById(R.id.ensemble_save_button);
        saveEnsembleButton.setOnClickListener(onSaveEnsembleClickListener);

        SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
        ListView ensembleListView = rootView.findViewById(R.id.ensemble_list_view);

        mEnsembleItemAdapter = new EnsembleItemAdapter(sharedPreferencesController.getEnsembleItemArrayList(), mContext.get());
        ensembleListView.setAdapter(mEnsembleItemAdapter);

        return rootView;
    }

    /**
     * onSaveEnsembleClickListener
     */
    private View.OnClickListener onSaveEnsembleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayList<EnsembleItem> ensembleItemArrayList = mEnsembleItemAdapter.getEnsembleItemsArrayList();
            SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
            sharedPreferencesController.setEnsembleItemArrayList(ensembleItemArrayList);

            int checkedCount = 0;
            for (EnsembleItem ensembleItem : ensembleItemArrayList) {
                if (ensembleItem.isChecked()) {
                    checkedCount++;
                }
            }

            Toast.makeText(mContext.get(), mContext.get().getResources().getString(R.string.ensemble_save_message, checkedCount), Toast.LENGTH_LONG).show();
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