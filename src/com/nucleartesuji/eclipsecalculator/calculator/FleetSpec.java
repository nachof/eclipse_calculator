package com.nucleartesuji.eclipsecalculator.calculator;

import java.util.List;

public class FleetSpec {
	private ShipSpec shipSpec;
	private int count;
	private int damage;
	
	public FleetSpec(ShipSpec shipSpec, int count) {
		this.shipSpec = shipSpec;
		this.count = count;
	}
	
	public FleetSpec(ShipSpec shipSpec) {
		this(shipSpec, 1);
	}
	
	public void resetDamage() {
		this.damage = 0;
	}
	
	public void fireAt(FleetSpec otherFleet) {
		for (int i = 0; i < aliveCount(); i++) {
			otherFleet.takeDamage(shipSpec.attackDice());
		}
	}

	private void takeDamage(List<AttackDie> attackDice) {
		for(AttackDie die : attackDice) {
			this.damage += die.damageAgainst(shipSpec);
		}
	}

	private int aliveCount() {
		int hitPoints = shipSpec.hitPoints();
		int totalHitPoints = hitPoints * count;
		int remaining = (int) Math.ceil(((double)totalHitPoints - damage) / hitPoints);
		return remaining;
	}
	
	public boolean alive() {
		return aliveCount() > 0;
	}

	public int initiative() {
		return shipSpec.initiative();
	}
}
