package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.model.Admin;
import com.sliit.y1s2._5.Roomie.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private CustomerService customerService;
    @Autowired private RoomService roomService;
    @Autowired private ReservationService reservationService;
    @Autowired private PaymentService paymentService;

    @GetMapping("/login")
    public String showLoginForm() { return "admin/login"; }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes ra) {
        return adminService.login(username, password).map(a -> {
            session.setAttribute("loggedAdmin", a);
            return "redirect:/admin/dashboard";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Invalid credentials.");
            return "redirect:/admin/login";
        });
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        model.addAttribute("totalCustomers",    customerService.getAllCustomers().size());
        model.addAttribute("totalRooms",        roomService.getAllRooms().size());
        model.addAttribute("totalReservations", reservationService.getAllReservations().size());
        model.addAttribute("totalPayments",     paymentService.getAllPayments().size());
        return "admin/dashboard";
    }

    @GetMapping("/manage")
    public String manageAdmins(HttpSession session, Model model) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("newAdmin", new Admin());
        return "admin/manageAdmins";
    }

    @PostMapping("/create")
    public String createAdmin(@ModelAttribute Admin admin, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) return "redirect:/admin/login";
        String result = adminService.createAdmin(admin);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Admin created.");
        return "redirect:/admin/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable String id, RedirectAttributes ra) {
        String result = adminService.deleteAdmin(id);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success", result);
        return "redirect:/admin/manage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
