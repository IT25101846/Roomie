package com.sliit.y1s2._5.Roomie.service;

import com.sliit.y1s2._5.Roomie.model.Review;
import com.sliit.y1s2._5.Roomie.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // ── Submit ───────────────────────────────────────────────────────────────

    public String submitReview(Review review) {
        // Validate rating
        if (review.getRating() < 1 || review.getRating() > 5) {
            return "ERROR: Rating must be between 1 and 5.";
        }
        // Validate comment
        if (review.getComment() == null || review.getComment().isBlank()) {
            return "ERROR: Comment cannot be empty.";
        }
        if (review.getComment().length() > 500) {
            return "ERROR: Comment must be 500 characters or fewer.";
        }
        // Validate reservation/room selection
        if (review.getReservationId() == null || review.getReservationId().isBlank()) {
            return "ERROR: Please select a reservation.";
        }

        review.setFeedbackId(reviewRepository.generateId());
        review.setFeedbackDate(LocalDate.now().toString());
        review.setVerified(false);
        reviewRepository.save(review);
        return "SUCCESS";
    }

    // ── Read ─────────────────────────────────────────────────────────────────

    public List<Review> getAllReviews()                 { return reviewRepository.findAll(); }
    public List<Review> getApprovedReviews()            { return reviewRepository.findApproved(); }
    public List<Review> getByReservationId(String id)   { return reviewRepository.findByReservationId(id); }
    public List<Review> getByGuestId(String guestId)    { return reviewRepository.findByGuestId(guestId); }
    public Optional<Review> getById(String id)          { return reviewRepository.findById(id); }

    // ── Update ───────────────────────────────────────────────────────────────

    public String updateReview(Review review) {
        if (review.getFeedbackId() == null || review.getFeedbackId().isBlank()) {
            return "ERROR: Feedback ID is required.";
        }
        if (reviewRepository.findById(review.getFeedbackId()).isEmpty()) {
            return "ERROR: Feedback not found.";
        }
        reviewRepository.update(review);
        return "SUCCESS";
    }

    // ── Delete ───────────────────────────────────────────────────────────────

    public String deleteReview(String id) {
        if (id == null || id.isBlank()) return "ERROR: Feedback ID is required.";
        if (reviewRepository.findById(id).isEmpty()) return "ERROR: Feedback not found.";
        reviewRepository.delete(id);
        return "SUCCESS";
    }

    // ── Moderation ───────────────────────────────────────────────────────────

    public String moderateReview(String id, boolean verified) {
        if (id == null || id.isBlank()) return "ERROR: Feedback ID is required.";
        Optional<Review> opt = reviewRepository.findById(id);
        if (opt.isEmpty()) return "ERROR: Feedback not found.";
        Review r = opt.get();
        r.setVerified(verified);
        reviewRepository.update(r);
        return "SUCCESS";
    }
}