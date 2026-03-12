package com.example.payment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(PaymentNotFoundException.class)
 public ResponseEntity<?> handleNotFound(PaymentNotFoundException ex) {

  return ResponseEntity
   .status(404)
   .body(Map.of(
     "error", "NOT_FOUND",
     "message", ex.getMessage()
   ));
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<?> handleGeneral(Exception ex) {

  return ResponseEntity
   .status(500)
   .body(Map.of(
     "error", "INTERNAL_ERROR",
     "message", ex.getMessage()
   ));
 }

}