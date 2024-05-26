package logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Task {
    static HashMap<Integer, ManagerStatus> numberTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    private Duration duration; //private Duration duration = Duration.ofMinutes(10);
    private LocalDateTime startTime;
    private int iD;
    private String name;

    public Task(int iD, TypeTask type, String name, Status status, String description, Duration duration, LocalDateTime startTime, List<Integer> connectioniD) {
        this.iD = iD;
        this.type = this.type;
        this.name = name;
        this.status = this.status;
        this.description = this.description;
        this.connectioniD = this.connectioniD;
        this.duration = duration;
        this.startTime = startTime;
    }

    public static void startVal() {
        numberTask = new HashMap<>();
    }

    public int getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }
}
