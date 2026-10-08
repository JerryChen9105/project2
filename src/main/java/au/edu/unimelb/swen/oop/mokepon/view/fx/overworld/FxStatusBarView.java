package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.StatusBarView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class FxStatusBarView implements StatusBarView {

    private StackPane statusBarRoot = new StackPane();
    private Rectangle statusTxtBg = new Rectangle(450, 35);
    private Text statusTxt = new Text();


    public FxStatusBarView() {
        statusTxt.setFont(Font.font(15));
        statusTxt.setStyle("-fx-fill: #000000;");

        statusTxtBg.setFill(Paint.valueOf("white"));

        statusBarRoot.getChildren().addAll(statusTxtBg, statusTxt);
        statusBarRoot.setAlignment(Pos.CENTER);
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

