package com.gcelani.lotosort.control;

import android.content.Context;
import android.content.SharedPreferences;

import com.gcelani.lotosort.model.EnsembleItem;
import com.gcelani.lotosort.util.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * SharedPreferencesController
 */
public class SharedPreferencesController {

    /** SharedPreferences */
    private SharedPreferences mSharedPreferences;

    /**
     * Constructor
     * @param context context
     */
    public SharedPreferencesController(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    /**
     * getQuantity
     * @return quantity
     */
    public int getQuantity() {
        return mSharedPreferences.getInt(Constants.SHARED_PREFERENCES_QUANTITY_KEY, Constants.DEFAULT_QUANTITY_VALUE);
    }

    /**
     * setQuantity
     * @param quantity quantity
     */
    public void setQuantity(int quantity) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Constants.SHARED_PREFERENCES_QUANTITY_KEY, quantity);
        editor.apply();
    }

    /**
     * getEnsembleArrayList
     * @return ensembleItemArrayList
     */
    public ArrayList<EnsembleItem> getEnsembleItemArrayList() {
        Set<String> ensembleSet = mSharedPreferences.getStringSet(Constants.SHARED_PREFERENCES_ENSEMBLE_KEY, Constants.DEFAULT_ENSEMBLE_ARRAY);

        ArrayList<EnsembleItem> ensembleItemArrayList = new ArrayList<>();
        for (String numberText : Constants.ENSEMBLE_NUMBERS_TEXT_ARRAY) {
            ensembleItemArrayList.add(new EnsembleItem(numberText, ensembleSet.contains(numberText)));
        }

        return ensembleItemArrayList;
    }

    /**
     * setEnsembleItemArrayList
     * @param ensembleItemArrayList ensembleItemArrayList
     */
    public void setEnsembleItemArrayList(ArrayList<EnsembleItem> ensembleItemArrayList) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        Set<String> ensembleSet = new HashSet<>();
        for (EnsembleItem ensembleItem : ensembleItemArrayList) {
            if (ensembleItem.isChecked()) {
                ensembleSet.add(ensembleItem.getNumberText());
            }
        }

        editor.putStringSet(Constants.SHARED_PREFERENCES_ENSEMBLE_KEY, ensembleSet);
        editor.apply();
    }
}
