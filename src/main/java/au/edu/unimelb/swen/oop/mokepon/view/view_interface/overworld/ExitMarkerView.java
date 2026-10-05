package au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import javafx.beans.property.IntegerProperty;

import java.util.function.Consumer;

public interface ExitMarkerView extends View {

    public void setTitle(String text);

    public void setReturnButton(IntegerProperty fightCount, Consumer<IntegerProperty> onReturnAction);

    public void setQuitButton(Consumer<Object> onTerminateProgram);

    public void setVisible(boolean visible);

}
