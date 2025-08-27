package controller;

import model.DataPersistence;
import model.Event;
import model.Team;
import model.User;
import view.EventView;
import view.MainView;
import view.TeamView;
import view.UserView;

import java.util.List;

public class MainController {
    private MainView view;
    private List<User> users;
    private List<Team> teams;
    private List<Event> events;
    private User currentUser;

    public MainController(MainView view, List<User> users, User currentUser) {
        this.view = view;
        this.users = users;
        this.teams = DataPersistence.loadTeams();
        this.events = DataPersistence.loadEvents();
        this.currentUser = currentUser;

        view.addUserListener(e -> {
            UserView userView = new UserView(this.users, this.teams);
            new UserController(userView, this.users, this.teams);
        });
        view.addTeamListener(e -> {
            TeamView teamView = new TeamView(this.teams);
            new TeamController(teamView, this.teams);
        });
        view.addEventListener(e -> {
            EventView eventView = new EventView(this.events);
            new EventController(eventView, this.events, this.currentUser);
        });
        view.addLogoutListener(e -> {
            view.dispose();
            new TorcidaManagerApp();
        });
    }
}