package task2;

public class Main {
    public static void main(String[] args) {

        Coffee americano = new Americano(50);
        Coffee cappucino = new Cappucino(50);
        Coffee syrupCappucino = new SyrupCappucino(SyrupCappucino.SyrupType.CHOCOLATE, 50);
        Coffee pumpkin = new PumpkinSpiceLatte(50, 3);

        americano.printDetails();
        cappucino.printDetails();
        syrupCappucino.printDetails();
        pumpkin.printDetails();
    }
}
