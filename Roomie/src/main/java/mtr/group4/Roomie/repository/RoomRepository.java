package mtr.group4.Roomie.repository;

import mtr.group4.Roomie.model.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class RoomRepository implements Storable<Room> {

    private final String filePath;

    public RoomRepository(@Value("${app.data.path}") String dataPath) {
        this.filePath = dataPath + "room.txt";
    }

    @Override
    public ArrayList<Room> findAll() {
        ArrayList<Room> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) return list;
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Room r = Room.fromCsv(line);
                    if (r != null) list.add(r);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Room> findById(String id) {
        return findAll().stream().filter(r -> r.getRoomId().equals(id)).findFirst();
    }

    public ArrayList<Room> findAvailable() {
        ArrayList<Room> list = new ArrayList<>();
        for (Room r : findAll()) if (r.isAvailable()) list.add(r);
        return list;
    }

    @Override
    public void save(Room room) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))) {
            pw.println(room.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Room updated) {
        ArrayList<Room> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Room r : all)
                pw.println(r.getRoomId().equals(updated.getRoomId()) ? updated.toCsv() : r.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(String id) {
        ArrayList<Room> all = findAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            for (Room r : all) if (!r.getRoomId().equals(id)) pw.println(r.toCsv());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public String generateId() {
        ArrayList<Room> all = findAll();
        int max = 0;
        for (Room r : all) {
            try { max = Math.max(max, Integer.parseInt(r.getRoomId().replace("R", ""))); }
            catch (NumberFormatException ignored) {}
        }
        return String.format("R%03d", max + 1);
    }
}
