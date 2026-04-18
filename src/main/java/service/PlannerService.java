package service;

import model.Subject;
import model.Topic;
import storage.FileStorageService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service responsible for generating priority-based study plans.
 */
public class PlannerService {

    private final FileStorageService storageService;

    public PlannerService(FileStorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Enum used to define display priority.
     */
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    /**
     * Wrapper class for Topic that includes its calculated priority.
     */
    public static class PlannedTopic {
        public Topic topic;
        public Priority priority;

        public PlannedTopic(Topic topic, Priority priority) {
            this.topic = topic;
            this.priority = priority;
        }
    }

    /**
     * Generates a sorted study plan based on difficulty and estimated hours.
     */
    public List<PlannedTopic> generateStudyPlan() {
        List<PlannedTopic> plan = new ArrayList<>();
        List<Topic> currentTopics = storageService.getTopics();

        for (Topic topic : currentTopics) {
            if (!topic.isCompleted()) {
                Subject subject = getSubjectByName(topic.getSubjectName());
                Priority priority = calculatePriority(topic, subject);
                plan.add(new PlannedTopic(topic, priority));
            }
        }

        // Sort by priority (HIGH first, then MEDIUM, then LOW)
        plan.sort(Comparator.comparing((PlannedTopic pt) -> pt.priority));
        return plan;
    }

    private Priority calculatePriority(Topic topic, Subject subject) {
        if (subject == null) return Priority.LOW;

        // Rule based prioritization
        if (subject.getDifficulty() == Subject.Difficulty.HIGH || topic.getEstimatedHours() > 4.0) {
            return Priority.HIGH;
        } else if (subject.getDifficulty() == Subject.Difficulty.MEDIUM) {
            return Priority.MEDIUM;
        } else {
            return Priority.LOW;
        }
    }

    private Subject getSubjectByName(String name) {
        for (Subject s : storageService.getSubjects()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
