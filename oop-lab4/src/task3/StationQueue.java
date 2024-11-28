package task3;

public class StationQueue<T> implements Queue<T>{
    private int capacity = 5;
    private Object[] cars;
    private int size;
    private int front;

    public StationQueue() {
        cars = new Object[capacity];
        size = 0;
        front = 0;
    }

    @Override
    public void enqueue(T car) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }

        int rear = (front + size) % capacity;
        cars[rear] = car;
        size++;

        System.out.println("New car arrived: " + car);
        printQueue();
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }

        Object car = cars[front];
        front = (front + 1) % capacity;
        size--;

        System.out.println("Car left: " + car);
        printQueue();

        return car;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("No cars in line");
            return;
        }

        System.out.println("Currently in line: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println((i + 1) + ": " + cars[index]);
        }
    }
}