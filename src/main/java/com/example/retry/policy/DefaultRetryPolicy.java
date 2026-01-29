package com.example.retry.policy;

public class DefaultRetryPolicy implements RetryPolicy {
    private final int maxAttempts;

    public DefaultRetryPolicy(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public boolean shouldRetry(int attempt, Exception e) {
        return attempt < maxAttempts;
    }
}