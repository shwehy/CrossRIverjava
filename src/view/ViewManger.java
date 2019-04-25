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
import Logic.Story1Logic;
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
    private CrossRiverSubScene chooseFarmerSubScene;
    private CrossRiverSubScene chooseCarnivorousAnimalSubScene;
    private CrossRiverSubScene chooseHerbivorousAnimalSubScene;
    private CrossRiverSubScene choosePlantsSubScene;
    private CrossRiverSubScene chooseStoryGameSubScene;


    private CrossRiverSubScene sceneToHide;
    List<ShipPicker> shipsList;

    List<CrossRiverButton> menuButtons;

    private SHIP choosenShip;
    private FARMER choosenFarmer;
    private STORY choosenStory;
    private HERBANIMAL choosenHerbAnimal;
    private CARNANIMAL choosenCarnAnimal;
    private PLANTS choosenPlants;
    private static ViewManger single_Instance = null;
    private ViewManger(){
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
    public static ViewManger getInstance(){
        if(single_Instance == null)
            single_Instance = new ViewManger();
        return single_Instance;
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

        createStoryGameSubScene();
        creatShipChooserSubScene();
        createFarmerChooserSubScene();
        createHerbAnimalSubScene();
        createCarnAnimalSubScene();
        createPlanetChooserSubScene();
    }

    private void createStoryGameSubScene(){
        chooseStoryGameSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(chooseStoryGameSubScene);

        InfoLabel choosesGameStoryLabel = new InfoLabel("Choose Story");
        choosesGameStoryLabel.setLayoutX(110);
        choosesGameStoryLabel.setLayoutY(25);
        chooseStoryGameSubScene.getPane().getChildren().addAll(choosesGameStoryLabel);
        chooseStoryGameSubScene.getPane().getChildren().addAll(creatStoryToChoose(),createNextToShip());
    }
    private void creatShipChooserSubScene(){
        chooseCharctersSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(chooseCharctersSubScene);

        InfoLabel chooseshipLabel = new InfoLabel("Choose your ship!");
        chooseshipLabel.setLayoutX(110);
        chooseshipLabel.setLayoutY(25);
        chooseCharctersSubScene.getPane().getChildren().add(chooseshipLabel);
        chooseCharctersSubScene.getPane().getChildren().addAll(creatShipToChoose(),createNextToFarmers());

    }
    private void createFarmerChooserSubScene() {
        chooseFarmerSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(chooseFarmerSubScene);

        InfoLabel chooseFarmerLabel = new InfoLabel("Choose Farmer");
        chooseFarmerLabel.setLayoutX(110);
        chooseFarmerLabel.setLayoutY(25);
        chooseFarmerSubScene.getPane().getChildren().add(chooseFarmerLabel);
        chooseFarmerSubScene.getPane().getChildren().addAll(creatFarmerToChoose(),createNextToHerb());
    }
    private void createHerbAnimalSubScene(){
        chooseHerbivorousAnimalSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(chooseHerbivorousAnimalSubScene);

        InfoLabel chooseAnimalLabel = new InfoLabel("Choose Animal");
        chooseAnimalLabel.setLayoutX(110);
        chooseAnimalLabel.setLayoutY(25);
        chooseHerbivorousAnimalSubScene.getPane().getChildren().add(chooseAnimalLabel);
        chooseHerbivorousAnimalSubScene.getPane().getChildren().addAll(creatHerbAnimalToChoose(), createNextToCarn());
        }
    private void createCarnAnimalSubScene(){
        chooseCarnivorousAnimalSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(chooseCarnivorousAnimalSubScene);

        InfoLabel chooseAnimalLabel = new InfoLabel("Choose Animal");
        chooseAnimalLabel.setLayoutX(110);
        chooseAnimalLabel.setLayoutY(25);
        chooseCarnivorousAnimalSubScene.getPane().getChildren().add(chooseAnimalLabel);
        chooseCarnivorousAnimalSubScene.getPane().getChildren().addAll(creatCarnAnimalToChoose(),createNextToPlants());
    }
    private void createPlanetChooserSubScene() {
        choosePlantsSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(choosePlantsSubScene);

        InfoLabel choosePlantLabel = new InfoLabel("Choose Plant");
        choosePlantLabel.setLayoutX(110);
        choosePlantLabel.setLayoutY(25);
        choosePlantsSubScene.getPane().getChildren().add(choosePlantLabel);
        choosePlantsSubScene.getPane().getChildren().addAll(creatPlantsToChoose(),createButtonToStart());
    }
    private CrossRiverButton createButtonToStart(){
        CrossRiverButton startButton = new CrossRiverButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(320);

        startButton.setOnAction(e->{
            if(choosenPlants!=null){
                //here will start game
                Story1Logic storyOne = new Story1Logic();
                storyOne.createCharcters();
                GameViewManger gameManger = new GameViewManger();
                gameManger.creatNewGame(mainStage, choosenShip , choosenFarmer , choosenHerbAnimal ,choosenCarnAnimal ,choosenPlants);
            }
            else if(choosenHerbAnimal!=null){
                GameViewManger gameManger = new GameViewManger();
                gameManger.creatStoryTwoGame(mainStage,choosenShip,choosenHerbAnimal);
            }
        });
        return startButton;
    }
    private CrossRiverButton createNextToShip(){
        CrossRiverButton nextToShipButton = new CrossRiverButton("NEXT");
        nextToShipButton.setLayoutX(350);
        nextToShipButton.setLayoutY(320);

        nextToShipButton.setOnAction(e->{
            if(choosenStory!=null){
                showSubScene(chooseCharctersSubScene);
            }
        });
        return nextToShipButton;
    }
    private CrossRiverButton createNextToFarmers(){
        CrossRiverButton nextToFarmersButton = new CrossRiverButton("NEXT");
        nextToFarmersButton.setLayoutX(350);
        nextToFarmersButton.setLayoutY(320);

        nextToFarmersButton.setOnAction(e->{
            //here will open new Scene to choose Farmers
            if(choosenShip!=null){
                if(String.valueOf(choosenStory).contains("STORYONE"))
                    showSubScene(chooseFarmerSubScene);
                else {
                    chooseHerbivorousAnimalSubScene.getPane().getChildren().remove(createNextToCarn());
                    chooseHerbivorousAnimalSubScene.getPane().getChildren().add(createButtonToStart());
                    showSubScene(chooseHerbivorousAnimalSubScene);
                }
            }
        });
        return nextToFarmersButton;
    }
    private CrossRiverButton createNextToHerb(){
        CrossRiverButton nextToHerbAnimalButton = new CrossRiverButton("NEXT");
        nextToHerbAnimalButton.setLayoutX(350);
        nextToHerbAnimalButton.setLayoutY(320);

        nextToHerbAnimalButton.setOnAction(e->{
            //here will open new Scene to choose Farmers
            if(choosenFarmer!=null){
                showSubScene(chooseHerbivorousAnimalSubScene);
            }
        });
        return nextToHerbAnimalButton;
    }
    private CrossRiverButton createNextToCarn(){
        CrossRiverButton nextToCarnAnimalButton = new CrossRiverButton("NEXT");
        nextToCarnAnimalButton.setLayoutX(350);
        nextToCarnAnimalButton.setLayoutY(320);

        nextToCarnAnimalButton.setOnAction(e->{
            //here will open new Scene to choose Farmers
            if(choosenHerbAnimal!=null){
                showSubScene(chooseCarnivorousAnimalSubScene);
            }
        });
        return nextToCarnAnimalButton;
    }
    private CrossRiverButton createNextToPlants(){
        CrossRiverButton nextToPlantsButton = new CrossRiverButton("NEXT");
        nextToPlantsButton.setLayoutX(350);
        nextToPlantsButton.setLayoutY(320);

        nextToPlantsButton.setOnAction(e->{
            //here will open new Scene to choose Farmers
            if(choosenCarnAnimal!=null){
                showSubScene(choosePlantsSubScene);
            }
        });
        return nextToPlantsButton;
    }
    private HBox creatStoryToChoose(){
        HBox box = new HBox();
        box.setSpacing(120);
        shipsList = new ArrayList<>();
        for(STORY story: STORY.values()){
            ShipPicker storyToPick = new ShipPicker(story,null,0,null,null,null,null);
            shipsList.add(storyToPick);
            box.getChildren().add(storyToPick);
            storyToPick.setOnMouseClicked(e->{
                for(ShipPicker ship1 : shipsList){
                    ship1.setIsCircleChoosen(false);
                }
                storyToPick.setIsCircleChoosen(true);
                choosenStory = storyToPick.getStory();
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }
    private HBox creatShipToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for(SHIP ship: SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(null, ship,1,null,null,null,null);
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
    private HBox creatFarmerToChoose(){
        HBox box = new HBox();
        box.setSpacing(30);
        shipsList = new ArrayList<>();
        for(FARMER farmer: FARMER.values()){
            ShipPicker farmerToPick = new ShipPicker(null, null,2,farmer,null,null,null);
            shipsList.add(farmerToPick);
            box.getChildren().add(farmerToPick);
            farmerToPick.setOnMouseClicked(e->{
                for(ShipPicker ship1 : shipsList){
                    ship1.setIsCircleChoosen(false);
                }
                farmerToPick.setIsCircleChoosen(true);
                choosenFarmer = farmerToPick.getFarmer();
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }
    private HBox creatHerbAnimalToChoose(){
        HBox box = new HBox();
        box.setSpacing(30);
        shipsList = new ArrayList<>();
        for(HERBANIMAL herbanimal: HERBANIMAL.values()){
            ShipPicker herbAnimalToPick = new ShipPicker(null, null,3,null, herbanimal,null,null);
            shipsList.add(herbAnimalToPick);
            box.getChildren().add(herbAnimalToPick);
            herbAnimalToPick.setOnMouseClicked(e->{
                for(ShipPicker ship1 : shipsList){
                    ship1.setIsCircleChoosen(false);
                }
                herbAnimalToPick.setIsCircleChoosen(true);
                choosenHerbAnimal = herbAnimalToPick.getHerbAnimal();
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }
    private HBox creatCarnAnimalToChoose(){
        HBox box = new HBox();
        box.setSpacing(30);
        shipsList = new ArrayList<>();
        for(CARNANIMAL carnanimal: CARNANIMAL.values()){
            ShipPicker carnAnimalToPick = new ShipPicker(null, null,4,null, null,carnanimal,null);
            shipsList.add(carnAnimalToPick);
            box.getChildren().add(carnAnimalToPick);
            carnAnimalToPick.setOnMouseClicked(e->{
                for(ShipPicker ship1 : shipsList){
                    ship1.setIsCircleChoosen(false);
                }
                carnAnimalToPick.setIsCircleChoosen(true);
                choosenCarnAnimal = carnAnimalToPick.getCarnAnimal();
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }
    private HBox creatPlantsToChoose(){
        HBox box = new HBox();
        box.setSpacing(30);
        shipsList = new ArrayList<>();
        for(PLANTS plants: PLANTS.values()){
            ShipPicker plantToPick = new ShipPicker(null, null,5,null, null,null,plants);
            shipsList.add(plantToPick);
            box.getChildren().add(plantToPick);
            plantToPick.setOnMouseClicked(e->{
                for(ShipPicker ship1 : shipsList){
                    ship1.setIsCircleChoosen(false);
                }
                plantToPick.setIsCircleChoosen(true);
                choosenPlants = plantToPick.getPlants();
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
            showSubScene(chooseStoryGameSubScene);
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
