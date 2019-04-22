package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {
    private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
    private final String BACKGROUND_IMAGE = "view/resources/green_button06.png";


    public InfoLabel(String text){
        setPrefHeight(49);
        setPrefWidth(380);
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,380,49,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
    }
    private void setLabelFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        }catch (FileNotFoundException e){
            setFont(Font.font("verdana",23));
        }
    }
}
