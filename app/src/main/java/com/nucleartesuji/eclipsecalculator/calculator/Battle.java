package com.nucleartesuji.eclipsecalculator.calculator;

public class Battle {
    private final FleetSpec attacker;
    private final FleetSpec defender;

    public Battle(FleetSpec attacker, FleetSpec defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public BattleResult fight() {
        attacker.resetDamage();
        defender.resetDamage();

        firstFleet().fireMissilesAt(secondFleet());
        secondFleet().fireMissilesAt(firstFleet());

        while (attacker.alive() && defender.alive()
                && attacker.hasNonMissileFirePower()) {
            firstFleet().fireAt(secondFleet());
            secondFleet().fireAt(firstFleet());
        }

        return defender.alive() ? BattleResult.defenderWins() : BattleResult
                .attackerWins();
    }

    // When resolving combat order, if initiative is the same, then defender
    // goes first.
    private FleetSpec secondFleet() {
        return (attacker.initiative() > defender.initiative()) ? defender
                : attacker;
    }

    private FleetSpec firstFleet() {
        return (attacker.initiative() > defender.initiative()) ? attacker
                : defender;
    }

}
