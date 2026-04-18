import ui.PlannerGUI;
import javafx.application.Application;

/**
 * Entry point for the Smart Study Planner Application.
 * This class isolates the main method from the Application subclass
 * to avoid Module-related JavaFX launch issues.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(PlannerGUI.class, args);
    }
}
