package com.nucleartesuji.eclipsecalculator;

import java.text.NumberFormat;

import com.nucleartesuji.eclipsecalculator.calculator.FleetSpec;
import com.nucleartesuji.eclipsecalculator.calculator.ShipSpec;
import com.nucleartesuji.eclipsecalculator.calculator.Simulation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ResultsActivity extends Activity {

	private FleetSpec attacker;
	private FleetSpec defender;
	private Simulation simulation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		loadData();
		
		showFleetDescription();

		runSimulation();

	}

	private void loadData() {
		attacker = loadFleetSpec(DataEntryActivity.EXTRA_ATTACKER_SHIP);
		defender = loadFleetSpec(DataEntryActivity.EXTRA_DEFENDER_SHIP);
		
		simulation = new Simulation(attacker, defender);
	}

	private FleetSpec loadFleetSpec(String fleetKey) {
		Bundle fleetData = getIntent().getBundleExtra(fleetKey); 
		
		ShipSpec shipSpec = ShipSpec.builder()
							.setHull(fleetData.getInt(ShipKey.KEY_HULL))
							.setIonCannons(fleetData.getInt(ShipKey.KEY_ION_CANNONS))
							.setPlasmaCannons(fleetData.getInt(ShipKey.KEY_PLASMA_CANNONS))
							.setPlasmaMissiles(fleetData.getInt(ShipKey.KEY_PLASMA_MISSILES))
							.setAntimatterCannons(fleetData.getInt(ShipKey.KEY_ANTIMATTER_CANNONS))
							.setComputer(fleetData.getInt(ShipKey.KEY_COMPUTER))
							.setShield(fleetData.getInt(ShipKey.KEY_SHIELD))
							.setInitiative(fleetData.getInt(ShipKey.KEY_INITIATIVE))
							.build();
		return new FleetSpec(shipSpec, fleetData.getInt(ShipKey.KEY_SHIP_COUNT));
	}

	private void showFleetDescription() {
		TextView attackerDescriptionArea = (TextView) findViewById(R.id.textAttackerFleetDescription);
		TextView defenderDescriptionArea = (TextView) findViewById(R.id.textDefenderFleetDescription);
		attackerDescriptionArea.setText(attacker.toString());
		defenderDescriptionArea.setText(defender.toString());
	}

	private void runSimulation() {
		simulation.run();
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMinimumFractionDigits(2);
		percentFormat.setMaximumFractionDigits(2);
		
		setFieldValue(R.id.textResultsAttackerWinsPercent, percentFormat.format(simulation.attackerWinRatio()));
		setFieldValue(R.id.textResultsDefenderWinsPercent, percentFormat.format(simulation.defenderWinRatio()));
		setFieldValue(R.id.textResultsAttackerWins,        Integer.toString(simulation.attackerWins()));
		setFieldValue(R.id.textResultsDefenderWins,        Integer.toString(simulation.defenderWins()));
		setFieldValue(R.id.textResultsRounds,              Integer.toString(simulation.totalRounds()));
	}

	private void setFieldValue(int fieldId, String fieldValue) {
		TextView resultsArea = (TextView) findViewById(fieldId);
		resultsArea.setText(fieldValue);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
