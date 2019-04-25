package view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Logic.Farmer;
import model.CrossRiverButton;
import model.FARMER;
import model.SHIP;
import model.HERBANIMAL;
import model.CARNANIMAL;
import model.PLANTS;
import javafx.scene.image.ImageView;

import javafx.scene.effect.DropShadow;
import sample.ICrosser;

public class GameViewManger {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;
    private ImageView ship,reversedShip, farmerImage, herbAnimalImage, carnAnimalImage, plantImage,farmerA,farmerB,farmerC,farmerD,deadFarmers;
    private Image idleFarmer, deadFarmer, JumpFarmer;
    private Image idleHerbAnimal, selectedHerbAnimal, deadHerbAnimal;
    private Image idleCarnAnimal, selectedCarnAnimal, killCarnAnimal, deadCarnAnimal;
    private Image plant, eatenPlant;
    private Image idleFarmerA,JumpFarmerA;
    private Image idleFarmerB,JumpFarmerB;
    private Image idleFarmerC,JumpFarmerC;
    private Image idleFarmerD,JumpFarmerD;

    private static final int GAME_WIDTH = 1250;
    private static final int GAME_HIGHT = 700;
    private int storyNumber;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isBoatOnTheLeftBank=true;
    private boolean isFarmerOnTheLeftBank=true;
    private boolean isFarmerClicked=false, farmerIsOnShip=false;
    private boolean onByone = true;
    private boolean endOfBank= true,oneTimeplease=true;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "view/resources/River.jpeg";
    private final static String BACKGROUND_IMAGE2 = "view/resources/Sky.png";

    public GameViewManger(){
        initStage();
    }

