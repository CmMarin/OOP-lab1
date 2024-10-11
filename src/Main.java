public class Main {
    public static void main(String[] args) {
        System.out.println("Papers, please!");

        Universe universe = new Universe();

        Aliens alien1 = new Aliens("Thor", "Asgard", true, 800 );
        Aliens alien2 = new Aliens("Hulk", null, false, 360 );
        Aliens alien3 = new Aliens("Yoda", null, false, 800 );


        universe.addAlien(alien1);
        universe.addAlien(alien2);
        universe.addAlien(alien3);

        universe.displayAliens();
    }


}