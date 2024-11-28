package task3;

public class Main {
    public static void main(String[] args) {

        Queue<Car> carQueue = new StationQueue<>();


        CarStation carStation = new CarStation(carQueue);


        Car car1 = new Car("CAR001", "Gas", "Robot", true);
        Car car2 = new Car("CAR002", "Electric", "Human", false);
        Car car3 = new Car("CAR003", "Gas", "Human", true);
        Car car4 = new Car("CAR004", "Gas", "Robot", true);
        Car car5 = new Car("CAR005", "Electric", "Human", true);
        Car car6 = new Car("CAR006", "Electric", "Human", true);

        carStation.addCar(car1);
        carStation.addCar(car2);
        carStation.addCar(car3);
        carStation.addCar(car4);
        carStation.addCar(car5);
        carStation.addCar(car6);

        System.out.println("\n--- Starting to serve cars ---");
        carStation.serveCars();

        System.out.println("\n--- Final Queue Status ---");
        carQueue.printQueue();

        carStation.printStats();
    }
}
