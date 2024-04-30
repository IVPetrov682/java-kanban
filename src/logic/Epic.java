package logic;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic {
    HashMap<Integer, ArrayList<ManagerStatus>> subTask; // создали таблицу индификатор/субзадача

    Epic() { // Метод по созданию стартой таблицы и ее заполнению стартовыми значениями
        subTask = new HashMap<>(); // создали объект таблицы

        ArrayList<ManagerStatus> miniTask = new ArrayList<>(); // создали лист таблицы
        miniTask.add(new ManagerStatus("Второстепенная задача 1", Status.INPROGRESS));
        miniTask.add(new ManagerStatus("Второстепенная задача 2", Status.DONE));
        subTask.put(1, miniTask);
    }
}

