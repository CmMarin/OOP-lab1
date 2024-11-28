package task3;

public class CarStation {
    private Queue<Car> queue;

    int electricStationCustomers = 0;
    int gasStationCustomers = 0;
    int humanCustomersServed = 0;
    int robotCustomersServed = 0;

    public CarStation(Queue<Car> queue) {
        this.queue = queue;
    }

    public void addCar(Car car) {
        if (!queue.isFull()) {
            queue.enqueue(car);
        } else {
            System.out.println("Queue is full. Cannot add car.");
        }
    }

    public void serveCars() {
        while (!queue.isEmpty()) {
            Car car = (Car) queue.dequeue();

            Refuable refuelingService;
            if (car.getType().equals("Electric")) {
                refuelingService = new ElectricStation();
                electricStationCustomers++;
            } else {
                refuelingService = new GasStation();
                gasStationCustomers++;
            }

            Dineable diningService;
            if (car.getPassengers().equals("Human")) {
                diningService = new PeopleDinner();
                humanCustomersServed++;
            } else {
                diningService = new RobotDinner();
                robotCustomersServed++;
            }

            if (car.isDining()) {
                diningService.serveDinner(car.getCarId());
            }


            refuelingService.refuel(car.getCarId());

            System.out.println("Car with ID: " + car.getCarId() + " has been served.\n");
        }
    }

    public void printStats() {
        System.out.println("\nElectric Stations Customer Count: " + electricStationCustomers);
        System.out.println("Gas Stations Customer Count: " + gasStationCustomers);
        System.out.println("Human Customers Served: " + humanCustomersServed);
        System.out.println("Robot Customers Served: " + robotCustomersServed);
    }
}
