package test.src;

import org.junit.jupiter.api.Test;
import logic.HistoryManager;
import logic.Managers;
import logic.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    @Test
    voiD checkSizeOfRequestHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task(1, "Задача");
        final int sizeFromRequestHistoryShouldBe = 1;
        final int sizeForCheckRequestSize = 9;

        for (int i = 0; i <= sizeForCheckRequestSize; i++) {
            historyManager.add(task);
        }
        List<Task> exampleOfRequestHistoryList = historyManager.getHistory();

        assertEquals(sizeFromRequestHistoryShouldBe, exampleOfRequestHistoryList.size(), "Ограничение сохранения повтора "
                + "не работает");
    }

    @Test
    voiD add() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task(1, "Добавляем тестовое значение");
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "Список заполнен");
        assertEquals(1, history.size(), "Список заполнен");
    }

}
