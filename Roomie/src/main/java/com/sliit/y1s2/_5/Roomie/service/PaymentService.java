package com.sliit.y1s2._5.Roomie.service;

import com.sliit.y1s2._5.Roomie.model.Payment;
import com.sliit.y1s2._5.Roomie.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired private PaymentRepository paymentRepository;

    public String recordPayment(Payment payment) {
        payment.setPaymentId(paymentRepository.generateId());
        if (payment.getPaymentDate() == null || payment.getPaymentDate().isBlank())
            payment.setPaymentDate(LocalDate.now().toString());
        payment.setStatus("COMPLETED");
        paymentRepository.save(payment);
        return "SUCCESS";
    }

    public List<Payment> getAllPayments()                     { return paymentRepository.findAll(); }
    public List<Payment> getByCustomerId(String customerId)   { return paymentRepository.findByCustomerId(customerId); }
    public Optional<Payment> getById(String id)               { return paymentRepository.findById(id); }
    public Optional<Payment> getByReservationId(String rId)   { return paymentRepository.findByReservationId(rId); }

    public String updatePayment(Payment payment) {
        if (paymentRepository.findById(payment.getPaymentId()).isEmpty()) return "ERROR: Payment not found.";
        paymentRepository.update(payment);
        return "SUCCESS";
    }

    public String deletePayment(String id) {
        if (paymentRepository.findById(id).isEmpty()) return "ERROR: Payment not found.";
        paymentRepository.delete(id);
        return "SUCCESS";
    }
}
