package logic;

import java.util.HashMap;

public class Task {
    static HashMap<Integer, ManagerStatus> numberTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика
    private int codeTask;
    private String codeTaskName;


    public Task(int codeTask, String codeTaskName) {
        this.codeTask= codeTask;
        this.codeTaskName= codeTaskName;
    }

    public static void startVal() {
        // с второстепенными задачами
        numberTask = new HashMap<>();
        number.increment(); // увеличили счетчик на 1
        numberTask.put(number.getCount(), new ManagerStatus("Отдохнуть", Status.INPROGRESS)); // сохранили в таблицу

        // без второстепенных задач
        number.increment(); // увеличили счетчик на 1
        numberTask.put(number.getCount(), new ManagerStatus("Посмотреть сериал звезды в Африке", Status.INPROGRESS)); // сохранили в таблицу


        number.increment(); // увеличили счетчик на 3
        numberTask.put(5, new ManagerStatus("Попить кофе", Status.INPROGRESS)); // сохранили в таблицу // вызвали ошибку. Задачи 1, 2, 5. По счетчику 3
    }


    public int getCodeTask() {
        return codeTask;
    }
    public String getCodeTaskName() {
        return codeTaskName;
    }
}
