package com.nucleartesuji.eclipsecalculator.calculator;

public class BattleResult {
    private static final int RESULT_CODE_DEFENDER_WINS = 2;
    private static final int RESULT_CODE_ATTACKER_WINS = 1;
    private final int resultCode;

    private BattleResult(int result) {
        this.resultCode = result;
    }

    public static BattleResult defenderWins() {
        return new BattleResult(RESULT_CODE_DEFENDER_WINS);
    }

    public static BattleResult attackerWins() {
        return new BattleResult(RESULT_CODE_ATTACKER_WINS);
    }

    public boolean wonByAttacker() {
        return resultCode == RESULT_CODE_ATTACKER_WINS;
    }

}
