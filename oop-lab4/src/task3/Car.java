package task3;

public class Car {
    private String carId;
    private String type;
    private String passengers;
    private boolean isDining;

    public Car(String carId, String type, String passengers, boolean isDining) {
        this.carId = carId;
        this.type = type;
        this.passengers = passengers;
        this.isDining = isDining;
    }

    public String getCarId() {
        return carId;
    }

    public String getType() {
        return type;
    }

    public String getPassengers() {
        return passengers;
    }

    public boolean isDining() {
        return isDining;
    }

    @Override
    public String toString() {
        return carId;
    }
}