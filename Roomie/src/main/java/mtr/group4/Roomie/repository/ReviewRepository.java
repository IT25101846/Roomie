package mtr.group4.Roomie.repository;

import mtr.group4.Roomie.model.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ReviewRepository implements Storable<Review> {

    private final String filePath;

    public ReviewRepository(@Value("${app.data.path}") String dataPath) {
        this.filePath = dataPath + "reviews.txt";
        try { new File(filePath).createNewFile(); } catch (IOException ignored) {}
    }

    @Override
    public ArrayList<Review> findAll() {
        ArrayList<Review> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return list;
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Review r = Review.fromCsv(line);
                    if (r != null) list.add(r);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Review> findById(String id) {
        return findAll().stream().filter(r -> r.getReviewId().equals(id)).findFirst();
    }

    public ArrayList<Review> findByCustomerId(String customerId) {
        ArrayList<Review> list = new ArrayList<>();
        for (Review r : findAll()) if (r.getCustomerId().equals(customerId)) list.add(r);
        return list;
    }

    public double getAverageRating() {
        ArrayList<Review> all = findAll();
        if (all.isEmpty()) return 0;
        int total = 0;
        for (Review r : all) total += r.getRating();
        return (double) total / all.size();
    }

    @Override
    public void save(Review review) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))) {
            pw.println(review.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Review updated) {
        ArrayList<Review> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Review r : all)
                pw.println(r.getReviewId().equals(updated.getReviewId()) ? updated.toCsv() : r.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(String id) {
        ArrayList<Review> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Review r : all) if (!r.getReviewId().equals(id)) pw.println(r.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public String generateId() {
        ArrayList<Review> all = findAll();
        int max = 0;
        for (Review r : all) {
            try { max = Math.max(max, Integer.parseInt(r.getReviewId().replace("REV", ""))); }
            catch (NumberFormatException ignored) {}
        }
        return String.format("REV%04d", max + 1);
    }
}
