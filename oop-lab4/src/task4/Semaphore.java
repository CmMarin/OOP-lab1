package task4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Semaphore {
    private final StationQueue<Car> gasQueue;
    private final StationQueue<Car> electricQueue;
    private boolean isLoadingComplete = false;
    private boolean isServingComplete = false;

    public Semaphore() {
        this.gasQueue = new StationQueue<>(5);
        this.electricQueue = new StationQueue<>(5);
    }

    public void loadCars(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("\nNo car files found in the specified folder: " + folderPath + "\n");
            isLoadingComplete = true;
            return;
        }

//        System.out.println("\nStarting to load car files from: " + folderPath + "\n");

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                try {
                    Car car = parseCarFromJsonFile(file);


                    while (gasQueue.isFull() && electricQueue.isFull()) {
                        System.out.println("\nBoth queues are full. Pausing loading...\n");
                        synchronized (this) {
                            wait();
                        }
                    }

                    distributeCarToStation(car);
//                    System.out.println("Successfully loaded car from file: " + file.getName() + "\n");
                } catch (IOException | InterruptedException e) {
                    System.out.println("\nFailed to load car from file: " + file.getName() + "\n");
                }
            }
        }


        isLoadingComplete = true;
        System.out.println("\nAll car files processed successfully.\n");
    }

    private void distributeCarToStation(Car car) throws InterruptedException {
//        System.out.println("\nDistributing car with ID: " + car.getCarId() + " to the appropriate station...");


        if (car.getType().equalsIgnoreCase("GAS")) {
            gasQueue.enqueue(car);
//            System.out.println("Car " + car.getCarId() + " enqueued to the Gas Station queue.\n");
        } else if (car.getType().equalsIgnoreCase("ELECTRIC")) {
            electricQueue.enqueue(car);
//            System.out.println("Car " + car.getCarId() + " enqueued to the Electric Station queue.\n");
        } else {
            System.out.println("\nUnknown car type: " + car.getType() + "\n");
        }


        synchronized (this) {
            notify();
        }
    }

    public Car parseCarFromJsonFile(File file) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine().trim());
            }
        }

        String json = jsonContent.toString();
        String id = "";
        String type = "";
        String passengers = "";
        boolean isDining = false;
        String consumption = "";


        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();


            switch (key) {
                case "id":
                    id = value;
                    break;
                case "type":
                    type = value;
                    break;
                case "passengers":
                    passengers = value;
                    break;
                case "isDining":
                    isDining = Boolean.parseBoolean(value);
                    break;
                case "consumption":
                    consumption = value;
                    break;
                default:
                    System.out.println("\nUnknown key: " + key + "\n");
                    break;
            }
        }

//        System.out.println("\nParsed car details: ID = " + id + ", Type = " + type + ", Passengers = " + passengers);

        return new Car(id, type, passengers, isDining);
    }

    public StationQueue<Car> getGasQueue() {
        return gasQueue;
    }

    public StationQueue<Car> getElectricQueue() {
        return electricQueue;
    }

    public boolean isLoadingComplete() {
        return isLoadingComplete;
    }

    public void setServingComplete() {
        isServingComplete = true;
    }

    public boolean isServingComplete() {
        return isServingComplete;
    }

    public synchronized void notifyLoadingComplete() {

        notifyAll();
        System.out.println("\nLoading process has been completed.\n");
    }
}
