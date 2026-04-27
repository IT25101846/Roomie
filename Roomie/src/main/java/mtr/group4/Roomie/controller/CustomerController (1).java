package mtr.group4.Roomie.controller;

import mtr.group4.Roomie.model.Customer;
import mtr.group4.Roomie.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "customer/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        HttpSession session, RedirectAttributes ra) {
        Optional<Customer> c = customerService.login(email, password);
        if (c.isPresent()) {
            session.setAttribute("loggedCustomer", c.get());
            return "redirect:/";
        }
        ra.addFlashAttribute("error", "Invalid email or password.");
        return "redirect:/customer/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "customer/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String email, @RequestParam String password,
                           @RequestParam String phone, @RequestParam String address,
                           RedirectAttributes ra) {
        boolean ok = customerService.register(firstName, lastName, email, password, phone, address);
        if (ok) {
            ra.addFlashAttribute("success", "Account created! Please login.");
            return "redirect:/customer/login";
        }
        ra.addFlashAttribute("error", "Email already registered.");
        return "redirect:/customer/register";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        model.addAttribute("customer", c);
        return "customer/profile";
    }

    /** Update logged-in customer's account details */
    @PostMapping("/update")
    public String update(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String email,
                         @RequestParam(required = false, defaultValue = "") String phone,
                         @RequestParam(required = false, defaultValue = "") String address,
                         @RequestParam(required = false, defaultValue = "") String newPassword,
                         HttpSession session, RedirectAttributes ra) {

        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";

        // Check if email is taken by someone else
        Optional<Customer> existing = customerService.findByEmail(email);
        if (existing.isPresent() && !existing.get().getId().equals(c.getId())) {
            ra.addFlashAttribute("profileError", "That email is already in use by another account.");
            return "redirect:/customer/profile";
        }

        c.setFirstName(firstName.trim());
        c.setLastName(lastName.trim());
        c.setEmail(email.trim());
        c.setPhone(phone.trim());
        c.setAddress(address.trim());
        if (!newPassword.isBlank()) {
            c.setPassword(newPassword);
        }

        customerService.updateCustomer(c);
        session.setAttribute("loggedCustomer", c);  // refresh session

        ra.addFlashAttribute("profileSuccess", "Your details have been updated successfully.");
        return "redirect:/customer/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedCustomer");
        return "redirect:/";
    }
}
