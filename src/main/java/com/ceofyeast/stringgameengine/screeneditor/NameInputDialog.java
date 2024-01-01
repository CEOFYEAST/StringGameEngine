package com.ceofyeast.stringgameengine.screeneditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameInputDialog extends JDialog {
    private JTextField nameTextField;
    private JButton okButton;
    private JButton cancelButton;

    private String enteredName;

    public NameInputDialog(JFrame parent) {
        super(parent, "Enter Name", true);
        setLayout(new BorderLayout());

        // Initialize components
        nameTextField = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        // Add components to the layout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredName = nameTextField.getText();
                dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredName = null;
                dispose(); // Close the dialog
            }
        });

        // Set dialog properties
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setSize(300, 120);
        setResizable(false);
    }

    // Method to get the entered name
    public String getEnteredName() {
        return enteredName;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Main Frame");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JButton openDialogButton = new JButton("Open Dialog");
                openDialogButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NameInputDialog dialog = new NameInputDialog(frame);
                        dialog.setVisible(true);

                        // Retrieve entered name after the dialog is closed
                        String enteredName = dialog.getEnteredName();
                        if (enteredName != null) {
                            JOptionPane.showMessageDialog(frame, "Entered Name: " + enteredName);
                        }
                    }
                });

                frame.add(openDialogButton);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
