package com.nucleartesuji.eclipsecalculator.calculator;

public class AttackDie {
    private static final int AUTOMATIC_MISS_THRESHOLD = 1;
    private static final int AUTOMATIC_HIT_THRESHOLD = 6;
    private static final int HIT_THRESHOLD = 6;
    private final int hits;
    private final int computer;

    public AttackDie(int hits, int computer) {
        this.hits = hits;
        this.computer = computer;

    }

    public int damageAgainst(ShipSpec shipSpec) {
        int roll = (int) Math.floor(Math.random() * 6) + 1;
        int modifiedRoll = roll + computer - shipSpec.getShield();

        if (roll >= AUTOMATIC_HIT_THRESHOLD) {
            return hits; // Hits automatically
        } else if (roll <= AUTOMATIC_MISS_THRESHOLD) {
            return 0; // Misses automatically :(
        } else if (modifiedRoll >= HIT_THRESHOLD) {
            return hits; // Hits with modified roll
        } else {
            return 0; // misses :(
        }
    }

}
