package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controllers.SelectScreenController;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/ModuleSelectScreen.fxml"));
        Parent root = loader.load();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        primaryStage.setTitle("KTANE");
        primaryStage.setScene(new Scene(root, screenSize.width * (0.50), screenSize.height * (0.93)));
        primaryStage.setResizable(false);
        primaryStage.show();

        SelectScreenController controller = loader.getController();
        controller.setStageAndInitialize(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
