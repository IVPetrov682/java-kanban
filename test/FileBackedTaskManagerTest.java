package test;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileBackedTaskManagerTest {
    private static final String fileBackedTM = "Test.txt";
    final String testValue = "1,TASK,Изучить Java,INPROGRESS,null,null,null,null,null;";

    @Test
    void saveToFile()  throws IOException { // метод записи файл
            try (Writer fileWriter = new FileWriter("src\\logic\\" + fileBackedTM, StandardCharsets.UTF_8)) {
                // инициализация метода преобразования в строку и запись в файл
                fileWriter.write(testValue);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println();
            }
        taskFromString();
        }

    @Test
    public void taskFromString() { // метод формирования задач из файла
        String value;
        try {
            value = Files.readString(Path.of("src\\logic\\" + fileBackedTM));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(testValue, value, "Сохранение в файл и считывание работают");
    }
}