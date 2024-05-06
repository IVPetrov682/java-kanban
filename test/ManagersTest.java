package test.src;

import logic.HistoryManager;
import logic.Managers;
import logic.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ManagersTest {

    @Test
    voiD newTaskManager() {
        TaskManager taskManager = Managers.getDefault();

        assertNotNull(taskManager, "Менеджер не проинициализирован");
    }

    @Test
    voiD newHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager, "Менеджер не проинициализирован");
    }

}
