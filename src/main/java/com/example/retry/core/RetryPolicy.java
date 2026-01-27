package com.example.retry.core;

public interface RetryPolicy {

    boolean shouldRetry(Exception exception, int attempt);

    int maxAttempts();
}