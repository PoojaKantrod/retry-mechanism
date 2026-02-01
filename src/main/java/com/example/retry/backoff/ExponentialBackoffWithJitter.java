package com.example.retry.backoff;

import java.util.concurrent.ThreadLocalRandom;

public class ExponentialBackoffWithJitter implements BackoffStrategy {
    private final long initialDelayMillis;

    public ExponentialBackoffWithJitter(long initialDelayMillis) {
        this.initialDelayMillis = initialDelayMillis;
    }

    @Override
    public long nextDelay(int attempt) {
        long base = initialDelayMillis * (1L << (attempt - 1));
        return ThreadLocalRandom.current().nextLong(base / 2, base);
    }
}