    private CrossRiverButton createButtonToStart(){
        CrossRiverButton startButton = new CrossRiverButton("Let's GO");
        startButton.setLayoutX(GAME_WIDTH/2);
        startButton.setLayoutY(100);

        startButton.setOnAction(e->{
            if(endOfBank) {
                if (onByone) {
                    isLeftKeyPressed = true;
                    isRightKeyPressed = false;
                    onByone = false;
                } else if (!onByone) {
                    isLeftKeyPressed = false;
                    isRightKeyPressed = true;
                    onByone = true;
                }
            }
        });
        return startButton;
    }
    private void initStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void creatNewGame(Stage menuStage, SHIP choosenShip, FARMER choosenFarmer, HERBANIMAL choosenHerbAnimal, CARNANIMAL choosenCarnAnimal, PLANTS choosenPlants){
        storyNumber=1;
        this.menuStage = menuStage;
        this.menuStage.hide();
        creatBackground();
        creatShip(choosenShip);
        creatFarmer(choosenFarmer);
        creatHerbAnimal(choosenHerbAnimal);
        creatCarnAnimal(choosenCarnAnimal);
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
    private void creatFarmerA(){
        idleFarmerA = new Image("view/resources/fourFarmer/adventurer_idle.png");
        JumpFarmerA = new Image("view/resources/fourFarmer/adventurer_jump.png");
        farmerA = new ImageView(idleFarmerA);
        farmerA.setFitHeight(120);
        farmerA.setFitWidth(120);
        farmerA.setLayoutX(GAME_WIDTH - 250);
        farmerA.setLayoutY(GAME_HIGHT-350);
        gamePane.getChildren().add(farmerA);
    }
    private void creatFarmerB(){
        idleFarmerB = new Image("view/resources/fourFarmer/female_idle.png");
        JumpFarmerB = new Image("view/resources/fourFarmer/female_jump.png");
        farmerB = new ImageView(idleFarmerB);
        farmerB.setFitHeight(120);
        farmerB.setFitWidth(120);
        farmerB.setLayoutX(farmerA.getLayoutX() - 100);
        farmerB.setLayoutY(farmerA.getLayoutY());
        gamePane.getChildren().add(farmerB);
    }
    private void creatFarmerC(){
        idleFarmerC = new Image("view/resources/fourFarmer/player_idle.png");
        JumpFarmerC = new Image("view/resources/fourFarmer/player_jump.png");
        farmerC = new ImageView(idleFarmerC);
        farmerC.setFitHeight(120);
        farmerC.setFitWidth(120);
        farmerC.setLayoutX(farmerB.getLayoutX() - 100);
        farmerC.setLayoutY(farmerA.getLayoutY());
        gamePane.getChildren().add(farmerC);
    }
    private void creatFarmerD(){
        idleFarmerD = new Image("view/resources/fourFarmer/soldier_idle.png");
        JumpFarmerD = new Image("view/resources/fourFarmer/soldier_jump.png");
        farmerD = new ImageView(idleFarmerD);
        farmerD.setFitHeight(120);
        farmerD.setFitWidth(120);
        farmerD.setLayoutX(farmerC.getLayoutX() - 100);
        farmerD.setLayoutY(farmerA.getLayoutY());
        gamePane.getChildren().add(farmerD);
    }

    private void creatShip(SHIP choosenShip){
        ship = new ImageView(choosenShip.getUrl());
        if(choosenShip.getUrl().contains("boat_large_E")){
            ship.setFitHeight(100);
            ship.setFitWidth(250);
            ship.setLayoutY(GAME_HIGHT - 150);
            reversedShip = new ImageView(choosenShip.getReversedShipUrl());
            reversedShip.setFitHeight(100);
            reversedShip.setFitWidth(250);
            reversedShip.setLayoutY(GAME_HIGHT - 150);
        }else {
            reversedShip = new ImageView(choosenShip.getReversedShipUrl());
            ship.setFitHeight(200);
            ship.setFitWidth(250);
            ship.setLayoutY(GAME_HIGHT - 250);
            reversedShip.setFitHeight(200);
            reversedShip.setFitWidth(250);
            reversedShip.setLayoutY(GAME_HIGHT - 250);
        }
        reversedShip.setLayoutX(GAME_WIDTH / 2 - 75);
        gamePane.getChildren().addAll(reversedShip);
    }
    private void creatFarmer(FARMER choosenFarmer){
        idleFarmer = new Image(choosenFarmer.getUrl());
        deadFarmer = new Image(choosenFarmer.getDeadFarmerUrl());
        JumpFarmer = new Image(choosenFarmer.getJumpFarmerUrl());
        farmerImage = new ImageView(idleFarmer);
        farmerImage.setFitHeight(160);
        farmerImage.setFitWidth(150);
        farmerImage.setLayoutY(GAME_HIGHT - 250);
        farmerImage.setLayoutX(GAME_WIDTH-150);
        gamePane.getChildren().addAll(farmerImage);
    }
    private void creatHerbAnimal(HERBANIMAL choosenHerbAnimal){
        idleHerbAnimal = new Image(choosenHerbAnimal.getUrl());
        selectedHerbAnimal = new Image(choosenHerbAnimal.getUrlherbSelectedAnimal());
        deadHerbAnimal = new Image(choosenHerbAnimal.getUrlherbDeadAnimal());
        herbAnimalImage = new ImageView(idleHerbAnimal);
        herbAnimalImage.setFitHeight(100);
        herbAnimalImage.setFitWidth(100);
        if(storyNumber==1){
            herbAnimalImage.setLayoutY(farmerImage.getLayoutY());
            herbAnimalImage.setLayoutX(farmerImage.getLayoutX() - 100);
        }
        else if(storyNumber==2) {
            herbAnimalImage.setLayoutY(GAME_HIGHT - 320);
            herbAnimalImage.setLayoutX(GAME_WIDTH - 120);
        }
        gamePane.getChildren().addAll(herbAnimalImage);
    }
    private void creatCarnAnimal(CARNANIMAL choosenCarnAnimal){
        idleCarnAnimal = new Image(choosenCarnAnimal.getUrl());
        selectedCarnAnimal = new Image(choosenCarnAnimal.getUrlherbSelectedAnimal());
        deadCarnAnimal = new Image(choosenCarnAnimal.getUrlherbDeadAnimal());
        killCarnAnimal = new Image(choosenCarnAnimal.getUrlherbKillAnimal());
        carnAnimalImage = new ImageView(idleCarnAnimal);
        carnAnimalImage.setFitHeight(200);
        carnAnimalImage.setFitWidth(200);
        carnAnimalImage.setLayoutY(farmerImage.getLayoutY()-100);
        carnAnimalImage.setLayoutX(herbAnimalImage.getLayoutX() - 150);
        gamePane.getChildren().addAll(carnAnimalImage);
    }
    private void creatPlant(PLANTS choosenPlant){
        plant = new Image(choosenPlant.getUrl());
        eatenPlant = new Image(choosenPlant.getUrlEatenPlant());
        plantImage = new ImageView(plant);
        plantImage.setFitHeight(100);
        plantImage.setFitWidth(100);
        plantImage.setLayoutY(farmerImage.getLayoutY());
        plantImage.setLayoutX(carnAnimalImage.getLayoutX() - 100);
        gamePane.getChildren().addAll(plantImage);
    }
    private void creatGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveShip();
                moveBackground();
                if(storyNumber==1)
                    moveFarmer();
            }
        };
        gameTimer.start();
    }
    private void moveShip(){
        if(isLeftKeyPressed && !isRightKeyPressed){
            if(angle < 6){
                angle += 2;
            }
            reversedShip.setRotate(angle);
            if(reversedShip.getLayoutX() > 150){
                reversedShip.setLayoutX(reversedShip.getLayoutX() -3);
                endOfBank = false;
                oneTimeplease = true;
                if(farmerIsOnShip){
                    farmerImage.setLayoutX(farmerImage.getLayoutX() -3);
                    isFarmerOnTheLeftBank = false;
                }
            }else {
                isBoatOnTheLeftBank=false;
                endOfBank=true;
                if(oneTimeplease) {
                    gamePane.getChildren().remove(reversedShip);
                    gamePane.getChildren().add(ship);
                    ship.setLayoutX(150);
                    oneTimeplease = false;
                }
            }
        }if(!isLeftKeyPressed && isRightKeyPressed){
            if(angle > -6){
                angle -= 2;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() < 550){
                ship.setLayoutX(ship.getLayoutX() +3);
                oneTimeplease = true;
                endOfBank = false;
            }else {
                endOfBank=true;
                isBoatOnTheLeftBank=true;
                if(oneTimeplease) {
                    gamePane.getChildren().remove(ship);
                    gamePane.getChildren().add(reversedShip);
                    reversedShip.setLayoutX(550);
                    oneTimeplease = false;
                }
            }
        }if(endOfBank){
            angle =0;
            ship.setRotate(angle);
            reversedShip.setRotate(angle);
        }
    }
    private void FarmerClicked(){
        farmerImage.setOnMouseClicked(e->{
            System.out.println("yes!");
            if(isBoatOnTheLeftBank && isFarmerOnTheLeftBank || !isBoatOnTheLeftBank && !isFarmerOnTheLeftBank)
                isFarmerClicked=true;
            else
                isFarmerClicked=false;
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
        });

        farmerA.setOnMouseEntered(e-> {
            farmerA.setEffect(new DropShadow());
            farmerA.setImage(JumpFarmerA);
        });
        farmerA.setOnMouseExited(e-> {
            farmerA.setEffect(null);
            farmerA.setImage(idleFarmerA);
        });
    }
    private void farmerBclicked(){
        farmerB.setOnMouseClicked(e->{
        });

        farmerB.setOnMouseEntered(e-> {
            farmerB.setEffect(new DropShadow());
            farmerB.setImage(JumpFarmerB);
        });
        farmerB.setOnMouseExited(e-> {
            farmerB.setEffect(null);
            farmerB.setImage(idleFarmerB);
        });
    }
    private void farmerCclicked(){
        farmerC.setOnMouseClicked(e->{
        });

        farmerC.setOnMouseEntered(e-> {
            farmerC.setEffect(new DropShadow());
            farmerC.setImage(JumpFarmerC);
        });
        farmerC.setOnMouseExited(e-> {
            farmerC.setEffect(null);
            farmerC.setImage(idleFarmerC);
        });
    }
    private void farmerDclicked(){
        farmerD.setOnMouseClicked(e->{
        });

        farmerD.setOnMouseEntered(e-> {
            farmerD.setEffect(new DropShadow());
            farmerD.setImage(JumpFarmerD);
        });
        farmerD.setOnMouseExited(e-> {
            farmerD.setEffect(null);
            farmerD.setImage(idleFarmerD);
        });
    }
    private void herbAnimalClicked(){
        herbAnimalImage.setOnMouseClicked(e->{
        });

        herbAnimalImage.setOnMouseEntered(e-> {
            herbAnimalImage.setEffect(new DropShadow());
            herbAnimalImage.setImage(selectedHerbAnimal);
        });
        herbAnimalImage.setOnMouseExited(e-> {
            herbAnimalImage.setEffect(null);
            herbAnimalImage.setImage(idleHerbAnimal);
        });
    }
    private void carnAnimalClicked(){
        carnAnimalImage.setOnMouseClicked(e->{
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
        });

        plantImage.setOnMouseEntered(e-> {
            plantImage.setEffect(new DropShadow());
        });
        plantImage.setOnMouseExited(e-> {
            plantImage.setEffect(null);
        });
    }
    private void moveFarmer(){
        if(isFarmerClicked) {
            if (isBoatOnTheLeftBank && isFarmerOnTheLeftBank) {
                System.out.println("Yes I can ride");
                if (angle < 6) {
                    angle += 2;
                }
                farmerImage.setRotate(angle);
                if (farmerImage.getLayoutX() > reversedShip.getLayoutX()) {
                    farmerImage.setLayoutX(farmerImage.getLayoutX() - 3);
                }
                else {
                    farmerIsOnShip = true;
                }
            } else if (!isBoatOnTheLeftBank && !isFarmerOnTheLeftBank) {
                System.out.println("I can ride");
            }
//            else farmerIsOnShip=false;
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
        gamePane.getChildren().addAll(backgroundImage3,createButtonToStart());
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
}