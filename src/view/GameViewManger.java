package view;

import Logic.test;
import javafx.scene.ImageCursor;
import Logic.GameEngine;
import Logic.ICrosser;
import Logic.ReadFile_XML;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CrossRiverButton;
import model.FARMER;
import model.SHIP;
import model.HERBANIMAL;
import model.CARNANIMAL;
import model.PLANTS;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.effect.DropShadow;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


public class GameViewManger {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private MediaPlayer mediaplayer;
    private MediaView mediaview;
    private ImageView ship, farmerImage, herbAnimalImage, carnAnimalImage, plantImage,farmerA,farmerB,farmerC,farmerD;
    private ImageView saveButton, redoButton, undoButton, backButton, solveButton;
    private ImageView gameoverIcon, winnergameIcon,stopVideo,playVideoAgian,getReady, helpButton, closeHelp;
    private Image idleship, reversedShip;
    private Image idleFarmer, deadFarmer, JumpFarmer;
    private Image idleHerbAnimal, selectedHerbAnimal, deadHerbAnimal;
    private Image idleCarnAnimal, selectedCarnAnimal, deadCarnAnimal;
    private Image plant, eatenPlant;
    private Image idleFarmerA,JumpFarmerA;
    private Image idleFarmerB,JumpFarmerB;
    private Image idleFarmerC,JumpFarmerC;
    private Image idleFarmerD,JumpFarmerD,deadFarmers;
    private Image zero = new Image("view/resources/Numbers/number0.png");
    private Image one = new Image("view/resources/Numbers/number1.png");
    private Image two = new Image("view/resources/Numbers/number2.png");
    private Image three = new Image("view/resources/Numbers/number3.png");
    private Image four = new Image("view/resources/Numbers/number4.png");
    private Image five = new Image("view/resources/Numbers/number5.png");
    private Image six = new Image("view/resources/Numbers/number6.png");
    private Image seven = new Image("view/resources/Numbers/number7.png");
    private Image eight = new Image("view/resources/Numbers/number8.png");
    private Image nine = new Image("view/resources/Numbers/number9.png");
    private Label weight;
    private ImageView units = new ImageView(zero);
    private ImageView Tenth = new ImageView(zero);
    public static int numberOfMoves;
    private Label txt1,txt2,helptxt;
    private Scene gameOver,helpScene;
    private boolean onebyone = true;

    GameEngine startGameLogic;
    private static final int GAME_WIDTH = 1250;
    private static final int GAME_HIGHT = 700;
    private int storyNumber;

    private boolean isFarmerClicked=false, letsgoButtonisClicked=false;
    private boolean isFarmerAClicked, isFarmerBClicked, isFarmerCClicked, isFarmerDClicked;
    private boolean isHerbAnimalClicked=false, isCarnAnimalClicked =false, isPlantClicked=false;
    private boolean endOfBank= true;
    private int angle,loserAngle,winnerAngle;
    private AnimationTimer gameTimer, winnerTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "view/resources/River.jpeg";
    private final static String BACKGROUND_IMAGE2 = "view/resources/Sky.png";
    private final static String GameOVERBACKGROUND_IMAGE= "view/resources/Back.png";
    List<ICrosser> boatRiders = new LinkedList<ICrosser>();

    SHIP choosenShip;
    FARMER choosenFarmer;
    HERBANIMAL choosenHerbAnimal;
    CARNANIMAL choosenCarnAnimal;
    PLANTS choosenPlants;


    //Passing the GameEngine object from the ViewManger.
    public GameViewManger(GameEngine startGameLogic){
        //Set the Scene and hight, Width of the game.
        System.out.println("init aho");
        initStage();
        this.startGameLogic = startGameLogic;
        //to init. the boat side on the left bank.
        startGameLogic.setLetsGoButtonClicked(true);
        startGameLogic.setCrossersOnboat(null);
    }

