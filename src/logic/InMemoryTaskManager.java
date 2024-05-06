package logic;

import java.util.List;
import java.util.Scanner;

class InMemoryTaskManager implements TaskManager {

    Scanner scanner = new Scanner(System.in);

    private int ID;
    private String name;
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectionID;
    Task task = new Task(ID, type, name, status, description, connectionID); // создали таск
    Epic epic = new Epic(ID, type, name, status, description, connectionID); // создали эпик
    SubTask subTask = new SubTask(ID, type, name, status, description, connectionID); // создали субтаск


    InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();


    @Override
    public String printMainTask() {
        for (Integer keyPrint : task.numberTask.keySet()) {
            String taskPrint = task.numberTask.get(keyPrint).getName();

            System.out.println("Индификатор № " + keyPrint + ". " + taskPrint + ".");
        }
        for (Integer keyPrint : epic.numberEpic.keySet()) {
            String taskPrint = epic.numberEpic.get(keyPrint).getName();

            System.out.println("Индификатор № " + keyPrint + ". " + taskPrint + ".");
        }
        for (Integer keyPrint : subTask.numberSubTask.keySet()) {
            String taskPrint = subTask.numberSubTask.get(keyPrint).getName();

            System.out.println("Индификатор № " + keyPrint + ". " + taskPrint + ".");
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String saveTask() { // метод сохранения задачи
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите наименование задачи которую необходимо сохранить");
        String name = scanner.nextLine();

        int x = 0;
        int keyX = 0;
        for (Integer inventory : task.numberTask.keySet()) {

            if (inventory - x > 1) {
                keyX = x + 1;
                Task.number.increment(); // увеличили счетчик
                task.numberTask.put(keyX, new ManagerStatus(TypeTask.TASK, name, Status.NEW, description, connectionID));

                break;
            } else {
                x++; // проодолжаем искать ключ
            }
        }

        if (keyX == 0) {
            Task.number.increment(); // увеличили счетчик
            task.numberTask.put(Task.number.getCount(), new ManagerStatus(TypeTask.SUBTASK, name, Status.NEW, description, connectionID));
            System.out.println("На счетчике " + Task.number.getCount()); // показали счетчик
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String removeAllTask() {
        task.numberTask.clear();
        epic.numberEpic.clear();
        subTask.numberSubTask.clear();
        Task.number.zeroCounter();
        Epic.number.zeroCounter();
        SubTask.number.zeroCounter();
        System.out.println("Задача удалена.");
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String searchID() {
        System.out.println("Введите номер индификатора задачи");
        int ID = scanner.nextInt();
        if (!task.numberTask.containsKey(ID)) {
            System.out.println("Такой задачи нет.");
        } else {
            String name = task.numberTask.get(ID).getName();
            System.out.println("Индификатор № " + ID + ". Задача: " + name + ".");
            Task task = new Task(ID, type, name, status, description, connectionID);

            inMemoryHistoryManager.add(task); ///////// Метод сохранения запроса в истории вызова задачи

        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String codeUpTask() { // метод сохранения задачи
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите наименование задачи которую необходимо сохранить");
        String name = scanner.nextLine();

        System.out.println("Введите ID задачи которую необходимо обновить");
        int codeUpTask = scanner.nextInt();

        if (task.numberTask.containsKey(codeUpTask)) {
            task.numberTask.put(codeUpTask, new ManagerStatus(TypeTask.SUBTASK, name, Status.INPROGRESS, description, connectionID));
            System.out.println("Задача с ID " + codeUpTask + " обновлена. Новое наименование задачи: " + name + ".");
        } else {
            System.out.println("Задачи с ID " + codeUpTask + " не существует.");
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String codeDelTask() { // метод удаления задачи по индификатору
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите индификатор задачи которую необходимо удалить");
        int codeDelTask = scanner.nextInt();


        if (task.numberTask.containsKey(codeDelTask)) {

            // Создаем taskDel чтобы можно по нему очистить историю задач. Начало
            String nameDelTask = task.numberTask.get(codeDelTask).getName();
            Task taskDel = new Task(codeDelTask, type, nameDelTask, status, description, connectionID);
            inMemoryHistoryManager.removeTaskHistory(taskDel); ///////// Удаляем запрос в истории вызова задачи
            // Создаем taskDel чтобы можно по нему очистить историю задач. Конец

            task.numberTask.remove(codeDelTask);
            Task.number.minusOneCounter(); // Уменьшили счетчик
            //if (epic.subTask.containsKey(codeDelTask)) {
            //    epic.subTask.remove(codeDelTask);
            //    System.out.println("Подзадачи с индификатором " + codeDelTask + " удалены.");
            //}
            System.out.println("Задача с индификатором " + codeDelTask + " удалена.");
        } else {
            System.out.println("Задачи с индификатором " + codeDelTask + " не существует.");
        }

        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String printAllTask() {
        //    for (Integer keyPrint : task.numberTask.keySet()) {
        //        ArrayList<ManagerStatus> miniTask = epic.subTask.get(keyPrint);
        //        if (miniTask != null) {
        //            for (ManagerStatus secTask : miniTask) {
        //                if (secTask.getStatus() == Status.INPROGRESS) {
        //                    System.out.println(secTask.getName());
        //                }
        //            }
        //        }

        //    }
        return "Что-то вернули в соответствии с ТЗ";
    }
}
