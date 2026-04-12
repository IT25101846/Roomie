package mtr.group4.Roomie.model;

public class Customer extends Person {

    private String phone;
    private String address;
    private String createdAt;

    public Customer() {
        super();
    }

    public Customer(String customerId, String firstName, String lastName,
                    String email, String password,
                    String phone, String address, String createdAt) {
        super(customerId, firstName, lastName, email, password);
        this.phone     = phone;
        this.address   = address;
        this.createdAt = createdAt;
    }

    @Override
    public String getRole() { return "CUSTOMER"; }

    public String getCustomerId()          { return getId(); }
    public void   setCustomerId(String id) { setId(id); }

    public String toCSV() {
        return String.join(",",
                getId(), getFirstName(), getLastName(),
                getEmail(), getPassword(),
                phone, address, createdAt);
    }

    public static Customer fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Customer(p[0], p[1], p[2], p[3], p[4],
                p[5], p[6], p[7]);
    }

    public String getPhone()              { return phone; }
    public void   setPhone(String phone)  { this.phone = phone; }
    public String getAddress()            { return address; }
    public void   setAddress(String a)    { this.address = a; }
    public String getCreatedAt()          { return createdAt; }
    public void   setCreatedAt(String d)  { this.createdAt = d; }
}