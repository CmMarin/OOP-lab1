package task2;

public class Main {
    public static void main(String[] args) {

        Coffee americano = new Americano(50, Coffee.Intensity.STRONG);
        Coffee cappucino = new Cappucino(50, Coffee.Intensity.NORMAL);
        Coffee syrupCappucino = new SyrupCappucino(SyrupCappucino.SyrupType.CHOCOLATE, 50, Coffee.Intensity.LIGHT);
        Coffee pumpkin = new PumpkinSpiceLatte(50, 3, Coffee.Intensity.LIGHT);

        americano.printDetails();
        cappucino.printDetails();
        syrupCappucino.printDetails();
        pumpkin.printDetails();
    }
}
