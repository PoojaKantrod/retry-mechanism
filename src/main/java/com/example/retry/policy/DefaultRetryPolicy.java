package com.example.retry.policy;

import com.example.retry.core.RetryPolicy;

public class DefaultRetryPolicy implements RetryPolicy {

    private final int maxAttempts;

    public DefaultRetryPolicy(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public boolean shouldRetry(int attempt, Exception lastException) {
        return attempt < maxAttempts;
    }
}