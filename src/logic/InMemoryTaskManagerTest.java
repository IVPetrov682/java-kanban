package logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTaskManagerTest {
    private List<Integer> connectioniD;
    static HashMap<Integer, ManagerStatus> numberSubTask;

    @Test
    void getStatusEpic() {
        Status testValue1 = Status.INPROGRESS;
        connectioniD = new ArrayList<>();
        connectioniD.add(2);
        connectioniD.add(3);
        Status statusEpic;
        numberSubTask = new HashMap<>();
        AllTask allTask = new AllTask(1, TypeTask.EPIC, "EpicName", null, null, null, null, connectioniD);
        numberSubTask.put(2, new ManagerStatus(TypeTask.SUBTASK, "SubName1", Status.NEW, null, null, null, null));
        numberSubTask.put(3, new ManagerStatus(TypeTask.SUBTASK, "SubName2", Status.DONE, null, null, null, null));
        if (connectioniD != null) {
            ArrayList<Status> statusEpicList = new ArrayList<>();
            for (int connectID : connectioniD) {
                Status typeThis = numberSubTask.get(connectID).getStatus();
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
        assertEquals(testValue1.toString(), statusEpic.toString(), "Метод работает нормально");


        Status testValue2 = Status.NEW;
        numberSubTask.clear();
        numberSubTask.put(2, new ManagerStatus(TypeTask.SUBTASK, "SubName1", Status.NEW, null, null, null, null));
        numberSubTask.put(3, new ManagerStatus(TypeTask.SUBTASK, "SubName2", Status.NEW, null, null, null, null));

        if (connectioniD != null) {
            ArrayList<Status> statusEpicList = new ArrayList<>();
            for (int connectID : connectioniD) {
                Status typeThis = numberSubTask.get(connectID).getStatus();
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
        assertEquals(testValue2.toString(), statusEpic.toString(), "Метод работает нормально");


        Status testValue3 = Status.DONE;
        numberSubTask.clear();
        numberSubTask.put(2, new ManagerStatus(TypeTask.SUBTASK, "SubName1", Status.DONE, null, null, null, null));
        numberSubTask.put(3, new ManagerStatus(TypeTask.SUBTASK, "SubName2", Status.DONE, null, null, null, null));

        if (connectioniD != null) {
            ArrayList<Status> statusEpicList = new ArrayList<>();
            for (int connectID : connectioniD) {
                Status typeThis = numberSubTask.get(connectID).getStatus();
                statusEpicList.add(typeThis);
            }
            if (statusEpicList.stream().allMatch(s -> s.toString().equals(Status.NEW.toString()))) {
                statusEpic = Status.NEW;
                System.out.println("NEW " + statusEpic);
            } else if (statusEpicList.stream().allMatch(s -> s.toString().equals(Status.DONE.toString()))) {
                statusEpic = Status.DONE;
            } else {
                statusEpic = Status.INPROGRESS;
            }
        } else {
            statusEpic = Status.NEW;
        }
        assertEquals(testValue3.toString(), statusEpic.toString(), "Метод работает нормально");


        Status testValue4 = Status.NEW;
        numberSubTask.clear();
        connectioniD = null;
        if (connectioniD != null) {
            ArrayList<Status> statusEpicList = new ArrayList<>();
            for (int connectID : connectioniD) {
                Status typeThis = numberSubTask.get(connectID).getStatus();
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
        assertEquals(testValue4.toString(), statusEpic.toString(), "Метод работает нормально");
    }
}