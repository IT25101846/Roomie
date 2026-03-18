package com.sliit.y1s2._5.Roomie.service;

import com.sliit.y1s2._5.Roomie.model.Reservation;
import com.sliit.y1s2._5.Roomie.model.Room;
import com.sliit.y1s2._5.Roomie.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private RoomService roomService;

    public String createBooking(Reservation reservation) {
        Optional<Room> roomOpt = roomService.getRoomById(reservation.getRoomId());
        if (roomOpt.isEmpty()) return "ERROR: Room not found.";
        if (!roomOpt.get().getStatus().equals("AVAILABLE")) return "ERROR: Room is not available.";

        // Calculate total price
        LocalDate checkIn  = LocalDate.parse(reservation.getCheckInDate());
        LocalDate checkOut = LocalDate.parse(reservation.getCheckOutDate());
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) return "ERROR: Check-out must be after check-in.";

        double total = nights * roomOpt.get().getPricePerNight();
        reservation.setReservationId(reservationRepository.generateId());
        reservation.setTotalPrice(total);
        reservation.setStatus("CONFIRMED");
        reservation.setCreatedAt(LocalDate.now().toString());

        reservationRepository.save(reservation);
        roomService.setRoomStatus(reservation.getRoomId(), "BOOKED");
        return "SUCCESS";
    }

    public List<Reservation> getAllReservations()                    { return reservationRepository.findAll(); }
    public List<Reservation> getByCustomerId(String customerId)      { return reservationRepository.findByCustomerId(customerId); }
    public Optional<Reservation> getById(String id)                  { return reservationRepository.findById(id); }

    public String updateBooking(Reservation reservation) {
        if (reservationRepository.findById(reservation.getReservationId()).isEmpty())
            return "ERROR: Reservation not found.";
        reservationRepository.update(reservation);
        return "SUCCESS";
    }

    public String cancelBooking(String reservationId) {
        Optional<Reservation> opt = reservationRepository.findById(reservationId);
        if (opt.isEmpty()) return "ERROR: Reservation not found.";
        Reservation r = opt.get();
        r.setStatus("CANCELLED");
        reservationRepository.update(r);
        roomService.setRoomStatus(r.getRoomId(), "AVAILABLE");
        return "SUCCESS";
    }
}