    private CrossRiverButton createLetsGoButton(){

        CrossRiverButton letsGoButton = new CrossRiverButton("Let's GO");
        letsGoButton.setLayoutX(GAME_WIDTH/2);
        letsGoButton.setLayoutY(100);
        letsGoButton.setOnAction(e->{
            String musicFile_Acheviement = "src/view/ach.mp3";
            String musicFile_Lose = "src/view/Lose.mp3";
            String musicFile_Win = "src/view/voctory.wav";


            String musicFile_Alert = "src/view/alert.wav";
            startGameLogic.setNumberOfSails(numberOfMoves);
            Media ach = new Media(new File(musicFile_Acheviement).toURI().toString());
            MediaPlayer mediaPlayer_ach = new MediaPlayer(ach);
            Media lose = new Media(new File(musicFile_Lose).toURI().toString());
            MediaPlayer mediaPlayer_lose = new MediaPlayer(lose);
            Media win = new Media(new File(musicFile_Win).toURI().toString());
            MediaPlayer mediaPlayer_win = new MediaPlayer(win);
            Media alert = new Media(new File(musicFile_Alert).toURI().toString());
            MediaPlayer mediaPlayer_alert= new MediaPlayer(alert);
            //Check if this is the end of bank to avoid pressing on the button,
                //while the boat is moving.
            if (endOfBank && startGameLogic.isValidforBoat(boatRiders)) {
                letsgoButtonisClicked = true;
                startGameLogic.REDOLIST(startGameLogic.story1RightBankList,startGameLogic.story1LeftBankList,startGameLogic.boatRiders);
                mediaPlayer_ach.play();

            } else if(endOfBank){
                System.out.println("unvalid move, Farmer is not on boat");
                mediaPlayer_alert.play();
                letsgoButtonisClicked = false;
            }
            if(letsgoButtonisClicked && endOfBank) {
                System.out.println("I will check validation for: ******");
                System.out.println("Right" + startGameLogic.story1RightBankList);
                System.out.println("Left" + startGameLogic.story1LeftBankList);
                System.out.println("Boat " + boatRiders);
                if(startGameLogic.story1RightBankList.isEmpty()) {
                    mediaPlayer_ach.stop();
                    mediaPlayer_win.play();
                    gameOverStoryone(1);
                }
                else if (storyNumber==1 && startGameLogic.isValidFromLogic(startGameLogic.story1RightBankList, startGameLogic.story1LeftBankList, boatRiders)) {
                        System.out.println("@@@ Valid Move @@@");
                        numberOfMoves++;
                        setNumberOfMoves();
                        System.out.println(numberOfMoves);
                }
                else if (storyNumber==2 && startGameLogic.isValidFromLogic2(startGameLogic.story1RightBankList, startGameLogic.story1LeftBankList, boatRiders)) {
                    System.out.println("@@@ Valid Move @@@");
                    numberOfMoves++;
                    setNumberOfMoves();
                    System.out.println(numberOfMoves);
                }
                else {
                    mediaPlayer_ach.stop();
                    mediaPlayer_lose.play();
                    gameOverStoryone(2);
                }
            }
        });
        return letsGoButton;
    }
     private void RestGameOne(){
         numberOfMoves =0;
        startGameLogic.resetGame(storyNumber);
        startGameLogic.IniStacks();
        gameStage.close();
        initStage();
        boatRiders = null;
        boatRiders = new LinkedList<ICrosser>();
        startGameLogic.setLetsGoButtonClicked(true);
        startGameLogic.setCrossersOnboat(null);
        if(storyNumber==1)
            creatNewGame(menuStage,choosenShip,choosenFarmer,choosenHerbAnimal,choosenCarnAnimal,choosenPlants);
        else
            creatStoryTwoGame(menuStage,choosenShip,choosenHerbAnimal);
        gameStage.setScene(gameScene);
    }
    private void gameOverStoryone(int Case){
        Stage gameOverStage = new Stage();
        gameOverStage.initModality(Modality.APPLICATION_MODAL);
        gameOverStage.initStyle(StageStyle.TRANSPARENT);
        AnchorPane root = new AnchorPane();

        GridPane gameOverLayout = new GridPane();
        gameOverLayout.setPadding(new Insets(20, 20, 20, 20)); //Setting gaps in corners
        gameOverLayout.setVgap(8);
        gameOverLayout.setHgap(10);

        BackgroundImage image = new BackgroundImage(new Image(GameOVERBACKGROUND_IMAGE,1000,300,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        gameoverIcon = new ImageView("view/resources/gameOver.png");
        winnergameIcon = new ImageView("view/resources/Winner.png");
        stopVideo = new ImageView("view/resources/stopVideo.png");
        playVideoAgian = new ImageView("view/resources/playAgain.png");
        stopVideo.setFitHeight(50);
        stopVideo.setFitWidth(50);
        playVideoAgian.setFitWidth(50);
        playVideoAgian.setFitHeight(50);
        stopVideo.setLayoutX(20);
        stopVideo.setLayoutY(20);
        playVideoAgian.setLayoutX(80);
        playVideoAgian.setLayoutY(20);
        gameoverIcon.setLayoutX(300);
        gameoverIcon.setLayoutY(100);
        winnergameIcon.setLayoutX(300);
        winnergameIcon.setLayoutY(80);
        ImageView exit = new ImageView("view/resources/Exit.png");
        exit.setLayoutX(10);
        exit.setLayoutY(10);
        ImageView playAgian = new ImageView("view/resources/playAgain.png");
        playAgian.setLayoutX(900);
        playAgian.setLayoutY(240);
        txt1 = new Label("Wanna play again?");
        txt1.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        txt1.setLayoutX(650);
        txt1.setLayoutY(250);
        txt2 = new Label("Solve?");
        txt2.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        txt2.setLayoutX(10);
        txt2.setLayoutY(250);
        solveButton = new ImageView("view/resources/Solve.png");
        solveButton.setFitHeight(40);
        solveButton.setFitWidth(40);
        solveButton.setLayoutY(240);
        solveButton.setLayoutX(100);
        root.setBackground(new Background(image));
        exit.setFitHeight(50);
        exit.setFitWidth(50);
        playAgian.setFitWidth(50);
        playAgian.setFitHeight(50);

        playAgian.setOnMouseClicked(e->{
            RestGameOne();
            gameOverStage.close();
            winnerTimer.stop();
        });

        exit.setOnMouseClicked(e->{
            gameStage.close();
            ViewManger v = ViewManger.getInstance();
            v.resetScene();
            v.mainStage.show();
            gameOverStage.close();
            winnerTimer.stop();
        });
        exit.setOnMouseEntered(e->{
            exit.setEffect(new DropShadow());
        });
        exit.setOnMouseExited(e->{
            exit.setEffect(null);
        });
        solveButton.setOnMouseEntered(e->{
            solveButton.setEffect(new DropShadow());
        });
        solveButton.setOnMouseExited(e->{
            solveButton.setEffect(null);
        });
        stopVideo.setOnMouseEntered(e->{
            stopVideo.setEffect(new DropShadow());
        });
        stopVideo.setOnMouseExited(e->{
            stopVideo.setEffect(null);
        });
        playVideoAgian.setOnMouseEntered(e->{
            playVideoAgian.setEffect(new DropShadow());
        });
        playVideoAgian.setOnMouseExited(e->{
            playVideoAgian.setEffect(null);
        });
        stopVideo.setOnMouseClicked(e->{
            mediaplayer.stop();
            gamePane.getChildren().removeAll(mediaview,stopVideo,playAgian);
            RestGameOne();
            winnerTimer.stop();
        });
        playVideoAgian.setOnMouseClicked(e->{
            mediaplayer.stop();
            mediaplayer.play();
        });

        solveButton.setOnMouseClicked(e->{
            gameOverStage.close();
            if(storyNumber==1) {
                //Add your path and don't delete mine!!!!!
                Media video = new Media(Paths.get("src/solveStoryOne.mp4").toUri().toString());
                mediaplayer = new MediaPlayer(video);
                mediaview = new MediaView(mediaplayer);
                mediaview.setFitHeight(GAME_HIGHT);
                mediaview.setFitWidth(GAME_WIDTH);
                mediaplayer.setVolume(0);
                mediaplayer.play();
                gamePane.getChildren().addAll(mediaview,stopVideo,playVideoAgian);

            }
            else{
                //Add your path and don't delete mine!!!!!
                Media video = new Media(Paths.get("src/SolveStoryTwo.mp4").toUri().toString());
                mediaplayer = new MediaPlayer(video);
                mediaview = new MediaView(mediaplayer);
                mediaview.setFitHeight(GAME_HIGHT);
                mediaview.setFitWidth(GAME_WIDTH);
                mediaplayer.setVolume(0);
                mediaplayer.play();
                gamePane.getChildren().addAll(mediaview,stopVideo,playVideoAgian);
            }
        });
        playAgian.setOnMouseEntered(e->{
            playAgian.setEffect(new DropShadow());
        });
        playAgian.setOnMouseExited(e->{
            playAgian.setEffect(null);
        });
        Image deadFarmers = new Image("view/resources/fourFarmer/zombie_hurt.png");
        if(Case==2) {
            if(storyNumber==1) {
                farmerImage.setImage(deadFarmer);
                carnAnimalImage.setImage(deadCarnAnimal);
                plantImage.setImage(eatenPlant);
            }
            else if(storyNumber==2){
                farmerA.setImage(deadFarmers);
                farmerB.setImage(deadFarmers);
                farmerC.setImage(deadFarmers);
                farmerD.setImage(deadFarmers);
            }
            herbAnimalImage.setImage(deadHerbAnimal);
            gameOverStage.setTitle("GAME OVER!!!");
            root.getChildren().addAll(gameOverLayout, exit, playAgian, gameoverIcon, txt1, txt2, solveButton);
            moveWinner(0);
        }
        else if(Case==1){
            gameOverStage.setTitle("WINNER!!");
            if(storyNumber==1) {
                farmerImage.setImage(JumpFarmer);
                carnAnimalImage.setImage(selectedCarnAnimal);
            }
            else if(storyNumber==2){
                farmerA.setImage(JumpFarmerA);
                farmerB.setImage(JumpFarmerB);
                farmerC.setImage(JumpFarmerC);
                farmerD.setImage(JumpFarmerD);
            }
            herbAnimalImage.setImage(selectedHerbAnimal);
            root.getChildren().addAll(gameOverLayout, exit, playAgian, winnergameIcon,txt1);
            moveWinner(1);
        }
        gameOver = new Scene(root, 1000,300);
        gameOverStage.setScene(gameOver);
//        gameOverStage.setAlwaysOnTop(true);
        gameOverStage.setX(100);
        gameOverStage.setY(100);

        gameOverStage.showAndWait();
    }
    private void initStage() {
        gamePane = new AnchorPane();
        gamePane.setCursor(new ImageCursor(ViewManger.cursorming));
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.initStyle(StageStyle.TRANSPARENT);
        saveButton = new ImageView("view/resources/Save.png");
        saveButton.setLayoutY(30);
        saveButton.setLayoutX(GAME_WIDTH-80);
        redoButton = new ImageView("view/resources/Redo.png");
        redoButton.setLayoutY(30);
        redoButton.setLayoutX(GAME_WIDTH-200);
        undoButton = new ImageView("view/resources/Undo.png");
        undoButton.setLayoutY(30);
        undoButton.setLayoutX(GAME_WIDTH-250);
        redoButton.setFitWidth(50);
        redoButton.setFitHeight(50);
        undoButton.setFitHeight(50);
        undoButton.setFitWidth(50);
        backButton = new ImageView("view/resources/Exit.png");
        backButton.setLayoutX(50);
        backButton.setLayoutY(30);
        backButton.setFitWidth(50);
        backButton.setFitHeight(50);
        getReady = new ImageView("view/resources/GetReady.png");
        getReady.setLayoutX(GAME_WIDTH/2-200);
        getReady.setLayoutY(GAME_HIGHT/2-100);
        helpButton = new ImageView("view/resources/Help.png");
        helpButton.setFitHeight(50);
        helpButton.setFitWidth(50);
        helpButton.setLayoutY(30);
        helpButton.setLayoutX(GAME_WIDTH-140);

        helpButton.setOnMouseEntered(e->{
            helpButton.setEffect(new DropShadow());
        });
        helpButton.setOnMouseExited(e->{
            helpButton.setEffect(null);
        });
        helpButton.setOnMouseClicked(e->{
            creatHelpSubScene();
        });
        getReady.setOnMouseExited(e->{
            gamePane.getChildren().remove(getReady);
        });
        saveButton.setOnMouseEntered(e->{
            saveButton.setEffect(new DropShadow());
        });
        saveButton.setOnMouseExited(e->{
            saveButton.setEffect(null);
        });
        saveButton.setOnMouseClicked(event -> {
            GameEngine.setGameInstance(startGameLogic);
            startGameLogic.Set_Save_object(startGameLogic.storyType,startGameLogic.getCrossersOnLeftBank(),startGameLogic.getCrossersOnRightBank(),
                    startGameLogic.getCrossersOnboat(),numberOfMoves,startGameLogic.isBoatOnTheLeftBank(), choosenShip,
                    choosenFarmer,choosenHerbAnimal,choosenCarnAnimal,choosenPlants);
            startGameLogic.saveGame();
            String musicFile_Save = "src/view/win.wav";
            Media save = new Media(new File(musicFile_Save).toURI().toString());
            MediaPlayer mediaPlayer_save = new MediaPlayer(save);
            mediaPlayer_save.play();

        });
        undoButton.setOnMouseEntered(e->{
            undoButton.setEffect(new DropShadow());
        });
        undoButton.setOnMouseExited(e->{
            undoButton.setEffect(null);
        });
        redoButton.setOnMouseEntered(e->{
            redoButton.setEffect(new DropShadow());
        });
        redoButton.setOnMouseExited(e->{
            redoButton.setEffect(null);
        });
        backButton.setOnMouseEntered(e->{
            backButton.setEffect(new DropShadow());
        });
        backButton.setOnMouseExited(e->{
            backButton.setEffect(null);
        });

        backButton.setOnMouseClicked(e->{
            gameStage.close();
            ViewManger v = ViewManger.getInstance();
            v.resetScene();
            v.mainStage.show();
        });
        undoButton.setOnMouseClicked(e->{
            startGameLogic.undo();
//            test t = new test();

//            List<ICrosser> rightt =  startGameLogic.UNDO_RIGHT;
            undoRedoFunc();
        });
        redoButton.setOnMouseClicked(e->{
            startGameLogic.redo();
            undoRedoFunc();
        });
        setNumberOfMoves();
    }
    private void undoRedoFunc(){
        System.out.println("LISTSSSS IN GUIIII");
        System.out.println("RIGHT" + test.getRightFinal());
        System.out.println("LEFT" + test.getLeftFinal());
        System.out.println("BOAT" + test.getBoatFinal());
        startGameLogic.story1RightBankList.clear();
        startGameLogic.story1LeftBankList.clear();
        boatRiders.clear();
        System.out.println("NOW LIST ARE CLEAREEDDDDD:  "+startGameLogic.story1RightBankList);
        System.out.println(boatRiders);
        numberOfMoves-=1;
        if(storyNumber==2) {
            if (test.getRightFinal().contains(startGameLogic.story2herbAnimal)) {
                System.out.println("SBAH el foll");
                startGameLogic.story1RightBankList.add(startGameLogic.story2herbAnimal);
                gamePane.getChildren().remove(herbAnimalImage);
                creatHerbAnimal(choosenHerbAnimal);
            }
            if (test.getRightFinal().contains(startGameLogic.farmerA)) {
                startGameLogic.story1RightBankList.add(startGameLogic.farmerA);
                gamePane.getChildren().remove(farmerA);
                creatFarmerA();
            }
            if (test.getRightFinal().contains(startGameLogic.farmerB)) {
                startGameLogic.story1RightBankList.add(startGameLogic.farmerB);
                gamePane.getChildren().remove(farmerB);
                creatFarmerB();
            }
            if (test.getRightFinal().contains(startGameLogic.farmerC)) {
                startGameLogic.story1RightBankList.add(startGameLogic.farmerC);
                gamePane.getChildren().remove(farmerC);
                creatFarmerC();
            }
            if (test.getRightFinal().contains(startGameLogic.farmerD)) {
                startGameLogic.story1RightBankList.add(startGameLogic.farmerD);
                gamePane.getChildren().remove(farmerD);
                creatFarmerD();
                System.out.println("zzzz");
            }


            if (test.getLeftFinal().contains(startGameLogic.story2herbAnimal)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.story2herbAnimal);
                gamePane.getChildren().remove(herbAnimalImage);
                creatHerbAnimal(choosenHerbAnimal);
            }
            if (test.getLeftFinal().contains(startGameLogic.farmerA)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.farmerA);
                gamePane.getChildren().remove(farmerA);
                creatFarmerA();
            }
            if (test.getLeftFinal().contains(startGameLogic.farmerB)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.farmerB);
                gamePane.getChildren().remove(farmerB);
                creatFarmerB();
            }
            if (test.getLeftFinal().contains(startGameLogic.farmerC)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.farmerC);
                gamePane.getChildren().remove(farmerC);
                creatFarmerC();
            }
            if (test.getLeftFinal().contains(startGameLogic.farmerD)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.farmerD);
                gamePane.getChildren().remove(farmerD);
                creatFarmerD();

