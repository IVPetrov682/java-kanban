package test.src;

import logic.HistoryManager;
import logic.Managers;
import logic.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ManagersTest {

    @Test
    void newTaskManager() {
        TaskManager taskManager = Managers.getDefault();

        assertNotNull(taskManager, "Менеджер не проинициализирован");
    }

    @Test
    void newHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager, "Менеджер не проинициализирован");
    }

}