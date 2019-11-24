package com.gcelani.lotosort.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Constants
 */
public class Constants {

    /** App Version */
    public final static String APP_VERSION = "0.0.3";

    /** Ensemble Numbers */
    public static final String[] ENSEMBLE_NUMBERS_TEXT_ARRAY = new String[] {
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"
    };

    /** Wrap Result Count Numbers */
    public final static int WRAP_COUNT = 5;

    /** Configuration Quantity Number Picker */
    public final static int QUANTITY_NUMBER_PICKER_MIN_VALUE = 1;
    public final static int QUANTITY_NUMBER_PICKER_MAX_VALUE = 60;

    /** Shared Preferences Default Values */
    public final static int DEFAULT_QUANTITY_VALUE = 15;
    public final static Set<String> DEFAULT_ENSEMBLE_ARRAY = new HashSet<>(Arrays.asList(
            "01", "03", "04", "05", "06", "07", "08", "09", "11",
            "14", "15", "16", "17", "19", "20", "21", "22", "23", "25"
    ));

    /** Shared Preferences Keys */
    public final static String SHARED_PREFERENCES_KEY = "lotosort_persistent_data";
    public final static String SHARED_PREFERENCES_QUANTITY_KEY = "lotosort_quantity";
    public final static String SHARED_PREFERENCES_ENSEMBLE_KEY = "lotosort_ensemble";

}
