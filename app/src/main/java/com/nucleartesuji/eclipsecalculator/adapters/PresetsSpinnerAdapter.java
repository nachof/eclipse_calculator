package com.nucleartesuji.eclipsecalculator.adapters;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = prepareBaseView(convertView, collection.get(position));
        row.findViewById(R.id.presetDescription).setVisibility(View.GONE);
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ShipSpec item = collection.get(position);
        View base = prepareBaseView(convertView, getItem(position));
        ViewGroup descLayout = (ViewGroup) base.findViewById(R.id.presetDescription);
        descLayout.removeAllViews();
        descLayout.setVisibility(View.VISIBLE);
        addDescPart(descLayout, R.drawable.tile_antimatter_cannon, item.getAntimatterCannons());
        addDescPart(descLayout, R.drawable.tile_ion_cannon,        item.getIonCannons());
        addDescPart(descLayout, R.drawable.tile_electron_computer, item.getComputer());
        addDescPart(descLayout, R.drawable.tile_hull,              item.getHull());
        addDescPart(descLayout, R.drawable.tile_initiative,        item.getInitiative());
        addDescPart(descLayout, R.drawable.tile_plasma_cannon,     item.getPlasmaCannons());
        addDescPart(descLayout, R.drawable.tile_plasma_missiles,   item.getPlasmaMissiles());
        return base;
    }

    private void addDescPart(ViewGroup descriptionLayout, int icon, int amount) {
        if (amount > 0) {
            ViewGroup container = new LinearLayout(getContext());

            ImageView iconView = new ImageView(getContext());
            iconView.setImageResource(icon);
            LinearLayout.LayoutParams iconViewParams = new LinearLayout.LayoutParams(40, 40);
            iconViewParams.gravity = Gravity.CENTER_VERTICAL;
            iconView.setLayoutParams(iconViewParams);
            container.addView(iconView);

            TextView amountView = new TextView(getContext());
            LinearLayout.LayoutParams amountViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            amountViewParams.rightMargin = 20;
            amountView.setText(Integer.toString(amount));
            amountView.setLayoutParams(amountViewParams);
            container.addView(amountView);

            descriptionLayout.addView(container);
        }
    }

    private View prepareBaseView(View convertView, ShipSpec item) {
        View row = convertView;
        if (row == null) {
            row = View.inflate(getContext(), R.layout.presets_spinner, null);
        }
        ((TextView) row.findViewById(R.id.presetName)).setText(getContext().getString(item.getLabel()));
        return row;
    }
}