package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CrossRiverButton extends Button {
    private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: " +
            "url('model/resources/green_button01.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: " +
            "url('model/resources/green_button00.png');";

    public CrossRiverButton(String text){
        setText(text);
        setButtonFont();
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(BUTTON_FREE_STYLE);
        initButtonListeners();
    }
    private void setButtonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        }catch (FileNotFoundException e){
            setFont(Font.font("verdana",23));
        }
    }
    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }
    private void setButtonReleasedStyle(){
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 4);
    }

    private void initButtonListeners(){

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });


        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonReleasedStyle();
                }
            }
        });


        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
