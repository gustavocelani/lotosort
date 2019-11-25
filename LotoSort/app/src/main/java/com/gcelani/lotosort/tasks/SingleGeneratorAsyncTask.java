package com.gcelani.lotosort.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.control.SharedPreferencesController;
import com.gcelani.lotosort.model.EnsembleItem;
import com.gcelani.lotosort.util.Constants;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * SingleGeneratorAsyncTask
 */
public class SingleGeneratorAsyncTask extends AsyncTask<Void, Integer, Void> {

    /** Context */
    private WeakReference<Context> mContext;

    /** ResultTextView */
    @SuppressLint("StaticFieldLeak")
    private TextView mResultTextView;

    /** Ensemble */
    private ArrayList<String> mEnsemble;
    /** Quantity */
    private int mQuantity;

    /** Formatted Single Generation */
    private StringBuilder mFormattedSingleGeneration;

    /**
     * Constructor
     * @param context context
     * @param resultTextView resultTextView
     */
    public SingleGeneratorAsyncTask(Context context, TextView resultTextView) {
        mContext = new WeakReference<>(context);
        mResultTextView = resultTextView;
    }

    /**
     * doInBackground
     * @param voids Voids
     * @return void
     */
    @Override
    protected Void doInBackground(Void... voids) {
        mFormattedSingleGeneration = new StringBuilder();

        if (mQuantity > mEnsemble.size()) {
            mFormattedSingleGeneration.append(mContext.get().getResources().getString(R.string.generate_fail_text));
            return null;
        }

        ArrayList<String> generation = new ArrayList<>();
        while (generation.size() < mQuantity) {
            String generated = mEnsemble.get(new Random().nextInt(mEnsemble.size()));

            if (!generation.contains(generated)) {
                generation.add(generated);
            }
        }

        Collections.sort(generation);
        mFormattedSingleGeneration.append(formatSingleGeneration(generation));

        return null;
    }

    /**
     * onPreExecute
     */
    @Override
    protected void onPreExecute() {
        SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
        ArrayList<EnsembleItem> ensembleItemArrayList = sharedPreferencesController.getEnsembleItemArrayList();
        mQuantity = sharedPreferencesController.getQuantity();

        mEnsemble = new ArrayList<>();
        for (EnsembleItem ensembleItem : ensembleItemArrayList) {
            if (ensembleItem.isChecked()) {
                mEnsemble.add(ensembleItem.getNumberText());
            }
        }
    }

    /**
     * onPostExecute
     * @param aVoid aVoids
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        mResultTextView.setText(mFormattedSingleGeneration.toString());
    }

    /**
     * formatSingleGeneration
     * @param generation generation
     * @return Formatted generation
     */
    private String formatSingleGeneration(ArrayList<String> generation) {
        StringBuilder formattedGeneration = new StringBuilder();
        int wrapCount = 0;

        if (generation == null) {
            return mContext.get().getResources().getString(R.string.generate_fail_text);
        }

        for (String number : generation) {

            if (wrapCount >= Constants.WRAP_COUNT) {
                formattedGeneration.append("\n");
                wrapCount = 0;

            } else {
                wrapCount++;
            }

            formattedGeneration.append(number).append(" ");
        }

        return formattedGeneration.toString();
    }
}
