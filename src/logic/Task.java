package logic;

import java.util.HashMap;
import java.util.List;

public class Task {
    static HashMap<Integer, ManagerStatus> numberTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    private int iD;
    private String name;

    public Task(int iD, String name) {
        this.iD = iD;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectioniD = connectioniD;
    }

    public static void startVal() {
        numberTask = new HashMap<>();
    }

    public int getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }
}
