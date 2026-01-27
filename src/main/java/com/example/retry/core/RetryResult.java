package com.example.retry.core;

public class RetryResult<T> {

    private final boolean success;
    private final T result;
    private final Exception lastException;
    private final int attempts;

    public RetryResult(boolean success, T result, Exception lastException, int attempts) {
        this.success = success;
        this.result = result;
        this.lastException = lastException;
        this.attempts = attempts;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public Exception getLastException() {
        return lastException;
    }

    public int getAttempts() {
        return attempts;
    }
}