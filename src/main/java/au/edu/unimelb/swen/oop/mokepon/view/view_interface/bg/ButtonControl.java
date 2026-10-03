package au.edu.unimelb.swen.oop.mokepon.view.view_interface.bg;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface ButtonControl extends View {

    public void setText(String btnText);
    public void setDisabled(boolean disabled);
    public void setOnAction(Runnable runable);

}
