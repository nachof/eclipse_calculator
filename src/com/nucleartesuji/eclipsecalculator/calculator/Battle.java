package com.nucleartesuji.eclipsecalculator.calculator;

public class Battle {
	private FleetSpec attacker;
	private FleetSpec defender;
	
	public Battle(FleetSpec attacker, FleetSpec defender) {
		this.attacker = attacker;
		this.defender = defender;
	}
	
	public BattleResult fight() {
		attacker.resetDamage();
		defender.resetDamage();
		
		while(attacker.alive() && defender.alive()) {
			firstFleet().fireAt(secondFleet());
			secondFleet().fireAt(firstFleet());
		}
		
		return defender.alive() ? BattleResult.defenderWins() : BattleResult.attackerWins(); 
	}

	
	// When resolving combat order, if initiative is the same, then defender goes first.
	private FleetSpec secondFleet() {
		return (attacker.initiative() > defender.initiative()) ? defender : attacker;
	}

	private FleetSpec firstFleet() {
		return (attacker.initiative() > defender.initiative()) ? attacker : defender;	
	}

}