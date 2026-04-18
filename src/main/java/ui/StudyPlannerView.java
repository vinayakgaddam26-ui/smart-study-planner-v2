package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Topic;
import service.PlannerService;
import storage.FileStorageService;

/**
 * UI displaying the prioritized study plan.
 */
public class StudyPlannerView extends VBox {

    public StudyPlannerView(PlannerService plannerService, FileStorageService storageService, Runnable onDataChanged) {
        setSpacing(15);
        setPadding(new Insets(20));

        Label title = new Label("Optimized Study Plan");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TableView<PlannerService.PlannedTopic> table = new TableView<>();
        
        TableColumn<PlannerService.PlannedTopic, String> topicCol = new TableColumn<>("Topic");
        topicCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().topic.getTopicName()));
        topicCol.setPrefWidth(200);

        TableColumn<PlannerService.PlannedTopic, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().topic.getSubjectName()));
        subjectCol.setPrefWidth(150);

        TableColumn<PlannerService.PlannedTopic, String> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().priority.name()));
        priorityCol.setPrefWidth(100);

        TableColumn<PlannerService.PlannedTopic, String> hoursCol = new TableColumn<>("Est. Hours");
        hoursCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().topic.getEstimatedHours())));
        hoursCol.setPrefWidth(100);

        TableColumn<PlannerService.PlannedTopic, Void> actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(120);
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button completeBtn = new Button("Mark Done");

            {
                completeBtn.setStyle("-fx-background-color: #10b981; -fx-text-fill: white;");
                completeBtn.setOnAction(event -> {
                    PlannerService.PlannedTopic pt = getTableView().getItems().get(getIndex());
                    storageService.markTopicCompleted(pt.topic);
                    if (onDataChanged != null) onDataChanged.run();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(completeBtn);
                }
            }
        });

        table.getColumns().addAll(topicCol, subjectCol, priorityCol, hoursCol, actionCol);
        table.setItems(FXCollections.observableArrayList(plannerService.generateStudyPlan()));

        // Color coding rows based on priority
        table.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(PlannerService.PlannedTopic item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    switch (item.priority) {
                        case HIGH:
                            setStyle("-fx-background-color: #fee2e2;"); // light red
                            break;
                        case MEDIUM:
                            setStyle("-fx-background-color: #ffedd5;"); // light orange
                            break;
                        case LOW:
                            setStyle("-fx-background-color: #dcfce7;"); // light green
                            break;
                    }
                }
            }
        });

        getChildren().addAll(title, table);
    }
}
