package mtr.group4.Roomie.repository;

import mtr.group4.Roomie.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class PaymentRepository implements Storable<Payment> {

    private final String filePath;

    public PaymentRepository(@Value("${app.data.path}") String dataPath) {
        this.filePath = dataPath + "payments.txt";
        try { new File(filePath).createNewFile(); } catch (IOException ignored) {}
    }

    @Override
    public ArrayList<Payment> findAll() {
        ArrayList<Payment> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return list;
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Payment p = Payment.fromCsv(line);
                    if (p != null) list.add(p);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Payment> findById(String id) {
        return findAll().stream().filter(p -> p.getPaymentId().equals(id)).findFirst();
    }

    public Optional<Payment> findByReservationId(String reservationId) {
        return findAll().stream().filter(p -> p.getReservationId().equals(reservationId)).findFirst();
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Payment p : findAll()) if (p.getStatus() == Payment.Status.COMPLETED) total += p.getAmount();
        return total;
    }

    @Override
    public void save(Payment payment) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))) {
            pw.println(payment.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Payment updated) {
        ArrayList<Payment> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Payment p : all)
                pw.println(p.getPaymentId().equals(updated.getPaymentId()) ? updated.toCsv() : p.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(String id) {
        ArrayList<Payment> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Payment p : all) if (!p.getPaymentId().equals(id)) pw.println(p.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public String generateId() {
        ArrayList<Payment> all = findAll();
        int max = 0;
        for (Payment p : all) {
            try { max = Math.max(max, Integer.parseInt(p.getPaymentId().replace("PAY", ""))); }
            catch (NumberFormatException ignored) {}
        }
        return String.format("PAY%04d", max + 1);
    }
}
