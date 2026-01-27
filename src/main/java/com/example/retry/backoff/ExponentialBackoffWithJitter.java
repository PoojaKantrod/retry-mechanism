package com.example.retry.backoff;

import com.example.retry.core.BackoffStrategy;

import java.util.concurrent.ThreadLocalRandom;

public class ExponentialBackoffWithJitter implements BackoffStrategy {

    private final long baseDelayMillis;
    private final long maxDelayMillis;

    public ExponentialBackoffWithJitter(long baseDelayMillis, long maxDelayMillis) {
        this.baseDelayMillis = baseDelayMillis;
        this.maxDelayMillis = maxDelayMillis;
    }

    @Override
    public long nextBackoffMillis(int attempt) {
        long expDelay = baseDelayMillis * (1L << (attempt - 1));
        long capped = Math.min(expDelay, maxDelayMillis);
        return ThreadLocalRandom.current().nextLong(capped);
    }
}