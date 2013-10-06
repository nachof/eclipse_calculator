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
		
		
		// FIXME get the real data!
		attacker = new FleetSpec(ShipSpec.Presets.defaultCruiser());
		defender = new FleetSpec(ShipSpec.Presets.ancient());
		simulation = new Simulation(attacker, defender);
		

		runSimulation();
		
		showFleetDescription();

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