                System.out.println("zzzz2222");
            }


            if (test.getBoatFinal().contains(startGameLogic.story2herbAnimal)) {
                boatRiders.add(startGameLogic.story2herbAnimal);
                gamePane.getChildren().remove(herbAnimalImage);
                creatHerbAnimal(choosenHerbAnimal);
            }
            if (test.getBoatFinal().contains(startGameLogic.farmerA)) {
                boatRiders.add(startGameLogic.farmerA);
                gamePane.getChildren().remove(farmerA);
                creatFarmerA();
            }
            if (test.getBoatFinal().contains(startGameLogic.farmerB)) {
                boatRiders.add(startGameLogic.farmerB);
                gamePane.getChildren().remove(farmerB);
                creatFarmerB();
            }
            if (test.getBoatFinal().contains(startGameLogic.farmerC)) {
                boatRiders.add(startGameLogic.farmerC);
                gamePane.getChildren().remove(farmerC);
                creatFarmerC();
            }
            if (test.getBoatFinal().contains(startGameLogic.farmerD)) {
                boatRiders.add(startGameLogic.farmerD);
                gamePane.getChildren().remove(farmerD);
                creatFarmerD();

                System.out.println("zzzz333333333333");
            }
            System.out.println("NOW LISTS:: " + startGameLogic.story1RightBankList);
            System.out.println(boatRiders);

            farmerAclicked();
            farmerBclicked();
            farmerCclicked();
            farmerDclicked();
            herbAnimalClicked();
        }
        if(storyNumber==1) {
            if (test.getRightFinal().contains(startGameLogic.herbanimal)) {
                startGameLogic.story1RightBankList.add(startGameLogic.herbanimal);
                gamePane.getChildren().remove(herbAnimalImage);
                creatHerbAnimal(choosenHerbAnimal);
            }
            if (test.getRightFinal().contains(startGameLogic.farmer)) {
                startGameLogic.story1RightBankList.add(startGameLogic.farmer);
                gamePane.getChildren().remove(farmerImage);
                creatFarmer(choosenFarmer);
            }
            if (test.getRightFinal().contains(startGameLogic.carnianimal)) {
                startGameLogic.story1RightBankList.add(startGameLogic.carnianimal);
                gamePane.getChildren().remove(carnAnimalImage);
                creatCarnAnimal(choosenCarnAnimal);
            }
            if (test.getRightFinal().contains(startGameLogic.planet)) {
                startGameLogic.story1RightBankList.add(startGameLogic.planet);
                gamePane.getChildren().remove(plantImage);
                creatPlant(choosenPlants);
            }



            if (test.getLeftFinal().contains(startGameLogic.herbanimal)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.herbanimal);
                gamePane.getChildren().remove(herbAnimalImage);
                creatHerbAnimal(choosenHerbAnimal);
            }
            if (test.getLeftFinal().contains(startGameLogic.farmer)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.farmer);
                gamePane.getChildren().remove(farmerImage);
                creatFarmer(choosenFarmer);
            }
            if (test.getLeftFinal().contains(startGameLogic.carnianimal)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.carnianimal);
                gamePane.getChildren().remove(carnAnimalImage);
                creatCarnAnimal(choosenCarnAnimal);
            }
            if (test.getLeftFinal().contains(startGameLogic.planet)) {
                startGameLogic.story1LeftBankList.add(startGameLogic.planet);
                gamePane.getChildren().remove(plantImage);
                creatPlant(choosenPlants);
            }



            if (test.getBoatFinal().contains(startGameLogic.herbanimal)) {
                boatRiders.add(startGameLogic.herbanimal);
                gamePane.getChildren().remove(herbAnimalImage);
                creatHerbAnimal(choosenHerbAnimal);
            }
            if (test.getBoatFinal().contains(startGameLogic.farmer)) {
                boatRiders.add(startGameLogic.farmer);
                gamePane.getChildren().remove(farmerImage);
                creatFarmer(choosenFarmer);
            }
            if (test.getBoatFinal().contains(startGameLogic.carnianimal)) {
                boatRiders.add(startGameLogic.carnianimal);
                gamePane.getChildren().remove(carnAnimalImage);
                creatCarnAnimal(choosenCarnAnimal);
            }
            if (test.getBoatFinal().contains(startGameLogic.planet)) {
                boatRiders.add(startGameLogic.planet);
                gamePane.getChildren().remove(plantImage);
                creatPlant(choosenPlants);
            }

            System.out.println("NOW LISTS:: " + startGameLogic.story1RightBankList);
            System.out.println(boatRiders);

            carnAnimalClicked();
            plantClicked();
            FarmerClicked();
            herbAnimalClicked();
        }
    }
    private void setNumberOfMoves(){
        System.out.println("Numberssss:: " + numberOfMoves);
        units.setLayoutX(60);
        units.setLayoutY(600);
        Tenth.setLayoutY(600);
        Tenth.setLayoutX(20);
        units.setFitWidth(40);
        units.setFitHeight(40);
        Tenth.setFitWidth(40);
        Tenth.setFitHeight(40);
        int nb = numberOfMoves;
        int unit,tenth;
        if(nb>=10){
            unit = nb%10;
            tenth = nb/10;
        }
        else {
            unit = nb;
            tenth =0;
        }
        System.out.println("Tenth: "+tenth+"Units "+unit);
        if (unit == 0)
            units.setImage(zero);
        if (unit == 1)
            units.setImage(one);
        if (unit == 2)
            units.setImage(two);
        if (unit == 3)
            units.setImage(three);
        if (unit == 4)
            units.setImage(four);
        if (unit == 5)
            units.setImage(five);
        if (unit == 6)
            units.setImage(six);
        if (unit == 7)
            units.setImage(seven);
        if (unit == 8)
            units.setImage(eight);
        if (unit == 9)
            units.setImage(nine);

        if (tenth == 0)
            Tenth.setImage(zero);
        if (tenth == 1)
            Tenth.setImage(one);
        if (tenth == 2)
            Tenth.setImage(two);
        if (tenth == 3)
            Tenth.setImage(three);
        if (tenth == 4)
            Tenth.setImage(four);
        if (tenth == 5)
            Tenth.setImage(five);
        if (tenth == 6)
            Tenth.setImage(six);
        if (tenth == 7)
            Tenth.setImage(seven);
        if (tenth == 8)
            Tenth.setImage(eight);
        if (tenth == 9)
            Tenth.setImage(nine);

    }
    //All the comming Functions are for Story one.
    public void creatNewGame(Stage menuStage, SHIP choosenShip, FARMER choosenFarmer, HERBANIMAL choosenHerbAnimal,
                             CARNANIMAL choosenCarnAnimal, PLANTS choosenPlants){
        this.choosenShip = choosenShip;
        this.choosenFarmer = choosenFarmer;
        this.choosenHerbAnimal = choosenHerbAnimal;
        this.choosenCarnAnimal = choosenCarnAnimal;
        this.choosenPlants = choosenPlants;
        storyNumber=1;
        this.menuStage = menuStage;
        this.menuStage.hide();
        creatBackground();
        creatShip(choosenShip);
        creatFarmer(choosenFarmer);
        creatCarnAnimal(choosenCarnAnimal);
        creatHerbAnimal(choosenHerbAnimal);
        creatPlant(choosenPlants);

        FarmerClicked();
        herbAnimalClicked();
        carnAnimalClicked();
        plantClicked();
        creatGameLoop();
        gameStage.show();
    }
    public void creatStoryTwoGame(Stage menuStage, SHIP choosenShip, HERBANIMAL choosenHerbAnimal){
        storyNumber=2;
        this.menuStage = menuStage;
        this.menuStage.hide();
        this.choosenShip = choosenShip;
        this.choosenHerbAnimal = choosenHerbAnimal;
        creatBackground();
        creatShip(choosenShip);
        creatFarmerA();
        creatFarmerB();
        creatFarmerC();
        creatFarmerD();
        farmerAclicked();
        farmerBclicked();
        farmerCclicked();
        farmerDclicked();
        creatHerbAnimal(choosenHerbAnimal);
        herbAnimalClicked();
        creatGameLoop();
        gameStage.show();
    }
    public void creatLoadGame(Stage mainStage) throws ParserConfigurationException, SAXException, IOException {

        ReadFile_XML readfile = new ReadFile_XML();
        readfile.Read();

        System.out.println("BEFORERight" + startGameLogic.story1RightBankList);
        System.out.println("Left" + startGameLogic.story1LeftBankList);
        System.out.println("Boat " + boatRiders);
        //Emptying lists
//        startGameLogic.story1RightBankList.remove(startGameLogic.farmer);
//        startGameLogic.story1RightBankList.remove(startGameLogic.planet);
//        startGameLogic.story1RightBankList.remove(startGameLogic.herbanimal);
//        startGameLogic.story1RightBankList.remove(startGameLogic.carnianimal);
//
//        startGameLogic.story1LeftBankList.remove(startGameLogic.farmer);
//        startGameLogic.story1LeftBankList.remove(startGameLogic.planet);
//        startGameLogic.story1LeftBankList.remove(startGameLogic.herbanimal);
//        startGameLogic.story1LeftBankList.remove(startGameLogic.carnianimal);
//
//        boatRiders.remove(startGameLogic.farmer);
//        boatRiders.remove(startGameLogic.planet);
//        boatRiders.remove(startGameLogic.herbanimal);
//        boatRiders.remove(startGameLogic.carnianimal);

        boatRiders.clear();
        startGameLogic.story1RightBankList.clear();
        startGameLogic.story1LeftBankList.clear();
        numberOfMoves = readfile.getScore();
        setNumberOfMoves();
        System.out.println("TYPEEEE>> "+readfile.getType());
        if(readfile.getType()==1) {
            if (readfile.getStory1RightList() != null) {
                System.out.println("LENGTH IN BLA:  " + readfile.getStory1RightList().getLength());
                for (int i = 0; i < readfile.getStory1RightList().getLength(); i++) {
                    System.out.println(readfile.getStory1RightList().item(i).getTextContent());
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("Farmer")) {
                        System.out.println("here");
                        startGameLogic.story1RightBankList.add(startGameLogic.farmer);
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("Planet")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.planet);
                        System.out.println("herePP");
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("Carnianimal")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.carnianimal);
                        System.out.println("hereCC");
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("Herbanimal")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.herbanimal);
                    }
                }
            }
            if (readfile.getStory1LeftList() != null) {
                System.out.println("LENGTH IN BLA LEFTT:  " + readfile.getStory1LeftList().getLength());
                for (int i = 0; i < readfile.getStory1LeftList().getLength(); i++) {
                    System.out.println(readfile.getStory1LeftList().item(i).getTextContent());
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("Farmer")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.farmer);
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("Planet")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.planet);
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("Carnianimal")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.carnianimal);
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("Herbanimal")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.herbanimal);
                    }
                }
            }
            if (readfile.getStory1BoatList() != null) {
                System.out.println("LENGTH IN BLA: BLAA " + readfile.getStory1BoatList().getLength());
                for (int i = 0; i < readfile.getStory1BoatList().getLength(); i++) {
                    System.out.println(readfile.getStory1BoatList().item(i).getTextContent());
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("Farmer")) {
                        boatRiders.add(startGameLogic.farmer);
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("Planet")) {
                        boatRiders.add(startGameLogic.planet);
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("Carnianimal")) {
                        boatRiders.add(startGameLogic.carnianimal);
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("Herbanimal")) {
                        boatRiders.add(startGameLogic.herbanimal);
                    }
                }
            }
            System.out.println("WHERE IS THE PROB???? "+readfile.getBoatisOnLeft());
            if(readfile.getBoatisOnLeft())
                startGameLogic.setLetsGoButtonClicked(false);
            else startGameLogic.setLetsGoButtonClicked(true);

            choosenShip = SHIP.valueOf(readfile.getChoosenShip());
            choosenFarmer = FARMER.valueOf(readfile.getChoosenFarmer());
            choosenPlants = PLANTS.valueOf(readfile.getChoosenPlants());
            choosenCarnAnimal = CARNANIMAL.valueOf(readfile.getChoosenCarnAn());
            choosenHerbAnimal = HERBANIMAL.valueOf(readfile.getChoosenHerbAnimal());
            System.out.println("I recieved    "+choosenShip);
            creatNewGame(mainStage,choosenShip,choosenFarmer,choosenHerbAnimal,choosenCarnAnimal,choosenPlants);
        }
        else{
            if (readfile.getStory1RightList() != null) {
                System.out.println("LENGTH IN BLA:  " + readfile.getStory1RightList().getLength());
                for (int i = 0; i < readfile.getStory1RightList().getLength(); i++) {
                    System.out.println(readfile.getStory1RightList().item(i).getTextContent());
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("90.0")) {
                        System.out.println("here");
                        startGameLogic.story1RightBankList.add(startGameLogic.farmerA);
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("80.0")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.farmerB);
                        System.out.println("herePP");
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("60.0")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.farmerC);
                        System.out.println("hereCC");
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("40.0")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.farmerD);
                    }
                    if (readfile.getStory1RightList().item(i).getTextContent().equalsIgnoreCase("20.0")) {
                        startGameLogic.story1RightBankList.add(startGameLogic.story2herbAnimal);
                    }

                }
            }
            if (readfile.getStory1LeftList() != null) {
                System.out.println("LENGTH IN BLA LEFTT:  " + readfile.getStory1LeftList().getLength());
                for (int i = 0; i < readfile.getStory1LeftList().getLength(); i++) {
                    System.out.println(readfile.getStory1LeftList().item(i).getTextContent());
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("90.0")) {
                        System.out.println("here");
                        startGameLogic.story1LeftBankList.add(startGameLogic.farmerA);
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("80.0")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.farmerB);
                        System.out.println("herePP");
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("60.0")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.farmerC);
                        System.out.println("hereCC");
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("40.0")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.farmerD);
                    }
                    if (readfile.getStory1LeftList().item(i).getTextContent().equalsIgnoreCase("20.0")) {
                        startGameLogic.story1LeftBankList.add(startGameLogic.story2herbAnimal);
                    }
                }
            }
            if (readfile.getStory1BoatList() != null) {
                System.out.println("LENGTH IN BLA: BLAA " + readfile.getStory1BoatList().getLength());
                for (int i = 0; i < readfile.getStory1BoatList().getLength(); i++) {
                    System.out.println(readfile.getStory1BoatList().item(i).getTextContent());
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("90.0")) {
                        System.out.println("here");
                        boatRiders.add(startGameLogic.farmerA);
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("80.0")) {
                        boatRiders.add(startGameLogic.farmerB);
                        System.out.println("herePP");
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("60.0")) {
                        boatRiders.add(startGameLogic.farmerC);
                        System.out.println("hereCC");
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("40.0")) {
                        boatRiders.add(startGameLogic.farmerD);
                    }
                    if (readfile.getStory1BoatList().item(i).getTextContent().equalsIgnoreCase("20.0")) {
                        boatRiders.add(startGameLogic.story2herbAnimal);
                    }
                }
            }
            System.out.println("WHERE IS THE PROB???? "+readfile.getBoatisOnLeft());
            if(readfile.getBoatisOnLeft())
                startGameLogic.setLetsGoButtonClicked(false);
            else startGameLogic.setLetsGoButtonClicked(true);

            choosenShip = SHIP.valueOf(readfile.getChoosenShip());
            choosenHerbAnimal = HERBANIMAL.valueOf(readfile.getChoosenHerbAnimal());
            System.out.println("I recieved    "+choosenShip);
            creatStoryTwoGame(mainStage,choosenShip,choosenHerbAnimal);
        }
        System.out.println("***LISTS***");
        System.out.println("Right" + startGameLogic.story1RightBankList);
        System.out.println("Left" + startGameLogic.story1LeftBankList);
        System.out.println("Boat " + boatRiders);
    }
    private void creatShip(SHIP choosenShip){
        this.choosenShip = choosenShip;
        idleship = new Image(choosenShip.getUrl());
        reversedShip = new Image(choosenShip.getReversedShipUrl());
        ship = new ImageView(reversedShip);
        if(choosenShip.getUrl().contains("boat_large_E")){
            ship.setFitHeight(100);
            ship.setFitWidth(250);
            ship.setLayoutY(GAME_HIGHT - 200);
        }else {
            ship.setFitHeight(200);
            ship.setFitWidth(250);
            ship.setLayoutY(GAME_HIGHT - 250);
        }
        System.out.println("WTFFFFF??? "+startGameLogic.isBoatOnTheLeftBank());
        if(!startGameLogic.isBoatOnTheLeftBank())
            ship.setLayoutX(500);
        else ship.setLayoutX(200);
        gamePane.getChildren().addAll(ship);
    }
    private void creatFarmer(FARMER choosenFarmer){
        this.choosenFarmer = choosenFarmer;
        idleFarmer = new Image(choosenFarmer.getUrl());
        deadFarmer = new Image(choosenFarmer.getDeadFarmerUrl());
        JumpFarmer = new Image(choosenFarmer.getJumpFarmerUrl());
        farmerImage = new ImageView(idleFarmer);
        farmerImage.setFitHeight(160);
        farmerImage.setFitWidth(150);
        farmerImage.setLayoutY(GAME_HIGHT - 300);
        System.out.println(startGameLogic.story1RightBankList);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.farmer)) {
            System.out.println("fe ehh la allah ela allah");
            farmerImage.setLayoutX(GAME_WIDTH - 150);
        }
        else if(startGameLogic.story1LeftBankList.contains(startGameLogic.farmer)){
            farmerImage.setLayoutX(40);
        }
        else
            farmerImage.setLayoutX(ship.getLayoutX());
        gamePane.getChildren().addAll(farmerImage);
    }
    private void creatHerbAnimal(HERBANIMAL choosenHerbAnimal){
        this.choosenHerbAnimal = choosenHerbAnimal;
        idleHerbAnimal = new Image(choosenHerbAnimal.getUrl());
        selectedHerbAnimal = new Image(choosenHerbAnimal.getUrlherbSelectedAnimal());
        deadHerbAnimal = new Image(choosenHerbAnimal.getUrlherbDeadAnimal());
        herbAnimalImage = new ImageView(idleHerbAnimal);
        herbAnimalImage.setFitHeight(100);
        herbAnimalImage.setFitWidth(100);
        if(storyNumber==1){
            herbAnimalImage.setLayoutY(farmerImage.getLayoutY()+50);
            if(startGameLogic.story1RightBankList.contains(startGameLogic.herbanimal) || startGameLogic.story1RightBankList.contains(startGameLogic.story2herbAnimal))
                herbAnimalImage.setLayoutX(GAME_WIDTH - 380);
            else if(startGameLogic.story1LeftBankList.contains(startGameLogic.herbanimal) || startGameLogic.story1LeftBankList.contains(startGameLogic.story2herbAnimal))
                herbAnimalImage.setLayoutX(40);
            else
                herbAnimalImage.setLayoutX(ship.getLayoutX());

        }
        else if(storyNumber==2) {
            herbAnimalImage.setLayoutY(GAME_HIGHT - 230);
            if(startGameLogic.story1RightBankList.contains(startGameLogic.story2herbAnimal))
                herbAnimalImage.setLayoutX(GAME_WIDTH - 110);
        }
        gamePane.getChildren().addAll(herbAnimalImage);
    }
    private void creatCarnAnimal(CARNANIMAL choosenCarnAnimal){
        this.choosenCarnAnimal = choosenCarnAnimal;
        idleCarnAnimal = new Image(choosenCarnAnimal.getUrl());
        selectedCarnAnimal = new Image(choosenCarnAnimal.getUrlherbSelectedAnimal());
        deadCarnAnimal = new Image(choosenCarnAnimal.getUrlherbDeadAnimal());
        carnAnimalImage = new ImageView(idleCarnAnimal);
        carnAnimalImage.setFitHeight(200);
        carnAnimalImage.setFitWidth(200);
        carnAnimalImage.setLayoutY(farmerImage.getLayoutY()-30);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.carnianimal))
            carnAnimalImage.setLayoutX(GAME_WIDTH - 300);
        else if(startGameLogic.story1LeftBankList.contains(startGameLogic.carnianimal))
            carnAnimalImage.setLayoutX(70);
        else
            carnAnimalImage.setLayoutX(ship.getLayoutX());
        gamePane.getChildren().addAll(carnAnimalImage);
    }
    private void creatPlant(PLANTS choosenPlant){
        this.choosenPlants = choosenPlant;
        plant = new Image(choosenPlant.getUrl());
        eatenPlant = new Image(choosenPlant.getUrlEatenPlant());
        plantImage = new ImageView(plant);
        plantImage.setFitHeight(100);
        plantImage.setFitWidth(100);
        plantImage.setLayoutY(farmerImage.getLayoutY()+50);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.planet))
            plantImage.setLayoutX(GAME_WIDTH - 380 - 80);
        else if(startGameLogic.story1LeftBankList.contains(startGameLogic.planet))
            plantImage.setLayoutX(5);
        else
            plantImage.setLayoutX(ship.getLayoutX());
        gamePane.getChildren().addAll(plantImage);
    }
    private void creatGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveShip();
                moveBackground();
                moveHerbAnimal();
                if(storyNumber==1) {
                    moveFarmer();
                    moveCarnAnimal();
                    movePlant();
                }
                else if(storyNumber==2){
                    moveFarmerA();
                    moveFarmerB();
                    moveFarmerC();
                    moveFarmerD();
                }
            }
        };
        gameTimer.start();
    }
    private void moveWinner(int Case){
        winnerTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(Case==0){
                    moveLoserLogo(Case);
                }
                else {
                    moveLoserLogo(Case);
                }
            }
        };
        winnerTimer.start();
    }
    private void moveLoserLogo(int Case){
        if(Case == 0) {
            if (loserAngle < 25 && onebyone) {
                loserAngle += 2;
            } else
                onebyone = false;
            gameoverIcon.setRotate(loserAngle);
            if (loserAngle > -25 && !onebyone) {
                loserAngle -= 2;
                gameoverIcon.setRotate(loserAngle);
            } else
                onebyone = true;
        }
        else{
            if (loserAngle < 25 && onebyone) {
                loserAngle += 2;
            } else
                onebyone = false;
            winnergameIcon.setRotate(loserAngle);
            if (loserAngle > -25 && !onebyone) {
                loserAngle -= 2;
                winnergameIcon.setRotate(loserAngle);
            } else
                onebyone = true;
        }

    }
    private void moveShip(){
        //Check first if the boat can be moved?
        if(letsgoButtonisClicked) {
            if(!startGameLogic.isBoatOnTheLeftBank()){
                if (angle < 6) {
                    angle += 2;
                }
                ship.setRotate(angle);
                if (ship.getLayoutX() >= 200) {
                    ship.setLayoutX(ship.getLayoutX() - 3);
                    endOfBank = false;
                    if(storyNumber==1) {
                        farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                        if (boatRiders.contains(startGameLogic.herbanimal))
                            herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                        else if (boatRiders.contains(startGameLogic.carnianimal))
                            carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 3);
                        else if (boatRiders.contains(startGameLogic.planet))
                            plantImage.setLayoutX(plantImage.getLayoutX() - 3);
                    }
                    else if(storyNumber==2){
                        if(boatRiders.contains(startGameLogic.farmerA))
                            farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                        if (boatRiders.contains(startGameLogic.story2herbAnimal))
                            herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                        if(boatRiders.contains(startGameLogic.farmerB))
                            farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                        if(boatRiders.contains(startGameLogic.farmerC))
                            farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                        if(boatRiders.contains(startGameLogic.farmerD))
                            farmerD.setLayoutX(farmerD.getLayoutX() - 3);

                    }
                } else {
                    endOfBank = true;
                    ship.setImage(idleship);
                    ship.setLayoutX(150);
                    startGameLogic.setLetsGoButtonClicked(false);
                }
            }
            else if (startGameLogic.isBoatOnTheLeftBank()) {
                if (angle > -6) {
                    angle -= 2;
                }
                ship.setRotate(angle);
                if (ship.getLayoutX() < 501) {
                    ship.setLayoutX(ship.getLayoutX() + 3);
                    endOfBank = false;
                    if(storyNumber==1) {
                        farmerImage.setLayoutX(farmerImage.getLayoutX() + 3);
                        if (boatRiders.contains(startGameLogic.herbanimal))
                            herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() + 3);
                        else if (boatRiders.contains(startGameLogic.carnianimal))
                            carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() + 3);
                        else if (boatRiders.contains(startGameLogic.planet))
                            plantImage.setLayoutX(plantImage.getLayoutX() + 3);
                    }
                    else if(storyNumber==2){
                        if(boatRiders.contains(startGameLogic.farmerA))
                            farmerA.setLayoutX(farmerA.getLayoutX() + 3);
                        if (boatRiders.contains(startGameLogic.story2herbAnimal))
                            herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() + 3);
                        if(boatRiders.contains(startGameLogic.farmerB))
                            farmerB.setLayoutX(farmerB.getLayoutX() + 3);
                        if(boatRiders.contains(startGameLogic.farmerC))
                            farmerC.setLayoutX(farmerC.getLayoutX() + 3);
                        if(boatRiders.contains(startGameLogic.farmerD))
                            farmerD.setLayoutX(farmerD.getLayoutX() + 3);
                    }
                } else {
                    endOfBank = true;
                    ship.setImage(reversedShip);
                    startGameLogic.setLetsGoButtonClicked(true);

                }
            }
            if (endOfBank) {
                angle = 0;
                ship.setRotate(angle);
                letsgoButtonisClicked = false;
            }
        }
    }
    private void FarmerClicked(){
        farmerImage.setOnMouseClicked(e->{
            if(endOfBank) {
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmer)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmer)
                        || boatRiders.contains(startGameLogic.farmer)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmer)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmer);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.farmer);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmer)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmer);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.farmer);
                    }   else if (boatRiders.contains(startGameLogic.farmer)) {
                            if (startGameLogic.isBoatOnTheLeftBank()) {
                                boatRiders.remove(startGameLogic.farmer);
                                startGameLogic.story1LeftBankList.add(startGameLogic.farmer);
                            } else {
                                boatRiders.remove(startGameLogic.farmer);
                                startGameLogic.story1RightBankList.add(startGameLogic.farmer);
                            }
                        }
                    isFarmerClicked = true;
                }
            } else
                isFarmerClicked = false;
        });

        farmerImage.setOnMouseEntered(e-> {
                farmerImage.setEffect(new DropShadow());
                farmerImage.setImage(JumpFarmer);
        });
        farmerImage.setOnMouseExited(e-> {
            farmerImage.setEffect(null);
            farmerImage.setImage(idleFarmer);
        });

    }
    private void farmerAclicked(){
        farmerA.setOnMouseClicked(e->{
            if(endOfBank) {
                System.out.println("Farmer A is Clicked");
                System.out.println("boat on left? "+boatRiders);
                System.out.println("List on Right"+startGameLogic.getCrossersOnRightBank());
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerA)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerA)
                        || boatRiders.contains(startGameLogic.farmerA)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerA)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerA);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.farmerA);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerA)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerA);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.farmerA);
                    }   else if (boatRiders.contains(startGameLogic.farmerA)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.farmerA);
                            startGameLogic.story1LeftBankList.add(startGameLogic.farmerA);
                        } else {
                            boatRiders.remove(startGameLogic.farmerA);
                            startGameLogic.story1RightBankList.add(startGameLogic.farmerA);
                        }
                    }
                    isFarmerAClicked = true;
                }
            } else
                isFarmerAClicked = false;
            System.out.println("Boat list now: "+boatRiders);
        });
