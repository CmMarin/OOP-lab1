package task4;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        String folderPath = "C:\\Users\\Marin\\IdeaProjects\\oop-lab1\\oop-lab4\\Resources";  // Replace with your folder path
        GasStation gasStation = new GasStation(semaphore.getGasQueue());
        ElectricStation electricStation = new ElectricStation(semaphore.getElectricQueue());
        gasStation.serveCars();
        electricStation.serveCars();
        // Load the cars from the folder
        semaphore.loadCars(folderPath);

        // Wait until the loading is complete
        while (!semaphore.isLoadingComplete()) {
            try {
                Thread.sleep(500);  // Check every 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Start serving the cars at both stations


        // After all cars are loaded, shut down the queues
        semaphore.getGasQueue().shutdown();
        semaphore.getElectricQueue().shutdown();

        // Wait until all cars are served (by checking both loading and serving status)
        while (!semaphore.isServingComplete()) {
            if (semaphore.getGasQueue().isEmpty() && semaphore.getElectricQueue().isEmpty()) {
                semaphore.setServingComplete();  // All cars served
            }

            try {
                Thread.sleep(500);  // Wait for threads to process cars
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Stop the service threads after all cars have been served
        gasStation.stopService();
        electricStation.stopService();

        // After serving completes, print the final stats
        System.out.println("Final Statistics:");
        System.out.println("----------------------------");
        System.out.println("Gas Station served " + gasStation.getGasCustomersServed() + " gas cars.");
        System.out.println("Electric Station served " + electricStation.getElectricCustomersServed() + " electric cars.");
        System.out.println("Total humans served at Gas Station: " + gasStation.getHumanCustomersServed());
        System.out.println("Total robots served at Gas Station: " + gasStation.getRobotCustomersServed());
        System.out.println("Total humans served at Electric Station: " + electricStation.getHumanCustomersServed());
        System.out.println("Total robots served at Electric Station: " + electricStation.getRobotCustomersServed());
        System.out.println("----------------------------");
        System.out.println("All cars have been processed and served.");
    }
}
