package com.example.retry.core;

@FunctionalInterface
public interface RetryableOperation<T> {
    T execute() throws Exception;
}