package mtr.group4.Roomie.repository;

import mtr.group4.Roomie.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class CustomerRepository implements Storable<Customer> {

    private final String filePath;

    public CustomerRepository(@Value("${app.data.path}") String dataPath) {
        this.filePath = dataPath + "customers.txt";
    }

    @Override
    public ArrayList<Customer> findAll() {
        ArrayList<Customer> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return list;
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Customer c = Customer.fromCsv(line);
                    if (c != null) list.add(c);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return findAll().stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<Customer> findByEmail(String email) {
        return findAll().stream().filter(c -> c.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @Override
    public void save(Customer customer) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))) {
            pw.println(customer.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Customer updated) {
        ArrayList<Customer> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Customer c : all)
                pw.println(c.getId().equals(updated.getId()) ? updated.toCsv() : c.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(String id) {
        ArrayList<Customer> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Customer c : all) if (!c.getId().equals(id)) pw.println(c.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public String generateId() {
        ArrayList<Customer> all = findAll();
        int max = 0;
        for (Customer c : all) {
            try { max = Math.max(max, Integer.parseInt(c.getId().replace("C", ""))); }
            catch (NumberFormatException ignored) {}
        }
        return String.format("C%03d", max + 1);
    }
}
