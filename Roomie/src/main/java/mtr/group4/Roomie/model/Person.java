package mtr.group4.Roomie.model;

public abstract class Person {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    protected Person() {}

    protected Person(String id, String firstName, String lastName,
                     String email, String password) {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.password  = password;
    }

    public abstract String getRole();

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean authenticate(String rawPassword) {
        return this.password != null && this.password.equals(rawPassword);
    }

    public String getId()                 { return id; }
    public void   setId(String id)        { this.id = id; }
    public String getFirstName()          { return firstName; }
    public void   setFirstName(String fn) { this.firstName = fn; }
    public String getLastName()           { return lastName; }
    public void   setLastName(String ln)  { this.lastName = ln; }
    public String getEmail()              { return email; }
    public void   setEmail(String email)  { this.email = email; }
    public String getPassword()           { return password; }
    public void   setPassword(String pw)  { this.password = pw; }
}