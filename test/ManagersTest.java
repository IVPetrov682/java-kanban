package test;

import logic.HistoryManager;
import logic.Managers;
import logic.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ManagersTest {

    @Test
    void newHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager, "Менеджер не проинициализирован");
    }
}
