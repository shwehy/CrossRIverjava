package sample;

import Logic.ReadFile_XML;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.ViewManger;
import javafx.scene.image.Image;
import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManger manger = ViewManger.getInstance();
        primaryStage = manger.getMainStage();
//        ReadFile_XML c =new ReadFile_XML();
//        c.Read();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        Image icon = new Image("model/resources/icon.png");
        primaryStage.getIcons().add(icon);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
