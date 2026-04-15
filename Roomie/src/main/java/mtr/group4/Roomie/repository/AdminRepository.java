package mtr.group4.Roomie.repository;

import mtr.group4.Roomie.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class AdminRepository implements Storable<Admin> {

    private final String filePath;

    public AdminRepository(@Value("${app.data.path}") String dataPath) {
        this.filePath = dataPath + "admins.txt";
    }

    @Override
    public ArrayList<Admin> findAll() {
        ArrayList<Admin> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return list;
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Admin a = Admin.fromCsv(line);
                    if (a != null) list.add(a);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Admin> findById(String id) {
        return findAll().stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public Optional<Admin> findByEmail(String email) {
        return findAll().stream().filter(a -> a.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @Override
    public void save(Admin admin) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))) {
            pw.println(admin.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Admin updated) {
        ArrayList<Admin> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Admin a : all)
                pw.println(a.getId().equals(updated.getId()) ? updated.toCsv() : a.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(String id) {
        ArrayList<Admin> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Admin a : all) if (!a.getId().equals(id)) pw.println(a.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public String generateId() {
        ArrayList<Admin> all = findAll();
        int max = 0;
        for (Admin a : all) {
            try { max = Math.max(max, Integer.parseInt(a.getId().replace("A", ""))); }
            catch (NumberFormatException ignored) {}
        }
        return String.format("A%03d", max + 1);
    }
}
