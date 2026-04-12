package mtr.group4.Roomie.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Room extends BaseEntity {

    private String            roomId;
    private String            roomNumber;
    private String            type;
    private double            pricePerNight;
    private int               capacity;
    private String            description;
    private ArrayList<String> amenities;
    private String            status;

    public Room() {
        super();
        this.amenities = new ArrayList<>();
        this.status    = "AVAILABLE";
    }

    public Room(String roomId, String roomNumber, String type,
                double pricePerNight, int capacity, String description,
                ArrayList<String> amenities, String status) {
        super(java.time.LocalDate.now().toString());
        this.roomId        = roomId;
        this.roomNumber    = roomNumber;
        this.type          = type;
        this.pricePerNight = pricePerNight;
        this.capacity      = capacity;
        this.description   = description;
        this.amenities     = amenities != null ? amenities : new ArrayList<>();
        this.status        = status;
    }

    @Override
    public String getEntityType() { return "ROOM"; }

    public boolean isAvailable() {
        return "AVAILABLE".equalsIgnoreCase(status);
    }

    public String getAmenitiesDisplay() {
        return String.join("|", amenities);
    }

    public String toCSV() {
        return String.join(",",
                roomId, roomNumber, type,
                String.valueOf(pricePerNight),
                String.valueOf(capacity),
                description,
                String.join("|", amenities),
                status);
    }

    public static Room fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        ArrayList<String> ams = new ArrayList<>();
        if (p.length > 6 && !p[6].isBlank())
            ams.addAll(Arrays.asList(p[6].split("\\|")));
        return new Room(p[0], p[1], p[2],
                Double.parseDouble(p[3]),
                Integer.parseInt(p[4]),
                p[5], ams,
                p.length > 7 ? p[7] : "AVAILABLE");
    }

    public String            getRoomId()                       { return roomId; }
    public void              setRoomId(String id)              { this.roomId = id; }
    public String            getRoomNumber()                   { return roomNumber; }
    public void              setRoomNumber(String rn)          { this.roomNumber = rn; }
    public String            getType()                         { return type; }
    public void              setType(String t)                 { this.type = t; }
    public double            getPricePerNight()                { return pricePerNight; }
    public void              setPricePerNight(double p)        { this.pricePerNight = p; }
    public int               getCapacity()                     { return capacity; }
    public void              setCapacity(int c)                { this.capacity = c; }
    public String            getDescription()                  { return description; }
    public void              setDescription(String d)          { this.description = d; }
    public ArrayList<String> getAmenities()                    { return amenities; }
    public void              setAmenities(ArrayList<String> a) { this.amenities = a; }
    public String            getStatus()                       { return status; }
    public void              setStatus(String s)               { this.status = s; }
}