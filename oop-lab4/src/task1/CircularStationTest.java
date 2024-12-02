package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularStationTest {
    private CircularStation<Integer> intStation;
    private CircularStation<String> stringStation;

    @BeforeEach
    void setUp() {
        intStation = new CircularStation<>();
        stringStation = new CircularStation<>();
    }

    @Test
    void testInitialState() {
        assertTrue(intStation.isEmpty());
        assertFalse(intStation.isFull());
    }

    @Test
    void testEnqueueDequeue() {
        intStation.enqueue(1);
        intStation.enqueue(2);
        assertFalse(intStation.isEmpty());
        assertEquals(1, intStation.dequeue());
        assertEquals(2, intStation.dequeue());
        assertTrue(intStation.isEmpty());
    }

    @Test
    void testCircularBehavior() {
        for (int i = 1; i <= 5; i++) {
            intStation.enqueue(i);
        }
        assertTrue(intStation.isFull());

        for (int i = 1; i <= 3; i++) {
            intStation.dequeue();
        }

        intStation.enqueue(6);
        intStation.enqueue(7);

        assertEquals(4, intStation.dequeue());
        assertEquals(5, intStation.dequeue());
        assertEquals(6, intStation.dequeue());
        assertEquals(7, intStation.dequeue());
    }

    @Test
    void testOverflow() {
        for (int i = 1; i <= 5; i++) {
            intStation.enqueue(i);
        }

        intStation.enqueue(6);  // Should not modify queue
        assertTrue(intStation.isFull());
    }

    @Test
    void testMultiTypeSupport() {
        stringStation.enqueue("Hello");
        stringStation.enqueue("World");
        assertEquals("Hello", stringStation.dequeue());
        assertEquals("World", stringStation.dequeue());
    }

    @Test
    void testEdgeCases() {
        // Single element operations
        intStation.enqueue(42);
        assertFalse(intStation.isEmpty());
        assertEquals(42, intStation.dequeue());
        assertTrue(intStation.isEmpty());

        // Reset after full dequeue
        for (int i = 1; i <= 5; i++) {
            intStation.enqueue(i);
        }
        while (!intStation.isEmpty()) {
            intStation.dequeue();
        }
        assertTrue(intStation.isEmpty());
    }
}