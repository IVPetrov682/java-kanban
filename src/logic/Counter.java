package logic;
public class Counter { // Класс счетчик
    private int count;

    public Counter() { // Присвоили начальное значение
        count = 0;
    }

    public int getCount() { // Показали значение
        return count;
    }

    public void increment() { // Увеличили значение
        count++;
    }

    public void minusOneCounter() { // Уменьшили значение
        count--;
    }

    public void zeroCounter() { // Увеличили значение
        count = 0;

    }
}
