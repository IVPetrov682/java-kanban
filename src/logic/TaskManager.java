package logic;

import java.util.TreeSet;

public interface TaskManager {

    public String printMainTask();

    public String saveTask();

    public String removeAllTask();

    public String searchiD();

    public String codeUpTask(); // метод сохранения задачи

    public String codeDelTask();

    TreeSet<AllTask> getPrioritizedTasks();

}


