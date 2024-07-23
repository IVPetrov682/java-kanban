package test;


import logic.Counter;
import logic.ManagerStatus;
import logic.Status;
import logic.TypeTask;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TaskTest {

    static HashMap<Integer, ManagerStatus> numberTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика


    String testTask1 = "Тестовая задача 1";
    String testTask2 = "Тестовая задача 2";
    private Duration duration = null;
    private LocalDateTime startTime = null;

    public TaskTest() {
    }

    @Test
    void addNTestTask() {

        numberTask = new HashMap<>();
        number.increment(); //
        numberTask.put(number.getCount(), new ManagerStatus(TypeTask.TASK, "Изучить Java", Status.INPROGRESS, "Тестовая задача 1", duration, startTime, null));// сохранили в таблицу

        number.increment(); //
        numberTask.put(number.getCount(), new ManagerStatus(TypeTask.TASK, "Изучить Java", Status.INPROGRESS, "Тестовая задача 2", duration, startTime, null)); // сохранили в таблицу

        for (Integer keyPrint : numberTask.keySet()) {
            String taskPrint = numberTask.get(keyPrint).getDescription();
            System.out.println("Индификатор № " + keyPrint + ". " + taskPrint + ".");
            int notEror = 0; //переменная для проверки счетчика
            assertNotNull(keyPrint, " Счетчик работает не корректно"); // Проверка, что счетчик присвоил значение (число) отличное от нуля
            assertNotEquals(keyPrint, notEror, " Счетчик работает не корректно"); // Проверка, что счетчик присвоил значение (текст) отличное от нуля
        }
    }
}
