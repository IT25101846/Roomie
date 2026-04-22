package mtr.group4.Roomie.controller;

import mtr.group4.Roomie.exception.RoomieException;
import mtr.group4.Roomie.model.Customer;
import mtr.group4.Roomie.model.Reservation;
import mtr.group4.Roomie.model.Room;
import mtr.group4.Roomie.service.ReservationService;
import mtr.group4.Roomie.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final RoomService roomService;

    public ReservationController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping("/book")
    public String bookForm(@RequestParam String roomId, Model model, HttpSession session) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        Optional<Room> room = roomService.getRoomById(roomId);
        if (!room.isPresent()) return "redirect:/room/list";
        model.addAttribute("room", room.get());
        return "reservation/book";
    }

    @PostMapping("/book")
    public String book(@RequestParam String roomId, @RequestParam String checkIn,
                       @RequestParam String checkOut, HttpSession session,
                       RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        try {
            Reservation res = reservationService.createBooking(c.getId(), roomId, checkIn, checkOut);
            return "redirect:/reservation/confirm/" + res.getReservationId();
        } catch (RoomieException e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/reservation/book?roomId=" + roomId;
        }
    }

    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable String id, Model model, HttpSession session) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        Optional<Reservation> res = reservationService.getById(id);
        if (!res.isPresent()) return "redirect:/reservation/my";
        model.addAttribute("reservation", res.get());
        Optional<Room> room = roomService.getRoomById(res.get().getRoomId());
        room.ifPresent(r -> model.addAttribute("room", r));
        return "reservation/confirm";
    }

    @GetMapping("/my")
    public String my(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        model.addAttribute("reservations", reservationService.getByCustomerId(c.getId()));
        model.addAttribute("rooms", roomService.getAllRooms());
        return "reservation/my";
    }

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable String id, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        reservationService.cancelReservation(id);
        ra.addFlashAttribute("success", "Reservation cancelled.");
        return "redirect:/reservation/my";
    }
}
