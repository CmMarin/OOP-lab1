package task2;

public class SyrupCappucino extends Cappucino {
    private SyrupType syrupType;
    enum SyrupType{
        MACADAMIA,
        VANILLA,
        COCONUT,
        CARAMEL,
        CHOCOLATE,
        POPCORN,
    }


    public SyrupCappucino(SyrupType syrupType, int mlOfMilk) {
        super(mlOfMilk);
        this.syrupType = syrupType;
        getName("Syrup Cappucino") ;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("With " + syrupType );

    }
}
