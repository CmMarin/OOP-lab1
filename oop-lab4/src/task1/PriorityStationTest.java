package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityStationTest {
    private PriorityStation priorityStation;
    private static final int CAPACITY = 5;

    @BeforeEach
    void setUp() {
        priorityStation = new PriorityStation(CAPACITY);
    }

    @Test
    void testInitialState() {
        assertTrue(priorityStation.isEmpty());
        assertFalse(priorityStation.isFull());
    }

    @Test
    void testBasicPriorityEnqueue() {
        priorityStation.enqueue("Car1", 3);
        priorityStation.enqueue("Car2", 1);
        priorityStation.enqueue("Car3", 2);

        assertEquals("Car1", priorityStation.dequeue());
        assertEquals("Car3", priorityStation.dequeue());
        assertEquals("Car2", priorityStation.dequeue());
    }

    @Test
    void testDefaultPriorityEnqueue() {
        priorityStation.enqueue("Car1");
        priorityStation.enqueue("Car2");

        assertEquals("Car1", priorityStation.dequeue());
        assertEquals("Car2", priorityStation.dequeue());
    }

    @Test
    void testQueueFull() {
        for (int i = 1; i <= CAPACITY; i++) {
            priorityStation.enqueue("Car" + i, i);
        }

        assertTrue(priorityStation.isFull());
        priorityStation.enqueue("ExtraCar", 6);
        assertEquals(CAPACITY, priorityStation.queue.size());
    }

    @Test
    void testDequeueOrder() {
        priorityStation.enqueue("LowPriority", 1);
        priorityStation.enqueue("HighPriority", 5);
        priorityStation.enqueue("MediumPriority", 3);

        assertEquals("HighPriority", priorityStation.dequeue());
        assertEquals("MediumPriority", priorityStation.dequeue());
        assertEquals("LowPriority", priorityStation.dequeue());
    }

    @Test
    void testEmptyQueueDequeue() {
        assertNull(priorityStation.dequeue());
        assertTrue(priorityStation.isEmpty());
    }
}