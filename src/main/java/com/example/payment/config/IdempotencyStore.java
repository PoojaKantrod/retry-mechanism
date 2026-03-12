package com.example.payment.config;

import com.example.payment.model.Payment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IdempotencyStore {

 private final Map<String, Payment> store = new HashMap<>();

 public Payment get(String key) {
  return store.get(key);
 }

 public void save(String key, Payment payment) {
  store.put(key, payment);
 }

}