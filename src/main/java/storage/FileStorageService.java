package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.StudySession;
import model.Subject;
import model.Topic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading application data to a JSON file.
 */
public class FileStorageService {

    private static final String FILE_PATH = "data.json";
    private final Gson gson;

    private List<Subject> subjects;
    private List<Topic> topics;
    private List<StudySession> sessions;

    public FileStorageService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.subjects = new ArrayList<>();
        this.topics = new ArrayList<>();
        this.sessions = new ArrayList<>();
        loadData();
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<StudySession> getSessions() {
        return sessions;
    }

    public void addSubject(Subject subject) {
        if (!subjects.contains(subject)) {
            subjects.add(subject);
            saveData();
        }
    }

    public void addTopic(Topic topic) {
        if (!topics.contains(topic)) {
            topics.add(topic);
            saveData();
        }
    }

    public void addSession(StudySession session) {
        sessions.add(session);
        saveData();
    }

    public void markTopicCompleted(Topic topic) {
        for (Topic t : topics) {
            if (t.equals(topic)) {
                t.setCompleted(true);
                break;
            }
        }
        saveData();
    }

    private void saveData() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            AppData appData = new AppData(subjects, topics, sessions);
            gson.toJson(appData, writer);
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }

    private void loadData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                AppData appData = gson.fromJson(reader, AppData.class);
                if (appData != null) {
                    if (appData.subjects != null) this.subjects = appData.subjects;
                    if (appData.topics != null) this.topics = appData.topics;
                    if (appData.sessions != null) this.sessions = appData.sessions;
                }
            } catch (IOException e) {
                System.err.println("Failed to load data: " + e.getMessage());
            }
        }
    }

    /**
     * Inner class helper for JSON serialization.
     */
    private static class AppData {
        List<Subject> subjects;
        List<Topic> topics;
        List<StudySession> sessions;

        public AppData(List<Subject> subjects, List<Topic> topics, List<StudySession> sessions) {
            this.subjects = subjects;
            this.topics = topics;
            this.sessions = sessions;
        }
    }
}
