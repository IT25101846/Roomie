package mtr.group4.Roomie.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation extends BaseEntity {

    public enum Status { PENDING, CONFIRMED, CHECKED_IN, CHECKED_OUT, CANCELLED }

    private String reservationId;
    private String customerId;
    private String roomId;
    private String checkIn;
    private String checkOut;
    private int    nights;
    private double totalPrice;
    private Status status;

    public Reservation() { super(); }

    public Reservation(String reservationId, String customerId, String roomId,
                       String checkIn, String checkOut, int nights,
                       double totalPrice, String status, String createdDate) {
        super(createdDate);
        this.reservationId = reservationId;
        this.customerId    = customerId;
        this.roomId        = roomId;
        this.checkIn       = checkIn;
        this.checkOut      = checkOut;
        this.nights        = nights;
        this.totalPrice    = totalPrice;
        this.status        = Status.valueOf(status);
    }

    @Override public String getEntityType() { return "RESERVATION"; }

    public boolean overlapsWith(String otherCheckIn, String otherCheckOut) {
        LocalDate s1 = LocalDate.parse(this.checkIn);
        LocalDate e1 = LocalDate.parse(this.checkOut);
        LocalDate s2 = LocalDate.parse(otherCheckIn);
        LocalDate e2 = LocalDate.parse(otherCheckOut);
        return s1.isBefore(e2) && s2.isBefore(e1);
    }

    public static int calcNights(String checkIn, String checkOut) {
        try {
            return (int) ChronoUnit.DAYS.between(LocalDate.parse(checkIn), LocalDate.parse(checkOut));
        } catch (Exception e) { return 0; }
    }

    public String toCsv() {
        return String.join(",", reservationId, customerId, roomId,
                checkIn, checkOut, String.valueOf(nights),
                String.valueOf(totalPrice), status.name(), getCreatedDate());
    }

    public static Reservation fromCsv(String line) {
        String[] p = line.split(",", 9);
        if (p.length < 9) return null;
        return new Reservation(p[0], p[1], p[2], p[3], p[4],
                Integer.parseInt(p[5]), Double.parseDouble(p[6]), p[7], p[8]);
    }

    public String getStatusBadgeClass() {
        switch (status) {
            case CONFIRMED: return "success";
            case CHECKED_IN: return "info";
            case CHECKED_OUT: return "secondary";
            case CANCELLED: return "danger";
            default: return "warning";
        }
    }

    public String getReservationId()                        { return reservationId; }
    public void   setReservationId(String reservationId)    { this.reservationId = reservationId; }
    public String getCustomerId()                   { return customerId; }
    public void   setCustomerId(String customerId)  { this.customerId = customerId; }
    public String getRoomId()               { return roomId; }
    public void   setRoomId(String roomId)  { this.roomId = roomId; }
    public String getCheckIn()              { return checkIn; }
    public void   setCheckIn(String ci)     { this.checkIn = ci; }
    public String getCheckOut()             { return checkOut; }
    public void   setCheckOut(String co)    { this.checkOut = co; }
    public int    getNights()               { return nights; }
    public void   setNights(int nights)     { this.nights = nights; }
    public double getTotalPrice()                   { return totalPrice; }
    public void   setTotalPrice(double totalPrice)  { this.totalPrice = totalPrice; }
    public Status getStatus()               { return status; }
    public void   setStatus(Status status)  { this.status = status; }
}