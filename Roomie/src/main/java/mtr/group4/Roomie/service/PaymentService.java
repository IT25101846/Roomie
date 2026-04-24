package mtr.group4.Roomie.service;

import mtr.group4.Roomie.model.Payment;
import mtr.group4.Roomie.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository payRepo;

    public PaymentService(PaymentRepository payRepo) {
        this.payRepo = payRepo;
    }

    public Payment processPayment(String reservationId, double amount, String method) {
        String id    = payRepo.generateId();
        String today = LocalDate.now().toString();
        Payment p = new Payment(id, reservationId, amount, method, "COMPLETED", today, today);
        payRepo.save(p);
        return p;
    }

    public Optional<Payment> getByReservationId(String reservationId) {
        return payRepo.findByReservationId(reservationId);
    }

    public double getTotalRevenue() { return payRepo.getTotalRevenue(); }
}
