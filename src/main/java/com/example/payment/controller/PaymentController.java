package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;
import com.example.payment.dto.PaymentRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/batch")
    public List<Payment> createPayments(@Valid @RequestBody List<PaymentRequest> requests) {
        return paymentService.createMultiplePayments(requests);
    }

    @PostMapping
public Payment createPayment(
        @RequestHeader("Idempotency-Key") String idempotencyKey,
        @Valid @RequestBody PaymentRequest request) {
    return paymentService.createPayment(idempotencyKey, request);
}

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}