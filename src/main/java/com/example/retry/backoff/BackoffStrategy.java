package com.example.retry.backoff;

public interface BackoffStrategy {
    long nextDelayMillis(int attempt);
    String name();
}