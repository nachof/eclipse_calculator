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

    public void resetDamage() {
        this.damage = 0;
    }

    public void fireAt(FleetSpec otherFleet) {
        for (int i = 0; i < aliveCount(); i++) {
            otherFleet.takeDamage(shipSpec.attackDice());
        }
    }

    public void fireMissilesAt(FleetSpec otherFleet) {
        for (int i = 0; i < aliveCount(); i++) {
            otherFleet.takeDamage(shipSpec.missileDice());
        }
    }

    private void takeDamage(List<AttackDie> attackDice) {
        for (AttackDie die : attackDice) {
            this.damage += die.damageAgainst(shipSpec);
        }
    }

    private int aliveCount() {
        int hitPoints = shipSpec.hitPoints();
        int totalHitPoints = hitPoints * count;
        return (int) Math.ceil(((double) totalHitPoints - damage)
                / hitPoints);
    }

    public boolean alive() {
        return aliveCount() > 0;
    }

    public int initiative() {
        return shipSpec.getInitiative();
    }

    public String toString() {
        return (new Presenter(this)).toString();
    }

    public boolean hasNonMissileFirePower() {
        return shipSpec.hasNonMissileFirePower();
    }

    public static class Presenter {
        private FleetSpec fleetSpec;

        public Presenter(FleetSpec fleetSpec) {
            this.fleetSpec = fleetSpec;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(Integer.toString(fleetSpec.count));
            result.append(" ship: ");
            result.append(fleetSpec.shipSpec.toString());
            return result.toString();
        }
    }

}
