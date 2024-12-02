package task4;

public class StationQueue<Car> implements Queue<Car> {
    private final Car[] queue;
    private int front;
    private int rear;
    private int size;
    private final int maxSize;
    private boolean isShuttingDown = false;

    public StationQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = (Car[]) new Object[maxSize];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public synchronized void enqueue(Car car) {
        while (size == maxSize && !isShuttingDown) {
            System.out.println("Queue is full, waiting for space...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (isShuttingDown) {
            return;
        }


        rear = (rear + 1) % maxSize;
        queue[rear] = car;
        size++;


        notifyAll();
        System.out.println("Enqueued: " + car);
    }

    @Override
    public synchronized Object dequeue() {

        while (size == 0 && !isShuttingDown) {
            System.out.println("Queue is empty, waiting for cars...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (size == 0 && isShuttingDown) {
            return null;
        }


        Car car = queue[front];
        front = (front + 1) % maxSize;
        size--;


        notifyAll();
        System.out.println("Dequeued: " + car);

        return car;
    }

    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    @Override
    public synchronized void printQueue() {
        System.out.println("Queue contents:");
        for (int i = 0; i < size; i++) {
            System.out.println(queue[(front + i) % maxSize]);
        }
    }

    @Override
    public synchronized boolean isFull() {
        return size == maxSize;
    }

    public synchronized void shutdown() {
        isShuttingDown = true;
        notifyAll();
    }
}
