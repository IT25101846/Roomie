package mtr.group4.Roomie.controller;

import mtr.group4.Roomie.model.Customer;
import mtr.group4.Roomie.model.Reservation;
import mtr.group4.Roomie.model.Room;
import mtr.group4.Roomie.service.ReservationService;
import mtr.group4.Roomie.service.ReviewService;
import mtr.group4.Roomie.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService      reviewService;
    private final RoomService        roomService;
    private final ReservationService reservationService;

    public ReviewController(ReviewService reviewService, RoomService roomService,
                            ReservationService reservationService) {
        this.reviewService      = reviewService;
        this.roomService        = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("reviews",   reviewService.getAllReviews());
        model.addAttribute("avgRating", reviewService.getAverageRating());
        model.addAttribute("rooms",     roomService.getAllRooms());
        return "review/all";
    }

    /**
     * Show the write-review form.
     * Only rooms the logged-in customer has previously booked (any non-CANCELLED
     * reservation) are shown in the dropdown.
     */
    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";

        // Gather booked room IDs (preserve insertion order, deduplicate)
        List<Reservation> reservations = reservationService.getByCustomerId(c.getId());
        Map<String, Room> bookedRooms = new LinkedHashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus() != Reservation.Status.CANCELLED
                    && !bookedRooms.containsKey(r.getRoomId())) {
                roomService.getRoomById(r.getRoomId())
                        .ifPresent(room -> bookedRooms.put(r.getRoomId(), room));
            }
        }

        model.addAttribute("rooms", new ArrayList<>(bookedRooms.values()));
        model.addAttribute("hasBookings", !bookedRooms.isEmpty());
        return "review/add";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String roomId, @RequestParam int rating,
                         @RequestParam String comment, HttpSession session,
                         RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        reviewService.addReview(c, roomId, rating, comment);
        ra.addFlashAttribute("success", "Review submitted! Thank you.");
        return "redirect:/review/all";
    }
}
