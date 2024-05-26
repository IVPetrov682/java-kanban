package logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class SubTask {
    static HashMap<Integer, ManagerStatus> numberSubTask; // создали таблицу номер/задача
    static Counter number = new Counter(); // Создали объект класса счетчика
    private int iD;
    private TypeTask type;
    private String name;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    private Duration duration; //private Duration duration = Duration.ofMinutes(10);
    private LocalDateTime startTime;

    public SubTask(int iD, TypeTask type, String name, Status status, String description, Duration duration, LocalDateTime startTime, List<Integer> connectioniD) {
        this.iD = iD;
        this.type = type;
        this.name = name;
        this.status = status;
        this.description = description;
        this.connectioniD = connectioniD;
        this.duration = duration;
        this.startTime = startTime;
    }

    public static void startVal() {
        // с второстепенными задачами
        numberSubTask = new HashMap<>();
    }
    public int getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }
}
