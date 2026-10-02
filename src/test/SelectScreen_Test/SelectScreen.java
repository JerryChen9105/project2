package au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test;

import javafx.scene.Group;

public class SelectScreen {
    private static Group selectScreenRoot = new Group();

    private SelectScreen() throws Exception {
        throw new IllegalAccessException("selectScreen initializer is not accessible");
    }

    public Group getSelectScreenRoot() {
        return selectScreenRoot;
    }

    public void addScreenElement(SelectScreenElement... selectScreenElementArr) {

    }

}
