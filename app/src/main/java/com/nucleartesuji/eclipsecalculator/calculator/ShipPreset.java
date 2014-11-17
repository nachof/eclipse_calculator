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

    public static ShipPreset defaultInterceptor() {
        ShipSpec spec = ShipSpec.builder().setInitiative(3).setIonCannons(1).build();
        return new ShipPreset(R.string.preset_name_default_interceptor, spec);
    }

    public static ShipPreset defaultCruiser() {
        ShipSpec spec =  ShipSpec.builder().setInitiative(2).setIonCannons(1).setHull(1).setComputer(1).build();
        return new ShipPreset(R.string.preset_name_default_cruiser, spec);
    }

    public static ShipPreset defaultDreadnought() {
        ShipSpec spec = ShipSpec.builder().setInitiative(1).setIonCannons(2).setHull(2).setComputer(1).build();
        return new ShipPreset(R.string.preset_name_default_dreadnought, spec);
    }

    public static ShipPreset orionInterceptor() {
        ShipSpec spec = ShipSpec.builder().setInitiative(4).setIonCannons(1).setShield(1).build();
        return new ShipPreset(R.string.preset_name_orion_interceptor, spec);
    }

    public static ShipPreset orionCruiser() {
        ShipSpec spec =  ShipSpec.builder().setInitiative(3).setIonCannons(1).setHull(1).setComputer(1).setShield(1).build();
        return new ShipPreset(R.string.preset_name_orion_cruiser, spec);
    }

    public static ShipPreset orionDreadnought() {
        ShipSpec spec = ShipSpec.builder().setInitiative(2).setIonCannons(2).setHull(2).setComputer(1).setShield(1).build();
        return new ShipPreset(R.string.preset_name_orion_dreadnought, spec);
    }

    public static ShipPreset plantaInterceptor() {
        ShipSpec spec = ShipSpec.builder().setInitiative(1).setIonCannons(1).setComputer(1).build();
        return new ShipPreset(R.string.preset_name_planta_interceptor, spec);
    }

    public static ShipPreset plantaCruiser() {
        ShipSpec spec =  ShipSpec.builder().setInitiative(1).setIonCannons(1).setHull(1).setComputer(1).build();
        return new ShipPreset(R.string.preset_name_planta_cruiser, spec);
    }

    public static ShipPreset ancient() {
        ShipSpec spec = ShipSpec.builder().setHull(1).setIonCannons(2).setComputer(1).setInitiative(2).build();
        return new ShipPreset(R.string.preset_name_ancient, spec);
    }

    public static List<ShipPreset> collection() {
        List<ShipPreset> result = new ArrayList<ShipPreset>();
        result.add(defaultInterceptor());
        result.add(defaultCruiser());
        result.add(defaultDreadnought());
        result.add(orionInterceptor());
        result.add(orionCruiser());
        result.add(orionDreadnought());
        result.add(plantaInterceptor());
        result.add(plantaCruiser());
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
