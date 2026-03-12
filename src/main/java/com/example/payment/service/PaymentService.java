package com.example.payment.service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.idempotency.RedisIdempotencyStore;
import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RedisIdempotencyStore idempotencyStore;

    public PaymentService(PaymentRepository paymentRepository,
                          RedisIdempotencyStore idempotencyStore) {
        this.paymentRepository = paymentRepository;
        this.idempotencyStore = idempotencyStore;
    }

    /**
     * Create a single payment using an idempotency key stored in Redis
     */
    public Payment createPayment(String idempotencyKey, PaymentRequest request) {
        Duration ttl = Duration.ofMinutes(10); // key expires in 10 minutes

        // Check Redis to ensure idempotency
        boolean isNew = idempotencyStore.storeKeyIfAbsent(idempotencyKey, ttl);
        if (!isNew) {
            // Return previous payment if exists, or throw exception
            return paymentRepository.findByIdempotencyKey(idempotencyKey)
                    .orElseThrow(() -> new IllegalStateException("Duplicate request"));
        }

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setDescription(request.getDescription());
        payment.setStatus("CREATED");
        payment.setIdempotencyKey(idempotencyKey);

        return paymentRepository.save(payment);
    }

    /**
     * Create multiple payments without idempotency (batch use-case)
     */
    public List<Payment> createMultiplePayments(List<PaymentRequest> requests) {
        List<Payment> payments = requests.stream()
                .map(req -> {
                    Payment p = new Payment();
                    p.setAmount(req.getAmount());
                    p.setCurrency(req.getCurrency());
                    p.setDescription(req.getDescription());
                    p.setStatus("CREATED");
                    return p;
                })
                .collect(Collectors.toList());

        return paymentRepository.saveAll(payments);
    }

    /**
     * Retrieve all payments
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}