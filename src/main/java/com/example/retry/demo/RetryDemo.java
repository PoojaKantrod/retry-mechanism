package com.example.retry.demo;

import com.example.retry.backoff.ExponentialBackoffWithJitter;
import com.example.retry.core.RetryExecutor;
import com.example.retry.core.RetryResult;
import com.example.retry.core.RetryableOperation;
import com.example.retry.policy.DefaultRetryPolicy;

public class RetryDemo {
    public static void main(String[] args) {
        RetryExecutor executor = new RetryExecutor();
        RetryableOperation<String> op = () -> {
            if (Math.random() < 0.7) throw new RuntimeException("Simulated failure");
            return "Success!";
        };

        RetryResult<String> result = executor.execute(
                op,
                new DefaultRetryPolicy(5),
                new ExponentialBackoffWithJitter(500, 300)
        );

        System.out.println(result);
    }
}