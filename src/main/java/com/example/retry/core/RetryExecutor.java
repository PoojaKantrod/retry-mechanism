package com.example.retry.core;

import java.util.concurrent.Callable;

public class RetryExecutor {

    private final RetryPolicy retryPolicy;
    private final BackoffStrategy backoffStrategy;

    public RetryExecutor(RetryPolicy retryPolicy, BackoffStrategy backoffStrategy) {
        this.retryPolicy = retryPolicy;
        this.backoffStrategy = backoffStrategy;
    }

    public <T> RetryResult<T> execute(Callable<T> task) {
        int attempt = 0;
        Exception lastException = null;

        while (attempt < retryPolicy.maxAttempts()) {
            attempt++;
            try {
                T result = task.call();
                return new RetryResult<>(true, result, null, attempt);
            } catch (Exception e) {
                lastException = e;

                if (!retryPolicy.shouldRetry(e, attempt)) {
                    break;
                }

                try {
                    Thread.sleep(backoffStrategy.nextBackoffMillis(attempt));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        return new RetryResult<>(false, null, lastException, attempt);
    }
}