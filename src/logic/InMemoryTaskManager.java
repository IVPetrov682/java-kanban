package logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

class InMemoryTaskManager implements TaskManager {

    Scanner scanner = new Scanner(System.in);

    private int iD;
    private String name;
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    private Duration duration; //private Duration duration = Duration.ofMinutes(10);
    private LocalDateTime startTime;

    Task task = new Task(iD, type, name, status, description, duration, startTime, connectioniD); // создали таск
    Epic epic = new Epic(iD, type, name, status, description, duration, startTime, connectioniD); // создали эпик
    SubTask subTask = new SubTask(iD, type, name, status, description, duration, startTime, connectioniD); // создали субтаск
    AllTask allTask = new AllTask(iD, type, name, status, description, duration, startTime, connectioniD); // создали AllTask
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
        System.out.println("Выберите тип задачи:");
        System.out.println("Нажмите 1 - если задача Task.");
        System.out.println("Нажмите 2 - если задача Epic.");
        System.out.println("Нажмите 3 - если задача SubTask.");
        int codeTypeTask = scanner.nextInt();
        TypeTask type;
        if (codeTypeTask == 1) {
            type = TypeTask.TASK;
        } else if (codeTypeTask == 2) {
            type = TypeTask.EPIC;
        } else if (codeTypeTask == 3) {
            type = TypeTask.SUBTASK;
        } else {
            type = null;
        }
        int keyX = 1;
        LocalDateTime now = LocalDateTime.now(); // тест для записи и функционировании программы
        Duration duration = Duration.ofMinutes(10);

