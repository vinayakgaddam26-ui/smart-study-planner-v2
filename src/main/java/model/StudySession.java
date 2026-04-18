package model;

/**
 * Represents a past or scheduled study session for a specific topic.
 */
public class StudySession {

    private String date; // Using simple String for dates (e.g. "YYYY-MM-DD") for easy serialization
    private String topicName;
    private double durationSpentHours;

    public StudySession() {}

    public StudySession(String date, String topicName, double durationSpentHours) {
        this.date = date;
        this.topicName = topicName;
        this.durationSpentHours = durationSpentHours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public double getDurationSpentHours() {
        return durationSpentHours;
    }

    public void setDurationSpentHours(double durationSpentHours) {
        this.durationSpentHours = durationSpentHours;
    }
}
