package task5;

public class ElectricStation extends CarStation implements Refuable {
    private int electricCustomersServed = 0;
    private int humanCustomersServed = 0;
    private int robotCustomersServed = 0;
    private boolean isRunning = true;

    private final Dineable peopleDinner;
    private final Dineable robotDinner;

    public ElectricStation(StationQueue<Car> queue) {
        super(queue);
        this.peopleDinner = new PeopleDinner();
        this.robotDinner = new RobotDinner();
    }

    @Override
    public void serveCars() {
        new Thread(() -> {
            while (isRunning) {
                Car car = (Car) queue.dequeue();
                if (car != null) {
                    refuel(car.getCarId());
                    separateAndServe(car);
                    electricCustomersServed++;
                    System.out.println("\n===============================");
                    System.out.println("Electric car " + car.getCarId() + " has been successfully served.");
                    System.out.println("===============================\n");
                }
            }
        }).start();
    }

    @Override
    public void refuel(String carId) {
        System.out.println("\nStarting to charge car: " + carId + "...");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Charging completed for car: " + carId + ".\n");
    }

    private void separateAndServe(Car car) {
        System.out.println("Processing car with passengers: " + car.getPassengers() + "\n");

        if (car.getPassengers().equalsIgnoreCase("People")) {
            humanCustomersServed++;
            System.out.println("Human passengers detected. Serving car: " + car.getCarId());
            peopleDinner.serveDinner(car.getCarId());
        } else if (car.getPassengers().equalsIgnoreCase("Robots")) {
            robotCustomersServed++;
            System.out.println("Robot passengers detected. Serving car: " + car.getCarId());
            robotDinner.serveDinner(car.getCarId());
        }
    }

    public void stopService() {
        isRunning = false;
        System.out.println("\nThe Electric Station service has been stopped.\n");
    }

    public int getElectricCustomersServed() {
        return electricCustomersServed;
    }

    public int getHumanCustomersServed() {
        return humanCustomersServed;
    }

    public int getRobotCustomersServed() {
        return robotCustomersServed;
    }
}
