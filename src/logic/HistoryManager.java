package logic;

import java.util.List;

public interface HistoryManager {

    void add(Task task);

    public List<AllTask> getHistory(); // Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()

    public int sizeHistory();

    void removeTaskHistory(Task task);

    void add(AllTask allTask);

    void removeTaskHistory(AllTask allTask);
}



