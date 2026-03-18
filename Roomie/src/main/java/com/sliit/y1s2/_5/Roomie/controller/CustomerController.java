package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.model.Customer;
import com.sliit.y1s2._5.Roomie.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerService customerService;

    // ── Registration ──────────────────────────────────────────────────────────
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer, RedirectAttributes ra) {
        String result = customerService.register(customer);
        if (result.startsWith("ERROR")) {
            ra.addFlashAttribute("error", result);
            return "redirect:/customer/register";
        }
        ra.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/customer/login";
    }

    // ── Login ─────────────────────────────────────────────────────────────────
    @GetMapping("/login")
    public String showLoginForm() { return "customer/login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        HttpSession session, RedirectAttributes ra) {
        return customerService.login(email, password).map(c -> {
            session.setAttribute("loggedCustomer", c);
            return "redirect:/";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Invalid email or password.");
            return "redirect:/customer/login";
        });
    }

    // ── Profile ───────────────────────────────────────────────────────────────
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        model.addAttribute("customer", c);
        return "customer/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Customer customer,
                                 HttpSession session, RedirectAttributes ra) {
        String result = customerService.updateProfile(customer);
        if (result.startsWith("ERROR")) {
            ra.addFlashAttribute("error", result);
        } else {
            session.setAttribute("loggedCustomer", customer);
            ra.addFlashAttribute("success", "Profile updated successfully.");
        }
        return "redirect:/customer/profile";
    }

    // ── Logout ────────────────────────────────────────────────────────────────
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/customer/login";
    }

    // ── Admin: List all customers ─────────────────────────────────────────────
    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/list";
    }

    // ── Admin: Delete customer ────────────────────────────────────────────────
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String id, RedirectAttributes ra) {
        String result = customerService.deleteCustomer(id);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success", result);
        return "redirect:/customer/list";
    }
}
