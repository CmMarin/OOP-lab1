package task5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Semaphore {
    private final StationQueue<Car> gasQueue;
    private final StationQueue<Car> electricQueue;
    private volatile boolean loadingComplete = false;
    private volatile boolean servingComplete = false;
    private volatile boolean keepLoading = true;

    private final String generatorScript = "C:\\Users\\Marin\\IdeaProjects\\oop-lab1\\GeneratorOOPlab4\\Resources\\generator.py";
    private final String folderPath = "C:\\Users\\Marin\\IdeaProjects\\oop-lab1\\GeneratorOOPlab4\\Resources\\ResourcesGEN"; //


    public Semaphore() {
        this.gasQueue = new StationQueue<>(5);
        this.electricQueue = new StationQueue<>(5);
    }

    public StationQueue<Car> getGasQueue() {
        return gasQueue;
    }

    public StationQueue<Car> getElectricQueue() {
        return electricQueue;
    }

    public boolean isLoadingComplete() {
        return loadingComplete;
    }

    public boolean isServingComplete() {
        return servingComplete;
    }

    public void setServingComplete() {
        this.servingComplete = true;
    }


    public void runGeneratorScript() {
        ProcessBuilder processBuilder = new ProcessBuilder("python", generatorScript);
        processBuilder.directory(new File(folderPath).getParentFile());
        try {
            processBuilder.start();
        } catch (IOException e) {
            System.err.println("Error executing generator script: " + e.getMessage());
        }
    }


    public void loadCarsFromFolder() {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try {
                    String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                    Car car = parseCarJson(content);
                    if ("GAS".equals(car.getType())) {
                        gasQueue.enqueue(car);
                    } else if ("ELECTRIC".equals(car.getType())) {
                        electricQueue.enqueue(car);
                    }
                    Files.delete(file.toPath());
                } catch (Exception e) {
                    System.err.println("Error reading or processing file: " + file.getName());
                }
            }
        }
    }


    public void scheduleCarLoading() {
        Thread loadingThread = new Thread(() -> {
            while (keepLoading) {
                try {
                    loadCarsFromFolder();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println("Loading thread interrupted: " + e.getMessage());
                }
            }
            System.out.println("Car loading task completed.");
        });

        loadingThread.start();
    }



    public void scheduleCarServing(GasStation gasStation, ElectricStation electricStation) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(gasStation::serveCars, 0, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(electricStation::serveCars, 0, 5, TimeUnit.SECONDS);
    }


    Car parseCarJson(String carJson) {
        String[] parts = carJson.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String id = parts[0].split(":")[1].trim();
        String type = parts[1].split(":")[1].trim();
        String passengers = parts[2].split(":")[1].trim();
        boolean isDining = Boolean.parseBoolean(parts[3].split(":")[1].trim());
        int consumption = Integer.parseInt(parts[4].split(":")[1].trim());

        return new Car(id, type, passengers, isDining);
    }

    public void stopCarLoading() {
        this.keepLoading = false;
        System.out.println("Car loading stopped.");
    }

}
