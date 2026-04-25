package mtr.group4.Roomie.service;

import mtr.group4.Roomie.model.Customer;
import mtr.group4.Roomie.model.Review;
import mtr.group4.Roomie.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;

    public ReviewService(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public ArrayList<Review> getAllReviews() { return reviewRepo.findAll(); }

    public void addReview(Customer customer, String roomId, int rating, String comment) {
        String id    = reviewRepo.generateId();
        String today = LocalDate.now().toString();
        Review r = new Review(id, customer.getId(), customer.getFullName(),
                roomId, rating, comment, today, today);
        reviewRepo.save(r);
    }

    public double getAverageRating() { return reviewRepo.getAverageRating(); }

    public int countReviews() { return reviewRepo.findAll().size(); }
}
