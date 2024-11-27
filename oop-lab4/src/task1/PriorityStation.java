package task1;

import java.util.ArrayList;

public class PriorityStation implements Queue<String> {
    private static class Entry {
        String car;
        int priority;

        public Entry(String car, int priority) {
            this.car = car;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return car + " (Priority: " + priority + ")";
        }
    }

    ArrayList<Entry> queue;
    private int capacity;

    public PriorityStation(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>();
    }

    @Override
    public void enqueue(String car) {
        enqueue(car, 0);
    }

    public void enqueue(String car, int priority) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            Entry newEntry = new Entry(car, priority);
            queue.add(newEntry);


            queue.sort((e1, e2) -> Integer.compare(e2.priority, e1.priority));

            System.out.println("New car arrived: " + car + " with priority " + priority);
            printQueue();
        }
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            Entry car = queue.remove(0);
            System.out.println("Car left: " + car.car + " with priority " + car.priority);
            printQueue();
            return car.car;
        }
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return queue.size() == capacity;
    }

    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("No cars in line");
            return;
        }
        System.out.println("Currently in line:");
        for (int i = 0; i < queue.size(); i++) {
            System.out.println((i + 1) + ": " + queue.get(i));
        }
    }
}
