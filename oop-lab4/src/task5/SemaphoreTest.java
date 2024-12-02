package task5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SemaphoreTest {
    private Semaphore semaphore;

    @BeforeEach
    void setUp() {
        semaphore = new Semaphore();
    }

    @Test
    @DisplayName("Test parsing of a gas car JSON string")
    void testParseCarJson_GasCar() {
        System.out.println("\n--- Test: Parsing Gas Car JSON ---");
        String jsonString = "{id: 1, type: GAS, passengers: ROBOTS, isDining: true, consumption: 15}";
        Car car = semaphore.parseCarJson(jsonString);

        System.out.println("Parsed Car: " + car);
        assertNotNull(car, "Car should be parsed successfully");
        assertEquals("1", car.getCarId(), "Car ID should match");
        assertEquals("GAS", car.getType(), "Car type should match");
        assertEquals("ROBOTS", car.getPassengers(), "Passengers should match");
        assertTrue(car.isDining(), "isDining should be true");
    }

    @Test
    @DisplayName("Test parsing of an electric car JSON string")
    void testParseCarJson_ElectricCar() {
        System.out.println("\n--- Test: Parsing Electric Car JSON ---");
        String jsonString = "{id: 2, type: ELECTRIC, passengers: HUMANS, isDining: false, consumption: 20}";
        Car car = semaphore.parseCarJson(jsonString);

        System.out.println("Parsed Car: " + car);
        assertNotNull(car, "Car should be parsed successfully");
        assertEquals("2", car.getCarId(), "Car ID should match");
        assertEquals("ELECTRIC", car.getType(), "Car type should match");
        assertEquals("HUMANS", car.getPassengers(), "Passengers should match");
        assertFalse(car.isDining(), "isDining should be false");
    }

    @Test
    @DisplayName("Test loading cars into gas and electric queues")
    void testLoadCarsToQueues() {
        System.out.println("\n--- Test: Loading Cars to Queues ---");
        String gasCar = "{id: 1, type: GAS, passengers: ROBOTS, isDining: true, consumption: 15}";
        String electricCar = "{id: 2, type: ELECTRIC, passengers: HUMANS, isDining: false, consumption: 20}";

        Car parsedGasCar = semaphore.parseCarJson(gasCar);
        Car parsedElectricCar = semaphore.parseCarJson(electricCar);

        semaphore.getGasQueue().enqueue(parsedGasCar);
        semaphore.getElectricQueue().enqueue(parsedElectricCar);

        System.out.println("Gas Queue after Enqueue: " + semaphore.getGasQueue());
        System.out.println("Electric Queue after Enqueue: " + semaphore.getElectricQueue());

        Car dequeuedGasCar = (Car) semaphore.getGasQueue().dequeue();
        Car dequeuedElectricCar = (Car) semaphore.getElectricQueue().dequeue();

        System.out.println("Dequeued Gas Car: " + dequeuedGasCar);
        System.out.println("Dequeued Electric Car: " + dequeuedElectricCar);

        assertEquals(parsedGasCar, dequeuedGasCar, "Dequeued gas car should match the enqueued car");
        assertEquals(parsedElectricCar, dequeuedElectricCar, "Dequeued electric car should match the enqueued car");
    }

    @Test
    @DisplayName("Test parsing invalid JSON strings")
    void testInvalidJsonParsing() {
        System.out.println("\n--- Test: Parsing Invalid JSON Strings ---");
        String[] invalidJsons = {
                "{invalid: json}",
                "{type: INVALID}",
                "completely wrong format"
        };

        for (String invalidJson : invalidJsons) {
            System.out.println("Testing invalid JSON: " + invalidJson);
            assertThrows(Exception.class, () -> semaphore.parseCarJson(invalidJson),
                    "Invalid JSON should throw an exception: " + invalidJson);
        }
    }

    @Test
    @DisplayName("Test initial state of the semaphore")
    void testInitialState() {
        System.out.println("\n--- Test: Initial State of Semaphore ---");
        assertNotNull(semaphore.getGasQueue(), "Gas queue should be initialized");
        assertNotNull(semaphore.getElectricQueue(), "Electric queue should be initialized");

        System.out.println("Gas Queue is empty: " + semaphore.getGasQueue().isEmpty());
        System.out.println("Electric Queue is empty: " + semaphore.getElectricQueue().isEmpty());

        assertTrue(semaphore.getGasQueue().isEmpty(), "Gas queue should be initially empty");
        assertTrue(semaphore.getElectricQueue().isEmpty(), "Electric queue should be initially empty");
    }

    @Test
    @DisplayName("Test setting serving complete flag")
    void testSetServingComplete() {
        System.out.println("\n--- Test: Setting Serving Complete Flag ---");
        assertFalse(semaphore.isServingComplete(), "Serving should not be complete initially");

        semaphore.setServingComplete();
        System.out.println("Serving Complete Flag: " + semaphore.isServingComplete());

        assertTrue(semaphore.isServingComplete(), "Serving should be marked complete");
    }

    @Test
    @DisplayName("Test scheduling car loading process")
    void testScheduleCarLoading() throws InterruptedException {
        System.out.println("\n--- Test: Scheduling Car Loading Process ---");
        semaphore.scheduleCarLoading();

        // Wait a short time to ensure loading starts
        Thread.sleep(100);

        // Stop loading
        semaphore.stopCarLoading();

        System.out.println("Loading Complete Flag: " + semaphore.isLoadingComplete());
        assertFalse(semaphore.isLoadingComplete(), "Loading should not be marked as complete");
    }

    @Test
    @DisplayName("Test adding multiple car types to their respective queues")
    void testMultipleCarTypes() {
        System.out.println("\n--- Test: Adding Multiple Car Types ---");
        String[] carJsons = {
                "{id: 1, type: GAS, passengers: ROBOTS, isDining: true, consumption: 15}",
                "{id: 2, type: ELECTRIC, passengers: HUMANS, isDining: false, consumption: 20}",
                "{id: 3, type: GAS, passengers: CYBORGS, isDining: true, consumption: 25}"
        };

        for (String jsonCar : carJsons) {
            Car car = semaphore.parseCarJson(jsonCar);
            System.out.println("Parsed Car: " + car);

            if (car.getType().equals("GAS")) {
                semaphore.getGasQueue().enqueue(car);
            } else if (car.getType().equals("ELECTRIC")) {
                semaphore.getElectricQueue().enqueue(car);
            }
        }

        System.out.println("Gas Queue: " + semaphore.getGasQueue());
        System.out.println("Electric Queue: " + semaphore.getElectricQueue());

        Car gasCar1 = (Car) semaphore.getGasQueue().dequeue();
        Car gasCar2 = (Car) semaphore.getGasQueue().dequeue();
        Car electricCar = (Car) semaphore.getElectricQueue().dequeue();

        System.out.println("Dequeued Gas Cars: " + gasCar1 + ", " + gasCar2);
        System.out.println("Dequeued Electric Car: " + electricCar);

        assertEquals("1", gasCar1.getCarId(), "First gas car ID should match");
        assertEquals("3", gasCar2.getCarId(), "Second gas car ID should match");
        assertEquals("2", electricCar.getCarId(), "Electric car ID should match");
    }
}
