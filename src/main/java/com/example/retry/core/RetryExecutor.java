package com.example.retry.core;

import com.example.retry.backoff.BackoffStrategy;
import com.example.retry.policy.RetryPolicy;

public class RetryExecutor {

    public <T> RetryResult<T> execute(RetryableOperation<T> operation,
                                      RetryPolicy policy,
                                      BackoffStrategy backoff) {
        int attempts = 0;
        long totalWait = 0;
        Exception lastException = null;

        while (true) {
            attempts++;
            try {
                T result = operation.execute();
                return new RetryResult<>(true, result, null, attempts, totalWait,
                        "Success", backoff.name());
            } catch (Exception e) {
                lastException = e;
                if (!policy.shouldRetry(attempts, e)) {
                    return new RetryResult<>(false, null, lastException, attempts, totalWait,
                            "Retry policy exhausted", backoff.name());
                }
                long delay = backoff.nextDelayMillis(attempts);
                totalWait += delay;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return new RetryResult<>(false, null, ex, attempts, totalWait,
                            "Interrupted", backoff.name());
                }
            }
        }
    }
}