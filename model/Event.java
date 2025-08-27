package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Event {
    private String name;
    private Date date;
    private String location;
    private String associatedTeam;

    public Event(String name, Date date, String location, String associatedTeam) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.associatedTeam = associatedTeam;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAssociatedTeam() { return associatedTeam; }
    public void setAssociatedTeam(String associatedTeam) { this.associatedTeam = associatedTeam; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return name + "," + sdf.format(date) + "," + location + "," + associatedTeam;
    }

    public static Event fromString(String line) throws Exception {
        String[] parts = line.split(",");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new Event(parts[0], sdf.parse(parts[1]), parts[2], parts[3]);
    }
}