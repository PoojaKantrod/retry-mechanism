package com.example.retry.core;

public class RetryResult<T> {
    private final boolean success;
    private final T result;
    private final Exception lastException;
    private final int attempts;
    private final long totalWaitMillis;
    private final String reasonStopped;
    private final String backoffStrategy;

    public RetryResult(boolean success, T result, Exception lastException,
                       int attempts, long totalWaitMillis,
                       String reasonStopped, String backoffStrategy) {
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
                ", lastException=" + (lastException != null ? lastException.getClass().getSimpleName() + ": " + lastException.getMessage() : null) +
                ", attempts=" + attempts +
                ", totalWaitMillis=" + totalWaitMillis +
                ", reasonStopped='" + reasonStopped + '\'' +
                ", backoffStrategy='" + backoffStrategy + '\'' +
                '}';
    }
}