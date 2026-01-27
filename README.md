📘 retry-mechanism

A robust Java retry mechanism with pluggable retry policies and backoff strategies.
Supports Fixed, Exponential, and Exponential-with-Jitter backoff. Designed for resilient microservices and APIs.

⸻

🔹 Features
	•	Retry Policies: Determine when to retry (customizable)
	•	Backoff Strategies:
	•	Fixed delay
	•	Exponential
	•	Exponential with jitter (production-grade)
	•	RetryResult: Returns success/failure, last exception, and attempt count
	•	Demo: Illustrates retries for a transiently failing service

⸻

🔹 How It Works
	1.	RetryExecutor takes a RetryPolicy and BackoffStrategy.
	2.	Executes a task (Callable).
	3.	If the task fails and RetryPolicy.shouldRetry() returns true:
	•	Waits for the next backoff
	•	Retries the task
	4.	Returns a RetryResult with the outcome.

⸻

🔹 Example Usage

RetryExecutor executor = new RetryExecutor(
    new DefaultRetryPolicy(5),
    new ExponentialBackoffWithJitter(200, 2000)
);

RetryResult<String> result = executor.execute(() -> {
    if (Math.random() < 0.7) throw new RuntimeException("Transient failure");
    return "SUCCESS";
});

System.out.println("Success: " + result.isSuccess());
System.out.println("Attempts: " + result.getAttempts());
System.out.println("Result: " + result.getResult());


⸻

🔹 How to Run

mvn clean compile
java -cp target/classes com.example.retry.demo.RetryDemo


⸻

🔹 Why This Project
	•	Pluggable, flexible retry mechanism for any Java task
	•	Backoff strategies suitable for distributed systems
	•	Easily extendable for circuit breaker or async tasks
