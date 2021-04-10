import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Road extends Stage {
    private AtomicInteger winner = new AtomicInteger(0);
    private static final int CARS_COUNT = 4;
    private CyclicBarrier road = new CyclicBarrier(CARS_COUNT);

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            road.await();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (this.length == 40){
                NeedForSpeed.finish.countDown();
                if(winner.incrementAndGet() == 1) {
                    System.out.println(c.getName() + " " + "- WIN!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
