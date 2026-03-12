package com.example.retry.backoff;

public interface BackoffStrategy {
    long nextDelay(int attempt);
}