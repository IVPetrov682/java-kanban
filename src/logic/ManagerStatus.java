package logic;

import java.util.List;

public class ManagerStatus {
    private TypeTask type;
    private String name;
    private Status status;
    private String description;
    private List<Integer> connectionID;

    //type,name,status,description, ConnectionID - формат сборки/разборки

    public ManagerStatus(TypeTask type, String name, Status status, String description, List<Integer> connectionID) { // добавьте конструктор класса
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectionID = connectionID;
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

    public List<Integer> getСonnectionID() {
        return connectionID;
    }
}
