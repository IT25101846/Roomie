package mtr.group4.Roomie.repository;

import mtr.group4.Roomie.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ReservationRepository implements Storable<Reservation> {

    private final String filePath;

    public ReservationRepository(@Value("${app.data.path}") String dataPath) {
        this.filePath = dataPath + "reservations.txt";
        try { new File(filePath).createNewFile(); } catch (IOException ignored) {}
    }

    @Override
    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return list;
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Reservation r = Reservation.fromCsv(line);
                    if (r != null) list.add(r);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Reservation> findById(String id) {
        return findAll().stream().filter(r -> r.getReservationId().equals(id)).findFirst();
    }

    public ArrayList<Reservation> findByCustomerId(String customerId) {
        ArrayList<Reservation> list = new ArrayList<>();
        for (Reservation r : findAll()) if (r.getCustomerId().equals(customerId)) list.add(r);
        return list;
    }

    public ArrayList<Reservation> findByRoomId(String roomId) {
        ArrayList<Reservation> list = new ArrayList<>();
        for (Reservation r : findAll()) if (r.getRoomId().equals(roomId)) list.add(r);
        return list;
    }

    public boolean hasConflict(String roomId, String checkIn, String checkOut, String excludeId) {
        for (Reservation r : findAll()) {
            if (!r.getRoomId().equals(roomId)) continue;
            if (r.getStatus() == Reservation.Status.CANCELLED) continue;
            if (excludeId != null && r.getReservationId().equals(excludeId)) continue;
            if (r.overlapsWith(checkIn, checkOut)) return true;
        }
        return false;
    }

    @Override
    public void save(Reservation res) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))) {
            pw.println(res.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Reservation updated) {
        ArrayList<Reservation> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Reservation r : all)
                pw.println(r.getReservationId().equals(updated.getReservationId()) ? updated.toCsv() : r.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(String id) {
        ArrayList<Reservation> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Reservation r : all) if (!r.getReservationId().equals(id)) pw.println(r.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public String generateId() {
        ArrayList<Reservation> all = findAll();
        int max = 0;
        for (Reservation r : all) {
            try { max = Math.max(max, Integer.parseInt(r.getReservationId().replace("RES", ""))); }
            catch (NumberFormatException ignored) {}
        }
        return String.format("RES%04d", max + 1);
    }
}
