package logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class AllTask {

    static TreeSet<AllTask> allTasklist;
    public InMemoryHistoryManager allTaskList;
    private int iD;
    private TypeTask type;
    private Status status;
    private String description;
    private List<Integer> connectioniD;
    private Duration duration; //private Duration duration = Duration.ofMinutes(10);
    private LocalDateTime startTime;
    private String name;

    public AllTask(int iD, TypeTask type, String name, Status status, String description, Duration duration, LocalDateTime startTime, List<Integer> connectioniD) {
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
        allTasklist = new TreeSet<>(Comparator.comparing(AllTask::getStartTime));

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }


    public LocalDateTime getEndTime() {
        LocalDateTime EndTime = startTime.plus(duration);
        if (type.toString().equals(TypeTask.EPIC.toString())) {
            ArrayList<LocalDateTime> dataTimesList = new ArrayList<>();
            Duration durationSum = null;
            for (int connectID : connectioniD) {
                LocalDateTime startTimeThis = Epic.numberEpic.get(connectID).getStartTime();
                dataTimesList.add(startTimeThis);
                durationSum = durationSum.plus(Epic.numberEpic.get(connectID).getDuration());
            }
            Optional<LocalDateTime> startTimeList = dataTimesList.stream().min(LocalDateTime::compareTo);
            EndTime = startTimeList.get().plus(durationSum);
        }
        return EndTime;
    }

    public int getID() {
        return iD;
    }

    public String getName() {

        return name;
    }

    public TypeTask getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Duration getDuration() {
        return duration;
    }

    public List<Integer> getConnectioniD() {
        return connectioniD;
    }
}
