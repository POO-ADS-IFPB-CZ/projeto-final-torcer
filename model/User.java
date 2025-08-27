package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String password;
    private String name;
    private String email;
    private String favoriteTeam;

    public User(String username, String password, String name, String email, String favoriteTeam) {
        this.username = username;
        this.password = hashPassword(password);
        this.name = name;
        this.email = email;
        this.favoriteTeam = favoriteTeam;
    }

    public User(String username, String passwordHash, String name, String email, String favoriteTeam, boolean isHashed) {
        this.username = username;
        this.password = passwordHash;
        this.name = name;
        this.email = email;
        this.favoriteTeam = favoriteTeam;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = hashPassword(password); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFavoriteTeam() { return favoriteTeam; }
    public void setFavoriteTeam(String favoriteTeam) { this.favoriteTeam = favoriteTeam; }

    @Override
    public String toString() {
        return username + "," + password + "," + name + "," + email + "," + favoriteTeam;
    }

    public static User fromString(String line) {
        String[] parts = line.split(",");
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4], true);
    }
}