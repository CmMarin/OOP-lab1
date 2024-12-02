package task5;

public abstract class CarStation {
    protected StationQueue<Car> queue;

    public CarStation(StationQueue<Car> queue) {
        this.queue = queue;
    }

    public abstract void serveCars();
}
