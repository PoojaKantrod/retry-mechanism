package com.example.retry.backoff;

public class ExponentialBackoff implements BackoffStrategy {
    private final long baseDelay;

    public ExponentialBackoff(long baseDelay) {
        this.baseDelay = baseDelay;
    }

    @Override
    public long nextDelayMillis(int attempt) {
        return baseDelay * (1L << (attempt - 1));
    }

    @Override
    public String name() {
        return "ExponentialBackoff";
    }
}