package controller;

import model.DataPersistence;
import model.Team;
import view.TeamView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeamController {
    private TeamView view;
    private List<Team> teams;

    public TeamController(TeamView view, List<Team> teams) {
        this.view = view;
        this.teams = teams;

        view.addCreateListener(new CreateListener());
        view.addUpdateListener(new UpdateListener());
        view.addDeleteListener(new DeleteListener());
    }

    class CreateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getName();
            String city = view.getCity();
            int year = view.getYear();
            Team newTeam = new Team(name, city, year);
            teams.add(newTeam);
            saveAndRefresh();
        }
    }

    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = view.getSelectedTeam();
            if (selected == null) return;
            for (Team team : teams) {
                if (team.getName().equals(selected)) {
                    team.setName(view.getName());
                    team.setCity(view.getCity());
                    team.setFoundationYear(view.getYear());
                    saveAndRefresh();
                    return;
                }
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = view.getSelectedTeam();
            if (selected == null) return;
            teams.removeIf(team -> team.getName().equals(selected));
            saveAndRefresh();
        }
    }

    private void saveAndRefresh() {
        try {
            DataPersistence.saveTeams(teams);
            view.refreshList(teams);
            view.showMessage("Operação realizada!");
        } catch (Exception ex) {
            view.showMessage("Erro ao salvar!");
        }
    }
}