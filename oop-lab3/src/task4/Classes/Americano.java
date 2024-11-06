package task4.Classes;

class Americano extends Coffee {
    private static int mlOfWater;

    public Americano(int mlOfWater, Intensity intensity) {
        super("Americano", intensity);
        this.mlOfWater = mlOfWater;
    }



    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Added " + mlOfWater  + " ml of water " );

    }

    public static Americano makeAmericano(int mlOfWater, Intensity intensity){
        Americano coffee = new Americano(mlOfWater, intensity);
        coffee.makeCoffee();
        System.out.println("Adding: " + mlOfWater + " ml of water " );
        return coffee;
    };
}
