package task3;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = Americano.makeAmericano(80, Coffee.Intensity.STRONG);
        Coffee coffee1 = Cappucino.makeCappucino(6, Coffee.Intensity.NORMAL);
        Coffee coffee2 = PumpkinSpiceLatte.makePumpkinSpice(3,60, Coffee.Intensity.LIGHT);
        Coffee coffee3 = SyrupCappucino.makeSyrupCappucino(SyrupCappucino.SyrupType.CHOCOLATE,20, Coffee.Intensity.LIGHT);
    }
}
