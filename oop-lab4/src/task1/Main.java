package task1;

public class Main {
    public static void main(String[] args) {
//    SimpleStation cars = new SimpleStation();
//    cars.enqueue("Toyota");
//    cars.enqueue("BMW");
//    cars.enqueue("Audi");
//    cars.enqueue("Audi R8");
//    cars.enqueue("Audi A4");
//    cars.enqueue("Audi A2");
//    cars.dequeue();
//    cars.dequeue();
//    cars.enqueue("Ford");
//    cars.enqueue("Mustang");


//        CircularStation station = new CircularStation();
//        station.enqueue("Skoda");
//        station.enqueue("Audi");
//        station.enqueue("Audi A4");
//        station.enqueue("Audi A7");
//        station.enqueue("Audi A8");
//        station.enqueue("Audi A1");
//        station.dequeue();
//        station.enqueue("Supra");


        PriorityStation station = new PriorityStation(5);
        station.enqueue("Skoda",4);
        station.enqueue("BMW",5);
        station.enqueue("Audi",9);
        station.enqueue("BMW series 1",10);
        station.enqueue("Audi R8",10);

        station.dequeue();
        station.enqueue("Audi A4",10);
    }
}
