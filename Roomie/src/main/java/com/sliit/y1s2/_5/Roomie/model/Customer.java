package com.sliit.y1s2._5.Roomie.model;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String createdAt;

    public Customer() {}

    public Customer(String customerId, String firstName, String lastName,
                    String email, String password, String phone,
                    String address, String createdAt) {
        this.customerId = customerId;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.password   = password;
        this.phone      = phone;
        this.address    = address;
        this.createdAt  = createdAt;
    }

    public String toCSV() {
        return String.join(",", customerId, firstName, lastName, email, password, phone, address, createdAt);
    }

    public static Customer fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Customer(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
    }

    public String getCustomerId()         { return customerId; }
    public void   setCustomerId(String v) { this.customerId = v; }
    public String getFirstName()          { return firstName; }
    public void   setFirstName(String v)  { this.firstName = v; }
    public String getLastName()           { return lastName; }
    public void   setLastName(String v)   { this.lastName = v; }
    public String getEmail()              { return email; }
    public void   setEmail(String v)      { this.email = v; }
    public String getPassword()           { return password; }
    public void   setPassword(String v)   { this.password = v; }
    public String getPhone()              { return phone; }
    public void   setPhone(String v)      { this.phone = v; }
    public String getAddress()            { return address; }
    public void   setAddress(String v)    { this.address = v; }
    public String getCreatedAt()          { return createdAt; }
    public void   setCreatedAt(String v)  { this.createdAt = v; }
    public String getFullName()           { return firstName + " " + lastName; }
}
