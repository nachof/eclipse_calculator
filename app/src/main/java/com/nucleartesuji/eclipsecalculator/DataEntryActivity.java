package com.nucleartesuji.eclipsecalculator;

import com.nucleartesuji.eclipsecalculator.adapters.PresetsSpinnerAdapter;
import com.nucleartesuji.eclipsecalculator.calculator.ShipPreset;
import com.nucleartesuji.eclipsecalculator.calculator.ShipSpec;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.List;

public class DataEntryActivity extends Activity {

    public static final String EXTRA_ATTACKER_SHIP = "com.nucleartesuji.eclipsecalculator.attackerShip";
    public static final String EXTRA_DEFENDER_SHIP = "com.nucleartesuji.eclipsecalculator.defenderShip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        initializePresets();
        loadPreset(defaultPreset.getShipSpec(), "attack");
        loadPreset(defaultPreset.getShipSpec(), "defense");
        prepareOnLongClickListenerForImages();

        List<ShipPreset> presets = ShipPreset.collection();
        SpinnerAdapter adapter = new PresetsSpinnerAdapter(this, presets);

        Spinner attackSpinner = (Spinner) findViewById(R.id.attackFleetPreset);
        Spinner defenseSpinner = (Spinner) findViewById(R.id.defenseFleetPreset);
        defenseSpinner.setAdapter(adapter);
        attackSpinner.setAdapter(adapter);
        defenseSpinner.setOnItemSelectedListener(new PresetSelectionListener("attack"));
        attackSpinner.setOnItemSelectedListener(new PresetSelectionListener("defense"));

    }

    private void initializePresets() {
        defaultPreset = ShipPreset.defaultCruiser();
    }

    private void prepareOnLongClickListenerForImages() {
        LinearLayout containerView = (LinearLayout) findViewById(R.id.dataEntryOuterContainer);
        prepareOnLongClickListenerForImagesUnderContainer(containerView);
    }

    private final View.OnLongClickListener longClickFeedbackListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            int[] location = {0, 0};
            view.getLocationOnScreen(location);
            Toast toast = Toast.makeText(getApplicationContext(), view.getContentDescription(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, location[1] - 5);
            toast.show();
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return false;
        }
    };
    private ShipPreset defaultPreset;

    private void prepareOnLongClickListenerForImagesUnderContainer(
            ViewGroup containerView) {
        int childCount = containerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = containerView.getChildAt(i);
            if (view instanceof ViewGroup) {
                prepareOnLongClickListenerForImagesUnderContainer((ViewGroup) view);
            } else if (view instanceof ImageView) {
                view.setOnLongClickListener(longClickFeedbackListener);
            }
        }
    }

    private void loadPreset(ShipSpec preset, String shipPrefix) {
        setFieldValue(getIdentifier(shipPrefix + "FleetShipCount"),         1);
        setFieldValue(getIdentifier(shipPrefix + "FleetHull"),              preset.getHull());
        setFieldValue(getIdentifier(shipPrefix + "FleetIonCannons"),        preset.getIonCannons());
        setFieldValue(getIdentifier(shipPrefix + "FleetPlasmaCannons"),     preset.getPlasmaCannons());
        setFieldValue(getIdentifier(shipPrefix + "FleetPlasmaMissiles"),    preset.getPlasmaMissiles());
        setFieldValue(getIdentifier(shipPrefix + "FleetAntimatterCannons"), preset.getAntimatterCannons());
        setFieldValue(getIdentifier(shipPrefix + "FleetComputer"),          preset.getComputer());
        setFieldValue(getIdentifier(shipPrefix + "FleetShield"),            preset.getShield());
        setFieldValue(getIdentifier(shipPrefix + "FleetInitiative"),        preset.getInitiative());

    }

    private void setFieldValue(int id, int value) {
        EditText editText = (EditText) findViewById(id);
        editText.setText(Integer.toString(value));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data_entry, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_calculate:
                doCalculateAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doCalculateAction() {
        Intent intent = new Intent(this, ResultsActivity.class);

        intent.putExtra(EXTRA_ATTACKER_SHIP, shipBundle("attack"));
        intent.putExtra(EXTRA_DEFENDER_SHIP, shipBundle("defense"));
        startActivity(intent);
    }

    private Bundle shipBundle(String shipPrefix) {
        Bundle ship = new Bundle();
        getShipParameter(ship, ShipKey.KEY_SHIP_COUNT,         getIdentifier(shipPrefix + "FleetShipCount"));
        getShipParameter(ship, ShipKey.KEY_HULL,               getIdentifier(shipPrefix + "FleetHull"));
        getShipParameter(ship, ShipKey.KEY_ION_CANNONS,        getIdentifier(shipPrefix + "FleetIonCannons"));
        getShipParameter(ship, ShipKey.KEY_PLASMA_CANNONS,     getIdentifier(shipPrefix + "FleetPlasmaCannons"));
        getShipParameter(ship, ShipKey.KEY_PLASMA_MISSILES,    getIdentifier(shipPrefix + "FleetPlasmaMissiles"));
        getShipParameter(ship, ShipKey.KEY_ANTIMATTER_CANNONS, getIdentifier(shipPrefix + "FleetAntimatterCannons"));
        getShipParameter(ship, ShipKey.KEY_COMPUTER,           getIdentifier(shipPrefix + "FleetComputer"));
        getShipParameter(ship, ShipKey.KEY_SHIELD,             getIdentifier(shipPrefix + "FleetShield"));
        getShipParameter(ship, ShipKey.KEY_INITIATIVE,         getIdentifier(shipPrefix + "FleetInitiative"));
        return ship;
    }

    private int getIdentifier(String idName) {
        return getResources().getIdentifier(idName, "id", getPackageName());
    }

    private void getShipParameter(Bundle ship, String fieldKey, int fieldId) {
        EditText textField = (EditText) findViewById(fieldId );
        String value = textField.getText().toString();
        int intVal;

        try {
            if (value.equals("")) intVal = 0;
            else intVal = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            intVal = 0;
        }
        ship.putInt(fieldKey, intVal);
    }

    private class PresetSelectionListener implements AdapterView.OnItemSelectedListener {
        private final String groupIdentifier;

        public PresetSelectionListener(String groupIdentifier) {
            this.groupIdentifier = groupIdentifier;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            loadPreset(((ShipPreset)parent.getAdapter().getItem(position)).getShipSpec(), groupIdentifier);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing
        }
    }
}