package view;

import model.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EventView extends CrudView {
    private JTextField nameField, dateField, locationField, teamField;
    private JComboBox<String> predictionComboBox;
    private JButton betButton;

    public EventView(List<Event> events) {
        super("Gerenciar Eventos", 400, 500);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Data (dd/MM/yyyy):"));
        dateField = new JTextField();
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Local:"));
        locationField = new JTextField();
        inputPanel.add(locationField);
        inputPanel.add(new JLabel("Jogo:"));
        teamField = new JTextField();
        inputPanel.add(teamField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel betPanel = new JPanel(new FlowLayout());
        predictionComboBox = new JComboBox<>();
        predictionComboBox.addItem("Vitoria do Time 1");
        predictionComboBox.addItem("Vitoria do Time 2");
        predictionComboBox.addItem("Empate");
        betButton = new JButton("Palpitar");

        betPanel.add(new JLabel("Seu Palpite:"));
        betPanel.add(predictionComboBox);
        betPanel.add(betButton);

        add(betPanel, BorderLayout.SOUTH);

        refreshList(events);
    }

    public String getSelectedEvent() { return itemList.getSelectedValue(); }
    public String getName() { return nameField.getText(); }
    public String getDate() { return dateField.getText(); }
    public String getEventLocation() { return locationField.getText(); }
    public String getTeam() { return teamField.getText(); }
    public String getPrediction() { return (String) predictionComboBox.getSelectedItem(); }

    public void addBetListener(ActionListener listener) { betButton.addActionListener(listener); }

    @Override
    public void refreshList(List<?> events) {
        listModel.clear();
        for (Object eventObj : events) {
            Event event = (Event) eventObj;
            listModel.addElement(event.getName());
        }
    }

}