        if (now != null && now.plus(duration) != null) {
            if (getPrioritizedTasks().stream().noneMatch(t -> isTimeCross(t, new AllTask(0, type, name, status, description, duration, now, connectioniD)))) {
                while (true) { // ключ x не содержится в таблицах
                    System.out.println("keyX до" + keyX);
                    if ((!Task.numberTask.containsKey(keyX)) && (!Epic.numberEpic.containsKey(keyX)) && (!SubTask.numberSubTask.containsKey(keyX))) {
                        System.out.println("keyX после" + keyX);
                        if (codeTypeTask == 1) {
                            Task.number.increment(); // увеличили счетчик
                            Task.numberTask.put(keyX, new ManagerStatus(type, name, Status.NEW, description, duration, now, connectioniD));
                            break;
                        } else if (codeTypeTask == 2) {
                            Task.number.increment(); // увеличили счетчик
                            Epic.numberEpic.put(keyX, new ManagerStatus(type, name, getStatusEpic(connectioniD), description, duration, now, connectioniD));
                            break;
                        } else if (codeTypeTask == 3) {
                            Task.number.increment(); // увеличили счетчик
                            SubTask.numberSubTask.put(keyX, new ManagerStatus(type, name, Status.NEW, description, duration, now, connectioniD));
                            break;
                        } else {
                            System.out.println("Введен неверный код задачи");
                        }
                    }
                    keyX++;
                }
                AllTask.allTasklist.add(new AllTask(keyX, type, name, Status.NEW, description, duration, now, connectioniD));
            } else {
                System.out.println("Новая задача не может быть создана, время пересекается");
            }
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
        AllTask.allTasklist.clear();
        System.out.println("Задача удалена.");
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String searchiD() {
        System.out.println("Введите номер индификатора задачи");
        int iD = scanner.nextInt();
        if (task.numberTask.containsKey(iD)) {
            String name = task.numberTask.get(iD).getName();
            System.out.println("Индификатор № " + iD + ". Задача: " + name + ".");
            AllTask allTask = new AllTask(iD, type, name, status, description, duration, startTime, connectioniD);
            inMemoryHistoryManager.add(allTask); ///////// Метод сохранения запроса в истории вызова задачи
        } else if (epic.numberEpic.containsKey(iD)) {
            String name = task.numberTask.get(iD).getName();
            System.out.println("Индификатор № " + iD + ". Задача: " + name + ".");
            AllTask allTask = new AllTask(iD, type, name, getStatusEpic(connectioniD), description, duration, startTime, connectioniD);
            inMemoryHistoryManager.add(allTask); ///////// Метод сохранения запроса в истории вызова задачи
        } else if (subTask.numberSubTask.containsKey(iD)) {
            String name = task.numberTask.get(iD).getName();
            System.out.println("Индификатор № " + iD + ". Задача: " + name + ".");
            AllTask allTask = new AllTask(iD, type, name, status, description, duration, startTime, connectioniD);
            inMemoryHistoryManager.add(allTask); ///////// Метод сохранения запроса в истории вызова задачи
        } else {
            System.out.println("Такой задачи нет.");
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String codeUpTask() { // метод обновления задачи
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите наименование задачи которую необходимо сохранить");
        String name = scanner.nextLine();

        System.out.println("Введите iD задачи которую необходимо обновить");
        int codeUpTask = scanner.nextInt();
        if (task.numberTask.containsKey(codeUpTask)) {
            task.numberTask.put(codeUpTask, new ManagerStatus(TypeTask.TASK, name, Status.INPROGRESS, description, duration, startTime, connectioniD));
            for (AllTask object : allTask.allTasklist) {
                if (object.getID() == codeUpTask) {
                    if ((duration != null) && (startTime != null)) {
                        allTask.allTasklist.remove(object);
                        allTask.allTaskList.add(new AllTask(codeUpTask, TypeTask.TASK, name, Status.INPROGRESS, description, duration, startTime, connectioniD));
                        break;
                    }
                }
            }
            System.out.println("Задача с iD " + codeUpTask + " обновлена. Новое наименование задачи: " + name + ".");
        } else if (epic.numberEpic.containsKey(codeUpTask)) {
            epic.numberEpic.put(codeUpTask, new ManagerStatus(TypeTask.EPIC, name, getStatusEpic(connectioniD), description, duration, startTime, connectioniD));
            for (AllTask object : allTask.allTasklist) {
                if (object.getID() == codeUpTask) {
                    if ((duration != null) && (startTime != null)) {
                        allTask.allTasklist.remove(object);
                        allTask.allTaskList.add(new AllTask(codeUpTask, TypeTask.EPIC, name, Status.INPROGRESS, description, duration, startTime, connectioniD));
                        break;
                    }
                }
            }
            System.out.println("Задача с iD " + codeUpTask + " обновлена. Новое наименование задачи: " + name + ".");
        } else if (subTask.numberSubTask.containsKey(codeUpTask)) {
            subTask.numberSubTask.put(codeUpTask, new ManagerStatus(TypeTask.SUBTASK, name, Status.INPROGRESS, description, duration, startTime, connectioniD));
            for (AllTask object : allTask.allTasklist) {
                if (object.getID() == codeUpTask) {
                    if ((duration != null) && (startTime != null)) {
                        allTask.allTasklist.remove(object);
                        allTask.allTaskList.add(new AllTask(codeUpTask, TypeTask.SUBTASK, name, Status.INPROGRESS, description, duration, startTime, connectioniD));
                        break;
                    }
                }
            }

            System.out.println("Задача с iD " + codeUpTask + " обновлена. Новое наименование задачи: " + name + ".");
        } else {

            System.out.println("Такого iD задачи не существует");
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public String codeDelTask() { // метод удаления задачи по индификатору
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите индификатор задачи которую необходимо удалить");
        int codeDelTask = scanner.nextInt();
        if (task.numberTask.containsKey(codeDelTask)) {
            String nameDelTask = task.numberTask.get(codeDelTask).getName();
            AllTask allTaskDel = new AllTask(codeDelTask, TypeTask.TASK, nameDelTask, status, description, duration, startTime, connectioniD);
            inMemoryHistoryManager.removeTaskHistory(allTaskDel); ///////// Удаляем запрос в истории вызова задачи
            task.numberTask.remove(codeDelTask);
            Task.number.minusOneCounter(); // Уменьшили счетчик

            System.out.println("Задача с индификатором " + codeDelTask + " удалена.");
        } else if (epic.numberEpic.containsKey(codeDelTask)) {
            String nameDelTask = epic.numberEpic.get(codeDelTask).getName();
            AllTask allTaskDel = new AllTask(codeDelTask, TypeTask.EPIC, nameDelTask, getStatusEpic(connectioniD), description, duration, startTime, connectioniD);
            inMemoryHistoryManager.removeTaskHistory(allTaskDel); ///////// Удаляем запрос в истории вызова задачи
            epic.numberEpic.remove(codeDelTask);
            Task.number.minusOneCounter(); // Уменьшили счетчик

            System.out.println("Задача с индификатором " + codeDelTask + " удалена.");
        } else if (subTask.numberSubTask.containsKey(codeDelTask)) {
            String nameDelTask = subTask.numberSubTask.get(codeDelTask).getName();
            AllTask allTaskDel = new AllTask(codeDelTask, TypeTask.SUBTASK, nameDelTask, status, description, duration, startTime, connectioniD);
            inMemoryHistoryManager.removeTaskHistory(allTaskDel); ///////// Удаляем запрос в истории вызова задачи
            subTask.numberSubTask.remove(codeDelTask);
            Task.number.minusOneCounter(); // Уменьшили счетчик

            System.out.println("Задача с индификатором " + codeDelTask + " удалена.");
        } else {

            System.out.println("Задачи с индификатором " + codeDelTask + " не существует.");
        }
        return "Что-то вернули в соответствии с ТЗ";
    }

    @Override
    public TreeSet<AllTask> getPrioritizedTasks() {
        return AllTask.allTasklist;
    }

    private boolean isTimeCross(AllTask alltask1, AllTask alltask2) {
        boolean notCross = alltask1.getStartTime().isAfter(alltask2.getEndTime()) || alltask1.getEndTime().isBefore(alltask2.getStartTime());
        return !notCross;
    }

    public Status getStatusEpic(List<Integer> connectioniD) {
        Status statusEpic;
        if (connectioniD != null) {
            ArrayList<Status> statusEpicList = new ArrayList<>();
            for (int connectID : connectioniD) {
                Status typeThis = SubTask.numberSubTask.get(connectID).getStatus();
                statusEpicList.add(typeThis);
            }
            if (statusEpicList.stream().allMatch(s -> s.toString().equals(Status.NEW.toString()))) {
                statusEpic = Status.NEW;
            } else if (statusEpicList.stream().allMatch(s -> s.toString().equals(Status.DONE.toString()))) {
                statusEpic = Status.DONE;
            } else {
                statusEpic = Status.INPROGRESS;
            }
        } else {
            statusEpic = Status.NEW;
        }
        return statusEpic;
    }
}



