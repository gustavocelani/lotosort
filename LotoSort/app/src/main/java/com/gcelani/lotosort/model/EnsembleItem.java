package com.gcelani.lotosort.model;

/**
 * EnsembleItem
 */
public class EnsembleItem {

    /** Number Text */
    private String numberText;

    /** Checked */
    private boolean checked;

    /**
     * Constructor
     * @param numberText numberText
     * @param checked checked
     */
    public EnsembleItem(String numberText, boolean checked) {
        this.numberText = numberText;
        this.checked = checked;
    }

    public String getNumberText() {
        return numberText;
    }

    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
