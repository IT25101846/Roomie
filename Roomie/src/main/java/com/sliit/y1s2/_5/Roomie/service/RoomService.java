package com.sliit.y1s2._5.Roomie.service;

import com.sliit.y1s2._5.Roomie.model.Room;
import com.sliit.y1s2._5.Roomie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public String addRoom(Room room) {
        room.setRoomId(roomRepository.generateId());
        if (room.getStatus() == null || room.getStatus().isBlank()) {
            room.setStatus("AVAILABLE");
        }
        roomRepository.save(room);
        return "SUCCESS";
    }

    public List<Room> getAllRooms()              { return roomRepository.findAll(); }
    public List<Room> getAvailableRooms()        { return roomRepository.findAvailable(); }
    public List<Room> getRoomsByType(String type){ return roomRepository.findByType(type); }
    public Optional<Room> getRoomById(String id) { return roomRepository.findById(id); }

    public String updateRoom(Room room) {
        if (roomRepository.findById(room.getRoomId()).isEmpty()) return "ERROR: Room not found.";
        roomRepository.update(room);
        return "SUCCESS";
    }

    public String deleteRoom(String roomId) {
        if (roomRepository.findById(roomId).isEmpty()) return "ERROR: Room not found.";
        roomRepository.delete(roomId);
        return "SUCCESS";
    }

    public void setRoomStatus(String roomId, String status) {
        roomRepository.findById(roomId).ifPresent(r -> {
            r.setStatus(status);
            roomRepository.update(r);
        });
    }
}
