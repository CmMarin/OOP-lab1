package task2;

public class PeopleDinner implements Dineable{
    private int count = 0;

    @Override
    public void serveDinner(String cardId) {
            System.out.println("Dinner is served to people in car: " + cardId);
            count++;
            System.out.println("Human Customer nr: " + count);
    }
}
