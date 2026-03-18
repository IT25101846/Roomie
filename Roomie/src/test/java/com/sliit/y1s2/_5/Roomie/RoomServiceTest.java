package com.sliit.y1s2._5.Roomie;

import com.sliit.y1s2._5.Roomie.model.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    @Test
    void testRoomToCSV() {
        Room r = new Room("R001","101","Deluxe",8500.0,2,"Sea view","AVAILABLE","WiFi|AC");
        String csv = r.toCSV();
        assertTrue(csv.startsWith("R001,101,Deluxe"));
        assertTrue(csv.contains("8500.0"));
    }

    @Test
    void testRoomFromCSV() {
        String csv = "R001,101,Deluxe,8500.0,2,Sea view,AVAILABLE,WiFi|AC";
        Room r = Room.fromCSV(csv);
        assertEquals("R001",      r.getRoomId());
        assertEquals("Deluxe",    r.getType());
        assertEquals(8500.0,      r.getPricePerNight(), 0.001);
        assertEquals("AVAILABLE", r.getStatus());
    }

    @Test
    void testRoomCSVRoundTrip() {
        Room original = new Room("R002","201","Suite",15000.0,4,"Luxury suite","AVAILABLE","WiFi|AC|TV|Jacuzzi");
        Room parsed = Room.fromCSV(original.toCSV());
        assertEquals(original.getRoomId(),       parsed.getRoomId());
        assertEquals(original.getPricePerNight(),parsed.getPricePerNight(), 0.001);
    }
}
