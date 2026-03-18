package com.sliit.y1s2._5.Roomie.model;

public class Admin {
    private String adminId;
    private String username;
    private String password;
    private String role;
    private String email;
    private String createdAt;

    public Admin() {}

    public Admin(String adminId, String username, String password,
                 String role, String email, String createdAt) {
        this.adminId   = adminId;
        this.username  = username;
        this.password  = password;
        this.role      = role;
        this.email     = email;
        this.createdAt = createdAt;
    }

    public String toCSV() {
        return String.join(",", adminId, username, password, role, email, createdAt);
    }

    public static Admin fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Admin(p[0], p[1], p[2], p[3], p[4], p[5]);
    }

    public String getAdminId()          { return adminId; }
    public void   setAdminId(String v)  { this.adminId = v; }
    public String getUsername()         { return username; }
    public void   setUsername(String v) { this.username = v; }
    public String getPassword()         { return password; }
    public void   setPassword(String v) { this.password = v; }
    public String getRole()             { return role; }
    public void   setRole(String v)     { this.role = v; }
    public String getEmail()            { return email; }
    public void   setEmail(String v)    { this.email = v; }
    public String getCreatedAt()        { return createdAt; }
    public void   setCreatedAt(String v){ this.createdAt = v; }
}
