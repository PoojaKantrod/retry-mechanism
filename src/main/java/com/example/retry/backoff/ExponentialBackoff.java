package com.example.retry.backoff;

public class ExponentialBackoff implements BackoffStrategy {
    private final long initialDelayMillis;

    public ExponentialBackoff(long initialDelayMillis) {
        this.initialDelayMillis = initialDelayMillis;
    }

    @Override
    public long nextDelay(int attempt) {
        return initialDelayMillis * (1L << (attempt - 1));
    }
}