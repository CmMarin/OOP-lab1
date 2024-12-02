package task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private List<Car> cars;
    private PeopleDinner peopleDinner;
    private RobotDinner robotDinner;
    private GasStation gasStation;
    private ElectricStation electricStation;

    @BeforeEach
    public void setUp() {
        cars = new ArrayList<>();
        peopleDinner = new PeopleDinner();
        robotDinner = new RobotDinner();
        gasStation = new GasStation();
        electricStation = new ElectricStation();
    }

    @Test
    public void testServeAndRefuelCars() {
        // Generate a large number of cars with random attributes
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            String carId = String.valueOf(i);
            String type = random.nextBoolean() ? "Gas" : "Electric";
            String passengers = random.nextBoolean() ? "Human" : "Robot";
            boolean isDining = random.nextBoolean();
            cars.add(new Car(carId, type, passengers, isDining));
        }

        for (Car car : cars) {
            Main.serveDinner(car, peopleDinner, robotDinner);
            Main.refuelCar(car, gasStation, electricStation);
        }

    }

    @Test
    public void testInvalidData() {
        // Test with invalid car attributes
        Car invalidCar1 = new Car(null, "Gas", "Human", true);
        Car invalidCar2 = new Car("123", null, "Robot", true);
        Car invalidCar3 = new Car("124", "Electric", null, true);
        Car invalidCar4 = new Car("125", "Electric", "Human", false);

        assertThrows(NullPointerException.class, () -> Main.serveDinner(invalidCar1, peopleDinner, robotDinner));
        assertThrows(NullPointerException.class, () -> Main.refuelCar(invalidCar2, gasStation, electricStation));
        assertThrows(NullPointerException.class, () -> Main.serveDinner(invalidCar3, peopleDinner, robotDinner));
        assertDoesNotThrow(() -> Main.refuelCar(invalidCar4, gasStation, electricStation));
    }

    @Test
    public void stressTest() {
        // Stress test with a very large number of cars
        for (int i = 0; i < 100000; i++) {
            String carId = String.valueOf(i);
            String type = i % 2 == 0 ? "Gas" : "Electric";
            String passengers = i % 2 == 0 ? "Human" : "Robot";
            boolean isDining = i % 2 == 0;
            cars.add(new Car(carId, type, passengers, isDining));
        }

        for (Car car : cars) {
            Main.serveDinner(car, peopleDinner, robotDinner);
            Main.refuelCar(car, gasStation, electricStation);
        }

    }
}
