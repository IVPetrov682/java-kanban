package logic;

public class ManagerStatus {
    private String description;
    private Status priority;

    public ManagerStatus(String description, Status priority) { // добавьте конструктор класса
        this.description = description;
        this.priority = priority;
    }

    public Status getPriority() { // добавьте метод get для приоритета
        return priority;
    }

    public String getDescription() {
        return description;
    }
}