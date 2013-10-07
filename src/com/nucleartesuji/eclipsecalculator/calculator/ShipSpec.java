package com.nucleartesuji.eclipsecalculator.calculator;

import java.util.ArrayList;
import java.util.List;

public class ShipSpec {
    private static final int ION_CANNON_HITS = 1;
	private static final int PLASMA_CANNON_HITS = 2;
	private static final int ANTIMATTER_CANNON_HITS = 4;
	private static final int PLASMA_MISSILE_HITS = 2;
	
	private int hull = 0;
    private int ionCannons = 0;
    private int plasmaCannons = 0;
    private int plasmaMissiles = 0; 
    private int antimatterCannons = 0;
    private int computer = 0;
    private int shield = 0;
    private int initiative = 0;
    
    public ShipSpec(int hull, int ionCannons, int plasmaCannons, int plasmaMissiles, int antimatterCannons, int computer, int shield, int initiative) {
    	this.hull = hull;
    	this.ionCannons = ionCannons;
    	this.plasmaCannons = plasmaCannons;
    	this.plasmaMissiles = plasmaMissiles;
    	this.antimatterCannons = antimatterCannons;
    	this.computer = computer;
    	this.shield = shield;
    	this.initiative = initiative;    	
    }
    
	private ShipSpec(Builder builder) {
		this(builder.hull, builder.ionCannons, builder.plasmaCannons, builder.plasmaMissiles, builder.antimatterCannons, builder.computer, builder.shield, builder.initiative);
	}

	public int hitPoints() {
		return hull + 1;
	}

	public List<AttackDie> attackDice() {
		List<AttackDie> dice = new ArrayList<AttackDie>();
		for (int i = 0; i < ionCannons; i++) dice.add(new AttackDie(ION_CANNON_HITS, computer));
		for (int i = 0; i < plasmaCannons; i++) dice.add(new AttackDie(PLASMA_CANNON_HITS, computer));
		for (int i = 0; i < antimatterCannons; i++) dice.add(new AttackDie(ANTIMATTER_CANNON_HITS, computer));
		
		return dice;
	}

	public List<AttackDie> missileDice() {
		List<AttackDie> dice = new ArrayList<AttackDie>();
		for (int i = 0; i < plasmaMissiles * 2; i++) dice.add(new AttackDie(PLASMA_MISSILE_HITS, computer));
		return dice;
	}

	public int shield() {
		return shield;
	}

	public int initiative() {
		return initiative;
	}
	
	public static class Builder {
		private int hull = 0;
	    private int ionCannons = 0;
	    private int plasmaCannons = 0;
	    private int plasmaMissiles = 0;
	    private int antimatterCannons = 0;
	    private int computer = 0;
	    private int shield = 0;
	    private int initiative = 0;
	    
	    public ShipSpec build() {
	    	return new ShipSpec(this);
	    }
	    
	    public Builder setHull(int hull) {
	    	this.hull = hull;
	    	return this;
	    }

	    public Builder setIonCannons(int ionCannons) {
	    	this.ionCannons = ionCannons;
	    	return this;
	    }

		public Builder setInitiative(int initiative) {
			this.initiative = initiative;
			return this;
		}

		public Builder setComputer(int computer) {
			this.computer = computer;
			return this;
		}

		public Builder setPlasmaCannons(int plasmaCannons) {
			this.plasmaCannons = plasmaCannons;
			return this;
		}

		public Builder setPlasmaMissiles(int plasmaMissiles) {
			this.plasmaMissiles = plasmaMissiles;
			return this;
		}

		public Builder setAntimatterCannons(int antimatterCannons) {
			this.antimatterCannons = antimatterCannons;
			return this;
		}

		public Builder setShield(int shield) {
			this.shield = shield;
			return this;
		}
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Presets {
		public static ShipSpec defaultCruiser() {
			return ShipSpec.builder()
					.setInitiative(2)
					.setIonCannons(1)
					.setHull(1)
					.setComputer(1)
					.build();
		}

		public static ShipSpec ancient() {
			return ShipSpec.builder()
					.setHull(1)
					.setIonCannons(2)
					.setComputer(1)
					.setInitiative(2)
					.build();
		}	
	}
	
	public static class Presenter {
		private ShipSpec shipSpec;

		public Presenter(ShipSpec shipSpec) {
			this.shipSpec = shipSpec;
			
		}
		
		public String toString() {
			StringBuilder result = new StringBuilder();
			List<StringBuilder> parts = new ArrayList<StringBuilder>();
			
			if (shipSpec.hull >= 1)              parts.add(new StringBuilder("Hull: ")              .append(shipSpec.hull));
			if (shipSpec.ionCannons >= 1)        parts.add(new StringBuilder("Ion Cannons: ")       .append(shipSpec.ionCannons));
			if (shipSpec.plasmaCannons >= 1)     parts.add(new StringBuilder("Plasma Cannons: ")    .append(shipSpec.plasmaCannons));
			if (shipSpec.plasmaMissiles >= 1)    parts.add(new StringBuilder("Plasma Missiles: ")   .append(shipSpec.plasmaMissiles));
			if (shipSpec.antimatterCannons >= 1) parts.add(new StringBuilder("Antimatter Cannons: ").append(shipSpec.antimatterCannons));
			if (shipSpec.computer >= 1)          parts.add(new StringBuilder("Computer: ")          .append(shipSpec.computer));
			if (shipSpec.shield >= 1)            parts.add(new StringBuilder("Shield: ")            .append(shipSpec.shield));
			if (shipSpec.initiative >= 1)        parts.add(new StringBuilder("Initiative: ")        .append(shipSpec.initiative));
			
			boolean isFirst = true;
			for(StringBuilder part : parts) {
				if (!isFirst) {
					result.append(", ");
				}
				result.append(part);
				isFirst = false;
			}
			
			return result.toString();
		}
	}
	
	public String toString() {
		return (new Presenter(this)).toString();
	}

}