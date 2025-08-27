package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {
    private static final String USERS_FILE = "users.txt";
    private static final String TEAMS_FILE = "teams.txt";
    private static final String EVENTS_FILE = "events.txt";
    private static final String BETS_FILE = "bets.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(User.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar users.txt: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                bw.write(user.toString());
                bw.newLine();
            }
        }
    }

    public static List<Team> loadTeams() {
        List<Team> teams = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TEAMS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                teams.add(Team.fromString(line));
            }
        } catch (IOException e) {}
        return teams;
    }

    public static void saveTeams(List<Team> teams) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEAMS_FILE))) {
            for (Team team : teams) {
                bw.write(team.toString());
                bw.newLine();
            }
        }
    }

    public static List<Event> loadEvents() {
        List<Event> events = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EVENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    events.add(Event.fromString(line));
                } catch (Exception e) {}
            }
        } catch (IOException e) {}
        return events;
    }

    public static void saveEvents(List<Event> events) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EVENTS_FILE))) {
            for (Event event : events) {
                bw.write(event.toString());
                bw.newLine();
            }
        }
    }
}