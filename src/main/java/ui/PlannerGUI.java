package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.AISuggestionService;
import service.PlannerService;
import storage.FileStorageService;

/**
 * Main graphical wrapper configuring the BorderPane layout and sidebar navigation.
 */
public class PlannerGUI extends Application {

    private FileStorageService storageService;
    private PlannerService plannerService;
    private AISuggestionService aiSuggestionService;

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        // Initialize Core Services
        storageService = new FileStorageService();
        plannerService = new PlannerService(storageService);
        aiSuggestionService = new AISuggestionService(storageService);

        root = new BorderPane();
        
        // Sidebar Content
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(20));
        sidebar.setSpacing(15);
        sidebar.setStyle("-fx-background-color: #1e293b;");
        sidebar.setPrefWidth(200);

        Button btnDashboard = createNavButton("Dashboard");
        Button btnAddSubject = createNavButton("Add Subject");
        Button btnAddTopic = createNavButton("Add Topic");
        Button btnPlanner = createNavButton("Study Planner");
        Button btnAI = createNavButton("AI Suggestions");

        sidebar.getChildren().addAll(btnDashboard, btnAddSubject, btnAddTopic, btnPlanner, btnAI);
        root.setLeft(sidebar);

        // Wiring standard navigation
        btnDashboard.setOnAction(e -> navigateToDashboard());
        btnAddSubject.setOnAction(e -> root.setCenter(new AddSubjectView(storageService, this::navigateToDashboard)));
        btnAddTopic.setOnAction(e -> root.setCenter(new AddTopicView(storageService, this::navigateToPlanner)));
        btnPlanner.setOnAction(e -> navigateToPlanner());
        btnAI.setOnAction(e -> root.setCenter(new AISuggestionView(aiSuggestionService)));

        // Default view
        navigateToDashboard();

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Smart Study Planner Version 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void navigateToDashboard() {
        root.setCenter(new DashboardView(storageService));
    }

    private void navigateToPlanner() {
        root.setCenter(new StudyPlannerView(plannerService, storageService, this::navigateToPlanner));
    }

    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: center-left; -fx-font-size: 14px;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #334155; -fx-text-fill: white; -fx-alignment: center-left; -fx-font-size: 14px;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: center-left; -fx-font-size: 14px;"));
        return btn;
    }
}
