package logic;

import java.util.List;

public class ManagerStatus {
    private TypeTask type;
    private String name;
    private Status status;
    private String description;
    private List<Integer> connectioniD;

    //type,name,status,description, ConnectioniD - формат сборки/разборки

    public ManagerStatus(TypeTask type, String name, Status status, String description, List<Integer> connectioniD) { // добавьте конструктор класса
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectioniD = connectioniD;
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
}
