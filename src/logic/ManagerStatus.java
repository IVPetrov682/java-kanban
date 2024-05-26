package logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ManagerStatus {
    private TypeTask type;
    private String name;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    private Duration duration; //private Duration duration = Duration.ofMinutes(10);
    private LocalDateTime startTime;

    public ManagerStatus(TypeTask type, String name, Status status, String description, Duration duration, LocalDateTime startTime, List<Integer> connectioniD) { // добавьте конструктор класса
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectioniD = connectioniD;
        this.duration = duration;
        this.startTime = startTime;
    }

    public TypeTask getTypeTask() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() { // добавьте метод get для приоритета
        return status;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getСonnectioniD() {
        return connectioniD;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }
}
