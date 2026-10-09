package au.edu.unimelb.swen.oop.mokepon.view.view_interface.battle;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.View;

public interface BattleWinMarkerView extends View {

    public void setReturnBtn(Runnable onReturnClicked);

    public void setVisible(boolean visible);
}


