package controller;

import model.DataPersistence;
import model.Event;
import model.User;
import view.EventView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventController {
    private EventView view;
    private List<Event> events;
    private User currentUser;

    public EventController(EventView view, List<Event> events, User currentUser) {
        this.view = view;
        this.events = events;
        this.currentUser = currentUser;

        view.addCreateListener(new CreateListener());
        view.addUpdateListener(new UpdateListener());
        view.addDeleteListener(new DeleteListener());
        view.addBetListener(new BetListener());
    }

    class CreateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getName();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(view.getDate());
                String location = view.getEventLocation();
                String team = view.getTeam();
                Event newEvent = new Event(name, date, location, team);
                events.add(newEvent);
                saveAndRefresh();
            } catch (Exception ex) {
                view.showMessage("Formato de data inválido! Use dd/MM/yyyy");
            }
        }
    }

    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = view.getSelectedEvent();
            if (selected == null) return;
            try {
                for (Event event : events) {
                    if (event.getName().equals(selected)) {
                        event.setName(view.getName());
                        event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(view.getDate()));
                        event.setLocation(view.getEventLocation());
                        event.setAssociatedTeam(view.getTeam());
                        saveAndRefresh();
                        return;
                    }
                }
            } catch (Exception ex) {
                view.showMessage("Formato de data inválido! Use dd/MM/yyyy");
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = view.getSelectedEvent();
            if (selected == null) return;
            events.removeIf(event -> event.getName().equals(selected));
            saveAndRefresh();
        }
    }

    class BetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eventName = view.getSelectedEvent();
            String prediction = view.getPrediction();

            if (eventName == null) {
                view.showMessage("Selecione um evento para palpitar.");
                return;
            }
        }
    }

    private void saveAndRefresh() {
        try {
            DataPersistence.saveEvents(events);
            view.refreshList(events);
            view.showMessage("Operação realizada!");
        } catch (Exception ex) {
            view.showMessage("Erro ao salvar!");
        }
    }
}