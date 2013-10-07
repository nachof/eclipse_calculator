package com.nucleartesuji.eclipsecalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class DataEntryActivity extends Activity {

	public static final String EXTRA_ATTACKER_SHIP = "com.nucleartesuji.eclipsecalculator.attackerShip";
	public static final String EXTRA_DEFENDER_SHIP = "com.nucleartesuji.eclipsecalculator.defenderShip";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_entry);
		setDefaultValues("attack");
		setDefaultValues("defense");
	}

	private void setDefaultValues(String shipPrefix) {		
		setFieldValue(getIdentifier(shipPrefix + "FleetShipCount"), 1);
		setFieldValue(getIdentifier(shipPrefix + "FleetHull"), 1);
		setFieldValue(getIdentifier(shipPrefix + "FleetIonCannons"), 2);
		setFieldValue(getIdentifier(shipPrefix + "FleetPlasmaCannons"), 0);
		setFieldValue(getIdentifier(shipPrefix + "FleetPlasmaMissiles"), 0);
		setFieldValue(getIdentifier(shipPrefix + "FleetAntimatterCannons"), 0);
		setFieldValue(getIdentifier(shipPrefix + "FleetComputer"), 1);
		setFieldValue(getIdentifier(shipPrefix + "FleetShield"), 0);		
		setFieldValue(getIdentifier(shipPrefix + "FleetInitiative"), 2);

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
}