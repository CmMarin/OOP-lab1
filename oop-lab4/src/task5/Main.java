package task5;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        GasStation gasStation = new GasStation(semaphore.getGasQueue());
        ElectricStation electricStation = new ElectricStation(semaphore.getElectricQueue());

        semaphore.runGeneratorScript();


        semaphore.scheduleCarLoading();
        semaphore.scheduleCarServing(gasStation, electricStation);


        int timeoutInMilliseconds = 15000;
        try {
            Thread.sleep(timeoutInMilliseconds);
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }


        semaphore.getGasQueue().shutdown();
        semaphore.getElectricQueue().shutdown();
        semaphore.stopCarLoading();


        while (!semaphore.isServingComplete()) {
            if (semaphore.getGasQueue().isEmpty() && semaphore.getElectricQueue().isEmpty()) {
                semaphore.setServingComplete();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        gasStation.stopService();
        electricStation.stopService();


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

        System.exit(0);
    }

}
