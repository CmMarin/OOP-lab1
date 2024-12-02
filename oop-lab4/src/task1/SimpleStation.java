package task1;


public class SimpleStation<T> implements Queue<T> {
    private int capacity = 50;
    public Object[] cars;
    public int front;
    public int rear;
    public int queueSize;
    public SimpleStation() {
        cars = new Object[capacity];
        front = 0;
        rear = -1;
    }

    @Override
    public void enqueue(T car) {
        if (rear == capacity - 1 && queueSize == capacity) {
            System.out.println("Queue is full. Womp Womp");
            return;
        }else if(queueSize < capacity){
        rear++;
        queueSize++;
        cars[rear] = car;
        System.out.println("New car added to the queue: " + car);
        printQueue();
        } else if (rear < queueSize) {
            rear = queueSize;
            cars[rear] = car;
            System.out.println("New car added to the queue: " + car);
            printQueue();
        }
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        System.out.println(cars[front] + " left the queeue");
        front++;
        queueSize--;
        printQueue();

        if (front > rear) {
            front = 0;
            rear = -1;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return rear < front || queueSize == 0;
    }

    @Override
    public boolean isFull(){
        return rear == capacity - 1;
    }


    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Cars currently in the queue:" + queueSize);
        for (int i = front; i <= rear; i++) {
            System.out.println((i+1) + ":" + cars[i]);
        }
    }
}
