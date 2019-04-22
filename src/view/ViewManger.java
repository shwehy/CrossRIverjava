package view;

import java.util.List;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class ViewManger {
    private static final int WIDTH = 1024, HEIGHT=700;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private CrossRiverSubScene creditsSubScene;
    private CrossRiverSubScene helpSubScene;
    private CrossRiverSubScene scoresSubScene;
    private CrossRiverSubScene chooseCharctersSubScene;

    private CrossRiverSubScene sceneToHide;

    List<ShipPicker> shipsList;
    List<CrossRiverButton> menuButtons;

    private SHIP choosenShip;

    public ViewManger(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        creatSubScene();
        creatButtons();
        creatBackground();
        creatLogo();
    }

    private void showSubScene(CrossRiverSubScene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    private void creatSubScene(){
        creditsSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(creditsSubScene);
        helpSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(helpSubScene);
        scoresSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(scoresSubScene);

        creatShipChooserSubScene();

    }
    private void creatShipChooserSubScene(){
        chooseCharctersSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(chooseCharctersSubScene);

        InfoLabel chooseshipLabel = new InfoLabel("Choose your ship!");
        chooseshipLabel.setLayoutX(110);
        chooseshipLabel.setLayoutY(25);
        chooseCharctersSubScene.getPane().getChildren().add(chooseshipLabel);
        chooseCharctersSubScene.getPane().getChildren().addAll(creatShipToChoose(),createButtonToStart());

    }

    private CrossRiverButton createButtonToStart(){
        CrossRiverButton startButton = new CrossRiverButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(320);
        return startButton;
    }
    private HBox creatShipToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for(SHIP ship: SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(e->{
                for(ShipPicker ship1 : shipsList){
                    ship1.setIsCircleChoosen(false);
                }
                shipToPick.setIsCircleChoosen(true);
                choosenShip = shipToPick.getShip();
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }
    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuButtons(CrossRiverButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }
    private void creatButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();

    }
    private void createStartButton(){
        CrossRiverButton startButton = new CrossRiverButton("PLAY");
        addMenuButtons(startButton);
        startButton.setOnAction(e->{
            showSubScene(chooseCharctersSubScene);
        });
    }
    private void createScoresButton(){
        CrossRiverButton scoresButton = new CrossRiverButton("SCORES");
        addMenuButtons(scoresButton);
        scoresButton.setOnAction(e->{
            showSubScene(scoresSubScene);
        });
    }
    private void createHelpButton(){
        CrossRiverButton helpButton = new CrossRiverButton("HELP");
        addMenuButtons(helpButton);
        helpButton.setOnAction(e->{
            showSubScene(helpSubScene);
        });
    }
    private void createCreditsButton(){
        CrossRiverButton creditsButton = new CrossRiverButton("CREDITS");
        addMenuButtons(creditsButton);
        creditsButton.setOnAction(e->{
            showSubScene(creditsSubScene);
        });
    }
    private void createExitButton(){
        CrossRiverButton exitButton = new CrossRiverButton("EXIT");
        addMenuButtons(exitButton);
        exitButton.setOnAction(e->{
            mainStage.close();
        });
    }
    private void creatBackground(){
        Image backgroundImage = new Image("view/resources/backgroundColorGrass.png",1024,1024,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
    }
    private void creatLogo(){
        ImageView logo = new ImageView("view/resources/logo.png");
        logo.setLayoutX(400);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }

}
