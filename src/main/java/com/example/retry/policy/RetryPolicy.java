package com.example.retry.policy;

import java.lang.Exception;

public interface RetryPolicy {
    boolean shouldRetry(int attempt, Exception e);
}