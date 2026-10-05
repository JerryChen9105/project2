package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.StatusBarView;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class FxStatusBarView implements StatusBarView {

    private StackPane statusBarRoot = new StackPane();
    private Text statusTxt = new Text();


    public FxStatusBarView() {
        statusTxt.setFont(FontLoader.getGostischFont(20));
        statusTxt.setStyle("-fx-fill: #ffffff;");
        statusBarRoot.getChildren().add(statusTxt);
    }


    @Override
    public Node node() {
        return statusBarRoot;
    }

    @Override
    public void setStatusText(String text) {
        statusTxt.setText(text);
    }

    @Override
    public void clear() {
        statusTxt.setText("");
    }
}
