package task3;

public interface Queue<Car> {
    void enqueue(Car T);
    Object dequeue();
    boolean isEmpty();
    void printQueue();
    boolean isFull();
}
