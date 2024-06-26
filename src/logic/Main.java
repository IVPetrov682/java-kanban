package logic;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InMemoryTaskManager managerTask = new InMemoryTaskManager(); // объявили объект класса
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(); // объявили объект класса
        Task.startVal(); // стартовые задачи из кода программы
        Epic.startVal(); // стартовые задачи из кода программы
        SubTask.startVal(); // стартовые задачи из кода программы
        AllTask.startVal(); // стартовые задачи из кода программы
        FileBackedTaskManager.createFileBackedTM();
        fileBackedTaskManager.taskFromString(); // стартовые задачи из файла
        Counter.getIntoCount(); // получаем значение счетчика

        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    String command1 = managerTask.printMainTask(); //String command1 нужна исключительно для выполнения ТЗ(надо что-то возвратить)
                    System.out.println(command1);
                    break;
                case "2":
                    fileBackedTaskManager.removeAllTaskFile();
                    break;
                case "3":
                    String command3 = managerTask.searchiD();
                    System.out.println(command3);
                    break;
                case "4":
                    fileBackedTaskManager.saveTaskFile();
                    break;

                case "5":
                    fileBackedTaskManager.codeUpTaskFile();
                    break;

                case "6":
                    fileBackedTaskManager.codeDelTaskFile();
                    break;

                case "7":
                    TreeSet<AllTask> command7 = managerTask.getPrioritizedTasks();
                    for (AllTask element : command7) {
                        System.out.println(element.getID() + " " + element.getType().toString() +  " " + element.getName() +  " " +
                        element.getStatus().toString() + " " + element.getDescription() + " " + element.getDuration() + " " + element.getStartTime() + " " +
                        element.getConnectioniD().toString());
                    }
                    break;

                case "8":
                    List<AllTask> command8 = managerTask.inMemoryHistoryManager.getHistory();
                    if (command8 != null) {
                        for (AllTask allTasktask : command8) {
                            System.out.println("Просмотренная задача с iD " + allTasktask.getID() + " " + allTasktask.getName()); /// ПРОБЛЕМА !!! Тут надо извлечь iD, name и вывести на печчать
                        }
                    }
                    break;

                case "9":
                    try {
                        fileBackedTaskManager.saveToFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Получить список всех задач");
        System.out.println("2 - Удалить список всех задач");
        System.out.println("3 - Получить задачу по индификатору");
        System.out.println("4 - Создать новую задачу");
        System.out.println("5 - Обновить существующую задачу по индификатору");
        System.out.println("6 - Удалить существующую задачу по индификатору");
        System.out.println("7 - Получение списка всех подзадач");
        System.out.println("8 - Показать историю просмотра задач");
        System.out.println("9 - Выход");
    }
}





