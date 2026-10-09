package au.edu.unimelb.swen.oop.mokepon.event;

import au.edu.unimelb.swen.oop.mokepon.state.GameState;

public record EnterBattleEvent(GameState state, double selfDamageMult, double enemyDamageMult) {}
