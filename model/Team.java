package model;

public class Team {
    private String name;
    private String city;
    private int foundationYear;

    public Team(String name, String city, int foundationYear) {
        this.name = name;
        this.city = city;
        this.foundationYear = foundationYear;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public int getFoundationYear() { return foundationYear; }
    public void setFoundationYear(int foundationYear) { this.foundationYear = foundationYear; }

    @Override
    public String toString() {
        return name + "," + city + "," + foundationYear;
    }

    public static Team fromString(String line) {
        String[] parts = line.split(",");
        return new Team(parts[0], parts[1], Integer.parseInt(parts[2]));
    }
}