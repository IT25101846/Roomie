package mtr.group4.Roomie.model;

public class Review extends BaseEntity {

    private String reviewId;
    private String customerId;
    private String customerName;
    private String roomId;
    private int    rating;
    private String comment;
    private String reviewDate;

    public Review() { super(); }

    public Review(String reviewId, String customerId, String customerName,
                  String roomId, int rating, String comment,
                  String reviewDate, String createdDate) {
        super(createdDate);
        this.reviewId     = reviewId;
        this.customerId   = customerId;
        this.customerName = customerName;
        this.roomId       = roomId;
        this.rating       = rating;
        this.comment      = comment;
        this.reviewDate   = reviewDate;
    }

    @Override public String getEntityType() { return "REVIEW"; }

    public String getStars() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) sb.append(i <= rating ? "★" : "☆");
        return sb.toString();
    }

    public String toCsv() {
        String safeComment = comment != null ? comment.replace(",", ";") : "";
        return String.join(",", reviewId, customerId, customerName, roomId,
                String.valueOf(rating), safeComment, reviewDate, getCreatedDate());
    }

    public static Review fromCsv(String line) {
        String[] p = line.split(",", 8);
        if (p.length < 8) return null;
        return new Review(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]), p[5], p[6], p[7]);
    }

    public String getReviewId()                 { return reviewId; }
    public void   setReviewId(String reviewId)  { this.reviewId = reviewId; }
    public String getCustomerId()                   { return customerId; }
    public void   setCustomerId(String customerId)  { this.customerId = customerId; }
    public String getCustomerName()                     { return customerName; }
    public void   setCustomerName(String customerName)  { this.customerName = customerName; }
    public String getRoomId()               { return roomId; }
    public void   setRoomId(String roomId)  { this.roomId = roomId; }
    public int    getRating()               { return rating; }
    public void   setRating(int rating)     { this.rating = rating; }
    public String getComment()                  { return comment; }
    public void   setComment(String comment)    { this.comment = comment; }
    public String getReviewDate()                   { return reviewDate; }
    public void   setReviewDate(String reviewDate)  { this.reviewDate = reviewDate; }
}
