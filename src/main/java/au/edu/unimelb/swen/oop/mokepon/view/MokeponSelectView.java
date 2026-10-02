package au.edu.unimelb.swen.oop.mokepon.view;

import au.edu.unimelb.swen.oop.mokepon.util.ScreenPosition;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponDetailView;
import au.edu.unimelb.swen.oop.mokepon.view.fx.select.FxMokeponListView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponChooseView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponDetailView;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.select.MokeponListView;
import javafx.scene.Group;
import javafx.scene.Node;

public class MokeponSelectView {

    private MokeponListView mokeponListView;
    private MokeponDetailView mokeponDetailView;
    private MokeponChooseView mokeponChooseView;

    private Group mokeponSelectRoot = new Group();


    public MokeponSelectView(
            MokeponListView mokeponListView,
            MokeponDetailView mokeponDetailView,
            MokeponChooseView mokeponChooseView) {
        this.mokeponListView = mokeponListView;
        this.mokeponDetailView = mokeponDetailView;
        this.mokeponChooseView = mokeponChooseView;

        Node mokeponList = this.mokeponListView.node();
        mokeponList.setLayoutX(0.044444 * ScreenPosition.SCREENWIDTH);
        mokeponList.setLayoutY(0.135982 * ScreenPosition.SCREENHEIGHT - 60);

        Node mokeponDetail = this.mokeponDetailView.node();
        mokeponDetail.setLayoutX(0.627778 * ScreenPosition.SCREENWIDTH - 215);
        mokeponDetail.setLayoutY(0.347826 * ScreenPosition.SCREENHEIGHT - 230);

        Node mokeponChooseBtn = this.mokeponChooseView.node();
        mokeponChooseBtn.setLayoutX(0.816406 * ScreenPosition.SCREENWIDTH);
        mokeponChooseBtn.setLayoutY(0.670572 * ScreenPosition.SCREENHEIGHT);


        this.mokeponSelectRoot.setLayoutX(ScreenPosition.SCREENLEFT);
        this.mokeponSelectRoot.setLayoutY(ScreenPosition.SCREENTOP);
        this.mokeponSelectRoot.getChildren().addAll(mokeponList, mokeponDetail, mokeponChooseBtn);
    }


    public Group getMokeponSelectRoot() {
        return mokeponSelectRoot;
    }

    public MokeponListView getMokeponListView() {
        return mokeponListView;
    }

    public MokeponDetailView getMokeponDetailView() {
        return mokeponDetailView;
    }

    public MokeponChooseView getMokeponChooseView() {
        return mokeponChooseView;
    }

}
