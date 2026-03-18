package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.model.Customer;
import com.sliit.y1s2._5.Roomie.model.Review;
import com.sliit.y1s2._5.Roomie.service.ReviewService;
import com.sliit.y1s2._5.Roomie.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired private ReviewService reviewService;
    @Autowired private RoomService roomService;

    // ── Show submission form ─────────────────────────────────────────────────

    @GetMapping("/submit")
    public String showForm(HttpSession session, Model model) {
        if (session.getAttribute("loggedCustomer") == null) {
            return "redirect:/customer/login";
        }
        model.addAttribute("review", new Review());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "review/feedback";
    }

    // ── Handle submission ────────────────────────────────────────────────────

    @PostMapping("/submit")
    public String submitReview(@ModelAttribute Review review,
                               HttpSession session,
                               RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) {
            return "redirect:/customer/login";
        }
        review.setGuestId(c.getCustomerId());

        String result = reviewService.submitReview(review);

        if (result.startsWith("ERROR")) {
            ra.addFlashAttribute("error", result.substring(7)); // strip "ERROR: " prefix
        } else {
            ra.addFlashAttribute("success", "Review submitted successfully. It is pending approval.");
        }
        return "redirect:/review/list";
    }

    // ── Public approved review list ──────────────────────────────────────────

    @GetMapping("/list")
    public String listApproved(Model model) {
        model.addAttribute("reviews", reviewService.getApprovedReviews());
        return "review/reviewList";
    }

    // ── Customer: list & edit own reviews ────────────────────────────────────

    @GetMapping("/my")
    public String myReviews(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) {
            return "redirect:/customer/login";
        }
        model.addAttribute("reviews", reviewService.getByGuestId(c.getCustomerId()));
        return "review/myReviews";
    }

    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable String id,
                                 HttpSession session,
                                 Model model,
                                 RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) {
            return "redirect:/customer/login";
        }
        return reviewService.getById(id).map(r -> {
            if (!c.getCustomerId().equals(r.getGuestId())) {
                ra.addFlashAttribute("error", "You can only edit your own reviews.");
                return "redirect:/review/my";
            }
            model.addAttribute("review", r);
            return "review/edit";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Review not found.");
            return "redirect:/review/my";
        });
    }

    @PostMapping("/edit")
    public String updateReview(@RequestParam String feedbackId,
                               @RequestParam int rating,
                               @RequestParam String comment,
                               HttpSession session,
                               RedirectAttributes ra) {
        Customer c = (Customer) session.getAttribute("loggedCustomer");
        if (c == null) {
            return "redirect:/customer/login";
        }

        return reviewService.getById(feedbackId).map(existing -> {
            if (!c.getCustomerId().equals(existing.getGuestId())) {
                ra.addFlashAttribute("error", "You can only edit your own reviews.");
                return "redirect:/review/my";
            }
            existing.setRating(rating);
            existing.setComment(comment);
            existing.setVerified(false);
            String result = reviewService.updateReview(existing);
            if (result.startsWith("ERROR")) {
                ra.addFlashAttribute("error", result.substring(7));
            } else {
                ra.addFlashAttribute("success", "Review updated. It is pending approval again.");
            }
            return "redirect:/review/my";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Review not found.");
            return "redirect:/review/my";
        });
    }

    // ── Admin: view all reviews ──────────────────────────────────────────────

    @GetMapping("/moderate")
    public String moderate(HttpSession session, Model model) {
        if (session.getAttribute("loggedAdmin") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "review/adminModeration";
    }

    // ── Admin: edit review content ────────────────────────────────────────────

    @GetMapping("/admin/edit/{id}")
    public String adminEditForm(@PathVariable String id,
                                HttpSession session,
                                Model model,
                                RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) {
            return "redirect:/admin/login";
        }
        return reviewService.getById(id).map(r -> {
            model.addAttribute("review", r);
            return "review/adminEdit";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Review not found.");
            return "redirect:/review/moderate";
        });
    }

    @PostMapping("/admin/edit")
    public String adminUpdateReview(@RequestParam String feedbackId,
                                    @RequestParam int rating,
                                    @RequestParam String comment,
                                    @RequestParam(defaultValue = "false") boolean verified,
                                    HttpSession session,
                                    RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) {
            return "redirect:/admin/login";
        }
        return reviewService.getById(feedbackId).map(existing -> {
            existing.setRating(rating);
            existing.setComment(comment);
            existing.setVerified(verified);
            String result = reviewService.updateReview(existing);
            if (result.startsWith("ERROR")) {
                ra.addFlashAttribute("error", result.substring(7));
            } else {
                ra.addFlashAttribute("success", "Review updated successfully.");
            }
            return "redirect:/review/moderate";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Review not found.");
            return "redirect:/review/moderate";
        });
    }

    // ── Admin: approve or reject ─────────────────────────────────────────────

    @GetMapping("/moderate/{id}/{flag}")
    public String moderateReview(@PathVariable String id,
                                 @PathVariable String flag,
                                 HttpSession session,
                                 RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) {
            return "redirect:/admin/login";
        }
        boolean verified = "APPROVED".equalsIgnoreCase(flag) || "true".equalsIgnoreCase(flag);
        String result = reviewService.moderateReview(id, verified);
        if (result.startsWith("ERROR")) {
            ra.addFlashAttribute("error", result.substring(7));
        } else {
            ra.addFlashAttribute("success", "Feedback verification updated.");
        }
        return "redirect:/review/moderate";
    }

    // ── Admin: delete ────────────────────────────────────────────────────────

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable String id,
                               HttpSession session,
                               RedirectAttributes ra) {
        if (session.getAttribute("loggedAdmin") == null) {
            return "redirect:/admin/login";
        }
        String result = reviewService.deleteReview(id);
        if (result.startsWith("ERROR")) {
            ra.addFlashAttribute("error", result.substring(7));
        } else {
            ra.addFlashAttribute("success", "Review deleted successfully.");
        }
        return "redirect:/review/moderate";
    }
}