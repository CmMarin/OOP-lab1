package task1;

public class SyrupCappucino extends Cappucino {
    int mltrOfMilk;
    static String coffee = "SyrupCappucino";
    private Intensity intensityOfCoffee;
    private SyrupType syrup;

    enum SyrupType{
        MACADAMIA,
        VANILLA,
        COCONUT,
        CARAMEL,
        CHOCOLATE,
        POPCORN,
    }
}
