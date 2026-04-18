package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import service.AISuggestionService;

/**
 * UI displaying intelligent study suggestions.
 */
public class AISuggestionView extends VBox {

    public AISuggestionView(AISuggestionService aiService) {
        setSpacing(20);
        setPadding(new Insets(30));

        Label title = new Label("AI Study Suggestions panel");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        VBox suggestionBox1 = createCard("What to study next?", aiService.generateNextStudySuggestion(), "#e0f2fe", "#0284c7");
        VBox suggestionBox2 = createCard("Revision Plan", aiService.generateRevisionSuggestion(), "#fef9c3", "#ca8a04");
        VBox suggestionBox3 = createCard("Time Management", aiService.generateTimeAllocationAdvice(), "#f3e8ff", "#9333ea");

        getChildren().addAll(title, suggestionBox1, suggestionBox2, suggestionBox3);
    }

    private VBox createCard(String headerText, String contentText, String bgColor, String borderColor) {
        VBox card = new VBox();
        card.setSpacing(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-border-color: " + borderColor + "; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-width: 2;");
        
        Label lblHeader = new Label(headerText);
        lblHeader.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblHeader.setStyle("-fx-text-fill: " + borderColor + ";");

        Label lblContent = new Label(contentText);
        lblContent.setFont(Font.font("Arial", 14));
        lblContent.setWrapText(true);

        card.getChildren().addAll(lblHeader, lblContent);
        return card;
    }
}
