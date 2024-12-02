package task2;

public class Main {

    public static void main(String[] args) {

        Car car1 = new Car("223", "Gas", "Robot", true);
        Car car2 = new Car("221", "Electric", "Human", true);
        Car car3 = new Car("668", "Electric", "Human", true);

        PeopleDinner peopleDinner = new PeopleDinner();
        RobotDinner robotDinner = new RobotDinner();
        GasStation gasStation = new GasStation();
        ElectricStation electricStation = new ElectricStation();

        serveDinner(car1, peopleDinner, robotDinner);
        serveDinner(car2, peopleDinner, robotDinner);
        serveDinner(car3, peopleDinner, robotDinner);

        refuelCar(car1,gasStation,electricStation);
        refuelCar(car2,gasStation,electricStation);
        refuelCar(car3,gasStation,electricStation);

    }


    public static void serveDinner(Car car, PeopleDinner peopleDinner, RobotDinner robotDinner) {
        if (car.isDining()) {
            if (car.getPassengers().equals("Human")) {
                peopleDinner.serveDinner(car.getCarId());
            } else if (car.getPassengers().equals("Robot")) {
                robotDinner.serveDinner(car.getCarId());
            }
        } else {
            System.out.println("Car id: " + car.getCarId() + " has no money for dinner\n");
        }
    }
    public static void refuelCar(Car car, GasStation gasStation, ElectricStation electricStation) {
        if (car.getType().equals("Gas")) {
            gasStation.refuel(car.getCarId());
        } else if (car.getType().equals("Electric")) {
            electricStation.refuel(car.getCarId());
        }
    }
}
