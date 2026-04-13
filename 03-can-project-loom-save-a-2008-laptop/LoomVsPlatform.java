import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LoomVsPlatform {
    // ADJUST THESE FOR CURRENT HARDWARE
    private static final int TASK_COUNT = 100_000; 
    private static final int IO_SIMULATION_MS = 1000;

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Please specify mode: PLATFORM or VIRTUAL");
            return;
        }

        String mode = args[0].toUpperCase();
        LongAdder completedTasks = new LongAdder();
        
        System.out.println("Starting benchmark in " + mode + " mode...");
        long startTime = System.currentTimeMillis();

        try (var executor = mode.equals("VIRTUAL") 
                ? Executors.newVirtualThreadPerTaskExecutor() 
                : Executors.newCachedThreadPool()) {

            IntStream.range(0, TASK_COUNT).forEach(i -> {
                executor.submit(() -> {
                    try {
                        // Simulate a database/API call
                        Thread.sleep(Duration.ofMillis(IO_SIMULATION_MS));
                        completedTasks.increment();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
        } // This auto-closes and waits for all tasks to finish

        long endTime = System.currentTimeMillis();
        System.out.printf("Summary for %s:%n", mode);
        System.out.printf("Completed: %d tasks%n", completedTasks.sum());
        System.out.printf("Total Time: %d ms%n", (endTime - startTime));
        System.out.println("---------------------------------------");
    }
}