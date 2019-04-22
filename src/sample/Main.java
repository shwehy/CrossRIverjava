package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewManger manger = new ViewManger();
        primaryStage = manger.getMainStage();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
