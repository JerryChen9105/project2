package au.edu.unimelb.swen.oop.mokepon.event;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;

public class ChooseMokeponEvent {

    public ChooseMokeponEvent(Mokepon selectedMokepon, Mokepon enemyMokepon, GameState state) {
        state.setPlayerMokepon(selectedMokepon);
        state.setEnemyMokepon(enemyMokepon);
    }

}
