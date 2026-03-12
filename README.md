# Retry Mechanism in Java

This project demonstrates a **flexible retry mechanism** with **configurable backoff strategies** in Java. It provides detailed logs for each attempt, showing which exception occurred, how long it waited, and why the retries stopped.

---

## Features

1. **Retry with Backoff Strategies**
   - **FixedBackoff**: Waits a constant time between retries.
   - **ExponentialBackoff**: Waits exponentially longer after each failed attempt.
   - **ExponentialBackoffWithJitter**: Adds random jitter to exponential backoff to avoid synchronized retries.

2. **Verbose Retry Logging**
   - Shows exception on each attempt.
   - Displays wait time before the next attempt.
   - Tracks total wait time.
   - Explains why the retry stopped (success, max attempts reached, etc.).
   - Shows which backoff strategy was used.

---

## Example Output

=== Testing with FixedBackoff ===
Attempt 1 failed: Random failure: 0.42
Waiting 200 ms before next attempt...
Attempt 2 failed: Random failure: 0.15
Waiting 200 ms before next attempt...
...
RetryResult{success=true, result=Success!, lastException=null, attempts=3, totalWaitMillis=400, reasonStopped='Success', backoffStrategy='FixedBackoff'}

=== Testing with ExponentialBackoff ===
Attempt 1 failed: Random failure: 0.33
Waiting 100 ms before next attempt...
Attempt 2 failed: Random failure: 0.55
Waiting 200 ms before next attempt...
...
RetryResult{success=true, result=Success!, lastException=null, attempts=3, totalWaitMillis=300, reasonStopped='Success', backoffStrategy='ExponentialBackoff'}

=== Testing with ExponentialBackoffWithJitter ===
Attempt 1 failed: Random failure: 0.48
Waiting 135 ms before next attempt...
Attempt 2 failed: Random failure: 0.22
Waiting 295 ms before next attempt...
...
RetryResult{success=true, result=Success!, lastException=null, attempts=3, totalWaitMillis=430, reasonStopped='Success', backoffStrategy='ExponentialBackoffWithJitter'}

---

## How to Run

1. Compile the project: mvn clean compile
2. Run the demo: java -cp target/classes com.example.retry.demo.RetryDemo

---

## Notes

- This project can be extended with **custom retry policies** or **distributed retry mechanisms**.
- Helps prevent overwhelming resources by applying controlled backoff.
- All strategies currently support **tracking total wait time**, **attempt count**, and **reason for stopping**.
