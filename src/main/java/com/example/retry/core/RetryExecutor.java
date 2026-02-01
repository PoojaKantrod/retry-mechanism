package com.example.retry.core;

import com.example.retry.backoff.BackoffStrategy;

import java.util.concurrent.Callable;

public class RetryExecutor {

    public <T> RetryResult<T> execute(
            Callable<T> operation,
            RetryPolicy policy,
            BackoffStrategy backoffStrategy
    ) {
        int attempts = 0;
        long totalWait = 0;
        Exception lastException = null;

        while (true) {
            attempts++;
            try {
                T result = operation.call();
                return new RetryResult<>(
                        true,
                        result,
                        null,
                        attempts,
                        totalWait,
                        "Success",
                        backoffStrategy.getClass().getSimpleName()
                );
            } catch (Exception e) {
                lastException = e;

                // Check if we should retry
                if (!policy.shouldRetry(attempts, e)) {
                    return new RetryResult<>(
                            false,
                            null,
                            e,
                            attempts,
                            totalWait,
                            "Max attempts reached",
                            backoffStrategy.getClass().getSimpleName()
                    );
                }

                // Calculate wait time
                long waitMillis = backoffStrategy.nextDelay(attempts);
                totalWait += waitMillis;

                // Print attempt info
                System.out.println("Attempt " + attempts + " failed: " + e.getMessage());
                System.out.println("Waiting " + waitMillis + " ms before next attempt...");

                try {
                    Thread.sleep(waitMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return new RetryResult<>(
                            false,
                            null,
                            ie,
                            attempts,
                            totalWait,
                            "Interrupted",
                            backoffStrategy.getClass().getSimpleName()
                    );
                }
            }
        }
    }
}