package view;

import model.Team;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TeamView extends CrudView {
    private JTextField nameField, cityField, yearField;

    public TeamView(List<Team> teams) {
        super("Gerenciar Times", 400, 400);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Cidade:"));
        cityField = new JTextField();
        inputPanel.add(cityField);
        inputPanel.add(new JLabel("Ano Fundação:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        add(inputPanel, BorderLayout.NORTH);

        refreshList(teams);
    }

    public String getSelectedTeam() { return itemList.getSelectedValue(); }
    public String getName() { return nameField.getText(); }
    public String getCity() { return cityField.getText(); }
    public int getYear() { return Integer.parseInt(yearField.getText()); }

    @Override
    public void refreshList(List<?> teams) {
        listModel.clear();
        for (Object teamObj : teams) {
            Team team = (Team) teamObj;
            listModel.addElement(team.getName());
        }
    }
}