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
		
		intent.putExtra(EXTRA_ATTACKER_SHIP, attackerShipBundle());
		intent.putExtra(EXTRA_DEFENDER_SHIP, defenderShipBundle());
		startActivity(intent);
	}

	private Bundle attackerShipBundle() {
		Bundle ship = new Bundle();
		
		getShipParameter(ship, ShipKey.KEY_SHIP_COUNT,         R.id.attackFleetShipCount);
		getShipParameter(ship, ShipKey.KEY_HULL,               R.id.attackFleetHull);
		getShipParameter(ship, ShipKey.KEY_ION_CANNONS,        R.id.attackFleetIonCannons);
		getShipParameter(ship, ShipKey.KEY_PLASMA_CANNONS,     R.id.attackFleetPlasmaCannons);
		getShipParameter(ship, ShipKey.KEY_PLASMA_MISSILES,    R.id.attackFleetPlasmaMissiles);
		getShipParameter(ship, ShipKey.KEY_ANTIMATTER_CANNONS, R.id.attackFleetAntimatterCannons);
		getShipParameter(ship, ShipKey.KEY_COMPUTER,           R.id.attackFleetComputer);
		getShipParameter(ship, ShipKey.KEY_SHIELD,             R.id.attackFleetShield);
		getShipParameter(ship, ShipKey.KEY_INITIATIVE,         R.id.attackFleetInitiative);
		return ship;
	}

	private Bundle defenderShipBundle() {
		Bundle ship = new Bundle();
		
		getShipParameter(ship, ShipKey.KEY_SHIP_COUNT,         R.id.defenseFleetShipCount);
		getShipParameter(ship, ShipKey.KEY_HULL,               R.id.defenseFleetHull);
		getShipParameter(ship, ShipKey.KEY_ION_CANNONS,        R.id.defenseFleetIonCannons);
		getShipParameter(ship, ShipKey.KEY_PLASMA_CANNONS,     R.id.defenseFleetPlasmaCannons);
		getShipParameter(ship, ShipKey.KEY_PLASMA_MISSILES,    R.id.defenseFleetPlasmaMissiles);
		getShipParameter(ship, ShipKey.KEY_ANTIMATTER_CANNONS, R.id.defenseFleetAntimatterCannons);
		getShipParameter(ship, ShipKey.KEY_COMPUTER,           R.id.defenseFleetComputer);
		getShipParameter(ship, ShipKey.KEY_SHIELD,             R.id.defenseFleetShield);
		getShipParameter(ship, ShipKey.KEY_INITIATIVE,         R.id.defenseFleetInitiative);
		return ship;
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