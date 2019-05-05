package model;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ShipPicker extends VBox {
    private ImageView circleImage, shipImage,farmerImage,storyImage,herbImage,carnImage, plantsImage,loadImage;
    private String circileNotChoosen = "view/resources/shipChooser/grey_circle.png";
    private String circileChoosen = "view/resources/shipChooser/green_boxTick.png";
    private static int i=0;
    private STORY story;
    private SHIP ship;
    private FARMER farmer;
    private HERBANIMAL herbanimal;
    private CARNANIMAL carnanimal;
    private PLANTS plants;
    private LOAD load;
    private Label name;
    private boolean isCircleChoosen;

    public ShipPicker(LOAD load, STORY story, SHIP ship, int Case, FARMER farmer, HERBANIMAL herbanimal, CARNANIMAL carnanimal, PLANTS plants){
        circleImage = new ImageView(circileNotChoosen);
        isCircleChoosen = false;
        if(Case==0){
            storyImage = new ImageView(story.getUrl());
            storyImage.setFitHeight(150);
            storyImage.setFitWidth(150);
            this.setSpacing(20);
            this.story = story;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().add(storyImage);
        }
        else if(Case==1) {
            shipImage = new ImageView(ship.getUrl());
            if (i == 0) {
                shipImage.setFitHeight(105);
                shipImage.setFitWidth(70);
                this.setSpacing(55);
                i++;
            } else {
                shipImage.setFitHeight(140);
                shipImage.setFitWidth(120);
                this.setSpacing(20);
            }
            this.ship = ship;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().add(shipImage);
        }
        else if(Case==2){
            farmerImage = new ImageView(farmer.getUrl());
             farmerImage.setFitHeight(150);
             farmerImage.setFitWidth(140);
             this.setSpacing(30);
            this.farmer = farmer;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().add(farmerImage);
        }
        else if(Case==3){
            herbImage = new ImageView(herbanimal.getUrl());
            herbImage.setFitHeight(150);
            herbImage.setFitWidth(150);
            this.setSpacing(20);
            this.herbanimal = herbanimal;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().add(herbImage);
        }
        else if(Case==4){
            carnImage = new ImageView(carnanimal.getUrl());
            carnImage.setFitHeight(150);
            carnImage.setFitWidth(150);
            this.setSpacing(20);
            this.carnanimal = carnanimal;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().add(carnImage);
        }
        else if(Case==5){
            plantsImage = new ImageView(plants.getUrl());
            plantsImage.setFitHeight(150);
            plantsImage.setFitWidth(150);
            this.setSpacing(20);
            this.plants = plants;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().add(plantsImage);
        }
        else if(Case==6){
            loadImage = new ImageView(load.getUrl());
            loadImage.setFitHeight(130);
            loadImage.setFitWidth(130);
            name = new Label(load.getName());
            name.setStyle("-fx-text-fill: #000;-fx-font-size: 26px; font-weight: bold");
            this.setSpacing(10);
            this.load = load;
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(circleImage);
            this.getChildren().addAll(loadImage, name);
        }
    }
    public SHIP getShip(){
        return ship;
    }
    public FARMER getFarmer(){
        return farmer;
    }
    public STORY getStory(){
        return story;
    }
    public HERBANIMAL getHerbAnimal(){
        return herbanimal;
    }
    public CARNANIMAL getCarnAnimal(){
        return carnanimal;
    }
    public PLANTS getPlants(){
        return plants;
    }
    public LOAD getLoad(){ return load;}

    public boolean getisCircleChoosen(){
        return isCircleChoosen;
    }
    public void setIsCircleChoosen(boolean isCircleChoosen){
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen? circileChoosen:circileNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }
}
