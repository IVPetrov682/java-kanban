package test.src;


import logic.Counter;
import logic.ManagerStatus;
import logic.Status;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TaskTest {

    static HashMap<Integer, ManagerStatus> numberTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика


    String testTask1 = "Тестовая задача 1";
    String testTask2 = "Тестовая задача 2";
    public TaskTest() {
    }

    @Test
    void addNTestTask() {

        numberTask = new HashMap<>();
        number.increment(); //
        numberTask.put(number.getCount(), new ManagerStatus(testTask1, Status.INPROGRESS)); // сохранили в таблицу

        number.increment(); //
        numberTask.put(number.getCount(), new ManagerStatus(testTask2, Status.INPROGRESS)); // сохранили в таблицу

            for (Integer keyPrint : numberTask.keySet()) {
                String taskPrint = numberTask.get(keyPrint).getDescription();
                System.out.println("Индификатор № " + keyPrint + ". " + taskPrint + ".");
                int notEror = 0; // переменная для проверки счетчика
                assertNotNull(keyPrint, "Счетчик работает не корректно");
                assertNotEquals(keyPrint, notEror, "Счетчик работает не корректно");
            }
    }
}