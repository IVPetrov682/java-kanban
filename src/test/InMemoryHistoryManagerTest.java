package test;

import logic.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    private Duration duration;
    private LocalDateTime startTime;

    @Test
    void checkSizeOfRequestHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        AllTask allTask = new AllTask(1, TypeTask.TASK, "Вася", Status.NEW, "Описание", duration, startTime, null);
        final int sizeFromRequestHistoryShouldBe = 1;
        final int sizeForCheckRequestSize = 9;

        for (int i = 0; i <= sizeForCheckRequestSize; i++) {
            historyManager.add(allTask);
        }
        List<AllTask> exampleOfRequestHistoryList = historyManager.getHistory();

        assertEquals(sizeFromRequestHistoryShouldBe, exampleOfRequestHistoryList.size(), "Ограничение сохранения повтора "
                + "не работает");
    }

    @Test
    void add() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task(1, TypeTask.TASK, "Вася", Status.NEW, "Описание", duration, startTime, null);
        historyManager.add(task);
        final List<AllTask> history = historyManager.getHistory();
        assertNotNull(history, "Список заполнен");
        assertEquals(1, history.size(), "Список заполнен");
    }

}
