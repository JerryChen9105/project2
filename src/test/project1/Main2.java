package project1;

import project1.Backend.Mokepon;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import project1.UIElement.Utils.GameWindow;


public class Main2 extends Application {

    protected static final String[] attrArr = {"HP", "Energy", "Strength", "Accuracy", "Defence", "Dodge", "Skills"};
    protected static final int maxMokeponNum = 50;

    @Override
    public void start(Stage stage) throws IOException {

        /* =========================================================================================
         *  PART 1: BACKEND ELEMENT
         * ========================================================================================= */

        Properties gameData = loadPropertiesFromArgument();

        ArrayList<Mokepon> mokeponList = new ArrayList<>();
        int mokeponCount = 0;
        String propertyPrefix = null;

        while (mokeponCount < maxMokeponNum) {
            propertyPrefix = "mokepon." + mokeponCount + ".";
            try {
                String name = gameData.getProperty(propertyPrefix + "name");
                String imgURL = gameData.getProperty(propertyPrefix + "image");
                String hp = gameData.getProperty(propertyPrefix + "hp");
                String energy = gameData.getProperty(propertyPrefix + "energy");
                String strength = gameData.getProperty(propertyPrefix + "strength");
                String accuracy = gameData.getProperty(propertyPrefix + "accuracy");
                String defence = gameData.getProperty(propertyPrefix + "defence");
                String dodge = gameData.getProperty(propertyPrefix + "dodge");
                String skillStr = gameData.getProperty(propertyPrefix + "skills");

                Mokepon mokepon = new Mokepon(name, imgURL, Integer.parseInt(hp), Integer.parseInt(energy),
                        Integer.parseInt(strength), Integer.parseInt(accuracy), Integer.parseInt(dodge),
                        Integer.parseInt(defence), skillStr);
                mokeponList.add(mokepon);
                mokeponCount++;
            } catch (Exception e) {
                break;
            }
        }

        HashMap<String, Mokepon> mokeponTable = new HashMap<>();
        mokeponList.forEach((mokepon) -> mokeponTable.put(mokepon.getName(), mokepon));

        // initialize battle object
        Mokepon enemy = new Mokepon();
        if (!mokeponList.isEmpty()) {
            enemy.attrCopy(mokeponList.getFirst());
        }
        Mokepon self = new Mokepon();
        Mokepon battleEnemy = new Mokepon();
        Mokepon battleSelf = new Mokepon();

        /* =========================================================================================
         *  PART 2: UI layout
         * ========================================================================================= */

        // 1. game window and font
        Font selectTitleFont = createGotischFont(34, new Main2());
        Font selectBtnFont = createGotischFont(20, new Main2());
        Font startBtnFont = createGotischFont(24, new Main2());
        Font battleNameFont = createGotischFont(28,  new Main2());
        Font actionBtnFont = createGotischFont(18,  new Main2());

        GameWindow gameWindow = new GameWindow();
        Pane mainRoot = new Pane();

        // 2. menu and background
        ArrayList<MenuItem> gameControlList = new ArrayList<>();
        MenuBar menuBar = createMenuBar(gameControlList);
        ImageView bgImgView = createBGImageView();

        // ------------------- Select Stage Layout -------------------
        Group selectRoot = new Group();
        Button startButton = createStartButton(startBtnFont);

        // -- Display Block
        VBox displayBlock = new VBox(10);
        Text displayName = createDisplayName("Select your Mokepon");
        Circle circle = createDisplayCircle();
        ImageView scrollImgView = createDisplayScroll();
        displayBlock.setLayoutX(0.627778 * screenWidth - 215);
        displayBlock.setLayoutY(0.347826 * screenHeight - 210);
        displayBlock.setAlignment(Pos.CENTER);
        displayBlock.getChildren().addAll(circle, displayName, scrollImgView);

        // -- Select Block
        VBox selectBlock = new VBox(20);
        selectBlock.setAlignment(Pos.CENTER);
        Text selectTitle = createSelectTitle("Choose your Mokepon", selectTitleFont);
        selectBlock.setLayoutX(titleX + 10);
        selectBlock.setLayoutY(titleY - 20);

        ObjectProperty<Button> displayTrigger = new SimpleObjectProperty<>(null);
        ArrayList<Button> selectBtnList = new ArrayList<>();
        ScrollPane buttonBlock = createSelectButtonBlock(mokeponList, selectBtnFont, selectBtnList);
        selectBlock.getChildren().addAll(selectTitle, buttonBlock);

        // -- Attributes & Image Display Block
        ArrayList<Text> attrTxtList = new ArrayList<>();
        GridPane attrBlock = createAttributeDisplayBlock(attrArr, attrTxtList);
        ImageView displayImgView = createDisplayImg("Bubbore");
        displayImgView.setImage(null);

        // -- construction of select screen
        selectRoot.getChildren().addAll(selectBlock, displayBlock, displayImgView, attrBlock, startButton);


        // ------------------- Battle Stage Layout -------------------
        Group battleRoot = new Group();
        TextArea battleLog = createBattleLog();

        // -- Enemy Display Block
        StackPane enemyBlock = new StackPane();
        enemyBlock.setLayoutX(screenWidth - 20 - 20 - 320);
        enemyBlock.setLayoutY(screenTop + menuBarHeight + 10 + 20);
        enemyBlock.setAlignment(Pos.CENTER);

        VBox enemyDisplayBlock = new VBox(2);
        enemyDisplayBlock.setAlignment(Pos.CENTER);
        Text enemyName = new Text("Enemy");
        enemyName.setFont(battleNameFont);
        ImageView enemyBg = createEnemyBG();
        ImageView enemyImgView = createImageView(enemy.getImgURL(), 256, 256, false);
        ProgressBar[] enemyBarArr = new ProgressBar[2];
        VBox enemyBarBlock = createEnemyBarBlock(enemyBarArr);

        enemyDisplayBlock.getChildren().addAll(enemyName, enemyImgView, enemyBarBlock);
        enemyBlock.getChildren().addAll(enemyBg, enemyDisplayBlock);

        // -- Self Display Block
        StackPane selfBlock = new StackPane();
        selfBlock.setLayoutX(screenLeft + 20 + 20);
        selfBlock.setLayoutY(screenHeight + menuBarHeight - 20 - 20 - 430);
        selfBlock.setAlignment(Pos.CENTER);

        VBox selfDisplayBlock = new VBox(2);
        selfDisplayBlock.setAlignment(Pos.CENTER);
        ImageView selfBg = createSelfBG();
        ImageView selfImgView = createImageView(enemy.getImgURL(), 256, 256, false);
        Text selfName = new Text("Self");
        selfName.setFont(battleNameFont);
        ProgressBar[] selfBarArr = new ProgressBar[2];
        Text[] selfBarInfoArr = new Text[2];
        VBox selfBarBlock = createSelfBarBlock(selfBarArr, selfBarInfoArr);

        ArrayList<Button> skillBtnList = new ArrayList<>();
        VBox skillBlock = createSkillBlock(startBtnFont, actionBtnFont, skillBtnList);

        selfDisplayBlock.getChildren().addAll(selfName, selfImgView, selfBarBlock);
        HBox selfDisplayActionBlock = new HBox(20);
        selfDisplayActionBlock.setAlignment(Pos.CENTER);
        selfDisplayActionBlock.getChildren().addAll(selfDisplayBlock, skillBlock);
        selfBlock.getChildren().addAll(selfBg, selfDisplayActionBlock);

        // -- construction of battle screen
        battleRoot.getChildren().addAll(battleLog, enemyBlock, selfBlock);
        battleRoot.setVisible(false);


        /* =========================================================================================
         *  PART 3: UI control
         * ========================================================================================= */

        // 1. Select Control
        selectBtnList.forEach(btn -> {
            btn.setOnMouseEntered(event -> {
                if (displayTrigger.get() == btn) { btn.setOpacity(0.9); }
                else { btn.setOpacity(0.5); }
            });
            btn.setOnMouseExited(event -> {
                if (displayTrigger.get() == btn) { btn.setOpacity(0.75); }
                else { btn.setOpacity(0.2); }
            });
            btn.setOnMouseClicked(event -> {
                if (displayTrigger.get() == btn) {
                    btn.setOpacity(0.2);
                    displayTrigger.set(null);
                } else {
                    btn.setOpacity(0.75);
                    displayTrigger.set(btn);
                }
            });
        });

        displayTrigger.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) { oldValue.setOpacity(0.2); }
            if (newValue == null) {
                displayName.setText("Select your Mokepon");
                displayImgView.setImage(null);
                attrTxtList.forEach(attrAppend -> attrAppend.setText(""));
            } else {
                String url = mokeponTable.get(newValue.getText()).getImgURL();
                displayName.setText(newValue.getText());
                displayImgView.setImage(createImage(url, 256, 256, false));

                Mokepon cur = mokeponTable.get(newValue.getText());
                attrTxtList.get(0).setText(cur.getHP() + "");
                attrTxtList.get(1).setText(cur.getEnergy() + "");
                attrTxtList.get(2).setText(cur.getStrength() + "");
                attrTxtList.get(3).setText(cur.getAccuracy() + "");
                attrTxtList.get(4).setText(cur.getDefence() + "");
                attrTxtList.get(5).setText(cur.getDodge() + "");
                attrTxtList.get(6).setText(String.join(", ", cur.getSkills()));
            }
        });

        // 2. Start Button Control
        startButton.disableProperty().bind(displayTrigger.isNull());
        startButton.setOnMouseEntered(event -> startButton.setOpacity(0.5));
        startButton.setOnMouseExited(event -> startButton.setOpacity(0.2));
        startButton.setOnMouseClicked(event -> {

            if (displayTrigger.get() == null) return;

            startButton.setOpacity(0.75);

            String name = displayTrigger.get().getText();
            self.attrCopy(mokeponTable.get(name));
            selfImgView.setImage(createImage(self.getImgURL(), 256, 256, false));

            enemy.attrCopy(mokeponList.get(randomInt(0, mokeponList.size())));
            enemyImgView.setImage(createImage(enemy.getImgURL(), 256, 256, false));

            battleSelf.attrCopy(self);
            battleEnemy.attrCopy(enemy);
            enemyName.setText(battleEnemy.getName());
            selfName.setText(battleSelf.getName());
            updateAllBattleStatus(battleEnemy, enemy, battleSelf, self, enemyBarArr, selfBarArr, selfBarInfoArr);
            updateSkillBtnStates(battleSelf, self, skillBtnList);

            battleLog.clear();
            enemyBg.setEffect(null);
            selfBg.setEffect(null);
            battleLog.appendText("Player chose " + name + ". Began a fight against enemy " + enemy.getName() + "!\n");

            selectRoot.setVisible(false);
            battleRoot.setVisible(true);
        });

        // 3. Menu Control
        battleRoot.visibleProperty().addListener((observable, oldvalue, newValue) -> {
            if (!newValue) {
                gameControlList.getFirst().setDisable(true);
                gameControlList.get(1).setDisable(true);
            } else {
                gameControlList.getFirst().setDisable(false);
                gameControlList.get(1).setDisable(false);
            }
        });

        gameControlList.getFirst().setOnAction(click -> {
            selectRoot.setVisible(true);
            battleRoot.setVisible(false);
            displayTrigger.set(null);
        });

        gameControlList.get(1).setOnAction(click -> {
            battleSelf.attrCopy(self);
            battleEnemy.attrCopy(enemy);

            updateAllBattleStatus(battleEnemy, enemy, battleSelf, self, enemyBarArr, selfBarArr, selfBarInfoArr);
            updateSkillBtnStates(battleSelf, self, skillBtnList);

            battleLog.clear();
            battleLog.appendText("Player chose " + battleSelf.getName() + ". Began a fight against enemy " + battleEnemy.getName() + "!\n");
            enemyBg.setEffect(null);
            selfBg.setEffect(null);
        });

        gameControlList.get(2).setOnAction(click -> gameWindow.close());

        // 4. Skill Buttons Control
        skillBtnList.forEach(btn -> {
            btn.setOnMouseEntered(event -> btn.setOpacity(0.5));
            btn.setOnMouseExited(event -> btn.setOpacity(0.2));
            btn.setOnMouseClicked(event -> btn.setOpacity(0.75));
        });

        skillBtnList.getFirst().setOnAction(click -> {
            battleLog.appendText("\nPlayer's " + battleSelf.getName() + " used Bite!\n");
            int damage = battleSelf.bite(battleEnemy);
            updateAllBattleStatus(battleEnemy, enemy, battleSelf, self, enemyBarArr, selfBarArr, selfBarInfoArr);

            if (damage > 0) {
                battleLog.appendText("\tHit " + battleEnemy.getName() + " for " + damage + " damage!\n");
            } else if (damage == 0) {
                battleLog.appendText("\tBut they missed.\n");
            }
            updateSkillBtnStates(battleSelf, self, skillBtnList);

            if (mokeponIsDied(battleEnemy, enemyBg)) {
                battleLog.appendText("\n\nEnemy's " + battleEnemy.getName() + " fainted!\nYou win\n");
                skillBtnList.forEach(btn -> btn.setDisable(true));
                return;
            }

            enemyAutoRandomAction(battleEnemy, enemy, battleSelf, self, battleLog, enemyBarArr, selfBarArr, selfBarInfoArr);

            if (mokeponIsDied(battleSelf, selfBg)) {
                battleLog.appendText("\n\nPlayer's " + battleSelf.getName() + " fainted!\nYou lose\n");
                updateSkillBtnStates(battleSelf, self, skillBtnList);
            }
        });

        skillBtnList.get(1).setOnAction(click -> {
            battleLog.appendText("\nPlayer's " + battleSelf.getName() + " used Tackle!\n");
            int damage = battleSelf.tackle(battleEnemy);
            updateAllBattleStatus(battleEnemy, enemy, battleSelf, self, enemyBarArr, selfBarArr, selfBarInfoArr);

            if (damage > 0) {
                battleLog.appendText("\tHit " + battleEnemy.getName() + " for " + damage + " damage!\n");
            } else if (damage == 0) {
                battleLog.appendText("\tBut they missed.\n");
            }
            updateSkillBtnStates(battleSelf, self, skillBtnList);

            if (mokeponIsDied(battleEnemy, enemyBg)) {
                battleLog.appendText("\n\nEnemy's " + battleEnemy.getName() + " fainted!\nYou win\n");
                skillBtnList.forEach(btn -> btn.setDisable(true));
                return;
            }

            enemyAutoRandomAction(battleEnemy, enemy, battleSelf, self, battleLog, enemyBarArr, selfBarArr, selfBarInfoArr);

            if (mokeponIsDied(battleSelf, selfBg)) {
                battleLog.appendText("\n\nPlayer's " + battleSelf.getName() + " fainted!\nYou lose\n");
                updateSkillBtnStates(battleSelf, self, skillBtnList);
            }
        });

        skillBtnList.get(2).setOnAction(click -> {
            battleLog.appendText("\nPlayer's " + battleSelf.getName() + " used Bunker!\n");
            int defence = battleSelf.bunker();
            updateAllBattleStatus(null, null, battleSelf, self, null, selfBarArr, selfBarInfoArr);

            battleLog.appendText("\tThey raised their defence by " + defence + " points!\n");
            updateSkillBtnStates(battleSelf, self, skillBtnList);

            enemyAutoRandomAction(battleEnemy, enemy, battleSelf, self, battleLog, enemyBarArr, selfBarArr, selfBarInfoArr);

            if (mokeponIsDied(battleSelf, selfBg)) {
                battleLog.appendText("\n\nPlayer's " + battleSelf.getName() + " fainted!\nYou lose\n");
                updateSkillBtnStates(battleSelf, self, skillBtnList);
            }
        });

        skillBtnList.get(3).setOnAction(click -> {
            battleLog.appendText("\nPlayer's " + battleSelf.getName() + " used Wait!\n");
            String waitStr = battleSelf.waitEnergy(self);
            updateAllBattleStatus(null, null, battleSelf, self, null, selfBarArr, selfBarInfoArr);

            battleLog.appendText("\t" + waitStr + "\n");
            updateSkillBtnStates(battleSelf, self, skillBtnList);

            enemyAutoRandomAction(battleEnemy, enemy, battleSelf, self, battleLog, enemyBarArr, selfBarArr, selfBarInfoArr);

            if (mokeponIsDied(battleSelf, selfBg)) {
                battleLog.appendText("\n\nPlayer's " + battleSelf.getName() + " fainted!\nYou lose\n");
                updateSkillBtnStates(battleSelf, self, skillBtnList);
            }
        });


        /* =========================================================================================
         *  PART 4: scene Rendering
         * ========================================================================================= */
        ArrayList<Node> nodeArrayList = new ArrayList<>();
        nodeArrayList.add(bgImgView);
        nodeArrayList.add(menuBar);
        nodeArrayList.add(selectRoot);
        nodeArrayList.add(battleRoot);

        selectScreen(gameWindow, mainRoot, nodeArrayList);
        gameWindow.show();
    }


    /* =========================================================================================
     *  Backend Logic Methods
     * ========================================================================================= */

    private void updateBarProgress(ProgressBar[] barArr, Mokepon current, Mokepon origin) {
        int curHP = current.getHP();
        int maxHP = origin.getHP();
        if (curHP <= 0) {
            barArr[0].setProgress(0);
        } else {
            barArr[0].setProgress(Math.max((double) curHP / maxHP, 0.03));
        }

        int curEnergy = current.getEnergy();
        int maxEnergy = origin.getEnergy();
        if (curEnergy <= 0) {
            barArr[1].setProgress(0);
        } else {
            barArr[1].setProgress(Math.max((double) curEnergy / maxEnergy, 0.03));
        }
    }

    private void updateBarInfo(Text[] selfBarInfoArr, Mokepon battleSelf, Mokepon self) {
        int curHP = battleSelf.getHP();
        int maxHP = self.getHP();
        if (curHP > maxHP) {
            battleSelf.setHP(maxHP);
        } else if (curHP < 0) {
            battleSelf.setHP(0);
        }
        selfBarInfoArr[0].setText(battleSelf.getHP() + "/" + maxHP);

        int curEnergy = battleSelf.getEnergy();
        int maxEnergy = self.getEnergy();
        if (curEnergy > maxEnergy) {
            battleSelf.setEnergy(maxEnergy);
        } else if (curEnergy < 0) {
            battleSelf.setEnergy(0);
        }
        selfBarInfoArr[1].setText(battleSelf.getEnergy() + "/" + maxEnergy);
    }

    private void updateAllBattleStatus(Mokepon battleEnemy, Mokepon enemy, Mokepon battleSelf, Mokepon self,
                                       ProgressBar[] enemyBarArr, ProgressBar[] selfBarArr,
                                       Text[] selfBarInfoArr) {
        if (battleEnemy != null && enemy != null && enemyBarArr != null ) {
            updateBarProgress(enemyBarArr, battleEnemy, enemy);
        }
        updateBarProgress(selfBarArr, battleSelf, self);
        updateBarInfo(selfBarInfoArr, battleSelf, self);
    }

    private Boolean mokeponIsDied(Mokepon battleMokepon, ImageView bgImageView) {
        if (battleMokepon.getHP() > 0) return false;

        ColorAdjust redAdjust = new ColorAdjust();
        redAdjust.setHue(-0.13);
        redAdjust.setSaturation(0.3);
        redAdjust.setBrightness(-0.1);
        bgImageView.setEffect(redAdjust);
        return true;
    }

    private void updateSkillBtnStates(Mokepon battleSelf, Mokepon self, ArrayList<Button> skillBtnList) {
        if (battleSelf.getHP() <= 0) {
            skillBtnList.forEach(btn -> btn.setDisable(true));
            return;
        }

        int currentEnergy = battleSelf.getEnergy();
        skillBtnList.get(0).setDisable(currentEnergy < 1);
        skillBtnList.get(1).setDisable(currentEnergy < 5);
        skillBtnList.get(2).setDisable(currentEnergy < 3 || battleSelf.getDefence() >= 100);
        skillBtnList.get(3).setDisable(currentEnergy >= self.getEnergy());
    }

    private void enemyAutoRandomAction(Mokepon battleEnemy, Mokepon enemy, Mokepon battleSelf, Mokepon self,
                                       TextArea battleLog, ProgressBar[] enemyBarArr, ProgressBar[] selfBarArr,
                                       Text[] selfBarInfoArr) {
        int actionIndex = -1;
        boolean inLoop = true;
        int damage = -1, defence = -1;
        String logMessage = null;

        while (inLoop) {
            actionIndex = randomInt(0, 4);
            switch (actionIndex) {
                case 0:
                    damage = battleEnemy.bite(battleSelf);
                    break;
                case 1:
                    damage = battleEnemy.tackle(battleSelf);
                    break;
                case 2:
                    defence = battleEnemy.bunker();
                    break;
                case 3:
                    logMessage = battleEnemy.waitEnergy(enemy);
                    break;
            }
            if (logMessage != null && !logMessage.isBlank()) inLoop = false;
            else if (defence > 0) inLoop = false;
            else if (damage != -1) inLoop = false;
        }
        switch (actionIndex) {
            case 0, 1:
                if (actionIndex == 0) {
                    battleLog.appendText("\nEnemy " + battleEnemy.getName() + " used Bite!\n");
                } else {
                    battleLog.appendText("\nEnemy " + battleEnemy.getName() + " used Tackle!\n");
                }

                if (damage != 0) {
                    battleLog.appendText("\tHit " + battleSelf.getName() + " for " + damage + " damage!\n");
                } else {
                    battleLog.appendText("\tBut they missed.\n");
                }
                break;
            case 2:
                battleLog.appendText("\nEnemy " + battleEnemy.getName() + " used Bunker!\n");
                battleLog.appendText("\tThey raised their defence by " + defence + " points!\n");
                break;
            case 3:
                battleLog.appendText("\nEnemy " + battleEnemy.getName() + " used Wait!\n");
                battleLog.appendText("\t" + logMessage + "\n");
                break;
        }
        updateAllBattleStatus(battleEnemy, enemy, battleSelf, self, enemyBarArr, selfBarArr, selfBarInfoArr);
    }

    public void selectScreen(GameWindow gameWindow, Pane root, ArrayList<Node> nodeList) {
        nodeList.forEach(node -> root.getChildren().add(node));
        Scene scene = new Scene(root);

        gameWindow.setX(relativeCenterX(screenBounds.getWidth() / 2, screenWidth));
        gameWindow.setY(screenTop);

        gameWindow.setTitle("Mokepon");
        gameWindow.setScene(scene);
    }

    private Properties loadPropertiesFromArgument() throws IOException {
        String argument = getParameters().getRaw().getFirst();
        if (!argument.startsWith("data=")) {
            throw new IllegalArgumentException("Missing expected argument in the form data=relativeFilePath");
        }

        Properties properties = new Properties();
        try (var input = Files.newInputStream(Path.of(argument.substring("data=".length())))) {
            properties.load(input);
        }
        return properties;
    }

    static void main(String[] args) {
        launch(args);
    }

}
