package com.example.retry.core;

public interface BackoffStrategy {

    long nextBackoffMillis(int attempt);
}