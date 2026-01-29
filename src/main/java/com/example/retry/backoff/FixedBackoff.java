package com.example.retry.backoff;

public class FixedBackoff implements BackoffStrategy {
    private final long delayMillis;

    public FixedBackoff(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    public long nextDelayMillis(int attempt) {
        return delayMillis;
    }

    @Override
    public String name() {
        return "FixedBackoff";
    }
}