package logic;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private static final String fileBackedTM = "FileBackedTM.txt";

    public static void createFileBackedTM() { // Создаем файл FileBackedTM, в который будем записывать и считывать информацию
        if (!Files.exists(Paths.get("src\\logic\\" + fileBackedTM))) { // проверка на наличие файла
            try {
                Path fileBackedTM = Files.createFile(Paths.get("src\\logic\\FileBackedTM.txt")); // создание файла
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveToFile() throws IOException { // метод записи файл
        try (Writer fileWriter = new FileWriter("src\\logic\\" + fileBackedTM, StandardCharsets.UTF_8)) {
            fileWriter.write(toStringTask(Task.numberTask));
            fileWriter.write("\n");
            fileWriter.write(toStringTask(SubTask.numberSubTask));
            fileWriter.write("\n");
            fileWriter.write(toStringTask(Epic.numberEpic));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public String toStringTask(HashMap<Integer, ManagerStatus> anyTask) { // Метод преобразования содержимого из программы в строку
        String[] allTaskLine = new String[anyTask.size()];
        int i = 0;
        for (Integer iD : anyTask.keySet()) {
            TypeTask type = anyTask.get(iD).getTypeTask();
            String name = anyTask.get(iD).getName();
            Status status = anyTask.get(iD).getStatus();
            String description = anyTask.get(iD).getDescription();
            Duration duration = anyTask.get(iD).getDuration();
            LocalDateTime startTimeTask = anyTask.get(iD).getStartTime();
            List<Integer> connectioniD = anyTask.get(iD).getСonnectioniD();

            String connectioniDToString;
            if (connectioniD == null) {
                connectioniDToString = null;
            } else {
                connectioniDToString = connectioniD.stream().map(String::valueOf).collect(Collectors.joining(","));
                if (connectioniDToString.equals("")) {
                    connectioniDToString = null;
                }
            }
            String durationToString = null;
            if (duration != null) {
                duration.toMinutes();
                durationToString = duration.toString();
            }
            if (type.toString().equals(TypeTask.EPIC.toString())) {
                duration = null; // обнулили
                for (int number : connectioniD)
                    if (duration == null) {
                        if (duration != SubTask.numberSubTask.get(number).getDuration()) {
                            duration = SubTask.numberSubTask.get(number).getDuration();
                        }
                    } else {
                        if (duration != SubTask.numberSubTask.get(number).getDuration()) {
                            duration = duration.plus(SubTask.numberSubTask.get(number).getDuration());
                        }
                    }
                duration.toMinutes();
                durationToString = duration.toString();
            }

            String startTimeToString;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy,HH:mm");
            if (startTimeTask == null) {
                LocalDateTime now = LocalDateTime.now(); // тест для записи и функционировании программы
                startTimeToString = now.format(formatter);
            } else {
                startTimeToString = startTimeTask.format(formatter);
            }

            if (type.toString().equals(TypeTask.EPIC.toString())) {
                List<LocalDateTime> listLocalDateTime = new ArrayList<LocalDateTime>();
                for (int iDSubTask : connectioniD) {
                    if ((SubTask.numberSubTask.get(iDSubTask)).getStartTime() != null) {
                        listLocalDateTime.add((SubTask.numberSubTask.get(iDSubTask)).getStartTime());
                    }
                }
                startTimeTask = Collections.min(listLocalDateTime);
                startTimeToString = startTimeTask.format(formatter);
            }

            if (type.toString().equals(TypeTask.EPIC.toString())) {
                status = getStatusEpic(connectioniD);
                System.out.println("status " + status + " connectioniD " + connectioniD);
            }

            // Формируем строку
            String taskLine = String.join(",", iD.toString(), type.toString(), name, status.toString(), description, durationToString, startTimeToString, connectioniDToString + ";");
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
        for (int k = 0; k < (split.length); k++) { //
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
                } // определяем type. Конец

                String name = taskSplit[2]; // name

                // Определяем status. Начало
                Status status = null;
                if (taskSplit[3].equals(Status.NEW.toString())) { // type
                    status = Status.NEW;
                } else if (taskSplit[3].equals(Status.INPROGRESS.toString())) {
                    status = Status.INPROGRESS;
                } else if (taskSplit[3].equals(Status.DONE.toString())) {
                    status = Status.DONE;
                } // определяем status. Конец

                String description = taskSplit[4]; // Описание

                // определяем duration. Начало
                Duration duration;
                if (taskSplit[5].equals("null")) {
                    duration = null;
                } else {
                    duration = Duration.parse(taskSplit[5]);
                } // определяем duration. Конец

                // определяем startTime. Начало
                LocalDate date;
                if (taskSplit[6].equals("null")) {
                    date = null;
                } else {
                    DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    date = LocalDate.parse(taskSplit[6], dataFormatter);
                } // определяем startTime. Конец

                LocalTime time;
                if (taskSplit[7].equals("null")) {
                    time = null;
                } else {
                    time = LocalTime.parse(taskSplit[7]);
                }
                LocalDateTime startTime = LocalDateTime.of(date, time);

                // определяем connectioniD. Начало
                List<Integer> connectioniD = new ArrayList<>(); //String connectioniD = null;
                if (!taskSplit[8].equals("null")) {
                    for (int i = 8; i < taskSplit.length; i++) {
                        connectioniD.add(Integer.valueOf(taskSplit[i].trim()));
                    }
                }
                // определяем connectioniD. Конец

                // Кладем задачи по таблицам. Начало
                if (taskSplit[1].equals(TypeTask.TASK.toString())) { // type
                    task.numberTask.put(iD, new ManagerStatus(type, name, status, description, duration, startTime, connectioniD));
                } else if (taskSplit[1].equals(TypeTask.EPIC.toString())) { // type
                    epic.numberEpic.put(iD, new ManagerStatus(type, name, status, description, duration, startTime, connectioniD));
                } else if (taskSplit[1].equals(TypeTask.SUBTASK.toString())) { // type
                    subTask.numberSubTask.put(iD, new ManagerStatus(type, name, status, description, duration, startTime, connectioniD));
                    Task.number.increment(); // увеличили счетчик
                }
                if ((duration != null) && (startTime != null)) {
                    allTask.allTasklist.add(new AllTask(iD, type, name, status, description, duration, startTime, connectioniD));
                } // Кладем задачи по таблицам. Конец
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
    }

    public void codeUpTaskFile() { // метод сохранения задачи
        super.codeUpTask();
    }

    public void codeDelTaskFile() { // метод удаления задачи по индификатору
        super.codeDelTask();
    }
}

