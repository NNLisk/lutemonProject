package com.example.lutemonnikomatei;

public class BattleManager {

    public static BattleManager battleManagerInstance = null;
    private BattleManager() {

    }

    public static BattleManager getInstance() {
        if (battleManagerInstance == null) {
            battleManagerInstance = new BattleManager();
        }
        return battleManagerInstance;
    }
}
