package task3;

public class PeopleDinner implements Dineable{
    @Override
    public void serveDinner(String carId) {
        System.out.println("Serving people in car: " + carId);
    }
}

