package com.gcelani.lotosort.tasks;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.control.SharedPreferencesController;
import com.gcelani.lotosort.model.EnsembleItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * SubsetsGeneratorAsyncTask
 */
public class SubsetsGeneratorAsyncTask extends AsyncTask<Void, Integer, Void> {

    /** Context */
    private WeakReference<Context> mContext;

    /** ProgressDialog */
    private ProgressDialog mProgressDialog;

    /** HintTextView */
    @SuppressLint("StaticFieldLeak")
    private TextView mHintTextView;
    /** ResultTextView */
    @SuppressLint("StaticFieldLeak")
    private TextView mResultTextView;

    /** Ensemble Array */
    private String[] mEnsembleArray;
    /** Quantity */
    private int mQuantity;

    /** Formatted Subsets */
    private StringBuilder mFormattedSubsets;
    /** Subsets Counter */
    private int mSubsetsCounter;

    /**
     * Constructor
     * @param context context
     * @param hintTextView hintTextView
     * @param resultTextView resultTextView
     */
    public SubsetsGeneratorAsyncTask(Context context, TextView hintTextView, TextView resultTextView) {
        mContext = new WeakReference<>(context);
        mHintTextView = hintTextView;
        mResultTextView = resultTextView;
    }

    /**
     * doInBackground
     * @param voids Voids
     * @return void
     */
    @Override
    protected Void doInBackground(Void... voids) {
        mFormattedSubsets = new StringBuilder();

        if (mQuantity > mEnsembleArray.length) {
            mFormattedSubsets.append(mContext.get().getResources().getString(R.string.generate_fail_text));
            return null;
        }

        mSubsetsCounter = 0;
        String[] aux = new String[mQuantity];
        subsetsRecursivePermutation(mEnsembleArray, mQuantity, 0, aux, 0);

        return null;
    }

    /**
     * onPreExecute
     */
    @Override
    protected void onPreExecute() {
        mResultTextView.setText(mContext.get().getResources().getString(R.string.time_warning_text));

        SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
        ArrayList<EnsembleItem> ensembleItemArrayList = sharedPreferencesController.getEnsembleItemArrayList();
        mQuantity = sharedPreferencesController.getQuantity();

        ArrayList<String> ensemble = new ArrayList<>();
        for (EnsembleItem ensembleItem : ensembleItemArrayList) {
            if (ensembleItem.isChecked()) {
                ensemble.add(ensembleItem.getNumberText());
            }
        }

        String[] ensembleArray = new String[ensemble.size()];
        mEnsembleArray = ensemble.toArray(ensembleArray);

        mProgressDialog = new ProgressDialog(mContext.get());
        mProgressDialog.setTitle(mContext.get().getResources().getString(R.string.generating_text));
        mProgressDialog.show();
    }

    /**
     * onPostExecute
     * @param aVoid aVoids
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        mHintTextView.setText(mContext.get().getResources().getString(R.string.subsets_generate_message, mSubsetsCounter));
        mResultTextView.setText(mFormattedSubsets.toString());
        mProgressDialog.dismiss();
    }

    /**
     * onProgressUpdate
     * @param values Count
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        mHintTextView.setText(mContext.get().getResources().getString(R.string.subsets_generate_message, values[0]));
    }

    /**
     * subsetsRecursivePermutation
     * @param ensembleArray ensembleArray
     * @param quantity quantity
     * @param currentDataIndex currentDataIndex
     * @param aux aux
     * @param iteration iteration
     */
    private void subsetsRecursivePermutation(String[] ensembleArray, int quantity, int currentDataIndex, String[] aux, int iteration) {
        if (currentDataIndex == quantity) {

            for (int j = 0; j < quantity; j++) {
                mFormattedSubsets.append(aux[j]);
                mFormattedSubsets.append(" ");
            }

            mFormattedSubsets.append("\n\n");
            mSubsetsCounter++;
            publishProgress(mSubsetsCounter);
            return;
        }

        if (iteration >= ensembleArray.length) {
            return;
        }

        aux[currentDataIndex] = ensembleArray[iteration];
        subsetsRecursivePermutation(ensembleArray, quantity, currentDataIndex + 1, aux, iteration + 1);
        subsetsRecursivePermutation(ensembleArray, quantity, currentDataIndex, aux, iteration + 1);
    }
}
