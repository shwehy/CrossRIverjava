package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.ImageCursor;
import Logic.GameEngine;
import Logic.ICrosseingStrategy;
import Logic.ReadFile_XML;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;
import javafx.scene.image.Image;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewManger {
    private static final int WIDTH = 1024, HEIGHT=700;
    private AnchorPane mainPane;
    public Scene mainScene;
    public static Stage mainStage;
    private ICrosseingStrategy iCrosseingStrategy;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;
    private final String FONT_PATH = "src/model/resources/Alessia.otf";
    private Label story1,story2;

    private CrossRiverSubScene secondhelpSubScene;
    private CrossRiverSubScene creditsSubScene;
    private CrossRiverSubScene helpSubScene;
    private CrossRiverSubScene scoresSubScene;
    private CrossRiverSubScene chooseCharctersSubScene;
    private CrossRiverSubScene chooseFarmerSubScene;
    private CrossRiverSubScene chooseCarnivorousAnimalSubScene;
    private CrossRiverSubScene chooseHerbivorousAnimalSubScene;
    private CrossRiverSubScene choosePlantsSubScene;
    private CrossRiverSubScene chooseStoryGameSubScene;
    private CrossRiverSubScene LoadOrSaveSubScene;
    private Label n;
    private ImageView creditphoto;
    GameEngine startGameLogic = new GameEngine();

    private CrossRiverSubScene sceneToHide;
    List<ShipPicker> shipsList;
    List<ShipPicker> storyList;
    List<ShipPicker> farmerList;
    List<ShipPicker> carnAnimalList;
    List<ShipPicker> herbAnimalList;
    List<ShipPicker> plantList;
    List<ShipPicker> loadList;

    List<CrossRiverButton> menuButtons;

    private SHIP choosenShip;
    private FARMER choosenFarmer;
    private STORY choosenStory;
    private HERBANIMAL choosenHerbAnimal;
    private CARNANIMAL choosenCarnAnimal;
    private PLANTS choosenPlants;
    private LOAD choosenLoad;
    private static ViewManger single_Instance = null;
    public static Image cursorming = new Image("view/resources/tap.png");

    //Constructor
    private ViewManger(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainPane.setCursor(new ImageCursor(cursorming));
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


    private void creatSubScene(){
        //create SubScenes.
        createSCORE();
        createLoadOrSaveSubScene();
        createCreditSubScene();
        createHelpSubScene();
        createSecondHelpSubScene();
//        createSaveSubScene();
        createStoryGameSubScene();
        creatShipChooserSubScene();
        createFarmerChooserSubScene();
        createHerbAnimalSubScene();
        createCarnAnimalSubScene();
        createPlanetChooserSubScene();
    }
    private void createHelpButton(){
        CrossRiverButton helpButton = new CrossRiverButton("HELP");
        addMenuButtons(helpButton);
        CrossRiverButton secondnextButton = new CrossRiverButton("NEXT");
        secondnextButton.setLayoutX(350);
        secondnextButton.setLayoutY(320);
        story1 = new Label("Story 1 :\nA farmer wants to cross a river with a carnivorous , herbivorous and a plant\n\nRules:\n-Farmer only can raft the boat and can't be eaten\n-Carnivorous can only eat herbivorous\n-Herbivorous can only eat plants ");
        helpButton.setOnMouseClicked(e->{
            story1.setLayoutX(80);
            story1.setLayoutY(25);
            story1.setPrefHeight(350);
            story1.setPrefWidth(500);

            story1.setWrapText(true);
            try{
                story1.setFont(Font.loadFont(new FileInputStream(FONT_PATH),20));
            }catch (FileNotFoundException x){
                story1.setFont(Font.font("verdana",18));
            }

            showSubScene(helpSubScene);
        });
        secondnextButton.setOnMouseEntered(e->{
            secondnextButton.setEffect(new DropShadow());
        });
        secondnextButton.setOnMouseExited(e->{
            secondnextButton.setEffect(null);
        });
        secondnextButton.setOnMouseClicked(e->{
            showSubScene(secondhelpSubScene);
        });
        helpSubScene.getPane().getChildren().addAll(story1,secondnextButton);
    }
    private void createSecondHelpSubScene(){
        secondhelpSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(secondhelpSubScene);
        story2 = new Label("Story 2 :\nFour farmers and their animal need to cross a river in a small boat." +
                " The weight of the four farmers are 90 kg, 80 kg, 60 kg, 40 kg respectively and the" +
                " weight of the animal is 20 kg\n\nRules:\n-Farmer only can raft the boat and can't be eaten\n" +
                "-The boat can't bear a load heavier than than 100 kg ");
        story2.setLayoutX(80);
        story2.setLayoutY(25);
        story2.setPrefHeight(350);
        story2.setPrefWidth(500);

        story2.setWrapText(true);
        try{
            story2.setFont(Font.loadFont(new FileInputStream(FONT_PATH),20));
        }catch (FileNotFoundException x){
            story2.setFont(Font.font("verdana",18));
        }

        secondhelpSubScene.getPane().getChildren().addAll(createBackButton(),story2);
    }
    private CrossRiverButton createBackButton() {
        CrossRiverButton backbutton2 = new CrossRiverButton("BACK");
        backbutton2.setLayoutX(350);
        backbutton2.setLayoutY(320);

        backbutton2.setOnMouseClicked(e->{
            showSubScene(helpSubScene);
        });
        backbutton2.setOnMouseEntered(e->{
            backbutton2.setEffect(new DropShadow());
        });
        backbutton2.setOnMouseExited(e->{
            backbutton2.setEffect(null);
        });
        return backbutton2;
    }

    public void showSubScene(CrossRiverSubScene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    //All Buttons on Left
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
            showSubScene(LoadOrSaveSubScene);
        });
    }
    private void createScoresButton(){
        CrossRiverButton scoresButton = new CrossRiverButton("SCORES");
        addMenuButtons(scoresButton);
        scoresButton.setOnAction(e->{
            showSubScene(scoresSubScene);
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
    private void addMenuButtons(CrossRiverButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

//    /BackGround and logo
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


    //All SubScenes
    private void createLoadOrSaveSubScene(){
        LoadOrSaveSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(LoadOrSaveSubScene);

        InfoLabel choosesLoadStory = new InfoLabel("New or Load?");
        choosesLoadStory.setLayoutX(110);
        choosesLoadStory.setLayoutY(25);
        LoadOrSaveSubScene.getPane().getChildren().add(choosesLoadStory);
        LoadOrSaveSubScene.getPane().getChildren().addAll(creatLoadToChoose(),createNextToStory());

    }

    private void createSCORE(){

        scoresSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(scoresSubScene);

        n = new Label("Last Score "+String.valueOf(GameViewManger.numberOfMoves));
        n.setLayoutX(100);
        n.setLayoutY(50);
        n.setStyle("-fx-text-fill: #000;-fx-font-size: 24px; font-weight: bold");
        System.out.println("f eh");
        scoresSubScene.getPane().getChildren().addAll(n);

    }
    private void createCreditSubScene(){
         creditsSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(creditsSubScene);
        creditphoto = new ImageView("model/resources/Credit.jpg");
        creditphoto.setFitHeight(400);
        creditphoto.setFitWidth(600);
        String musicFile = "src/Francais Lai - Love Story.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        Label w = new Label("This game was created on 30 of April, 2019\nAs a college project, For more information" +
                "\nContact The Team-Leader1: Mahmoud Elkarargy\n\tmahmoud.elkarargy@gmail.com\n");
        w.setLayoutX(50);
        w.setLayoutY(20);
        w.setStyle("-fx-text-fill: #fff;-fx-font-size: 24px; font-weight: bold");
        creditphoto.setOnMouseEntered(e->{
            creditphoto.setEffect(new DropShadow());
            creditsSubScene.getPane().getChildren().addAll(w);
            mediaPlayer.play();

        });
        creditphoto.setOnMouseExited(e->{
            creditphoto.setEffect(null);
            creditsSubScene.getPane().getChildren().remove(w);
            mediaPlayer.stop();
        });
        creditsSubScene.getPane().getChildren().addAll(creditphoto);
    }
    private void createHelpSubScene(){
        helpSubScene = new CrossRiverSubScene();
        mainPane.getChildren().add(helpSubScene);
    }
//    private void createSaveSubScene(){
//        scoresSubScene = new CrossRiverSubScene();
//        mainPane.getChildren().addAll(scoresSubScene);
//        System.out.println("ehhhhhhhhhhhhhhj");
//    }
//    private void createScoresSubScene(){
//        scoresSubScene = new CrossRiverSubScene();
//        mainPane.getChildren().add(n);
//    }
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



    //SubScenes view Sequence

    private CrossRiverButton createNextToStory(){
        CrossRiverButton nextToLoad = new CrossRiverButton("NEXT");
        nextToLoad.setLayoutX(350);
        nextToLoad.setLayoutY(320);

        nextToLoad.setOnAction(e->{
            if(choosenLoad!=null){
                if(String.valueOf(choosenLoad).equalsIgnoreCase("NEW"))
                    showSubScene(chooseStoryGameSubScene);
                else{
                    GameViewManger gameManger = new GameViewManger(startGameLogic);
                    ReadFile_XML x = new ReadFile_XML();
                    try {
                        x.Read();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }

                    startGameLogic.setStoryType(x.getType());
                    startGameLogic.newGame(iCrosseingStrategy);
                    try {
                        gameManger.creatLoadGame(mainStage);
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        return nextToLoad;
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
                if(String.valueOf(choosenStory).contains("STORYONE")) {
                    showSubScene(chooseFarmerSubScene);
                    chooseHerbivorousAnimalSubScene.getPane().getChildren().remove(createButtonToStart());
                    chooseHerbivorousAnimalSubScene.getPane().getChildren().add(createNextToCarn());
                }
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
    private CrossRiverButton createButtonToStart(){
        CrossRiverButton startButton = new CrossRiverButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(320);

        startButton.setOnAction(e->{
            if(choosenPlants!=null && choosenStory.getUrl().contains("StoryOne")){
                //here will start game
                startGameLogic.setStoryType(1);
                startGameLogic.newGame(iCrosseingStrategy);
                GameViewManger gameManger = new GameViewManger(startGameLogic);
                gameManger.creatNewGame(mainStage, choosenShip , choosenFarmer , choosenHerbAnimal ,choosenCarnAnimal ,choosenPlants);

            }
            else if(choosenHerbAnimal!=null && choosenStory.getUrl().contains("StoryTwo")){
                startGameLogic.setStoryType(2);
                startGameLogic.newGame(iCrosseingStrategy);
                GameViewManger gameManger = new GameViewManger(startGameLogic);
                gameManger.creatStoryTwoGame(mainStage,choosenShip,choosenHerbAnimal);
            }
        });
        return startButton;
    }


    //Choose from Stories/animals/farmers..

    private HBox creatLoadToChoose(){
        HBox box = new HBox();
        box.setSpacing(120);
        loadList = new ArrayList<>();
        for(LOAD load: LOAD.values()){
            ShipPicker loadToPick = new ShipPicker(load, null,null,6,null,null,null,null);
            loadList.add(loadToPick);
            box.getChildren().add(loadToPick);
            loadToPick.setOnMouseClicked(e->{
                for(ShipPicker load1 : loadList){
                    load1.setIsCircleChoosen(false);
                }
                loadToPick.setIsCircleChoosen(true);
                choosenLoad = loadToPick.getLoad();
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }

    private HBox creatStoryToChoose(){
        HBox box = new HBox();
        box.setSpacing(120);
        storyList = new ArrayList<>();
        for(STORY story: STORY.values()){
            ShipPicker storyToPick = new ShipPicker(null, story,null,0,null,null,null,null);
            storyList.add(storyToPick);
            box.getChildren().add(storyToPick);
            storyToPick.setOnMouseClicked(e->{
                for(ShipPicker story1 : storyList){
                    story1.setIsCircleChoosen(false);
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
            ShipPicker shipToPick = new ShipPicker(null, null, ship,1,null,null,null,null);
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
        farmerList = new ArrayList<>();
        for(FARMER farmer: FARMER.values()){
            ShipPicker farmerToPick = new ShipPicker(null, null, null,2,farmer,null,null,null);
            farmerList.add(farmerToPick);
            box.getChildren().add(farmerToPick);
            farmerToPick.setOnMouseClicked(e->{
                for(ShipPicker farmer1 : farmerList){
                    farmer1.setIsCircleChoosen(false);
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
        herbAnimalList = new ArrayList<>();
        for(HERBANIMAL herbanimal: HERBANIMAL.values()){
            ShipPicker herbAnimalToPick = new ShipPicker(null, null, null,3,null, herbanimal,null,null);
            herbAnimalList.add(herbAnimalToPick);
            box.getChildren().add(herbAnimalToPick);
            herbAnimalToPick.setOnMouseClicked(e->{
                for(ShipPicker herb1 : herbAnimalList){
                    herb1.setIsCircleChoosen(false);
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
        carnAnimalList = new ArrayList<>();
        for(CARNANIMAL carnanimal: CARNANIMAL.values()){
            ShipPicker carnAnimalToPick = new ShipPicker(null, null, null,4,null, null,carnanimal,null);
            carnAnimalList.add(carnAnimalToPick);
            box.getChildren().add(carnAnimalToPick);
            carnAnimalToPick.setOnMouseClicked(e->{
                for(ShipPicker carn1 : carnAnimalList){
                    carn1.setIsCircleChoosen(false);
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
        plantList = new ArrayList<>();
        for(PLANTS plants: PLANTS.values()){
            ShipPicker plantToPick = new ShipPicker(null, null, null,5,null, null,null,plants);
            plantList.add(plantToPick);
            box.getChildren().add(plantToPick);
            plantToPick.setOnMouseClicked(e->{
                for(ShipPicker plant1 : plantList){
                    plant1.setIsCircleChoosen(false);
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
    public void resetScene(){
        showSubScene(LoadOrSaveSubScene);
        mainPane.setCursor(new ImageCursor(cursorming));
    }
}