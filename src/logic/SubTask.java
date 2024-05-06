package logic;

import java.util.HashMap;
import java.util.List;

public class SubTask {
    static HashMap<Integer, ManagerStatus> numberSubTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика
    private int ID;
    private TypeTask type;
    private String name;
    private Status status;
    private String description;
    private List<Integer> connectionID;


    public SubTask(int ID, TypeTask type, String name, Status status, String description, List<Integer> connectionID) {
        this.ID = ID;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectionID = connectionID;
    }

    public static void startVal() {
        // с второстепенными задачами
        numberSubTask = new HashMap<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
