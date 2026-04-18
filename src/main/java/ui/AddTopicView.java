package ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Subject;
import model.Topic;
import storage.FileStorageService;

import java.util.stream.Collectors;

/**
 * UI for adding a new Topic.
 */
public class AddTopicView extends VBox {

    public AddTopicView(FileStorageService storageService, Runnable onTopicAdded) {
        setSpacing(20);
        setPadding(new Insets(30));

        Label title = new Label("Add New Topic");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);

        Label subLabel = new Label("Select Subject:");
        ComboBox<String> subCombo = new ComboBox<>(FXCollections.observableArrayList(
                storageService.getSubjects().stream().map(Subject::getName).collect(Collectors.toList())
        ));

        Label topicLabel = new Label("Topic Name:");
        TextField topicField = new TextField();

        Label hrsLabel = new Label("Estimated Hours:");
        TextField hrsField = new TextField();
        hrsField.setPromptText("e.g. 2.5");

        Button saveButton = new Button("Save Topic");
        saveButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-font-weight: bold;");

        Label statusLabel = new Label();

        saveButton.setOnAction(e -> {
            String subject = subCombo.getValue();
            String topicName = topicField.getText().trim();
            String hrsText = hrsField.getText().trim();

            if (subject == null || topicName.isEmpty() || hrsText.isEmpty()) {
                statusLabel.setText("All fields are required.");
                statusLabel.setTextFill(Color.RED);
                return;
            }

            try {
                double hrs = Double.parseDouble(hrsText);
                storageService.addTopic(new Topic(topicName, subject, hrs));
                statusLabel.setText("Topic saved successfully!");
                statusLabel.setTextFill(Color.GREEN);
                topicField.clear();
                hrsField.clear();
                
                if (onTopicAdded != null) onTopicAdded.run();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Estimated hours must be a valid number.");
                statusLabel.setTextFill(Color.RED);
            }
        });

        grid.add(subLabel, 0, 0);
        grid.add(subCombo, 1, 0);
        grid.add(topicLabel, 0, 1);
        grid.add(topicField, 1, 1);
        grid.add(hrsLabel, 0, 2);
        grid.add(hrsField, 1, 2);
        grid.add(saveButton, 1, 3);
        grid.add(statusLabel, 1, 4);

        getChildren().addAll(title, grid);
    }
}
