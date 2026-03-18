package com.sliit.y1s2._5.Roomie.model;

public class Reservation {
    private String reservationId;
    private String customerId;
    private String roomId;
    private String checkInDate;
    private String checkOutDate;
    private String status;       // PENDING | CONFIRMED | CANCELLED
    private double totalPrice;
    private String createdAt;

    public Reservation() {}

    public Reservation(String reservationId, String customerId, String roomId,
                       String checkInDate, String checkOutDate, String status,
                       double totalPrice, String createdAt) {
        this.reservationId = reservationId;
        this.customerId    = customerId;
        this.roomId        = roomId;
        this.checkInDate   = checkInDate;
        this.checkOutDate  = checkOutDate;
        this.status        = status;
        this.totalPrice    = totalPrice;
        this.createdAt     = createdAt;
    }

    public String toCSV() {
        return String.join(",", reservationId, customerId, roomId,
                checkInDate, checkOutDate, status,
                String.valueOf(totalPrice), createdAt);
    }

    public static Reservation fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Reservation(p[0], p[1], p[2], p[3], p[4], p[5],
                Double.parseDouble(p[6]), p[7]);
    }

    public String getReservationId()           { return reservationId; }
    public void   setReservationId(String v)   { this.reservationId = v; }
    public String getCustomerId()              { return customerId; }
    public void   setCustomerId(String v)      { this.customerId = v; }
    public String getRoomId()                  { return roomId; }
    public void   setRoomId(String v)          { this.roomId = v; }
    public String getCheckInDate()             { return checkInDate; }
    public void   setCheckInDate(String v)     { this.checkInDate = v; }
    public String getCheckOutDate()            { return checkOutDate; }
    public void   setCheckOutDate(String v)    { this.checkOutDate = v; }
    public String getStatus()                  { return status; }
    public void   setStatus(String v)          { this.status = v; }
    public double getTotalPrice()              { return totalPrice; }
    public void   setTotalPrice(double v)      { this.totalPrice = v; }
    public String getCreatedAt()               { return createdAt; }
    public void   setCreatedAt(String v)       { this.createdAt = v; }
}
