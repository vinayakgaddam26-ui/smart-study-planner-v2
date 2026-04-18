package service;

import model.Topic;
import storage.FileStorageService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service that acts as a mock-AI for recommending what and when to study.
 */
public class AISuggestionService {

    private final FileStorageService storageService;

    public AISuggestionService(FileStorageService storageService) {
        this.storageService = storageService;
    }

    public String generateNextStudySuggestion() {
        List<Topic> uncompleted = storageService.getTopics().stream()
                .filter(t -> !t.isCompleted())
                .collect(Collectors.toList());

        if (uncompleted.isEmpty()) {
            return "You have no pending topics! Great job! 🎉";
        }

        // Pick one that is relatively quick to get a dopamine boost
        Topic suggestion = uncompleted.stream()
                .min((t1, t2) -> Double.compare(t1.getEstimatedHours(), t2.getEstimatedHours()))
                .orElse(uncompleted.get(0));

        return "AI Suggestion: Tackle '" + suggestion.getTopicName() + 
               "' next. It only takes ~" + suggestion.getEstimatedHours() + 
               " hours and will give you a quick win.";
    }

    public String generateRevisionSuggestion() {
        List<Topic> completed = storageService.getTopics().stream()
                .filter(Topic::isCompleted)
                .collect(Collectors.toList());

        if (completed.isEmpty()) {
            return "No completed topics to revise yet. Keep studying! 📚";
        }

        // Suggest the first completed one since real "Spaced Repetition" needs detailed timestamps
        Topic toRevise = completed.get(0);
        return "AI Revision: You completed '" + toRevise.getTopicName() + 
               "' a while ago. Spend 15 minutes reviewing your notes today to enforce retention!";
    }

    public String generateTimeAllocationAdvice() {
        long highDifficultyCount = storageService.getSubjects().stream()
                .filter(s -> s.getDifficulty() == model.Subject.Difficulty.HIGH)
                .count();

        if (highDifficultyCount > 2) {
            return "AI Advice: You have many High Difficulty subjects. Use the Pomodoro Technique (25m study, 5m break) to avoid burnout.";
        } else {
            return "AI Advice: Your difficulty spread is balanced. Standard 50-minute blocks with 10-minute breaks should work well.";
        }
    }
}
