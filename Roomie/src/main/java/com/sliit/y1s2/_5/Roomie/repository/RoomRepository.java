package com.sliit.y1s2._5.Roomie.repository;

import com.sliit.y1s2._5.Roomie.model.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RoomRepository {

    @Value("${app.data.path}")
    private String dataPath;

    private String filePath() { return dataPath + "rooms.txt"; }

    public List<Room> findAll() {
        List<Room> list = new ArrayList<>();
        File file = new File(filePath());
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) list.add(Room.fromCSV(line));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public Optional<Room> findById(String roomId) {
        return findAll().stream().filter(r -> r.getRoomId().equals(roomId)).findFirst();
    }

    public List<Room> findAvailable() {
        return findAll().stream()
                .filter(r -> r.getStatus().equals("AVAILABLE"))
                .collect(Collectors.toList());
    }

    public List<Room> findByType(String type) {
        return findAll().stream()
                .filter(r -> r.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public void save(Room room) {
        new File(dataPath).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), true))) {
            writer.write(room.toCSV());
            writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void update(Room updated) {
        List<Room> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Room r : all) {
                writer.write(r.getRoomId().equals(updated.getRoomId()) ? updated.toCSV() : r.toCSV());
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void delete(String roomId) {
        List<Room> all = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath(), false))) {
            for (Room r : all) {
                if (!r.getRoomId().equals(roomId)) { writer.write(r.toCSV()); writer.newLine(); }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String generateId() {
        return String.format("R%03d", findAll().size() + 1);
    }
}
