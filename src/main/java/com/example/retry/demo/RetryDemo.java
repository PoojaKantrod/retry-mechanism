package com.example.retry.demo;

import com.example.retry.core.*;
import com.example.retry.backoff.*;
import com.example.retry.policy.DefaultRetryPolicy;

import java.util.concurrent.Callable;

public class RetryDemo {

    public static void main(String[] args) {
        Callable<String> unreliableOperation = () -> {
        double rand = Math.random();
        if (rand < 0.9) { // increase chance of failure
            throw new RuntimeException("Random failure: " + String.format("%.2f", rand));
        }
        return "Success!";
    };

        RetryPolicy policy = new DefaultRetryPolicy(5);

        BackoffStrategy fixedBackoff = new FixedBackoff(200);
        BackoffStrategy exponentialBackoff = new ExponentialBackoff(100);
        BackoffStrategy jitterBackoff = new ExponentialBackoffWithJitter(100);

        System.out.println("=== Testing with FixedBackoff ===");
        runTest(unreliableOperation, policy, fixedBackoff);

        System.out.println("\n=== Testing with ExponentialBackoff ===");
        runTest(unreliableOperation, policy, exponentialBackoff);

        System.out.println("\n=== Testing with ExponentialBackoffWithJitter ===");
        runTest(unreliableOperation, policy, jitterBackoff);
    }

    private static void runTest(Callable<String> operation, RetryPolicy policy, BackoffStrategy backoff) {
        RetryExecutor executor = new RetryExecutor();
        RetryResult<String> result = executor.execute(operation, policy, backoff);
        System.out.println(result);
    }
}