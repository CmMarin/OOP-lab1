package task4;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SemaphoreTest {
    private Semaphore semaphore;

    @BeforeEach
    void setUp() {
        semaphore = new Semaphore();
    }

    @Test
    void testParseCarFromJsonFile() throws IOException {
        File tempFile = File.createTempFile("car", ".json");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("{\"id\":\"1\",\"type\":\"GAS\",\"passengers\":\"ROBOTS\",\"isDining\":true,\"consumption\":\"15\"}");
        }

        Car car = semaphore.parseCarFromJsonFile(tempFile);
        assertNotNull(car);
        assertEquals("1", car.getCarId());
        assertEquals("GAS", car.getType());
        assertEquals("ROBOTS", car.getPassengers());
        assertTrue(car.isDining());

        tempFile.delete();
    }

    @Test
    void testLoadCarsWithValidFiles() throws IOException {
        File tempFolder = new File("testFolder");
        tempFolder.mkdir();

        File gasFile = new File(tempFolder, "gasCar.json");
        File electricFile = new File(tempFolder, "electricCar.json");

        try (FileWriter writer = new FileWriter(gasFile)) {
            writer.write("{\"id\":\"1\",\"type\":\"GAS\",\"passengers\":\"ROBOTS\",\"isDining\":true,\"consumption\":\"15\"}");
        }

        try (FileWriter writer = new FileWriter(electricFile)) {
            writer.write("{\"id\":\"2\",\"type\":\"ELECTRIC\",\"passengers\":\"HUMANS\",\"isDining\":false,\"consumption\":\"20\"}");
        }

        semaphore.loadCars(tempFolder.getPath());

        assertFalse(semaphore.getGasQueue().isEmpty(), "Gas queue should not be empty after loading");
        assertFalse(semaphore.getElectricQueue().isEmpty(), "Electric queue should not be empty after loading");

        Car gasCar = (Car) semaphore.getGasQueue().dequeue();
        Car electricCar = (Car) semaphore.getElectricQueue().dequeue();

        assertEquals("1", gasCar.getCarId());
        assertEquals("2", electricCar.getCarId());

        gasFile.delete();
        electricFile.delete();
        tempFolder.delete();
    }

    @Test
    void testDistributeCarToStation() throws InterruptedException {
        Car gasCar = new Car("1", "GAS", "ROBOTS", true);
        Car electricCar = new Car("2", "ELECTRIC", "HUMANS", false);

        semaphore.getGasQueue().enqueue(gasCar);
        semaphore.getElectricQueue().enqueue(electricCar);

        assertFalse(semaphore.getGasQueue().isEmpty(), "Gas queue should have cars after enqueuing");
        assertFalse(semaphore.getElectricQueue().isEmpty(), "Electric queue should have cars after enqueuing");

        Car dequeuedGasCar = (Car) semaphore.getGasQueue().dequeue();
        Car dequeuedElectricCar = (Car) semaphore.getElectricQueue().dequeue();

        assertEquals("1", dequeuedGasCar.getCarId());
        assertEquals("2", dequeuedElectricCar.getCarId());
    }

    @Test
    void testQueuesAreFull() throws InterruptedException {
        Car gasCar = new Car("1", "GAS", "ROBOTS", true);

        for (int i = 0; i < 5; i++) {
            semaphore.getGasQueue().enqueue(gasCar);
        }

        assertTrue(semaphore.getGasQueue().isFull(), "Gas queue should be full after enqueuing 5 cars");

        semaphore.getGasQueue().dequeue();

        assertFalse(semaphore.getGasQueue().isFull(), "Gas queue should not be full after dequeuing one car");
    }

    @Test
    void testLoadingCompleteFlag() {
        assertFalse(semaphore.isLoadingComplete(), "Loading should not be marked complete initially");
        semaphore.loadCars("nonexistentFolder");
        assertTrue(semaphore.isLoadingComplete(), "Loading should be marked complete after attempting to load");
    }

    @Test
    void testServingCompleteFlag() {
        assertFalse(semaphore.isServingComplete(), "Serving should not be marked complete initially");
        semaphore.setServingComplete();
        assertTrue(semaphore.isServingComplete(), "Serving should be marked complete after setting");
    }
}
