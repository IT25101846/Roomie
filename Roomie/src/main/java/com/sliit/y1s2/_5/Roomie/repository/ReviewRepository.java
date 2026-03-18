package com.sliit.y1s2._5.Roomie.repository;

import com.sliit.y1s2._5.Roomie.model.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReviewRepository {

    @Value("${app.data.path}")
    private String dataPath;

    private String filePath() { return dataPath + "reviews.txt"; }

    // ── Read ─────────────────────────────────────────────────────────────────

    public List<Review> findAll() {
        List<Review> list = new ArrayList<>();
        File file = new File(filePath());
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    try {
                        list.add(Review.fromCSV(line));
                    } catch (Exception e) {
                        System.err.println("Skipping malformed review line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Optional<Review> findById(String id) {
        if (id == null || id.isBlank()) return Optional.empty();
        return findAll().stream()
                .filter(r -> r.getFeedbackId().equals(id))
                .findFirst();
    }

    public List<Review> findByReservationId(String reservationId) {
        if (reservationId == null || reservationId.isBlank()) return Collections.emptyList();
        return findAll().stream()
                .filter(r -> reservationId.equals(r.getReservationId()))
                .collect(Collectors.toList());
    }

    public List<Review> findByGuestId(String guestId) {
        if (guestId == null || guestId.isBlank()) return Collections.emptyList();
        return findAll().stream()
                .filter(r -> guestId.equals(r.getGuestId()))
                .collect(Collectors.toList());
    }

    public List<Review> findApproved() {
        return findAll().stream()
                .filter(Review::isVerified)
                .collect(Collectors.toList());
    }

    // ── Write ────────────────────────────────────────────────────────────────

    public void save(Review r) {
        new File(dataPath).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), true))) {
            writer.write(r.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Review updated) {
        List<Review> all = findAll();
        // Write to a temp file first, then atomically replace — prevents data loss on crash
        File tmp = new File(filePath() + ".tmp");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmp))) {
            for (Review r : all) {
                writer.write(r.getFeedbackId().equals(updated.getFeedbackId())
                        ? updated.toCSV() : r.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            Files.move(tmp.toPath(), new File(filePath()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        List<Review> all = findAll();
        File tmp = new File(filePath() + ".tmp");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmp))) {
            for (Review r : all) {
                if (!r.getFeedbackId().equals(id)) {
                    writer.write(r.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            Files.move(tmp.toPath(), new File(filePath()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ── ID Generation ────────────────────────────────────────────────────────

    /**
     * Generates the next available ID by finding the current highest REVxxx number.
     * This is safer than size()+1 which breaks after any deletion.
     */
    public String generateId() {
        int max = findAll().stream()
                .map(Review::getFeedbackId)
                .filter(id -> id != null && id.matches("FDB\\d+"))
                .mapToInt(id -> Integer.parseInt(id.substring(3)))
                .max()
                .orElse(0);
        return String.format("FDB%03d", max + 1);
    }
}