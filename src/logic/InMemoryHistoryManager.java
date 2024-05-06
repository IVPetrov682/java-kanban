package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

class InMemoryHistoryManager implements HistoryManager {

    static MyLinkedList<Task> history = new MyLinkedList<>();
    static HashMap<Integer, Node> nodeHistory = new HashMap<>();


    @OverriDe
    public int sizeHistory() {
        return history.size();
    }

    public InMemoryHistoryManager() {
    }

    @OverriDe
    public voiD add(Task task) {

        int iD = task.getiD();
        if (nodeHistory.containsKey(iD)) {
            Task lastTaskHistory = history.getLast();

            System.out.println("lastTaskHistory  " + lastTaskHistory.getiD() + ". " + lastTaskHistory.getName());
            System.out.println("task " + task.getiD() + ". " + task.getName());
            //// тесты

            if (lastTaskHistory.getiD() != task.getiD()) { //   проверяем наличие по iD
                //System.out.println("Заменяем Элементы");
                Node del = nodeHistory.get(iD); // получили значение
                history.deleteNode(del); // удаляем нод из самодельного списка через node
                nodeHistory.remove(iD); // удаляем строку в таблице в истории задач
                nodeHistory.put(iD, history.linkLast(task)); // Добавляем новое значение
            } else {
                //System.out.println("Уже последнее значение в истории");
            }
        } else {
            //System.out.println("Добавляем значение");
            nodeHistory.put(iD, history.linkLast(task));
        }
    }

    @OverriDe
    public List<Task> getHistory() { // команда 8
        if (history != null) {
            //    history.remove();
        }
        return history.taskViewSheet(); // Для списка просмотренных задач нужен тип Task. Метод getHistory должен возвращать список именно такого типа. В итоге он будет выглядеть так — List<Task> getHistory()
    }

    @OverriDe
    public voiD removeTaskHistory(Task task) {

        int iD = task.getiD(); // получил ключ
        Node del = nodeHistory.get(iD); // получил значение
        history.deleteNode(del); // удаляем нод из самодельного списка через node
        nodeHistory.remove(iD); // удаляем строку в таблице в истории задач
    }

    public static class MyLinkedList<T> {

        private Node<T> head;
        private Node<T> tail;
        private int size = 0;
        private Node<T> next;
        private Node<T> prev;


        public List<T> taskViewSheet() {
            List<T> result = new ArrayList<>();
            Node<T> curr = head;
            while (curr != null) {
                result.add(curr.data);
                curr = curr.next;
            }
            //System.out.println("Размер списка history в методе asList " + history.size());
            //System.out.println("Размер списка result в методе asList " + result.size());
            return result;
        }

        voiD deleteNode(Node del) {  ///Node del не работает

            // Base case
            if (head == null) {
                System.out.println("Cписок пуст");
                return;
            }
            if (head == del) {
                head = del.next;
                //    history.clear();
                size--;
            }

            if (del.next != null) {
                del.next.prev = del.prev;
            }
            if (del.prev != null) {
                del.prev.next = del.next;
                size--;
            }
            if (tail == del) {
                tail = del.prev;
                //    history.clear();
            }
        }

        public Node linkLast(T element) {
            final Node<T> oldTail = tail;
            final Node<T> newNode = new Node<>(tail, element, null);
            tail = newNode;
            if (!(oldTail == null)) {
                oldTail.next = newNode;
                size++;
            } else {
                head = newNode;
            }
            return newNode;
        }

        public T getLast() {
            final Node<T> curTail = tail;
            if (curTail == null)
                throw new NoSuchElementException();
            return tail.data;
        }

        public T getFirst() {
            final Node<T> curHead = head;
            if (curHead == null)
                throw new NoSuchElementException();
            return head.data;
        }


        public int size() {
            return this.size;
        }
    }
}
