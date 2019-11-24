package com.gcelani.lotosort.control;

import android.content.Context;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.model.EnsembleItem;
import com.gcelani.lotosort.util.Constants;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * GeneratorController
 */
public class GeneratorController {

    /** Context */
    private WeakReference<Context> mContext;

    /**
     * Constructor
     * @param context context
     */
    public GeneratorController(Context context) {
        mContext = new WeakReference<>(context);
    }

    /**
     * generateAndFormat
     * @return Formatted Generation
     */
    public String generateAndFormat() {
        return formatGeneration(generate());
    }

    /**
     * generate
     * @return Generation
     */
    private ArrayList<String> generate() {
        SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
        ArrayList<EnsembleItem> ensembleItemArrayList = sharedPreferencesController.getEnsembleItemArrayList();
        int quantity = sharedPreferencesController.getQuantity();

        ArrayList<String> ensemble = new ArrayList<>();
        for (EnsembleItem ensembleItem : ensembleItemArrayList) {
            if (ensembleItem.isChecked()) {
                ensemble.add(ensembleItem.getNumberText());
            }
        }

        if (quantity >= ensemble.size()) {
            return null;
        }

        ArrayList<String> generation = new ArrayList<>();
        while (generation.size() < quantity) {
            String generated = ensemble.get(new Random().nextInt(ensemble.size()));

            if (!generation.contains(generated)) {
                generation.add(generated);
            }
        }

//        for (int i = 0; i < quantity; i++) {
//            generation.add(ensemble.get(new Random().nextInt(ensemble.size())));
//        }

        Collections.sort(generation);
        return generation;
    }

    /**
     * formatGeneration
     * @param generation generation
     * @return Formatted generation
     */
    private String formatGeneration(ArrayList<String> generation) {
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
