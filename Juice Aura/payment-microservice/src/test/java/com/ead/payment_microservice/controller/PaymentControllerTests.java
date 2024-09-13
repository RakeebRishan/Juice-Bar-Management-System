package com.ead.payment_microservice.controller;


import com.ead.payment_microservice.entity.Payment;
import com.ead.payment_microservice.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentControllerTests {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    public PaymentControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPayments() {
        when(paymentService.getAllPayments()).thenReturn(Collections.singletonList(new Payment()));
        assertEquals(1, paymentController.getAllPayments().size());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment();
        when(paymentService.getPaymentById(1L)).thenReturn(payment);
        ResponseEntity<Payment> response = paymentController.getPaymentById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment();
        when(paymentService.createPayment(payment)).thenReturn(payment);
        assertEquals(payment, paymentController.createPayment(payment));
    }

    @Test
    void testUpdatePayment() {
        Payment payment = new Payment();
        when(paymentService.updatePayment(1L, payment)).thenReturn(payment);
        ResponseEntity<Payment> response = paymentController.updatePayment(1L, payment);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testDeletePayment() {
        doNothing().when(paymentService).deletePayment(1L);
        assertDoesNotThrow(() -> paymentController.deletePayment(1L));
    }
}
