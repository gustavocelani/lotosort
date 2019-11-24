package com.gcelani.lotosort.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.control.SharedPreferencesController;
import com.gcelani.lotosort.util.Constants;

import java.lang.ref.WeakReference;

/**
 * FragmentConfiguration
 */
public class FragmentConfiguration extends Fragment {

    /** Context */
    private WeakReference<Context> mContext;

    /**
     * onCreateView
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return rootView
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configuration, container, false);

        NumberPicker quantityNumberPicker = rootView.findViewById(R.id.quantity_number_picker);
        quantityNumberPicker.setMinValue(Constants.QUANTITY_NUMBER_PICKER_MIN_VALUE);
        quantityNumberPicker.setMaxValue(Constants.QUANTITY_NUMBER_PICKER_MAX_VALUE);
        quantityNumberPicker.setWrapSelectorWheel(true);
        quantityNumberPicker.setOnValueChangedListener(onQuantityChangeListener);

        SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
        quantityNumberPicker.setValue(sharedPreferencesController.getQuantity());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext.get(), R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner languagesSpinner = rootView.findViewById(R.id.language_spinner);
        languagesSpinner.setAdapter(adapter);

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
     * onQuantityChangeListener
     */
    private NumberPicker.OnValueChangeListener onQuantityChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            if (newVal != oldVal) {
                SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(mContext.get());
                sharedPreferencesController.setQuantity(newVal);
            }
        }
    };
}