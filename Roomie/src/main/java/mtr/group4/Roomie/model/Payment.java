package mtr.group4.Roomie.model;

public class Payment extends BaseEntity {

    public enum Method { CASH, CARD, ONLINE }

    private String paymentId;
    private String reservationId;
    private double amount;
    private String paymentDate;
    private Method method;
    private String status;

    public Payment() {
        super();
        this.status = "COMPLETED";
    }

    public Payment(String paymentId, String reservationId,
                   double amount, String paymentDate,
                   Method method, String status) {
        super(paymentDate);
        this.paymentId     = paymentId;
        this.reservationId = reservationId;
        this.amount        = amount;
        this.paymentDate   = paymentDate;
        this.method        = method;
        this.status        = status;
    }

    @Override
    public String getEntityType() { return "PAYMENT"; }

    public String toCSV() {
        return String.join(",",
                paymentId, reservationId,
                String.valueOf(amount),
                paymentDate, method.name(), status);
    }

    public static Payment fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Payment(p[0], p[1],
                Double.parseDouble(p[2]),
                p[3], Method.valueOf(p[4]), p[5]);
    }

    public String getPaymentId()               { return paymentId; }
    public void   setPaymentId(String id)      { this.paymentId = id; }
    public String getReservationId()           { return reservationId; }
    public void   setReservationId(String id)  { this.reservationId = id; }
    public double getAmount()                  { return amount; }
    public void   setAmount(double a)          { this.amount = a; }
    public String getPaymentDate()             { return paymentDate; }
    public void   setPaymentDate(String d)     { this.paymentDate = d; }
    public Method getMethod()                  { return method; }
    public void   setMethod(Method m)          { this.method = m; }
    public String getStatus()                  { return status; }
    public void   setStatus(String s)          { this.status = s; }
}