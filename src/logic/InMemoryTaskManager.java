package logic;

import java.util.ArrayList;
import java.util.Scanner;

class InMemoryTaskManager implements TaskManager{

    Scanner scanner = new Scanner(System.in);
    Epic epic = new Epic();

    private int codeTask;
    private String codeTaskName;
    Task task = new Task(codeTask, codeTaskName); // создали таск !!!!!!!!!!!!!!!

    InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

    @Override
        public String printMainTask() {
        for (Integer keyPrint : task.numberTask.keySet()) {
            String taskPrint = task.numberTask.get(keyPrint).getDescription();
            System.out.println("Индификатор № " + keyPrint + ". " + taskPrint + ".");
        }
        return "Что-то вернули в соответствии с ТЗ";
    }
        @Override
        public String saveTask() { // метод сохранения задачи
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите наименование задачи которую необходимо сохранить");
        String nameTask = scanner.nextLine();

        int x = 0;
        int keyX = 0;
        for (Integer inventory : task.numberTask.keySet()) {

            if (inventory - x > 1) {
                keyX = x + 1;
                Task.number.increment(); // увеличили счетчик
                task.numberTask.put(keyX, new ManagerStatus(nameTask, Status.INPROGRESS));

                break;
            } else {
                x++; // проодолжаем искать ключ
            }
        }

        if (keyX == 0) {
            Task.number.increment(); // увеличили счетчик
            task.numberTask.put(Task.number.getCount(), new ManagerStatus(nameTask, Status.INPROGRESS));
            System.out.println("На счетчике " + Task.number.getCount()); // показали счетчик
        }
        return "Что-то вернули в соответствии с ТЗ";
    }
        @Override
        public String removeAllTask() {
        task.numberTask.clear();
        epic.subTask.clear();
        Task.number.zeroCounter();
        System.out.println("Задачи удалены.");
        return "Что-то вернули в соответствии с ТЗ";
    }
        @Override
        public String searchCodeTask() {
        System.out.println("Введите номер индификатора задачи");
        int codeTask = scanner.nextInt();
        if (!task.numberTask.containsKey(codeTask)) {
            System.out.println("Такой задачи нет.");
        }
        else {
            String codeTaskName = task.numberTask.get(codeTask).getDescription();
            System.out.println("Индификатор № " + codeTask + ". Задача: " + codeTaskName + ".");
            Task task = new Task(codeTask, codeTaskName);

            inMemoryHistoryManager.add(task); ///////// Метод сохранения запроса в истории вызова задачи

            ArrayList<ManagerStatus> oneSubTaskPrint = epic.subTask.get(codeTask);
            if (oneSubTaskPrint != null) {
                for (ManagerStatus printSubTask : oneSubTaskPrint) {
                System.out.println(printSubTask.getDescription());
                }
            }
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

        @Override
        public String codeUpTask() { // метод сохранения задачи
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите наименование задачи которую необходимо сохранить");
        String nameUpTask = scanner.nextLine();

        System.out.println("Введите индификатор задачи которую необходимо обновить");
        int codeUpTask = scanner.nextInt();

        if (task.numberTask.containsKey(codeUpTask)) {
            task.numberTask.put(codeUpTask, new ManagerStatus(nameUpTask, Status.INPROGRESS));
            System.out.println("Задача с индификатором " + codeUpTask + " обновлена. Новое наименование задачи: " + nameUpTask + ".");
        } else {
            System.out.println("Задачи с индификатором " + codeUpTask + " не существует.");
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
            String nameDelTask = task.numberTask.get(codeDelTask).getDescription();
            Task taskDel = new Task(codeDelTask, nameDelTask);
            inMemoryHistoryManager.removeTaskHistory(taskDel); ///////// Удаляем запрос в истории вызова задачи
            // Создаем taskDel чтобы можно по нему очистить историю задач. Конец

            task.numberTask.remove(codeDelTask);
            Task.number.minusOneCounter(); // Уменьшили счетчик
            if (epic.subTask.containsKey(codeDelTask)) {
                epic.subTask.remove(codeDelTask);
                System.out.println("Подзадачи с индификатором " + codeDelTask + " удалены.");
            }
            System.out.println("Задача с индификатором " + codeDelTask + " удалена.");
        } else {
            System.out.println("Задачи с индификатором " + codeDelTask + " не существует.");
        }

        return "Что-то вернули в соответствии с ТЗ";
        }
        @Override
        public String printAllTask() {
        for (Integer keyPrint : task.numberTask.keySet()) {
            ArrayList<ManagerStatus> miniTask = epic.subTask.get(keyPrint);
            if (miniTask != null) {
                for (ManagerStatus secTask : miniTask) {
                    if (secTask.getPriority() == Status.INPROGRESS) {
                        System.out.println(secTask.getDescription());
                    }
                }
            }

        }
        return "Что-то вернули в соответствии с ТЗ";
    }
}
