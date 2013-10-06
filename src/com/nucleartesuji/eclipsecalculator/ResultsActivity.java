package com.nucleartesuji.eclipsecalculator;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// FIXME: This is just testing values, to see that it actually works!
		// FIXME: Seriously, replace this with actually getting the values from the other activity.
		FleetSpec attacker = new FleetSpec(ShipSpec.Presets.defaultCruiser());
		FleetSpec defender = new FleetSpec(ShipSpec.Presets.ancient());
		Simulation simulation = new Simulation(attacker, defender);
		
		simulation.run();
		
		TextView resultsArea = (TextView) findViewById(R.id.textResults);
		resultsArea.setText(Double.toString(simulation.attackerWinPercentage()));
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
