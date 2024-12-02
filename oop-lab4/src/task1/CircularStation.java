package task1;

public class CircularStation<T> implements Queue<T>{
    private int capacity = 5;
    private Object[] cars;
    private int rear, front;

    public CircularStation() {
        cars = new Object[capacity];
        rear = -1;
        front = -1;
    }


    @Override
    public void enqueue(T car) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % capacity;
            cars[rear] = car;
            System.out.println("New car arrived: " + car);
            printQueue();
        }
    }

    @Override
    public Object dequeue() {
        Object car = cars[front];
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return car;
        } else {
            car = cars[front];
            if (front == rear){
                front = -1;
                rear = -1;
            }else {
                front = (front + 1) % capacity;
                System.out.println("Car left: " + car);
                printQueue();
            }
            return(car);
        }

    }

    @Override
    public boolean isFull(){
        if (front == 0 && rear == capacity-1){
            return true;
        }
        if (front == rear + 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (front == -1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("No cars in line");
            return;
        }
        System.out.println("Currently in line: ");
        if (rear >= front) {
            for (int i = front; i <= rear; i++) {
                System.out.println((i + 1) + ": " + cars[i]);
            }
        } else {
            for (int i = front; i < capacity; i++) {
                System.out.println((i + 1) + ": " + cars[i]);
            }
            for (int i = 0; i <= rear; i++) {
                System.out.println((i + 1) + ": " + cars[i]);
            }
        }
    }
}
