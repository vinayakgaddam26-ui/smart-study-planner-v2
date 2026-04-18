package model;

import java.util.Objects;

/**
 * Represents a study topic belonging to a specific subject.
 */
public class Topic {

    private String topicName;
    private String subjectName;
    private double estimatedHours;
    private boolean isCompleted;

    public Topic() {}

    public Topic(String topicName, String subjectName, double estimatedHours) {
        this.topicName = topicName;
        this.subjectName = subjectName;
        this.estimatedHours = estimatedHours;
        this.isCompleted = false;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(topicName, topic.topicName) && Objects.equals(subjectName, topic.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName, subjectName);
    }

    @Override
    public String toString() {
        return topicName + " [" + subjectName + "] - " + estimatedHours + "h";
    }
}
