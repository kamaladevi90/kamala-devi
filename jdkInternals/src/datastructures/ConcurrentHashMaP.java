package datastructures;


import java.util.concurrent.*;
import java.util.*;

public class ConcurrentHashMaP {
    public static void main(String[] args) throws InterruptedException {
        String text = "concurrent hash map example concurrent access hash map example map example";

        // Split text into words
        String[] words = text.split("\\s+");

        // Create ConcurrentHashMap to store word counts
        ConcurrentHashMap<String, Integer> wordCounts = new ConcurrentHashMap<>();

        // Executor to run multiple threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (String word : words) {
            executor.submit(() -> {
                // Use merge to atomically update count
                wordCounts.merge(word, 1, Integer::sum);
            });
        }

        // Shutdown executor and wait for tasks to finish
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        // Print results
        wordCounts.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
