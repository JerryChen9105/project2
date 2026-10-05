package au.edu.unimelb.swen.oop.mokepon.view.fx.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;
import au.edu.unimelb.swen.oop.mokepon.state.Inventory;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.FontLoader;
import au.edu.unimelb.swen.oop.mokepon.view.view_interface.overworld.PlayerStatisticView;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FxPlayerStatisticView implements PlayerStatisticView {

    private VBox playerStatisticRoot = new VBox(5);
    private Text mokeponStatisticTxt = new Text();
    private Text inventoryStatisticTxt = new Text();


    public FxPlayerStatisticView() {
        mokeponStatisticTxt.setFont(FontLoader.getGostischFont(18));
        inventoryStatisticTxt.setFont(FontLoader.getGostischFont(18));
        playerStatisticRoot.getChildren().addAll(mokeponStatisticTxt, inventoryStatisticTxt);
    }


    @Override
    public Node node() {
        return playerStatisticRoot;
    }

    @Override
    public void setMokeponStatistic(Mokepon mokepon) {
        if (mokepon == null) {
            mokeponStatisticTxt.setText("");
            return;
        }
        MokeponStatus status = mokepon.getMokeponStatus();
        String hpText = status == null
                ? mokepon.getMaxHP() + " / " + mokepon.getMaxHP()
                : status.getCurrentHP() + " / " + mokepon.getMaxHP();
        String energyText = status == null
                ? mokepon.getMaxEnergy() + " / " + mokepon.getMaxEnergy()
                : status.getCurrentEnergy() + " / " + mokepon.getMaxEnergy();
        mokeponStatisticTxt.setText(
                mokepon.getName()
                        + "\nHP: " + hpText
                        + "\nEnergy: " + energyText
        );
    }

    @Override
    public void setInventoryStatistic(Inventory inventory) {
        if (inventory == null) {
            inventoryStatisticTxt.setText("");
            return;
        }
        StringBuilder builder = new StringBuilder("Items:");
        for (ItemType itemType : ItemType.values()) {
            builder.append("\n").append(itemType.name())
                    .append(": ").append(inventory.getCount(itemType));
        }
        inventoryStatisticTxt.setText(builder.toString());
    }
}
