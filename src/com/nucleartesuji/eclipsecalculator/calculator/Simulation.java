package com.nucleartesuji.eclipsecalculator.calculator;

import android.util.Log;

public class Simulation {
	private static final int DEFAULT_FIGHT_ROUNDS = 4000;
	private int doneRounds = 0;
	private int attackerWinsRounds = 0;
	private FleetSpec attackerFleet;
	private FleetSpec defenderFleet;
	
	public Simulation(FleetSpec attacker, FleetSpec defender) {
		attackerFleet = attacker;
		defenderFleet = defender;
	}
	
	public void run(int rounds) {
		Battle battle = new Battle(attackerFleet, defenderFleet);
		
		for (int i = 0; i < rounds; i++) {
			BattleResult result = battle.fight();
			doneRounds++;
			if (result.wonByAttacker()) attackerWinsRounds++;
		}
	}
	
	public void run() {
		run(DEFAULT_FIGHT_ROUNDS);
	}
	
	public void resetStats() {
		doneRounds = 0;
		attackerWinsRounds = 0;
	}

	public double attackerWinRatio() {
		Log.i("Simulation", "Rounds: " + Integer.toString(doneRounds));
		Log.i("Simulation", "Won by attacker: " + Integer.toString(attackerWinsRounds));
		return ((double)attackerWinsRounds / doneRounds);
	}

	public int attackerWins() {
		return attackerWinsRounds;
	}

	public int defenderWins() {
		return doneRounds - attackerWinsRounds;
	}

	public double defenderWinRatio() {
		return ((double)defenderWins() / doneRounds);
	}

	public int totalRounds() {
		return doneRounds;
	}
	
	
}
