package com.example.retry.backoff;

import java.util.Random;

public class ExponentialBackoffWithJitter implements BackoffStrategy {
    private final long baseDelay;
    private final long maxJitter;
    private final Random random = new Random();

    public ExponentialBackoffWithJitter(long baseDelay, long maxJitter) {
        this.baseDelay = baseDelay;
        this.maxJitter = maxJitter;
    }

    @Override
    public long nextDelayMillis(int attempt) {
        long exponential = baseDelay * (1L << (attempt - 1));
        return exponential + random.nextInt((int) maxJitter + 1);
    }

    @Override
    public String name() {
        return "ExponentialBackoffWithJitter";
    }
}