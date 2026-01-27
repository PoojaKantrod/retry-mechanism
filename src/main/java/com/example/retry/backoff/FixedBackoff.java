package com.example.retry.backoff;

import com.example.retry.core.BackoffStrategy;

public class FixedBackoff implements BackoffStrategy {

    private final long delayMillis;

    public FixedBackoff(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    public long nextBackoffMillis(int attempt) {
        return delayMillis;
    }
}