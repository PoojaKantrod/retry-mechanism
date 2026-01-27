package com.example.retry.demo;

import com.example.retry.backoff.ExponentialBackoffWithJitter;
import com.example.retry.core.RetryExecutor;
import com.example.retry.core.RetryResult;
import com.example.retry.policy.DefaultRetryPolicy;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryDemo {

    public static void main(String[] args) {

        RetryExecutor executor = new RetryExecutor(
                new DefaultRetryPolicy(5),
                new ExponentialBackoffWithJitter(200, 2000)
        );

        AtomicInteger counter = new AtomicInteger(0);

        Callable<String> flakyService = () -> {
            int attempt = counter.incrementAndGet();
            System.out.println("Attempt " + attempt);

            if (attempt < 3) {
                throw new RuntimeException("Transient failure");
            }
            return "SUCCESS";
        };

        RetryResult<String> result = executor.execute(flakyService);

        System.out.println("\nFinal Result:");
        System.out.println("Success: " + result.isSuccess());
        System.out.println("Attempts: " + result.getAttempts());
        System.out.println("Result: " + result.getResult());
    }
}