package services;

import play.libs.concurrent.Futures.*;
import play.libs.concurrent.*;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import javax.inject.Inject;

public class CalculationService {
    private final Futures futures;
    private final Executor customExecutor = ForkJoinPool.commonPool();

    @Inject
    public CalculationService(Futures futures) {
        this.futures = futures;
    }

    CompletionStage<Double> calculatePIAsync() {
        return CompletableFuture.completedFuture(3.14);
    }

    CompletionStage<Double> callWithOneSecondTimeout() {
        return futures.timeout(calculatePIAsync(), Duration.ofSeconds(1));
    }

    public CompletionStage<String> delayedResult() {
        long start = System.currentTimeMillis();
        return futures.delayed(() -> CompletableFuture.supplyAsync(() -> {
            long end = System.currentTimeMillis();
            long milliSeconds = end - start;
            return "rendered after " + milliSeconds;
        }, customExecutor), Duration.ofSeconds(3));
    }
}
