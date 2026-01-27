package com.example.retry.policy;

import com.example.retry.core.RetryPolicy;

public class DefaultRetryPolicy implements RetryPolicy {

    private final int maxAttempts;

    public DefaultRetryPolicy(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public boolean shouldRetry(Exception exception, int attempt) {
        // Retry only for transient failures
        return exception instanceof RuntimeException && attempt < maxAttempts;
    }

    @Override
    public int maxAttempts() {
        return maxAttempts;
    }
}