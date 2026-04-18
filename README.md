# Smart Study Planner (Version 2)

A complete, modern desktop application built with Java and JavaFX for planning study sessions, keeping track of topics, and getting AI-driven study suggestions.

## Features
- **Dashboard**: Track overall progress and number of subjects.
- **Subject Management**: Add subjects with customized difficulty levels (Low, Medium, High).
- **Topic Management**: Add topics and estimate time required for completion.
- **Study Planner**: A prioritized, color-coded study plan ensuring optimal focus.
- **AI Suggestions**: Intelligent scheduling to know exactly what to study next.
- **Local JSON Storage**: Automatically saves state across sessions using Gson.

## Tech Stack
- **Language**: Java 17
- **UI Framework**: JavaFX (Pure Java, no FXML)
- **Build Tool**: Maven
- **File Storage**: JSON (Gson)

## Folder Structure
```text
src/main/java/
    ├── model/        (Core data objects)
    ├── service/      (Business logic and AI suggestions)
    ├── storage/      (File operations)
    ├── ui/           (JavaFX components)
    └── Main.java     (Entry point)
```

## How to Run

1. Open the project folder in **IntelliJ IDEA**.
2. Assuming Maven has downloaded all dependencies, right-click `Main.java` inside `src/main/java` and select **Run 'Main.main()'**.
3. (Optional) Run from terminal using Maven:
   ```bash
   mvn clean javafx:run
   ```

## Screenshots
*(Add your application screenshots here once running)*

---
*Built as a portfolio project showcasing Object-Oriented design, JavaFX pure-code UI, and File IO.*
