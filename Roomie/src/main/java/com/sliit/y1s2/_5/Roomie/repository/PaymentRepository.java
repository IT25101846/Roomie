package com.sliit.y1s2._5.Roomie.repository;

import com.sliit.y1s2._5.Roomie.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository {

    @Value("${app.data.path}")
    private String dataPath;

    private String filePath() { return dataPath + "payments.txt"; }

    public List<Payment> findAll() {
        List<Payment> list = new ArrayList<>();
        File file = new File(filePath());
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) list.add(Payment.fromCSV(line));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public Optional<Payment> findById(String id) {
        return findAll().stream().filter(p -> p.getPaymentId().equals(id)).findFirst();
    }

    public Optional<Payment> findByReservationId(String reservationId) {
        return findAll().stream().filter(p -> p.getReservationId().equals(reservationId)).findFirst();
    }

    public List<Payment> findByCustomerId(String customerId) {
        return findAll().stream().filter(p -> p.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    public void save(Payment p) {
        new File(dataPath).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), true))) {
            writer.write(p.toCSV()); writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void update(Payment updated) {
        List<Payment> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Payment p : all) {
                writer.write(p.getPaymentId().equals(updated.getPaymentId()) ? updated.toCSV() : p.toCSV());
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        List<Payment> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Payment p : all) {
                if (!p.getPaymentId().equals(id)) { writer.write(p.toCSV()); writer.newLine(); }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String generateId() {
        return String.format("PAY%03d", findAll().size() + 1);
    }
}
