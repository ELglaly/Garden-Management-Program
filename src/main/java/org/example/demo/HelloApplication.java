package org.example.demo;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("My Window");
        BorderPane myPanel = new BorderPane();
        Canvas myCanvas = new Canvas(600, 400);
        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        Color c = Color.web("94300f");
        gc.setFill(c);
        gc.fillRect(10, 10, 10, 10);

        TextField userEntry = new TextField();
        userEntry.setPromptText("Enter your command:");
        userEntry.getText();


        myPanel.setCenter(myCanvas);

        GridPane myGrid = new GridPane();
        myGrid.setPadding(new Insets(10, 10, 10, 10));
        myPanel.setTop(myGrid);
        myGrid.getChildren().add(userEntry);

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        myGrid.getChildren().add(submit);

        scene = new Scene(myPanel, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}