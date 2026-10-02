package au.edu.unimelb.swen.oop.mokepon.state;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class GameState {

    private ObjectProperty<Mokepon> selectedMokepon = new SimpleObjectProperty<>();
    private ObjectProperty<Mokepon> playerMokepon = new SimpleObjectProperty<>();
    private ObjectProperty<Mokepon> enemyMokepon = new SimpleObjectProperty<>();
    private IntegerProperty fightCount = new SimpleIntegerProperty(0);
    private Inventory inventory = new Inventory();

    public ObjectProperty<Mokepon> selectedMokeponProperty() {
        return selectedMokepon;
    }

    public Mokepon getSelectedMokepon() {
        return selectedMokepon.getValue();
    }

    public void setSelectedMokepon(Mokepon mokepon) {
        selectedMokepon.setValue(mokepon);
    }

    public Mokepon getPlayerMokepon() {
        return playerMokepon.getValue();
    }

    public void setPlayerMokepon(Mokepon mokepon) {
        playerMokepon.setValue(mokepon);
    }

    public Mokepon getEnemyMokepon() {
        return enemyMokepon.getValue();
    }

    public void setEnemyMokepon(Mokepon mokepon) {
        enemyMokepon.setValue(mokepon);
    }

    public int getFightCount() {
        return fightCount.getValue();
    }

    public void incrementFightCount() {
        fightCount.setValue(fightCount.getValue() + 1);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void resetForNewSelection() {
        selectedMokepon.setValue(null);
        playerMokepon.setValue(null);
    }

}
