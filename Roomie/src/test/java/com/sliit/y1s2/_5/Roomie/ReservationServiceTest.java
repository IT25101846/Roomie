package com.sliit.y1s2._5.Roomie;

import com.sliit.y1s2._5.Roomie.model.Reservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    @Test
    void testReservationToCSV() {
        Reservation r = new Reservation("RES001","C001","R001",
                "2024-02-10","2024-02-14","CONFIRMED",34000.0,"2024-01-20");
        String csv = r.toCSV();
        assertTrue(csv.contains("RES001"));
        assertTrue(csv.contains("CONFIRMED"));
        assertTrue(csv.contains("34000.0"));
    }

    @Test
    void testReservationFromCSV() {
        String csv = "RES001,C001,R001,2024-02-10,2024-02-14,CONFIRMED,34000.0,2024-01-20";
        Reservation r = Reservation.fromCSV(csv);
        assertEquals("RES001",     r.getReservationId());
        assertEquals("C001",       r.getCustomerId());
        assertEquals("CONFIRMED",  r.getStatus());
        assertEquals(34000.0,      r.getTotalPrice(), 0.001);
    }

    @Test
    void testReservationCSVRoundTrip() {
        Reservation original = new Reservation("RES002","C002","R003",
                "2024-05-01","2024-05-05","PENDING",60000.0,"2024-04-15");
        Reservation parsed = Reservation.fromCSV(original.toCSV());
        assertEquals(original.getReservationId(), parsed.getReservationId());
        assertEquals(original.getTotalPrice(),     parsed.getTotalPrice(), 0.001);
    }
}
