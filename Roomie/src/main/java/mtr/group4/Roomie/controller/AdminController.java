package mtr.group4.Roomie.controller;

import mtr.group4.Roomie.model.Admin;
import mtr.group4.Roomie.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService       adminService;
    private final RoomService        roomService;
    private final CustomerService    customerService;
    private final ReservationService reservationService;
    private final PaymentService     paymentService;
    private final ReviewService      reviewService;

    public AdminController(AdminService adminService, RoomService roomService,
                           CustomerService customerService, ReservationService reservationService,
                           PaymentService paymentService, ReviewService reviewService) {
        this.adminService       = adminService;
        this.roomService        = roomService;
        this.customerService    = customerService;
        this.reservationService = reservationService;
        this.paymentService     = paymentService;
        this.reviewService      = reviewService;
    }

    @GetMapping("/login")
    public String loginForm() { return "admin/login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        HttpSession session, RedirectAttributes ra) {
        Optional<Admin> a = adminService.login(email, password);
        if (a.isPresent()) {
            session.setAttribute("loggedAdmin", a.get());
            return "redirect:/admin/dashboard";
        }
        ra.addFlashAttribute("error", "Invalid admin credentials.");
        return "redirect:/admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        model.addAttribute("totalRooms",        roomService.countRooms());
        model.addAttribute("availableRooms",    roomService.countAvailableRooms());
        model.addAttribute("totalCustomers",    customerService.countCustomers());
        model.addAttribute("totalReservations", reservationService.countReservations());
        model.addAttribute("totalRevenue",      paymentService.getTotalRevenue());
        model.addAttribute("totalReviews",      reviewService.countReviews());
        model.addAttribute("recentReservations",reservationService.getAllReservations());
        return "admin/dashboard";
    }

    @GetMapping("/reservations")
    public String manageReservations(HttpSession session, Model model) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("rooms",        roomService.getAllRooms());
        model.addAttribute("customers",    customerService.getAllCustomers());
        return "admin/manage-reservations";
    }

    @PostMapping("/reservations/status")
    public String updateStatus(@RequestParam String reservationId, @RequestParam String status,
                               HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        reservationService.updateStatus(reservationId, status);
        ra.addFlashAttribute("success", "Status updated.");
        return "redirect:/admin/reservations";
    }

    @GetMapping("/customers")
    public String manageCustomers(HttpSession session, Model model) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/manage-customers";
    }

    @PostMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable String id, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        customerService.deleteCustomer(id);
        ra.addFlashAttribute("success", "Customer removed.");
        return "redirect:/admin/customers";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedAdmin");
        return "redirect:/";
    }
}
