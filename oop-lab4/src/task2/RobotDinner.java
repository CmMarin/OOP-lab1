package task2;

public class RobotDinner implements Dineable{
    private int count = 0;

    @Override
    public void serveDinner(String cardId) {
        System.out.println("Dinner is served to robots in car: " + cardId);
        count++;
        System.out.println("Robot Customer nr: " + count);

}
}

