package logic;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private static final String fileBackedTM = "FileBackedTM.txt";
    private int iD;
    private String name;
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    Task task = new Task(iD, type, name, status, description, connectioniD); // создали таск

    public static void createFileBackedTM() { // Создаем файл FileBackedTM, в который будем записывать и считывать информацию
        if (!Files.exists(Paths.get("src\\logic\\" + fileBackedTM))) { // проверка на наличие файла
            try {
                Path fileBackedTM = Files.createFile(Paths.get("src\\logic\\FileBackedTM.txt")); // создание файла
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveToFile() throws IOException { // метод записи файл
        try (Writer fileWriter = new FileWriter("src\\logic\\" + fileBackedTM, StandardCharsets.UTF_8)) {
            // инициализация метода преобразования в строку и запись в файл
            fileWriter.write(toStringTask(Task.numberTask));
            fileWriter.write("\n");
            fileWriter.write(toStringTask(Epic.numberEpic));
            fileWriter.write("\n");
            fileWriter.write(toStringTask(SubTask.numberSubTask));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public static String toStringTask(HashMap<Integer, ManagerStatus> anyTask) { // Метод преобразования содержимого из программы в строку
        String[] allTaskLine = new String[anyTask.size()];
        int i = 0;
        for (Integer iD : anyTask.keySet()) {
            TypeTask type = anyTask.get(iD).getTypeTask();
            String name = anyTask.get(iD).getName();
            Status status = anyTask.get(iD).getStatus();
            String description = anyTask.get(iD).getDescription();
            List<Integer> connectioniD = anyTask.get(iD).getСonnectioniD();

            String connectioniDToString;
            if (connectioniD == null) {
                System.out.println("значение нуль");
                connectioniDToString = null;
            } else {
                connectioniDToString = connectioniD.stream().map(String::valueOf).collect(Collectors.joining(","));
                if (connectioniDToString.equals("")) {
                    System.out.println("значение ковычки");
                    connectioniDToString = null;
                }
            }

            // Формируем строку
            String taskLine = String.join(",", iD.toString(), type.toString(), name, status.toString(), description, connectioniDToString + ";");
            allTaskLine[i] = taskLine;
            i++;
        }
        String textTask = String.join("\n", allTaskLine);
        return textTask;
    }

    public void taskFromString() { // метод формирования задач из файла
        String value;
        try {
            value = Files.readString(Path.of("src\\logic\\" + fileBackedTM));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] split = value.split(";");// разбили на массив строк
        // Определяем iD. Начало
        int iD;
        for (int k = 0; k < split.length; k++) {
            String lineTask = String.join(",", split[k].trim()); // преобразуем массив в строку
            String[] taskSplit = lineTask.split(",");
            // Определяем iD. Начало
            if (!value.isEmpty()) { // если файл пустой ошибки не будет
                iD = Integer.parseInt(taskSplit[0].trim()); // iD
                // Определяем iD. Конец
                // Определяем type. Начало
                TypeTask type = null;
                if (taskSplit[1].equals(TypeTask.TASK.toString())) { // type
                    type = TypeTask.TASK;
                } else if (taskSplit[1].equals(TypeTask.EPIC.toString())) { // type
                    type = TypeTask.EPIC;
                } else if (taskSplit[1].equals(TypeTask.SUBTASK.toString())) { // type
                    type = TypeTask.SUBTASK;
                }
                // определяем type. Конец

                String name = taskSplit[2]; // name

                // Определяем status. Начало
                Status status = null;
                if (taskSplit[3].equals(Status.NEW.toString())) { // type
                    status = Status.NEW;
                } else if (taskSplit[3].equals(Status.INPROGRESS.toString())) {
                    status = Status.INPROGRESS;
                } else if (taskSplit[3].equals(Status.DONE.toString())) {
                    status = Status.DONE;
                }
                // определяем status. Конец

                String description = taskSplit[4]; // name

                // определяем connectioniD. Начало
                List<Integer> connectioniD = new ArrayList<>(); //String connectioniD = null;
                if (!taskSplit[5].equals("null")) {
                    for (int i = 5; i < taskSplit.length; i++) {
                        connectioniD.add(Integer.valueOf(taskSplit[i].trim()));
                    }
                }
                // определяем connectioniD. Конец

                // Кладем задачи по таблицам. Начало
                if (taskSplit[1].equals(TypeTask.TASK.toString())) { // type
                    task.numberTask.put(iD, new ManagerStatus(type, name, status, description, connectioniD));
                    Task.number.increment(); // увеличили счетчик
                } else if (taskSplit[1].equals(TypeTask.EPIC.toString())) { // type
                    epic.numberEpic.put(iD, new ManagerStatus(type, name, status, description, connectioniD));
                    Epic.number.increment(); // увеличили счетчик
                } else if (taskSplit[1].equals(TypeTask.SUBTASK.toString())) { // type
                    subTask.numberSubTask.put(iD, new ManagerStatus(type, name, status, description, connectioniD));
                    SubTask.number.increment(); // увеличили счетчик
                    // Кладем задачи по таблицам. Конец
                }
            }

        }
    }

    public void saveTaskFile() { // метод сохранения задачи c сохранением в файл
        super.saveTask();
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAllTaskFile() {
        super.removeAllTask();
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void codeUpTaskFile() { // метод сохранения задачи
        super.codeUpTask();
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void codeDelTaskFile() { // метод удаления задачи по индификатору
        super.codeDelTask();
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

