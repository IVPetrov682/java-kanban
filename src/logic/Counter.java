package logic;

public class Counter { // Класс счетчик
    private static int count;

    public Counter() { // Присвоили начальное значение
        count = 0;
    }

    public int getCount() { // Показали значение
        return count;
    }

    public void increment() { // Увеличили значение
        count++;
    }

    public static void getIntoCount() { // Увеличили значение
        count = Task.numberTask.size() + Epic.numberEpic.size() + SubTask.numberSubTask.size();
        System.out.println("На счетчике " + count); // показали счетчик
    }

    public void minusOneCounter() { // Уменьшили значение
        count--;
    }

    public void zeroCounter() { // Увеличили значение
        count = 0;

    }
}

