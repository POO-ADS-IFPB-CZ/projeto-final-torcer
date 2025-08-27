package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class CrudView extends JFrame {
    protected JList<String> itemList;
    protected DefaultListModel<String> listModel;
    protected JButton createButton, updateButton, deleteButton;

    public CrudView(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        add(new JScrollPane(itemList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        createButton = new JButton("Criar");
        updateButton = new JButton("Atualizar");
        deleteButton = new JButton("Deletar");
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addCreateListener(ActionListener listener) { createButton.addActionListener(listener); }
    public void addUpdateListener(ActionListener listener) { updateButton.addActionListener(listener); }
    public void addDeleteListener(ActionListener listener) { deleteButton.addActionListener(listener); }

    public void showMessage(String message) { JOptionPane.showMessageDialog(this, message); }

    public abstract void refreshList(List<?> items);
}