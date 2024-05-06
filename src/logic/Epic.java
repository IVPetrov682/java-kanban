package logic;

import java.util.HashMap;
import java.util.List;

public class Epic {
    static HashMap<Integer, ManagerStatus> numberEpic; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика
    private int ID;
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectionID;

    private String name;

    public Epic(int ID, TypeTask type, String name, Status status, String description, List<Integer> connectionID) {
        this.ID = ID;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectionID = connectionID;
    }

    public static void startVal() {
        // с второстепенными задачами
        numberEpic = new HashMap<>();
        //    number.increment(); // увеличили счетчик на 1
        //    numberTask.put(number.getCount(), new ManagerStatus(TypeTask.TASK, "Изучить Java", Status.INPROGRESS, null, null));

        // без второстепенных задач
        //    number.increment(); // увеличили счетчик на 1
        //    numberTask.put(number.getCount(), new ManagerStatus(TypeTask.TASK, "Сходить в спорот зал", Status.INPROGRESS, null, null));


        //    number.increment(); // увеличили счетчик на 1
        //    numberTask.put(5, new ManagerStatus(TypeTask.TASK, "Убраться дома", Status.INPROGRESS, null, null));
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
