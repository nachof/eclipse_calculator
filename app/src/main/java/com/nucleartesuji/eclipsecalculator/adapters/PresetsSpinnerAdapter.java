package com.nucleartesuji.eclipsecalculator.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.nucleartesuji.eclipsecalculator.R;
import com.nucleartesuji.eclipsecalculator.calculator.ShipSpec;

import java.util.List;

public class PresetsSpinnerAdapter extends ArrayAdapter<ShipSpec> implements SpinnerAdapter {
    private List<ShipSpec> collection;

    public PresetsSpinnerAdapter(Context context, List<ShipSpec> collection) {
        super(context, R.layout.presets_spinner, collection);
        this.collection = collection;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = View.inflate(getContext(), R.layout.presets_spinner, null);
        }
        ShipSpec item = collection.get(position);
        ((TextView) row.findViewById(R.id.presetName)).setText(getContext().getString(item.getLabel()));
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }
}
