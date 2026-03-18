package com.sliit.y1s2._5.Roomie.repository;

import com.sliit.y1s2._5.Roomie.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class CustomerRepository {

    @Value("${app.data.path}")
    private String dataPath;

    private String filePath() { return dataPath + "customers.txt"; }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        File file = new File(filePath());
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) list.add(Customer.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ── FIND BY ID ────────────────────────────────────────────────────────────
    public Optional<Customer> findById(String customerId) {
        return findAll().stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    // ── FIND BY EMAIL ─────────────────────────────────────────────────────────
    public Optional<Customer> findByEmail(String email) {
        return findAll().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // ── SAVE (append) ─────────────────────────────────────────────────────────
    public void save(Customer customer) {
        new File(dataPath).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), true))) {
            writer.write(customer.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ── UPDATE (rewrite file) ─────────────────────────────────────────────────
    public void update(Customer updated) {
        List<Customer> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Customer c : all) {
                if (c.getCustomerId().equals(updated.getCustomerId())) {
                    writer.write(updated.toCSV());
                } else {
                    writer.write(c.toCSV());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public void delete(String customerId) {
        List<Customer> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Customer c : all) {
                if (!c.getCustomerId().equals(customerId)) {
                    writer.write(c.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ── GENERATE NEXT ID ──────────────────────────────────────────────────────
    public String generateId() {
        List<Customer> all = findAll();
        return String.format("C%03d", all.size() + 1);
    }
}
