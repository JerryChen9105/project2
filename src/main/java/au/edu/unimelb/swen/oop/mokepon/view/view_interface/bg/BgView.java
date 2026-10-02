package au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;
import javafx.scene.control.MenuItem;

import java.util.function.Consumer;

public interface BgView extends View {

    public void enable(int index);

    public void disable(int index);

    public MenuItem[] getMenuItemArr();

}
