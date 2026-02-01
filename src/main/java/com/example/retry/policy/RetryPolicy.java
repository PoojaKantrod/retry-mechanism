package com.example.retry.core;

public interface RetryPolicy {
    int getMaxAttempts();
    boolean shouldRetry(int attempt, Exception lastException);
}