package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.model.Customer;
import com.sliit.y1s2._5.Roomie.model.Payment;
import com.sliit.y1s2._5.Roomie.service.PaymentService;
import com.sliit.y1s2._5.Roomie.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired private PaymentService paymentService;
    @Autowired private ReservationService reservationService;

    @GetMapping("/form/{reservationId}")
    public String showPaymentForm(@PathVariable String reservationId,
                                   HttpSession session, Model model) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        reservationService.getById(reservationId).ifPresent(r ->
                model.addAttribute("reservation", r));
        model.addAttribute("payment", new Payment());
        return "payment/payment";
    }

    @PostMapping("/record")
    public String recordPayment(@ModelAttribute Payment payment,
                                 HttpSession session, RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        payment.setCustomerId(c.getCustomerId());
        String result = paymentService.recordPayment(payment);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Payment recorded.");
        return "redirect:/payment/history";
    }

    @GetMapping("/history")
    public String paymentHistory(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) return "redirect:/customer/login";
        model.addAttribute("payments", paymentService.getByCustomerId(c.getCustomerId()));
        return "payment/history";
    }

    @GetMapping("/invoice/{id}")
    public String invoice(@PathVariable String id, Model model, RedirectAttributes ra) {
        return paymentService.getById(id).map(p -> {
            model.addAttribute("payment", p);
            reservationService.getById(p.getReservationId())
                    .ifPresent(r -> model.addAttribute("reservation", r));
            return "payment/invoice";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Payment not found.");
            return "redirect:/payment/history";
        });
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable String id, RedirectAttributes ra) {
        String result = paymentService.deletePayment(id);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success", result);
        return "redirect:/payment/history";
    }
}
