package com.sliit.y1s2._5.Roomie.model;

public class Review {
    private String  feedbackId;
    private String  guestId;
    private String  reservationId;
    private int     rating;        // 1-5
    private String  comment;
    private String  feedbackDate;
    private boolean isVerified;    // true = approved, false = not approved/pending

    public Review() {}

    public Review(String feedbackId, String guestId, String reservationId,
                  int rating, String comment, String feedbackDate, boolean isVerified) {
        this.feedbackId    = feedbackId;
        this.guestId       = guestId;
        this.reservationId = reservationId;
        this.rating        = rating;
        this.comment       = comment;
        this.feedbackDate  = feedbackDate;
        this.isVerified    = isVerified;
    }

    /**
     * Serialises to pipe-delimited string to avoid conflicts with commas in comments.
     * New format: feedbackId|guestId|reservationId|rating|comment|feedbackDate|isVerified
     * isVerified is stored as "true"/"false".
     * Backwards compatibility: fromCSV still understands legacy status values.
     */
    public String toCSV() {
        // Escape any pipe characters already in the comment to keep parsing safe
        String safeComment = comment == null ? "" : comment.replace("|", "&#124;");
        return String.join("|",
                feedbackId,
                guestId,
                reservationId,
                String.valueOf(rating),
                safeComment,
                feedbackDate,
                String.valueOf(isVerified));
    }

    public static Review fromCSV(String line) {
        // Support both legacy comma-delimited lines and new pipe-delimited lines
        String[] p;
        if (line.contains("|")) {
            p = line.split("\\|", 7);
        } else {
            // Legacy fallback: split on comma but only first 6 commas so comment is kept intact
            p = line.split(",", 7);
        }
        if (p.length < 7) throw new IllegalArgumentException("Invalid review line: " + line);
        String comment = p[4].replace("&#124;", "|");

        String rawFlag = p[6].trim();
        boolean verified;
        // Legacy support: status column PENDING | APPROVED | REJECTED
        if ("APPROVED".equalsIgnoreCase(rawFlag)) {
            verified = true;
        } else if ("true".equalsIgnoreCase(rawFlag) || "1".equals(rawFlag)) {
            verified = true;
        } else {
            verified = false; // PENDING, REJECTED, or anything else
        }

        return new Review(
                p[0].trim(),
                p[1].trim(),
                p[2].trim(),
                Integer.parseInt(p[3].trim()),
                comment,
                p[5].trim(),
                verified
        );
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public String  getFeedbackId()            { return feedbackId; }
    public void    setFeedbackId(String v)    { this.feedbackId = v; }

    public String  getGuestId()              { return guestId; }
    public void    setGuestId(String v)      { this.guestId = v; }

    public String  getReservationId()        { return reservationId; }
    public void    setReservationId(String v){ this.reservationId = v; }

    public int     getRating()               { return rating; }
    public void    setRating(int v)          { this.rating = v; }

    public String  getComment()              { return comment; }
    public void    setComment(String v)      { this.comment = v; }

    public String  getFeedbackDate()         { return feedbackDate; }
    public void    setFeedbackDate(String v) { this.feedbackDate = v; }

    public boolean isVerified()              { return isVerified; }
    public void    setVerified(boolean v)    { this.isVerified = v; }
}