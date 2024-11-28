package task3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarStationTest {

    private CarStation carStation;
    private Queue<Car> queue;

    @Before
    public void setUp() {
        // Create a mock queue for testing
        queue = new StationQueue<>();
        // Initialize the CarStation with the mock queue
        carStation = new CarStation(queue);
    }

    @Test
    public void testAddCarToQueue() {
        Car car1 = new Car("CAR001", "Gas", "Human", true);

        carStation.addCar(car1);

        assertFalse(queue.isEmpty());
        System.out.println("testAddCarToQueue: Car added to queue successfully");
    }

    @Test
    public void testServeCarsWithDining() {

        Car car1 = new Car("CAR001", "Gas", "Human", true);

        carStation.addCar(car1);

        carStation.serveCars();

        System.out.println("testServeCarsWithDining: Car served with dining service");
    }

    @Test
    public void testServeCarsWithoutDining() {

        Car car2 = new Car("CAR002", "Electric", "Robot", false);

        carStation.addCar(car2);

        carStation.serveCars();


        System.out.println("testServeCarsWithoutDining: Car served with refueling only");
    }

    @Test
    public void testStatsAfterServingCars() {

        Car car1 = new Car("CAR001", "Gas", "Human", true);
        Car car2 = new Car("CAR002", "Electric", "Robot", false);

        carStation.addCar(car1);
        carStation.addCar(car2);

        carStation.serveCars();

        // Print stats to verify
        carStation.printStats();


        assertEquals(1, carStation.electricStationCustomers); // 1 electric car
        assertEquals(1, carStation.gasStationCustomers); // 1 gas car
        assertEquals(1, carStation.humanCustomersServed); // 1 human
        assertEquals(1, carStation.robotCustomersServed); // 1 robot

        System.out.println("testStatsAfterServingCars: Stats correctly tracked");
    }

    @Test
    public void testQueueOverflow() {
        for (int i = 0; i < 5; i++) {
            carStation.addCar(new Car("CAR" + i, "Gas", "Human", true));
        }


        Car car6 = new Car("CAR006", "Electric", "Robot", false);
        carStation.addCar(car6);


        assertTrue(queue.isFull());

        System.out.println("testQueueOverflow: Queue overflow handled");
    }

    @Test
    public void testServeCarsWhenQueueIsEmpty() {
        // Serve cars when queue is empty
        carStation.serveCars();

        // There should be no output or errors, just ensure nothing happens.
        System.out.println("testServeCarsWhenQueueIsEmpty: No cars to serve, queue is empty");
    }
}
