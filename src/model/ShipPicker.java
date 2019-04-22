package model;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShipPicker extends VBox {
    private ImageView circleImage, shipImage;
    private String circileNotChoosen = "view/resources/shipChooser/grey_circle.png";
    private String circileChoosen = "view/resources/shipChooser/green_boxTick.png";
    private static int i=0;
    private SHIP ship;
    private boolean isCircleChoosen;

    public ShipPicker(SHIP ship){
        circleImage = new ImageView(circileNotChoosen);
        shipImage = new ImageView(ship.getUrl());
        if(i==0) {
            shipImage.setFitHeight(105);
            shipImage.setFitWidth(70);
            this.setSpacing(55);
            i++;
        }
        else{
            shipImage.setFitHeight(140);
            shipImage.setFitWidth(120);
            this.setSpacing(20);
        }
        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }
    public SHIP getShip(){
        return ship;
    }
    public boolean getisCircleChoosen(){
        return isCircleChoosen;
    }
    public void setIsCircleChoosen(boolean isCircleChoosen){
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen? circileChoosen:circileNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }
}