//        System.out.println( startGameLogic.farmerA.weight );
        Label weight = new Label("  " +String.valueOf(startGameLogic.farmerA.getWeight()));
        weight.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        farmerA.setOnMouseEntered(e-> {
            farmerA.setEffect(new DropShadow());
            farmerA.setImage(JumpFarmerA);
            weight.setLayoutX(farmerA.getLayoutX());
            weight.setLayoutY(farmerA.getLayoutY()-25);
            gamePane.getChildren().add(weight);
        });
        farmerA.setOnMouseExited(e-> {
            farmerA.setEffect(null);
            farmerA.setImage(idleFarmerA);
            gamePane.getChildren().remove(weight);
        });
    }
    private void farmerBclicked(){
        farmerB.setOnMouseClicked(e->{
            if(endOfBank) {
                System.out.println("Farmer B is Clicked");
                System.out.println("boat on left? "+boatRiders);
                System.out.println("List on Right"+startGameLogic.getCrossersOnRightBank());
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerB)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerB)
                        || boatRiders.contains(startGameLogic.farmerB)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerB)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerB);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.farmerB);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerB)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerB);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.farmerB);
                    }   else if (boatRiders.contains(startGameLogic.farmerB)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.farmerB);
                            startGameLogic.story1LeftBankList.add(startGameLogic.farmerB);
                        } else {
                            boatRiders.remove(startGameLogic.farmerB);
                            startGameLogic.story1RightBankList.add(startGameLogic.farmerB);
                        }
                    }
                    isFarmerBClicked = true;
                }
            } else
                isFarmerBClicked = false;
            System.out.println("Boat list now: "+boatRiders);
        });
        Label weight = new Label("  " +String.valueOf(startGameLogic.farmerB.getWeight()));
        weight.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        farmerB.setOnMouseEntered(e-> {
            farmerB.setEffect(new DropShadow());
            farmerB.setImage(JumpFarmerB);
            weight.setLayoutX(farmerB.getLayoutX());
            weight.setLayoutY(farmerB.getLayoutY()-25);
            gamePane.getChildren().add(weight);
        });
        farmerB.setOnMouseExited(e-> {
            farmerB.setEffect(null);
            farmerB.setImage(idleFarmerB);
            gamePane.getChildren().remove(weight);
        });
    }
    private void farmerCclicked(){
        farmerC.setOnMouseClicked(e->{
            if(endOfBank) {
                System.out.println("Farmer A is Clicked");
                System.out.println("boat on left? "+boatRiders);
                System.out.println("List on Right"+startGameLogic.getCrossersOnRightBank());
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerC)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerC)
                        || boatRiders.contains(startGameLogic.farmerC)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerC)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerC);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.farmerC);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerC)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerC);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.farmerC);
                    }   else if (boatRiders.contains(startGameLogic.farmerC)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.farmerC);
                            startGameLogic.story1LeftBankList.add(startGameLogic.farmerC);
                        } else {
                            boatRiders.remove(startGameLogic.farmerC);
                            startGameLogic.story1RightBankList.add(startGameLogic.farmerC);
                        }
                    }
                    isFarmerCClicked = true;
                }
            } else
                isFarmerCClicked = false;
            System.out.println("Boat list now: "+boatRiders);
        });
        Label weight = new Label("  " +String.valueOf(startGameLogic.farmerC.getWeight()));
        weight.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        farmerC.setOnMouseEntered(e-> {
            farmerC.setEffect(new DropShadow());
            farmerC.setImage(JumpFarmerC);
            weight.setLayoutX(farmerC.getLayoutX());
            weight.setLayoutY(farmerC.getLayoutY()-25);
            gamePane.getChildren().add(weight);
        });
        farmerC.setOnMouseExited(e-> {
            farmerC.setEffect(null);
            farmerC.setImage(idleFarmerC);
            gamePane.getChildren().remove(weight);
        });
    }
    private void farmerDclicked(){
        farmerD.setOnMouseClicked(e->{
            if(endOfBank) {
                System.out.println("Farmer A is Clicked");
                System.out.println("boat on left? "+boatRiders);
                System.out.println("List on Right"+startGameLogic.getCrossersOnRightBank());
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerD)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerD)
                        || boatRiders.contains(startGameLogic.farmerD)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.farmerD)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerD);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.farmerD);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.farmerD)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.farmerD);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.farmerD);
                    }   else if (boatRiders.contains(startGameLogic.farmerD)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.farmerD);
                            startGameLogic.story1LeftBankList.add(startGameLogic.farmerD);
                        } else {
                            boatRiders.remove(startGameLogic.farmerD);
                            startGameLogic.story1RightBankList.add(startGameLogic.farmerD);
                        }
                    }
                    isFarmerDClicked = true;
                }
            } else
                isFarmerDClicked = false;
            System.out.println("Boat list now: "+boatRiders);
        });
        Label weight = new Label("  " +String.valueOf(startGameLogic.farmerD.getWeight()));
        weight.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        farmerD.setOnMouseEntered(e-> {
            farmerD.setEffect(new DropShadow());
            farmerD.setImage(JumpFarmerD);
            weight.setLayoutX(farmerD.getLayoutX());
            weight.setLayoutY(farmerD.getLayoutY()-25);
            gamePane.getChildren().add(weight);
        });
        farmerD.setOnMouseExited(e-> {
            farmerD.setEffect(null);
            farmerD.setImage(idleFarmerD);
            gamePane.getChildren().remove(weight);
        });
    }
    private void herbAnimalClicked(){
        herbAnimalImage.setOnMouseClicked(e->{
            if(endOfBank&&storyNumber==1) {
                    if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.herbanimal)
                            || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.herbanimal)
                            || boatRiders.contains(startGameLogic.herbanimal)) {
                        if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.herbanimal)&&boatRiders.size()!=2) {
                            boatRiders.add(startGameLogic.herbanimal);
                            startGameLogic.setCrossersOnboat(boatRiders);
                            startGameLogic.story1RightBankList.remove(startGameLogic.herbanimal);
                        } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.herbanimal)&&boatRiders.size()!=2) {
                            boatRiders.add(startGameLogic.herbanimal);
                            startGameLogic.setCrossersOnboat(boatRiders);
                            startGameLogic.story1LeftBankList.remove(startGameLogic.herbanimal);
                        } else if (boatRiders.contains(startGameLogic.herbanimal)) {
                            if (startGameLogic.isBoatOnTheLeftBank()) {
                                boatRiders.remove(startGameLogic.herbanimal);
                                startGameLogic.story1LeftBankList.add(startGameLogic.herbanimal);
                            } else {
                                boatRiders.remove(startGameLogic.herbanimal);
                                startGameLogic.story1RightBankList.add(startGameLogic.herbanimal);
                            }
                        }
                        isHerbAnimalClicked = true;
                    }
            }

            else if(endOfBank&&storyNumber==2) {
                System.out.println("HEREeEEEEEEE");
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.story2herbAnimal)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.story2herbAnimal)
                        || boatRiders.contains(startGameLogic.story2herbAnimal)) {
                    System.out.println("tb eh>>>>");
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.story2herbAnimal)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.story2herbAnimal);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.story2herbAnimal);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.story2herbAnimal)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.story2herbAnimal);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.story2herbAnimal);
                    } else if (boatRiders.contains(startGameLogic.story2herbAnimal)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.story2herbAnimal);
                            startGameLogic.story1LeftBankList.add(startGameLogic.story2herbAnimal);
                        } else {
                            boatRiders.remove(startGameLogic.story2herbAnimal);
                            startGameLogic.story1RightBankList.add(startGameLogic.story2herbAnimal);
                        }
                    }
                    isHerbAnimalClicked = true;
                }
            }
            else
                    isHerbAnimalClicked = false;

        });
        if(storyNumber==2) {
            weight = new Label("  " + String.valueOf(startGameLogic.story2herbAnimal.getWeight()));
            weight.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        }
        herbAnimalImage.setOnMouseEntered(e-> {
            herbAnimalImage.setEffect(new DropShadow());
            herbAnimalImage.setImage(selectedHerbAnimal);
            if(storyNumber==2) {
                weight.setLayoutX(herbAnimalImage.getLayoutX());
                weight.setLayoutY(herbAnimalImage.getLayoutY() - 25);
                gamePane.getChildren().add(weight);
            }
        });
        herbAnimalImage.setOnMouseExited(e-> {
            herbAnimalImage.setEffect(null);
            herbAnimalImage.setImage(idleHerbAnimal);
            gamePane.getChildren().remove(weight);
        });
    }
    private void carnAnimalClicked(){
        carnAnimalImage.setOnMouseClicked(e->{
            if(endOfBank) {
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.carnianimal)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.carnianimal)
                        || boatRiders.contains(startGameLogic.carnianimal)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.carnianimal)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.carnianimal);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.carnianimal);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.carnianimal)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.carnianimal);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.carnianimal);
                    } else if (boatRiders.contains(startGameLogic.carnianimal)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.carnianimal);
                            startGameLogic.story1LeftBankList.add(startGameLogic.carnianimal);
                        } else {
                            boatRiders.remove(startGameLogic.carnianimal);
                            startGameLogic.story1RightBankList.add(startGameLogic.carnianimal);
                        }
                    }
                    isCarnAnimalClicked = true;
                }
            }
            else
                isCarnAnimalClicked = false;
        });

        carnAnimalImage.setOnMouseEntered(e-> {
            carnAnimalImage.setEffect(new DropShadow());
            carnAnimalImage.setImage(selectedCarnAnimal);
        });
        carnAnimalImage.setOnMouseExited(e-> {
            carnAnimalImage.setEffect(null);
            carnAnimalImage.setImage(idleCarnAnimal);
        });
    }
    private void plantClicked(){
        plantImage.setOnMouseClicked(e->{
            if(endOfBank) {
                if (startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.planet)
                        || !startGameLogic.isBoatOnTheLeftBank() && startGameLogic.getCrossersOnRightBank().contains(startGameLogic.planet)
                        || boatRiders.contains(startGameLogic.planet)) {
                    if (startGameLogic.getCrossersOnRightBank().contains(startGameLogic.planet)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.planet);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1RightBankList.remove(startGameLogic.planet);
                    } else if (startGameLogic.getCrossersOnLeftBank().contains(startGameLogic.planet)&&boatRiders.size()!=2) {
                        boatRiders.add(startGameLogic.planet);
                        startGameLogic.setCrossersOnboat(boatRiders);
                        startGameLogic.story1LeftBankList.remove(startGameLogic.planet);
                    } else if (boatRiders.contains(startGameLogic.planet)) {
                        if (startGameLogic.isBoatOnTheLeftBank()) {
                            boatRiders.remove(startGameLogic.planet);
                            startGameLogic.story1LeftBankList.add(startGameLogic.planet);
                        } else {
                            boatRiders.remove(startGameLogic.planet);
                            startGameLogic.story1RightBankList.add(startGameLogic.planet);
                        }
                    }
                    isPlantClicked = true;
                }
            } else
                isPlantClicked = false;
        });

        plantImage.setOnMouseEntered(e-> {
            plantImage.setEffect(new DropShadow());
        });
        plantImage.setOnMouseExited(e-> {
            plantImage.setEffect(null);
        });
    }
    private void movePlant(){
        if(isPlantClicked){
            if(boatRiders.contains(startGameLogic.planet)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    plantImage.setRotate(angle);


                    boatRiders.remove(startGameLogic.planet);
                    if(boatRiders.isEmpty()) {
                        if (plantImage.getLayoutX() > ship.getLayoutX()) {
                            plantImage.setLayoutX(plantImage.getLayoutX() - 3);
                        } else {
                            isPlantClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.farmer)) {
                            if (plantImage.getLayoutX() > farmerImage.getLayoutX()+150) {
                                plantImage.setLayoutX(plantImage.getLayoutX() - 3);
                            } else {
                                isPlantClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.herbanimal)) {
                            if (plantImage.getLayoutX() > herbAnimalImage.getLayoutX()+150) {
                                plantImage.setLayoutX(plantImage.getLayoutX() - 3);
                            } else {
                                isPlantClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.carnianimal)) {
                            if (plantImage.getLayoutX() > carnAnimalImage.getLayoutX()+150) {
                                plantImage.setLayoutX(plantImage.getLayoutX() - 3);
                            } else {
                                isPlantClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.planet);

                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    plantImage.setRotate(angle);
                    if (plantImage.getLayoutX() < ship.getLayoutX()) {
                        plantImage.setLayoutX(plantImage.getLayoutX() + 3);
                    } else {
                        isPlantClicked = false;
                    }
                }
            }
            else{
                if(plantImage.getLayoutX()>=ship.getLayoutX()) {
                    if (plantImage.getLayoutX() < farmerImage.getLayoutX() && boatRiders.contains(startGameLogic.farmer))
                        farmerImage.setLayoutX(farmerImage.getLayoutX() - 100);
                    else if (plantImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
                    else if (plantImage.getLayoutX() < carnAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.carnianimal))
                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    plantImage.setRotate(angle);
                    if (plantImage.getLayoutX() <= GAME_WIDTH-450) {
                        plantImage.setLayoutX(plantImage.getLayoutX() + 3);
                    } else {
                        isPlantClicked = false;
                    }
                }
                else {
                    if (angle < 6) {
                        angle += 2;
                    }
                    plantImage.setRotate(angle);
                    if (plantImage.getLayoutX() >= 5) {
                        plantImage.setLayoutX(plantImage.getLayoutX() - 3);
                    } else {
                        isPlantClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            plantImage.setRotate(angle);
        }
    }
    private void moveCarnAnimal(){
        if(isCarnAnimalClicked){
            if(boatRiders.contains(startGameLogic.carnianimal)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    carnAnimalImage.setRotate(angle);

                    boatRiders.remove(startGameLogic.carnianimal);
                    if(boatRiders.isEmpty()) {
                        if (carnAnimalImage.getLayoutX() > ship.getLayoutX()) {
                            carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 3);
                        } else {
                            isCarnAnimalClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.farmer)) {
                            if (carnAnimalImage.getLayoutX() > farmerImage.getLayoutX()+150) {
                                carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 3);
                            } else {
                                isCarnAnimalClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.herbanimal)) {
                            if (carnAnimalImage.getLayoutX() > herbAnimalImage.getLayoutX()+150) {
                                carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 3);
                            } else {
                                isCarnAnimalClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.planet)) {
                            if (carnAnimalImage.getLayoutX() > plantImage.getLayoutX()+150) {
                                carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 3);
                            } else {
                                isCarnAnimalClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.carnianimal);

                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    carnAnimalImage.setRotate(angle);
                    if (carnAnimalImage.getLayoutX() < ship.getLayoutX()) {
                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() + 3);
                    } else {
                        isCarnAnimalClicked = false;
                    }
                }
            }
            else{
                if(carnAnimalImage.getLayoutX()>=ship.getLayoutX()) {
                    if (carnAnimalImage.getLayoutX() < farmerImage.getLayoutX() && boatRiders.contains(startGameLogic.farmer))
                        farmerImage.setLayoutX(farmerImage.getLayoutX() - 100);
                    else if (carnAnimalImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
                    else if (carnAnimalImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
                        plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    carnAnimalImage.setRotate(angle);
                    if (carnAnimalImage.getLayoutX() <= GAME_WIDTH-350) {
                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() + 3);
                    } else {
                        isCarnAnimalClicked = false;

                    }
                }
                else {
//                    if(carnAnimalImage.getLayoutX()<farmerImage.getLayoutX())
//                        farmerImage.setLayoutX(farmerImage.getLayoutX()-100);
                    if (angle < 6) {
                        angle += 2;
                    }
                    carnAnimalImage.setRotate(angle);
                    if (carnAnimalImage.getLayoutX() >= 70) {
                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 3);
                    } else {
                        isCarnAnimalClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            carnAnimalImage.setRotate(angle);
        }
    }
    private void moveHerbAnimal(){
//        if(storyNumber==1) {
            if (isHerbAnimalClicked) {
                if (boatRiders.contains(startGameLogic.herbanimal) || boatRiders.contains(startGameLogic.story2herbAnimal) ) {
                    if (!startGameLogic.isBoatOnTheLeftBank()) {
                        if (angle < 6) {
                            angle += 2;
                        }

                        herbAnimalImage.setRotate(angle);
                        if(storyNumber==1)
                            boatRiders.remove(startGameLogic.herbanimal);
                        else
                            boatRiders.remove(startGameLogic.story2herbAnimal);
                        if (boatRiders.isEmpty()) {
                            System.out.println("ehhhhh??");
                            if (herbAnimalImage.getLayoutX() > ship.getLayoutX()) {
                                System.out.println("mtr7amny");
                                herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                            } else {
                                isHerbAnimalClicked = false;
                            }
                        } else {
                            if(storyNumber==1) {
                                if (boatRiders.contains(startGameLogic.farmer)) {
                                    if (herbAnimalImage.getLayoutX() > farmerImage.getLayoutX() + 200) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                } else if (boatRiders.contains(startGameLogic.carnianimal)) {
                                    if (herbAnimalImage.getLayoutX() > carnAnimalImage.getLayoutX() + 150) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                } else if (boatRiders.contains(startGameLogic.planet)) {
                                    if (herbAnimalImage.getLayoutX() > plantImage.getLayoutX() + 150) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                }
                            }
                            else if(storyNumber==2){
                                if(boatRiders.contains(startGameLogic.farmerB)) {
                                    if (herbAnimalImage.getLayoutX() > farmerB.getLayoutX()+100) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                }
                                else if(boatRiders.contains(startGameLogic.farmerC)) {
                                    if (herbAnimalImage.getLayoutX() > farmerC.getLayoutX()+100) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                }
                                else if(boatRiders.contains(startGameLogic.farmerD)) {
                                    if (herbAnimalImage.getLayoutX() > farmerD.getLayoutX()+100) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                }
                                else if(boatRiders.contains(startGameLogic.farmerA)) {
                                    if (herbAnimalImage.getLayoutX() > farmerA.getLayoutX()+100) {
                                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                                    } else {
                                        isHerbAnimalClicked = false;
                                    }
                                }
                            }
                        }
                        if(storyNumber==1)
                            boatRiders.add(startGameLogic.herbanimal);
                        else
                            boatRiders.add(startGameLogic.story2herbAnimal);
                    } else {
                        if (angle > -6) {
                            angle -= 2;
                        }
                        herbAnimalImage.setRotate(angle);
                        if (herbAnimalImage.getLayoutX() < ship.getLayoutX()) {
                            herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() + 3);
                        } else {
                            isHerbAnimalClicked = false;
                        }
                    }
                } else {
                    if(storyNumber==1) {
                        if (herbAnimalImage.getLayoutX() >= ship.getLayoutX()) {
                            if (herbAnimalImage.getLayoutX() < farmerImage.getLayoutX() && boatRiders.contains(startGameLogic.farmer))
                                farmerImage.setLayoutX(farmerImage.getLayoutX() - 50);
                            else if (herbAnimalImage.getLayoutX() < carnAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.carnianimal))
                                carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
                            else if (herbAnimalImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
                                plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                        }
                    }
                    if (!startGameLogic.isBoatOnTheLeftBank()) {
                        if (angle > -6) {
                            angle -= 2;
                        }
                        herbAnimalImage.setRotate(angle);
                        if (herbAnimalImage.getLayoutX() <= GAME_WIDTH - 250) {
                            herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() + 3);
                        } else {
                            isHerbAnimalClicked = false;
                        }
                    } else {
                        if (angle < 6) {
                            angle += 2;
                        }
                        herbAnimalImage.setRotate(angle);
                        if(storyNumber==1) {
                            if (herbAnimalImage.getLayoutX() >= 40) {
                                herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                            } else {
                                isHerbAnimalClicked = false;
                            }
                        }
                        else{
                            if (herbAnimalImage.getLayoutX() >= 0) {
                                herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 3);
                            } else {
                                isHerbAnimalClicked = false;
                            }
                        }
                    }
                }
            } else {
                angle = 0;
                herbAnimalImage.setRotate(angle);
            }
