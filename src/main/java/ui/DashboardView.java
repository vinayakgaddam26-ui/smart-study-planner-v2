package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import storage.FileStorageService;

/**
 * Dashboard UI showing overall statistics.
 */
public class DashboardView extends VBox {

    public DashboardView(FileStorageService storageService) {
        setSpacing(20);
        setPadding(new Insets(40));
        setAlignment(Pos.TOP_CENTER);
        
        Label welcomeLabel = new Label("Welcome to Smart Study Planner");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        long subjectCount = storageService.getSubjects().size();
        Label subjectLabel = new Label("Total Subjects: " + subjectCount);
        subjectLabel.setFont(Font.font("Arial", 16));
        
        long topicCount = storageService.getTopics().size();
        Label topicLabel = new Label("Total Topics: " + topicCount);
        topicLabel.setFont(Font.font("Arial", 16));
        
        long completedTopics = storageService.getTopics().stream().filter(model.Topic::isCompleted).count();
        double progress = topicCount > 0 ? (double) completedTopics / topicCount : 0.0;
        
        Label progressText = new Label("Overall Progress:");
        progressText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        ProgressBar progressBar = new ProgressBar(progress);
        progressBar.setPrefWidth(300);
        progressBar.setPrefHeight(25);
        progressBar.setStyle("-fx-accent: #2e8b57;");

        Label metricsLabel = new Label(completedTopics + " out of " + topicCount + " topics completed.");

        getChildren().addAll(welcomeLabel, subjectLabel, topicLabel, progressText, progressBar, metricsLabel);
    }
}
