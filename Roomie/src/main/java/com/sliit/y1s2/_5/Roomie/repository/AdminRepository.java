package com.sliit.y1s2._5.Roomie.repository;

import com.sliit.y1s2._5.Roomie.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class AdminRepository {

    @Value("${app.data.path}")
    private String dataPath;

    private String filePath() { return dataPath + "admins.txt"; }

    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();
        File file = new File(filePath());
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) list.add(Admin.fromCSV(line));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public Optional<Admin> findById(String id) {
        return findAll().stream().filter(a -> a.getAdminId().equals(id)).findFirst();
    }

    public Optional<Admin> findByUsername(String usernameOrEmail) {
        if (usernameOrEmail == null || usernameOrEmail.isBlank()) return Optional.empty();
        return findAll().stream()
                .filter(a ->
                        a.getUsername().equals(usernameOrEmail) ||
                        (a.getEmail() != null && a.getEmail().equalsIgnoreCase(usernameOrEmail)))
                .findFirst();
    }

    public void save(Admin a) {
        new File(dataPath).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), true))) {
            writer.write(a.toCSV()); writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void update(Admin updated) {
        List<Admin> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Admin a : all) {
                writer.write(a.getAdminId().equals(updated.getAdminId()) ? updated.toCSV() : a.toCSV());
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        List<Admin> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Admin a : all) {
                if (!a.getAdminId().equals(id)) { writer.write(a.toCSV()); writer.newLine(); }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String generateId() {
        return String.format("ADM%03d", findAll().size() + 1);
    }
}
