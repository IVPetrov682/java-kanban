package logic;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InMemoryTaskManager managerTask = new InMemoryTaskManager(); // объявили объект класса
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(); // объявили объект класса
        Task.startVal(); // стартовые задачи из кода программы
        Epic.startVal(); // стартовые задачи из кода программы
        SubTask.startVal(); // стартовые задачи из кода программы
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
                    //String command2 = managerTask.removeAllTask(); // Работает! Строки 34 и 35 дублируют работу 36 строкой. 36 строка с сохранением в файл
                    //System.out.println(command2);
                    fileBackedTaskManager.removeAllTaskFile();
                    break;

                case "3":
                    String command3 = managerTask.searchiD();
                    System.out.println(command3);
                    break;

                case "4":

                    //String command4 = managerTask.saveTask(); // Работает! Строки 46 и 47 дублируют работу 48 строкой. 46 строка с сохранением в файл
                    //System.out.println(command4);
                    fileBackedTaskManager.saveTaskFile();
                    break;

                case "5":
                    //String command5 = managerTask.codeUpTask(); // Работает!
                    //System.out.println(command5);
                    fileBackedTaskManager.codeUpTaskFile();

                    break;

                case "6":
                    //String command6 = managerTask.codeDelTask(); // Работает!
                    //System.out.println(command6);
                    fileBackedTaskManager.codeDelTaskFile();

                    break;

                case "7":
                    String command7 = managerTask.printAllTask();
                    System.out.println(command7);
                    break;

                case "8":
                    List<Task> command8 = managerTask.inMemoryHistoryManager.getHistory(); //Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()
                    if (command8 != null) {
                        for (Task task : command8) {
                            System.out.println("Просмотренная задача с iD " + task.getiD() + " " + task.getName()); /// ПРОБЛЕМА !!! Тут надо извлечь iD, name и вывести на печчать
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





