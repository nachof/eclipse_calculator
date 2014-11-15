package com.nucleartesuji.eclipsecalculator.calculator;

import com.nucleartesuji.eclipsecalculator.R;

import java.util.ArrayList;
import java.util.List;

/**
* Created by nacho on 11/15/14.
*/
public class ShipPreset {
    

    public static ShipSpec defaultCruiser() {
        return ShipSpec.builder().setLabel(R.string.preset_name_default_cruiser).setInitiative(2).setIonCannons(1)
                .setHull(1).setComputer(1).build();
    }

    public static ShipSpec ancient() {
        return ShipSpec.builder().setLabel(R.string.preset_name_ancient).setHull(1).setIonCannons(2)
                .setComputer(1).setInitiative(2).build();
    }

    public static List<ShipSpec> collection() {
        List<ShipSpec> result = new ArrayList<ShipSpec>();
        result.add(defaultCruiser());
        result.add(ancient());
        return result;
    }
}
