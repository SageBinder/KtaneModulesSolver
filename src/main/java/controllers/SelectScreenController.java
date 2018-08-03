package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SelectScreenController {
    private String[] modules = {
            "Wires",
            "The Button",
            "Keypads",
            "Simon Says",
            "Who's on First",
            "Memory",
            "Morse Code",
            "Complicated Wires",
            "Wire Sequences",
            "Mazes",
            "Passwords",
            "Knobs",
    };
    private Stage stage;

    @FXML
    GridPane gridPane;

    @FXML
    private VBox listViewVBox;
    @FXML
    private ScrollPane listViewScrollPane;
    @FXML
    private ListView<String> modulesListView;

    @FXML
    VBox imageVBox;

    @FXML
    private Pane imageViewPane;
    @FXML
    private ImageView imageView;

    @FXML
    private HBox imageHBox;
    @FXML
    private Button startModuleButton;
    @FXML
    private Button previousPageButton;
    @FXML
    private Button nextPageButton;

    private ArrayList<Image> loadedManualImages = new ArrayList<>();
    private int loadedImageIndex;

    public void setStageAndInitialize(Stage stage) {
        this.stage = stage;

        listViewScrollPane.setPrefHeight(listViewVBox.getHeight());
        listViewScrollPane.setMinWidth(110);

        modulesListView.setItems(FXCollections.observableArrayList(Arrays.asList(modules)));

        modulesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadManualPages(newValue);
            imageView.setImage(loadedManualImages.get(0));
            loadedImageIndex = 0;

            previousPageButton.setVisible(false);
            if(loadedManualImages.size() > 1) {
                nextPageButton.setVisible(true);
            } else {
                nextPageButton.setVisible(false);
            }
        });

        imageViewPane.setPrefSize(imageVBox.getWidth() - 10, imageVBox.getHeight());
        imageView.fitWidthProperty().bind(imageViewPane.widthProperty());
        imageView.fitHeightProperty().bind(imageViewPane.heightProperty());

        imageHBox.setMinSize(imageVBox.getWidth() - 10, imageVBox.getHeight() / 13.0);

        startModuleButton.setPrefHeight(imageHBox.getHeight() * 0.27);

        nextPageButton.setPrefHeight(imageHBox.getHeight() * 0.27);
        previousPageButton.setPrefHeight(imageHBox.getHeight() * 0.27);

        previousPageButton.setVisible(false);
        nextPageButton.setVisible(false);
    }

    @FXML
    public void nextPage(Event e) {
        if(loadedImageIndex == loadedManualImages.size() - 1) {
            return;
        }

        loadedImageIndex++;
        imageView.setImage(loadedManualImages.get(loadedImageIndex));

        previousPageButton.setVisible(true);

        if(loadedImageIndex == loadedManualImages.size() - 1) {
            nextPageButton.setVisible(false);
        }
    }

    @FXML
    public void previousPage(Event e) {
        if(loadedImageIndex == 0) {
            return;
        }

        loadedImageIndex--;
        imageView.setImage(loadedManualImages.get(loadedImageIndex));

        nextPageButton.setVisible(true);

        if(loadedImageIndex == 0) {
            previousPageButton.setVisible(false);
        }
    }

    private void loadManualPages(String moduleName) {
        loadedManualImages.clear();

        File folder = new File("src/main/resources/images/manualpages/" + (moduleName.replaceAll("[^a-zA-Z]", "").toLowerCase()));
        for(File image : Objects.requireNonNull(folder.listFiles())) {
            loadedManualImages.add(new Image(image.toURI().toString()));
        }
    }
}
