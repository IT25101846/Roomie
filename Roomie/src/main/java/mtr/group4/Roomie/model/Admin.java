package mtr.group4.Roomie.model;

public class Admin extends Person {

    private int adminLevel;

    public Admin() { super(); }

    public Admin(String adminId, String firstName, String lastName,
                 String email, String password, int adminLevel) {
        super(adminId, firstName, lastName, email, password);
        this.adminLevel = adminLevel;
    }

    @Override
    public String getRole() { return "ADMIN"; }

    public boolean isSuperAdmin() { return adminLevel >= 2; }

    public String getAdminId()             { return getId(); }
    public void   setAdminId(String id)    { setId(id); }
    public int    getAdminLevel()          { return adminLevel; }
    public void   setAdminLevel(int level) { this.adminLevel = level; }

    public String toCSV() {
        return String.join(",",
                getId(), getFirstName(), getLastName(),
                getEmail(), getPassword(),
                String.valueOf(adminLevel));
    }

    public static Admin fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Admin(p[0], p[1], p[2], p[3], p[4],
                Integer.parseInt(p[5]));
    }
}