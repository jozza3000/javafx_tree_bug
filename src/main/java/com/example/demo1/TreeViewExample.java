package com.example.demo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TreeViewExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Example");

        // Create the root node
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);


        // Create some tree items and add them to the root
        int i = 1;
        while (i <= 10) {
            var item1 = new TreeItem<>(("Item  " + i));
            root.getChildren().add(item1);
            i++;
        }

        // Create the TreeView
        TreeView<String> treeView = new TreeView<>(root);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        treeView.setCellFactory(param -> new TreeCell<>() {
            @Override
            public void updateItem(String text, boolean empty) {

                super.updateItem(text, empty);
                if (empty || text == null) {
                    setText(null);
                    setGraphic(null);
                } else {

                    var row = new VBox(new Label(text));
                    setGraphic(row);
                    setText(null);
                }
            }
        });

        // Add the TreeView to a StackPane
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(treeView);

        // Show the stage
        primaryStage.setScene(new Scene(rootPane, 300, 250));
        primaryStage.show();

        // set updates
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            int j = 1;
            while (j <= 10) {
                root.getChildren().get(j - 1).setValue(("Item" + j));
                j++;
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
}

