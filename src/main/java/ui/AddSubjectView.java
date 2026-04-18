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
import storage.FileStorageService;

/**
 * UI for adding a new Subject.
 */
public class AddSubjectView extends VBox {

    public AddSubjectView(FileStorageService storageService, Runnable onSubjectAdded) {
        setSpacing(20);
        setPadding(new Insets(30));

        Label title = new Label("Add New Subject");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);

        Label nameLabel = new Label("Subject Name:");
        TextField nameField = new TextField();

        Label diffLabel = new Label("Difficulty Level:");
        ComboBox<Subject.Difficulty> diffCombo = new ComboBox<>(FXCollections.observableArrayList(Subject.Difficulty.values()));
        diffCombo.getSelectionModel().select(Subject.Difficulty.MEDIUM);

        Button saveButton = new Button("Save Subject");
        saveButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold;");

        Label statusLabel = new Label();

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Please enter a subject name.");
                statusLabel.setTextFill(Color.RED);
                return;
            }
            Subject.Difficulty diff = diffCombo.getValue();
            storageService.addSubject(new Subject(name, diff));
            statusLabel.setText("Subject '" + name + "' saved successfully!");
            statusLabel.setTextFill(Color.GREEN);
            nameField.clear();
            
            if (onSubjectAdded != null) onSubjectAdded.run();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(diffLabel, 0, 1);
        grid.add(diffCombo, 1, 1);
        grid.add(saveButton, 1, 2);
        grid.add(statusLabel, 1, 3);

        getChildren().addAll(title, grid);
    }
}
