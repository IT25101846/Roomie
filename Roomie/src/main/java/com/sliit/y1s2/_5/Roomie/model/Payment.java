package com.sliit.y1s2._5.Roomie.model;

public class Payment {
    private String paymentId;
    private String reservationId;
    private String customerId;
    private double amount;
    private String paymentDate;
    private String method;    // CASH | CREDIT_CARD | BANK_TRANSFER
    private String status;    // PENDING | COMPLETED | REFUNDED

    public Payment() {}

    public Payment(String paymentId, String reservationId, String customerId,
                   double amount, String paymentDate, String method, String status) {
        this.paymentId     = paymentId;
        this.reservationId = reservationId;
        this.customerId    = customerId;
        this.amount        = amount;
        this.paymentDate   = paymentDate;
        this.method        = method;
        this.status        = status;
    }

    public String toCSV() {
        return String.join(",", paymentId, reservationId, customerId,
                String.valueOf(amount), paymentDate, method, status);
    }

    public static Payment fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Payment(p[0], p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], p[6]);
    }

    public String getPaymentId()             { return paymentId; }
    public void   setPaymentId(String v)     { this.paymentId = v; }
    public String getReservationId()         { return reservationId; }
    public void   setReservationId(String v) { this.reservationId = v; }
    public String getCustomerId()            { return customerId; }
    public void   setCustomerId(String v)    { this.customerId = v; }
    public double getAmount()                { return amount; }
    public void   setAmount(double v)        { this.amount = v; }
    public String getPaymentDate()           { return paymentDate; }
    public void   setPaymentDate(String v)   { this.paymentDate = v; }
    public String getMethod()                { return method; }
    public void   setMethod(String v)        { this.method = v; }
    public String getStatus()                { return status; }
    public void   setStatus(String v)        { this.status = v; }
}
