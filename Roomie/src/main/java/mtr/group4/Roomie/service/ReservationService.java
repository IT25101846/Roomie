package mtr.group4.Roomie.service;

import mtr.group4.Roomie.exception.RoomieException;
import mtr.group4.Roomie.model.Reservation;
import mtr.group4.Roomie.model.Room;
import mtr.group4.Roomie.repository.ReservationRepository;
import mtr.group4.Roomie.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository resRepo;
    private final RoomRepository  roomRepo;

    public ReservationService(ReservationRepository resRepo, RoomRepository roomRepo) {
        this.resRepo  = resRepo;
        this.roomRepo = roomRepo;
    }

    public Reservation createBooking(String customerId, String roomId,
                                     String checkIn, String checkOut) {
        // Validate dates
        LocalDate ci = LocalDate.parse(checkIn);
        LocalDate co = LocalDate.parse(checkOut);
        if (!co.isAfter(ci)) throw new RoomieException("Check-out must be after check-in");
        if (ci.isBefore(LocalDate.now())) throw new RoomieException("Check-in cannot be in the past");

        // Double-booking check
        if (resRepo.hasConflict(roomId, checkIn, checkOut, null))
            throw new RoomieException("Room is not available for selected dates");

        Optional<Room> roomOpt = roomRepo.findById(roomId);
        if (!roomOpt.isPresent()) throw new RoomieException("Room not found");

        int nights = Reservation.calcNights(checkIn, checkOut);
        double total = nights * roomOpt.get().getPricePerNight();
        String id = resRepo.generateId();
        String today = LocalDate.now().toString();

        Reservation r = new Reservation(id, customerId, roomId, checkIn, checkOut,
                nights, total, "PENDING", today);
        resRepo.save(r);
        return r;
    }

    public void confirmReservation(String reservationId) {
        Optional<Reservation> opt = resRepo.findById(reservationId);
        if (opt.isPresent()) {
            Reservation r = opt.get();
            r.setStatus(Reservation.Status.CONFIRMED);
            resRepo.update(r);
        }
    }

    public void cancelReservation(String reservationId) {
        Optional<Reservation> opt = resRepo.findById(reservationId);
        if (opt.isPresent()) {
            Reservation r = opt.get();
            r.setStatus(Reservation.Status.CANCELLED);
            resRepo.update(r);
        }
    }

    public void updateStatus(String reservationId, String status) {
        Optional<Reservation> opt = resRepo.findById(reservationId);
        if (opt.isPresent()) {
            Reservation r = opt.get();
            r.setStatus(Reservation.Status.valueOf(status));
            resRepo.update(r);
        }
    }

    public Optional<Reservation> getById(String id) { return resRepo.findById(id); }

    public ArrayList<Reservation> getByCustomerId(String customerId) {
        return resRepo.findByCustomerId(customerId);
    }

    public ArrayList<Reservation> getAllReservations() { return resRepo.findAll(); }

    public int countReservations() { return resRepo.findAll().size(); }
}
