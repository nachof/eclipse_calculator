package com.nucleartesuji.eclipsecalculator.calculator;

import android.support.annotation.StringRes;

import com.nucleartesuji.eclipsecalculator.R;

import java.util.ArrayList;
import java.util.List;

public class ShipPreset {
    private final ShipSpec shipSpec;
    @StringRes private final int label;

    public ShipPreset(@StringRes int label, ShipSpec shipSpec) {
        this.shipSpec = shipSpec;
        this.label    = label;
    }

    public static ShipPreset defaultCruiser() {
        ShipSpec spec =  ShipSpec.builder().setInitiative(2).setIonCannons(1).setHull(1).setComputer(1).build();
        return new ShipPreset(R.string.preset_name_default_cruiser, spec);
    }

    public static ShipPreset ancient() {
        ShipSpec spec = ShipSpec.builder().setHull(1).setIonCannons(2).setComputer(1).setInitiative(2).build();
        return new ShipPreset(R.string.preset_name_ancient, spec);
    }

    public static List<ShipPreset> collection() {
        List<ShipPreset> result = new ArrayList<ShipPreset>();
        result.add(defaultCruiser());
        result.add(ancient());
        return result;
    }

    public ShipSpec getShipSpec() {
        return shipSpec;
    }

    public int getLabel() {
        return label;
    }
}
