package com.example.retry.backoff;

import com.example.retry.core.BackoffStrategy;

public class ExponentialBackoff implements BackoffStrategy {

    private final long baseDelayMillis;

    public ExponentialBackoff(long baseDelayMillis) {
        this.baseDelayMillis = baseDelayMillis;
    }

    @Override
    public long nextBackoffMillis(int attempt) {
        return baseDelayMillis * (1L << (attempt - 1));
    }
}