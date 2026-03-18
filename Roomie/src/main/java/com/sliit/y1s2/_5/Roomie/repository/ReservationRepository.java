package com.sliit.y1s2._5.Roomie.repository;

import com.sliit.y1s2._5.Roomie.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository {

    @Value("${app.data.path}")
    private String dataPath;

    private String filePath() { return dataPath + "reservations.txt"; }

    public List<Reservation> findAll() {
        List<Reservation> list = new ArrayList<>();
        File file = new File(filePath());
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) list.add(Reservation.fromCSV(line));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public Optional<Reservation> findById(String id) {
        return findAll().stream().filter(r -> r.getReservationId().equals(id)).findFirst();
    }

    public List<Reservation> findByCustomerId(String customerId) {
        return findAll().stream()
                .filter(r -> r.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public void save(Reservation r) {
        new File(dataPath).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), true))) {
            writer.write(r.toCSV()); writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void update(Reservation updated) {
        List<Reservation> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Reservation r : all) {
                writer.write(r.getReservationId().equals(updated.getReservationId()) ? updated.toCSV() : r.toCSV());
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        List<Reservation> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Reservation r : all) {
                if (!r.getReservationId().equals(id)) { writer.write(r.toCSV()); writer.newLine(); }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String generateId() {
        return String.format("RES%03d", findAll().size() + 1);
    }
}
