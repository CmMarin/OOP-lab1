package task2;

public class Cappucino extends Coffee {
    private int mlOfMilk;

    public Cappucino(int mlOfMilk) {
        super("Cappucino",Intensity.NORMAL);
        this.mlOfMilk = mlOfMilk;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Added " + mlOfMilk  + " ml of milk ");

    }
}
