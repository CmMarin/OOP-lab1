package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class SimpleStationTest {
    private SimpleStation<Integer> station;
    private static final int MAX_CAPACITY = 50;

    @BeforeEach
    void setUp() {
        station = new SimpleStation<>();
    }

    @Test
    void testEnqueueDequeueSequence() {
        int[] testData = {1, 2, 3, 4, 5};

        for (int value : testData) {
            station.enqueue(value);
        }
        assertEquals(5, station.queueSize);

        for (int value : testData) {
            station.dequeue();
        }

        assertTrue(station.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40, 50})
    void testMaxCapacityEnqueue(int numElements) {
        for (int i = 0; i < numElements; i++) {
            station.enqueue(i);
        }

        assertEquals(numElements, station.queueSize);
    }

    @Test
    void testEdgeCaseEnqueueDequeue() {
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < MAX_CAPACITY; i++) {
            station.enqueue(counter.incrementAndGet());
        }

        assertTrue(station.isFull());

        for (int i = 0; i < MAX_CAPACITY / 2; i++) {
            station.dequeue();
        }


        for (int i = 0; i < MAX_CAPACITY / 2; i++) {
            station.enqueue(counter.incrementAndGet());
        }

        assertEquals(MAX_CAPACITY, station.queueSize);
    }

    @Test
    void testOverflowPrevention() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            station.enqueue(i);
        }

        int initialSize = station.queueSize;
        station.enqueue(MAX_CAPACITY + 1);

        assertEquals(initialSize, station.queueSize,
                "Queue should prevent adding elements beyond capacity");
    }

    @Test
    void testEmptyQueueBehavior() {
        assertTrue(station.isEmpty(), "Initial queue should be empty");

        assertNull(station.dequeue(), "Dequeue on empty queue should return null");

        assertEquals(0, station.queueSize, "Queue size should remain 0 after empty dequeue");
    }

    @Test
    void testMultiTypeCompatibility() {
        SimpleStation<String> stringStation = new SimpleStation<>();
        SimpleStation<Double> doubleStation = new SimpleStation<>();

        stringStation.enqueue("Test");
        doubleStation.enqueue(3.14);

        assertEquals(1, stringStation.queueSize);
        assertEquals(1, doubleStation.queueSize);
    }
}