package com.sliit.y1s2._5.Roomie.model;

public class Room {
    private String roomId;
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private int    capacity;
    private String description;
    private String status;      // AVAILABLE | BOOKED | MAINTENANCE
    private String amenities;

    public Room() {}

    public Room(String roomId, String roomNumber, String type, double pricePerNight,
                int capacity, String description, String status, String amenities) {
        this.roomId       = roomId;
        this.roomNumber   = roomNumber;
        this.type         = type;
        this.pricePerNight = pricePerNight;
        this.capacity     = capacity;
        this.description  = description;
        this.status       = status;
        this.amenities    = amenities;
    }

    public String toCSV() {
        return String.join(",", roomId, roomNumber, type,
                String.valueOf(pricePerNight), String.valueOf(capacity),
                description, status, amenities);
    }

    public static Room fromCSV(String csv) {
        String[] p = csv.split(",", -1);
        return new Room(p[0], p[1], p[2], Double.parseDouble(p[3]),
                Integer.parseInt(p[4]), p[5], p[6], p[7]);
    }

    public String getRoomId()              { return roomId; }
    public void   setRoomId(String v)      { this.roomId = v; }
    public String getRoomNumber()          { return roomNumber; }
    public void   setRoomNumber(String v)  { this.roomNumber = v; }
    public String getType()                { return type; }
    public void   setType(String v)        { this.type = v; }
    public double getPricePerNight()       { return pricePerNight; }
    public void   setPricePerNight(double v){ this.pricePerNight = v; }
    public int    getCapacity()            { return capacity; }
    public void   setCapacity(int v)       { this.capacity = v; }
    public String getDescription()         { return description; }
    public void   setDescription(String v) { this.description = v; }
    public String getStatus()              { return status; }
    public void   setStatus(String v)      { this.status = v; }
    public String getAmenities()           { return amenities; }
    public void   setAmenities(String v)   { this.amenities = v; }
}
