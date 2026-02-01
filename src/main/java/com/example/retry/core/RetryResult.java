package com.example.retry.core;

public class RetryResult<T> {
    public final boolean success;
    public final T result;
    public final Exception lastException;
    public final int attempts;
    public final long totalWaitMillis;
    public final String reasonStopped;
    public final String backoffStrategy;

    public RetryResult(boolean success, T result, Exception lastException,
                       int attempts, long totalWaitMillis, String reasonStopped,
                       String backoffStrategy) {
        this.success = success;
        this.result = result;
        this.lastException = lastException;
        this.attempts = attempts;
        this.totalWaitMillis = totalWaitMillis;
        this.reasonStopped = reasonStopped;
        this.backoffStrategy = backoffStrategy;
    }

    @Override
    public String toString() {
        return "RetryResult{" +
                "success=" + success +
                ", result=" + result +
                ", lastException=" + lastException +
                ", attempts=" + attempts +
                ", totalWaitMillis=" + totalWaitMillis +
                ", reasonStopped='" + reasonStopped + '\'' +
                ", backoffStrategy='" + backoffStrategy + '\'' +
                '}';
    }
}