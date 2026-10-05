package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.MovementView;
import javafx.scene.Group;
import javafx.scene.Node;

public class FxMovementView implements MovementView {

    private Group movementRoot = new Group();


    public FxMovementView() {
    }


    @Override
    public Node node() {
        return movementRoot;
    }
}
