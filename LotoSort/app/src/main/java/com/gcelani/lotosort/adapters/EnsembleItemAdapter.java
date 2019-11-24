package com.gcelani.lotosort.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcelani.lotosort.R;
import com.gcelani.lotosort.model.EnsembleItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * EnsembleItemAdapter
 */
public class EnsembleItemAdapter extends BaseAdapter {

    /** Context */
    private WeakReference<Context> mContext;

    /** EnsembleItemsArrayList */
    private ArrayList<EnsembleItem> mEnsembleItemsArrayList;

    /**
     * Constructor
     * @param ensembleItemsArrayList ensembleItemsArrayList
     * @param context context
     */
    public EnsembleItemAdapter(ArrayList<EnsembleItem> ensembleItemsArrayList, Context context) {
        super();
        mContext = new WeakReference<>(context);
        this.mEnsembleItemsArrayList = ensembleItemsArrayList;
    }

    /**
     * getCount
     * @return Ensemble Size
     */
    @Override
    public int getCount() {
        return mEnsembleItemsArrayList.size();
    }

    /**
     * getItem
     * @param position position
     * @return Item
     */
    @Override
    public String getItem(int position) {
        return mEnsembleItemsArrayList.get(position).toString();
    }

    /**
     * getItemId
     * @param position position
     * @return position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * getEnsembleItemsArrayList
     * @return mEnsembleItemsArrayList
     */
    public ArrayList<EnsembleItem> getEnsembleItemsArrayList() {
        return mEnsembleItemsArrayList;
    }

    /**
     * ViewHolder
     */
    public class ViewHolder {
        /** ItemText */
        TextView itemText;
        /** Item Checkbox */
        CheckBox itemCheckBox;
    }

    /**
     * getView
     * @param position position
     * @param convertView convertView
     * @param parent parent
     * @return View
     */
    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = ((Activity) mContext.get()).getLayoutInflater();

        viewHolder = new ViewHolder();
        convertView = layoutInflater.inflate(R.layout.ensemble_list_view_item, null);
        viewHolder.itemText = convertView.findViewById(R.id.ensemble_item_text);
        viewHolder.itemCheckBox = convertView.findViewById(R.id.ensemble_item_checkbox);

        viewHolder.itemCheckBox.setTag(position);
        viewHolder.itemCheckBox.setText(mEnsembleItemsArrayList.get(position).getNumberText());
        viewHolder.itemCheckBox.setChecked(mEnsembleItemsArrayList.get(position).isChecked());

        LinearLayout itemLinearLayout = convertView.findViewById(R.id.ensemble_item_layout);
        itemLinearLayout.setOnClickListener(onItemClickListener);

        convertView.setTag(viewHolder);
        return convertView;
    }

    /**
     * onItemClickListener
     */
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {

        /**
         * onClick
         * @param view view
         */
        @Override
        public void onClick(View view) {
            CheckBox checkBox = view.findViewById(R.id.ensemble_item_checkbox);
            int position = (Integer) checkBox.getTag();

            checkBox.setChecked(!checkBox.isChecked());
            mEnsembleItemsArrayList.get(position).setChecked(checkBox.isChecked());
        }
    };
}