//        }

    }
    private void moveFarmerA(){
        if(isFarmerAClicked) {
            if(boatRiders.contains(startGameLogic.farmerA)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerA.setRotate(angle);
                    boatRiders.remove(startGameLogic.farmerA);
                    if(boatRiders.isEmpty()) {
                        if (farmerA.getLayoutX() > ship.getLayoutX()) {
                            farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                        } else {
                            isFarmerAClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.farmerB)) {
                            if (farmerA.getLayoutX() > farmerB.getLayoutX()+100) {
                                farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                            } else {
                                isFarmerAClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerC)) {
                            if (farmerA.getLayoutX() > farmerC.getLayoutX()+100) {
                                farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                            } else {
                                isFarmerAClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerD)) {
                            if (farmerA.getLayoutX() > farmerD.getLayoutX()+100) {
                                farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                            } else {
                                isFarmerAClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.story2herbAnimal)) {
                            if (farmerA.getLayoutX() > herbAnimalImage.getLayoutX()+100) {
                                farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                            } else {
                                isFarmerClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.farmerA);
                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerA.setRotate(angle);
                    if (farmerA.getLayoutX() < ship.getLayoutX()) {
                        farmerA.setLayoutX(farmerA.getLayoutX() + 3);
                    } else {
                        isFarmerAClicked = false;
                    }
                }
            }
            else{
                if(farmerA.getLayoutX()>=ship.getLayoutX()) {
//                    if (farmerA.getLayoutX() < farmerB.getLayoutX() && boatRiders.contains(startGameLogic.farmerB))
//                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
//                    else if (farmerImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
//                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
//                    else if (farmerImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
//                        plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerA.setRotate(angle);
                    if (farmerA.getLayoutX() <= GAME_WIDTH-250) {
                        farmerA.setLayoutX(farmerA.getLayoutX() + 3);
                    } else {
                        isFarmerAClicked = false;
                    }
                }
                else {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerA.setRotate(angle);
                    if (farmerA.getLayoutX() >= 60) {
                        farmerA.setLayoutX(farmerA.getLayoutX() - 3);
                    } else {
                        isFarmerAClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            farmerA.setRotate(angle);
        }
    }
    private void moveFarmerB(){
        if(isFarmerBClicked) {
            if(boatRiders.contains(startGameLogic.farmerB)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerB.setRotate(angle);
                    boatRiders.remove(startGameLogic.farmerB);
                    if(boatRiders.isEmpty()) {
                        if (farmerB.getLayoutX() > ship.getLayoutX()) {
                            farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                        } else {
                            isFarmerBClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.farmerA)) {
                            if (farmerB.getLayoutX() > farmerA.getLayoutX()+100) {
                                farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                            } else {
                                isFarmerBClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerC)) {
                            if (farmerB.getLayoutX() > farmerC.getLayoutX()+100) {
                                farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                            } else {
                                isFarmerBClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerD)) {
                            if (farmerB.getLayoutX() > farmerD.getLayoutX()+100) {
                                farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                            } else {
                                isFarmerBClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.story2herbAnimal)) {
                            if (farmerB.getLayoutX() > herbAnimalImage.getLayoutX()+100) {
                                farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                            } else {
                                isFarmerBClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.farmerB);
                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerB.setRotate(angle);
                    if (farmerB.getLayoutX() < ship.getLayoutX()) {
                        farmerB.setLayoutX(farmerB.getLayoutX() + 3);
                    } else {
                        isFarmerBClicked = false;
                    }
                }
            }
            else{
                if(farmerB.getLayoutX()>=ship.getLayoutX()) {
//                    if (farmerA.getLayoutX() < farmerB.getLayoutX() && boatRiders.contains(startGameLogic.farmerB))
//                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
//                    else if (farmerImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
//                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
//                    else if (farmerImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
//                        plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerB.setRotate(angle);
                    if (farmerB.getLayoutX() <= GAME_WIDTH-350) {
                        farmerB.setLayoutX(farmerB.getLayoutX() + 3);
                    } else {
                        isFarmerBClicked = false;
                    }
                }
                else {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerB.setRotate(angle);
                    if (farmerB.getLayoutX() >= 40) {
                        farmerB.setLayoutX(farmerB.getLayoutX() - 3);
                    } else {
                        isFarmerBClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            farmerB.setRotate(angle);
        }
    }
    private void moveFarmerC(){
        if(isFarmerCClicked) {
            if(boatRiders.contains(startGameLogic.farmerC)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerC.setRotate(angle);
                    boatRiders.remove(startGameLogic.farmerC);
                    if(boatRiders.isEmpty()) {
                        if (farmerC.getLayoutX() > ship.getLayoutX()) {
                            farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                        } else {
                            isFarmerCClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.farmerA)) {
                            if (farmerC.getLayoutX() > farmerA.getLayoutX()+100) {
                                farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                            } else {
                                isFarmerCClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerB)) {
                            if (farmerC.getLayoutX() > farmerB.getLayoutX()+100) {
                                farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                            } else {
                                isFarmerCClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerD)) {
                            if (farmerC.getLayoutX() > farmerD.getLayoutX()+100) {
                                farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                            } else {
                                isFarmerCClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.story2herbAnimal)) {
                            if (farmerC.getLayoutX() > herbAnimalImage.getLayoutX()+100) {
                                farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                            } else {
                                isFarmerClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.farmerC);
                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerC.setRotate(angle);
                    if (farmerC.getLayoutX() < ship.getLayoutX()) {
                        farmerC.setLayoutX(farmerC.getLayoutX() + 3);
                    } else {
                        isFarmerCClicked = false;
                    }
                }
            }
            else{
                if(farmerC.getLayoutX()>=ship.getLayoutX()) {
//                    if (farmerA.getLayoutX() < farmerB.getLayoutX() && boatRiders.contains(startGameLogic.farmerB))
//                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
//                    else if (farmerImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
//                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
//                    else if (farmerImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
//                        plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerC.setRotate(angle);
                    if (farmerC.getLayoutX() <= GAME_WIDTH-450) {
                        farmerC.setLayoutX(farmerC.getLayoutX() + 3);
                    } else {
                        isFarmerCClicked = false;
                    }
                }
                else {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerC.setRotate(angle);
                    if (farmerC.getLayoutX() >= 25) {
                        farmerC.setLayoutX(farmerC.getLayoutX() - 3);
                    } else {
                        isFarmerCClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            farmerC.setRotate(angle);
        }
    }
    private void moveFarmerD(){
        if(isFarmerDClicked) {
            if(boatRiders.contains(startGameLogic.farmerD)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerD.setRotate(angle);
                    boatRiders.remove(startGameLogic.farmerD);
                    if(boatRiders.isEmpty()) {
                        if (farmerD.getLayoutX() > ship.getLayoutX()) {
                            farmerD.setLayoutX(farmerD.getLayoutX() - 3);
                        } else {
                            isFarmerDClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.farmerD)) {
                            if (farmerD.getLayoutX() > farmerA.getLayoutX()+100) {
                                farmerD.setLayoutX(farmerD.getLayoutX() - 3);
                            } else {
                                isFarmerDClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerC)) {
                            if (farmerD.getLayoutX() > farmerC.getLayoutX()+100) {
                                farmerD.setLayoutX(farmerD.getLayoutX() - 3);
                            } else {
                                isFarmerDClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.farmerB)) {
                            if (farmerD.getLayoutX() > farmerB.getLayoutX()+100) {
                                farmerD.setLayoutX(farmerD.getLayoutX() - 3);
                            } else {
                                isFarmerDClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.story2herbAnimal)) {
                            if (farmerD.getLayoutX() > herbAnimalImage.getLayoutX()+100) {
                                farmerD.setLayoutX(farmerD.getLayoutX() - 3);
                            } else {
                                isFarmerDClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.farmerD);
                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerD.setRotate(angle);
                    if (farmerD.getLayoutX() < ship.getLayoutX()) {
                        farmerD.setLayoutX(farmerD.getLayoutX() + 3);
                    } else {
                        isFarmerDClicked = false;
                    }
                }
            }
            else{
                if(farmerD.getLayoutX()>=ship.getLayoutX()) {
                    System.out.println("hena el moshkla D");
//                    if (farmerA.getLayoutX() < farmerB.getLayoutX() && boatRiders.contains(startGameLogic.farmerB))
//                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
//                    else if (farmerImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
//                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
//                    else if (farmerImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
//                        plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerD.setRotate(angle);
                    if (farmerD.getLayoutX() <= GAME_WIDTH-550) {
                        farmerD.setLayoutX(farmerD.getLayoutX() + 3);
                    } else {
                        isFarmerDClicked = false;
                    }
                }
                else {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerD.setRotate(angle);
                    if (farmerD.getLayoutX() >= 10) {
                        farmerD.setLayoutX(farmerD.getLayoutX() - 3);
                    } else {
                        isFarmerDClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            farmerD.setRotate(angle);
        }
    }
    private void moveFarmer(){
        if(isFarmerClicked) {
            if(boatRiders.contains(startGameLogic.farmer)) {
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerImage.setRotate(angle);
                    boatRiders.remove(startGameLogic.farmer);
                    if(boatRiders.isEmpty()) {
                        if (farmerImage.getLayoutX() > ship.getLayoutX()) {
                            farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                        } else {
                            isFarmerClicked = false;
                        }
                    }
                    else{
                        if(boatRiders.contains(startGameLogic.herbanimal)) {
                            if (farmerImage.getLayoutX() > herbAnimalImage.getLayoutX()+100) {
                                farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                            } else {
                                isFarmerClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.carnianimal)) {
                            if (farmerImage.getLayoutX() > carnAnimalImage.getLayoutX()+100) {
                                farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                            } else {
                                isFarmerClicked = false;
                            }
                        }
                        else if(boatRiders.contains(startGameLogic.planet)) {
                            if (farmerImage.getLayoutX() > plantImage.getLayoutX()+100) {
                                farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                            } else {
                                isFarmerClicked = false;
                            }
                        }
                    }
                    boatRiders.add(startGameLogic.farmer);
                }
                else {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerImage.setRotate(angle);
                    if (farmerImage.getLayoutX() < ship.getLayoutX()) {
                        farmerImage.setLayoutX(farmerImage.getLayoutX() + 3);
                    } else {
                        isFarmerClicked = false;
                        }
                }
            }
            else{
                if(farmerImage.getLayoutX()>=ship.getLayoutX()) {
                    if (farmerImage.getLayoutX() < carnAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.carnianimal))
                        carnAnimalImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
                    else if (farmerImage.getLayoutX() < herbAnimalImage.getLayoutX() && boatRiders.contains(startGameLogic.herbanimal))
                        herbAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 50);
                    else if (farmerImage.getLayoutX() < plantImage.getLayoutX() && boatRiders.contains(startGameLogic.planet))
                        plantImage.setLayoutX(plantImage.getLayoutX() - 50);
                }
                if (!startGameLogic.isBoatOnTheLeftBank()) {
                    if (angle > -6) {
                        angle -= 2;
                    }
                    farmerImage.setRotate(angle);
                    if (farmerImage.getLayoutX() <= GAME_WIDTH-150) {
                        farmerImage.setLayoutX(farmerImage.getLayoutX() + 3);
                    } else {
                        isFarmerClicked = false;
                    }
                }
                else {
                    if (angle < 6) {
                        angle += 2;
                    }
                    farmerImage.setRotate(angle);
                    if (farmerImage.getLayoutX() >= 40) {
                        farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                    } else {
                        isFarmerClicked = false;
                    }
                }
            }
        }
        else{
            angle =0;
            farmerImage.setRotate(angle);
        }
    }


    private void creatBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for(int i=0;i<12;i++){
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE2);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE2);
            GridPane.setConstraints(backgroundImage1, i%3, 0);
            GridPane.setConstraints(backgroundImage2, i%3, 0);
            backgroundImage1.setFitWidth(1250);
            backgroundImage2.setFitWidth(1250);
            backgroundImage1.setFitHeight(300);
            backgroundImage2.setFitHeight(300);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutX(-1250);
        gamePane.getChildren().addAll(gridPane1,gridPane2);
        ImageView backgroundImage3 = new ImageView(BACKGROUND_IMAGE);
        backgroundImage3.setFitWidth(1250);
        backgroundImage3.setLayoutY(300);
        backgroundImage3.setFitHeight(400);

        gamePane.getChildren().addAll(backgroundImage3,createLetsGoButton(),saveButton,redoButton,undoButton,
                backButton,units,Tenth,getReady,helpButton);
    }
    private void creatHelpSubScene(){
        Stage helpStage = new Stage();
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.initStyle(StageStyle.TRANSPARENT);
        AnchorPane root = new AnchorPane();

        int HELPHIGHT = 600;
        int HELPWIDTH = 400;
        GridPane helpLayout = new GridPane();
        helpLayout.setPadding(new Insets(20, 20, 20, 20)); //Setting gaps in corners
        helpLayout.setVgap(8);
        helpLayout.setHgap(10);

        BackgroundImage image = new BackgroundImage(new Image(GameOVERBACKGROUND_IMAGE,HELPWIDTH,HELPHIGHT,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        closeHelp = new ImageView("view/resources/Exit.png");
        closeHelp.setFitHeight(50);
        closeHelp.setFitWidth(50);
        closeHelp.setLayoutX(20);
        closeHelp.setLayoutY(20);
        if(storyNumber==1)
            helptxt = new Label("Rules: \n\nThe farmer is the only one \nwho can sail the boat.\nHe can only take\none passenger, " +
                    "\nin addition to himself.\n" +
                    "\nYou can not leave any \ntwo crossers on \nthe same bank \nif they can harm(eat) \neach other");
        else
            helptxt = new Label("Rules: \n\n\nThe boat cannot bear\na load heavier than \n100 kg.\n\n\n" +
                    "All farmers can raft, \nwhile the animal \ncannot.");
        helptxt.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
        helptxt.setLayoutX(40);
        helptxt.setLayoutY(100);
        root.setBackground(new Background(image));

        closeHelp.setOnMouseClicked(e->{
            helpStage.close();
        });

        closeHelp.setOnMouseEntered(e->{
            closeHelp.setEffect(new DropShadow());
        });
        closeHelp.setOnMouseExited(e->{
            closeHelp.setEffect(null);
        });

        root.getChildren().addAll(closeHelp,helptxt);
        helpScene = new Scene(root, HELPWIDTH,HELPHIGHT);
        helpStage.setScene(helpScene);
        helpStage.showAndWait();
    }

    private void moveBackground(){
        gridPane1.setLayoutX(gridPane1.getLayoutX() + 0.5);
        gridPane2.setLayoutX(gridPane2.getLayoutX()+0.5);
        if(gridPane1.getLayoutX()>=1250){
            gridPane1.setLayoutX(-1250);
        }
        if(gridPane2.getLayoutX() >= 1250){
            gridPane2.setLayoutX(-1250);
        }
    }


    private void creatFarmerA(){
        idleFarmerA = new Image("view/resources/fourFarmer/adventurer_idle.png");
        JumpFarmerA = new Image("view/resources/fourFarmer/adventurer_jump.png");
        farmerA = new ImageView(idleFarmerA);
        farmerA.setFitHeight(120);
        farmerA.setFitWidth(120);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.farmerA))
            farmerA.setLayoutX(GAME_WIDTH - 220);
        else if(startGameLogic.story1LeftBankList.contains(startGameLogic.farmerA))
            farmerA.setLayoutX(60);
        else farmerA.setLayoutX(ship.getLayoutX());
        farmerA.setLayoutY(GAME_HIGHT-250);
        gamePane.getChildren().add(farmerA);
    }
    private void creatFarmerB(){
        idleFarmerB = new Image("view/resources/fourFarmer/female_idle.png");
        JumpFarmerB = new Image("view/resources/fourFarmer/female_jump.png");
        farmerB = new ImageView(idleFarmerB);
        farmerB.setFitHeight(120);
        farmerB.setFitWidth(120);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.farmerB))
            farmerB.setLayoutX(GAME_WIDTH - 320);
        else if(startGameLogic.story1RightBankList.contains(startGameLogic.farmerB))
            farmerB.setLayoutX(40);
        else farmerB.setLayoutX(ship.getLayoutX());
        farmerB.setLayoutY(farmerA.getLayoutY());
        gamePane.getChildren().add(farmerB);
    }
    private void creatFarmerC(){
        idleFarmerC = new Image("view/resources/fourFarmer/player_idle.png");
        JumpFarmerC = new Image("view/resources/fourFarmer/player_jump.png");
        farmerC = new ImageView(idleFarmerC);
        farmerC.setFitHeight(120);
        farmerC.setFitWidth(120);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.farmerC))
            farmerC.setLayoutX(GAME_WIDTH - 420);
        else if(startGameLogic.story1LeftBankList.contains(startGameLogic.farmerC))
            farmerC.setLayoutX(25);
        else farmerC.setLayoutX(ship.getLayoutX());

        farmerC.setLayoutY(farmerA.getLayoutY());
        gamePane.getChildren().add(farmerC);
    }
    private void creatFarmerD(){
        System.out.println("CRATTTINGGGGGGGGGGGGGGGGGGGGGGGGGG");
        idleFarmerD = new Image("view/resources/fourFarmer/soldier_idle.png");
        JumpFarmerD = new Image("view/resources/fourFarmer/soldier_jump.png");
        farmerD = new ImageView(idleFarmerD);
        farmerD.setFitHeight(120);
        farmerD.setFitWidth(120);
        if(startGameLogic.story1RightBankList.contains(startGameLogic.farmerD))
            farmerD.setLayoutX(GAME_WIDTH - 520);
        else if(startGameLogic.story1LeftBankList.contains(startGameLogic.farmerD))
            farmerD.setLayoutX(10);
        else farmerD.setLayoutX(ship.getLayoutX());
        farmerD.setLayoutY(farmerA.getLayoutY());
        gamePane.getChildren().add(farmerD);
    }
}