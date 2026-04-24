package mtr.group4.Roomie.controller;

import mtr.group4.Roomie.model.Payment;
import mtr.group4.Roomie.model.Reservation;
import mtr.group4.Roomie.model.Room;
import mtr.group4.Roomie.service.PaymentService;
import mtr.group4.Roomie.service.ReservationService;
import mtr.group4.Roomie.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService     paymentService;
    private final ReservationService reservationService;
    private final RoomService        roomService;

    public PaymentController(PaymentService paymentService, ReservationService reservationService,
                             RoomService roomService) {
        this.paymentService     = paymentService;
        this.reservationService = reservationService;
        this.roomService        = roomService;
    }

    @GetMapping("/checkout/{resId}")
    public String checkout(@PathVariable String resId, Model model, HttpSession session) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        Optional<Reservation> res = reservationService.getById(resId);
        if (!res.isPresent()) return "redirect:/reservation/my";
        model.addAttribute("reservation", res.get());
        Optional<Room> room = roomService.getRoomById(res.get().getRoomId());
        room.ifPresent(r -> model.addAttribute("room", r));
        return "payment/checkout";
    }

    @PostMapping("/process")
    public String process(@RequestParam String reservationId, @RequestParam String method,
                          HttpSession session) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        Optional<Reservation> res = reservationService.getById(reservationId);
        if (!res.isPresent()) return "redirect:/reservation/my";
        Payment p = paymentService.processPayment(reservationId, res.get().getTotalPrice(), method);
        reservationService.confirmReservation(reservationId);
        return "redirect:/payment/success/" + p.getPaymentId();
    }

    @GetMapping("/success/{paymentId}")
    public String success(@PathVariable String paymentId, Model model, HttpSession session) {
        if (session.getAttribute("loggedCustomer") == null) return "redirect:/customer/login";
        model.addAttribute("paymentId", paymentId);
        return "payment/success";
    }
}
