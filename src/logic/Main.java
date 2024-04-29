package logic;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
        InMemoryTaskManager managertask = new InMemoryTaskManager(); // объявили объект класса и запустили метод по заполнению стартовых задач
        Task.startVal();

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    String command1 = managertask.printMainTask(); //String command1 нужна исключительно для выполнения ТЗ(надо что-то возвратить)
                    System.out.println(command1);
                    break;

                case "2":
                    String command2  = managertask.removeAllTask();
                    System.out.println(command2);
                    break;

                case "3":
                    String command3 = managertask.searchCodeTask();
                    System.out.println(command3);
                    break;

                case "4":

                    String command4 = managertask.saveTask();
                    System.out.println(command4);
                    break;

                case "5":
                    String command5 = managertask.codeUpTask();
                    System.out.println(command5);
                    break;

                case "6":
                    String command6 = managertask.codeDelTask();
                    System.out.println(command6);
                    break;

                case "7":
                    String command7 = managertask.printAllTask();
                    System.out.println(command7);
                    break;

                case "8":
                    List<Task> command8 = managertask.inMemoryHistoryManager.getHistory(); //Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()
                    if (command8 != null) {
                        for (Task task : command8) {
                            System.out.println("Просмотренная задача с ID " + task.getCodeTask() + " " + task.getCodeTaskName()); /// ПРОБЛЕМА !!! Тут надо извлечь codeTask, codeTaskName и вывести на печчать
                        }
                    }
                    break;

                case "9":
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





