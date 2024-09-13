package com.ead.payment_microservice.controller;

import com.ead.payment_microservice.entity.Payment;
import com.ead.payment_microservice.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PaymentController.class)
public class PaymentControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testGetAllPayments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPaymentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreatePayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Manel\", \"number\": \"0776971436\", \"email\": \"manel.doe@example.com\", \"billvalue\": 1100.00 }"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testUpdatePayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/payments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Manel\", \"number\": \"0776971436\", \"email\": \"manel.updated@example.com\", \"billvalue\": 1100.00 }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeletePayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/payments/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
