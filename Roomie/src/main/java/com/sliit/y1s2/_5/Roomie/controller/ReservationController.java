package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.model.Customer;
import com.sliit.y1s2._5.Roomie.model.Reservation;
import com.sliit.y1s2._5.Roomie.service.ReservationService;
import com.sliit.y1s2._5.Roomie.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired private ReservationService reservationService;
    @Autowired private RoomService roomService;

    @GetMapping("/book")
    public String showBookingForm(@RequestParam(required = false) String roomId,
                                   HttpSession session, Model model) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", roomService.getAvailableRooms());
        if (roomId != null) model.addAttribute("selectedRoomId", roomId);
        return "reservation/booking";
    }

    @PostMapping("/book")
    public String createBooking(@ModelAttribute Reservation reservation,
                                 HttpSession session, RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        reservation.setCustomerId(c.getCustomerId());
        String result = reservationService.createBooking(reservation);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Booking confirmed!");
        return "redirect:/reservation/list";
    }

    @GetMapping("/list")
    public String listReservations(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        model.addAttribute("reservations", reservationService.getByCustomerId(c.getCustomerId()));
        return "reservation/reservationList";
    }

    @GetMapping("/all")
    public String listAll(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservation/reservationList";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes ra) {
        return reservationService.getById(id).map(r -> {
            model.addAttribute("reservation", r);
            model.addAttribute("rooms", roomService.getAllRooms());
            return "reservation/updateReservation";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Reservation not found.");
            return "redirect:/reservation/list";
        });
    }

    @PostMapping("/edit")
    public String updateReservation(@ModelAttribute Reservation reservation, RedirectAttributes ra) {
        String result = reservationService.updateBooking(reservation);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Reservation updated.");
        return "redirect:/reservation/list";
    }

    @GetMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable String id, RedirectAttributes ra) {
        String result = reservationService.cancelBooking(id);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Reservation cancelled.");
        return "redirect:/reservation/list";
    }
}
