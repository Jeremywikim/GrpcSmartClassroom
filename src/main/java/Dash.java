import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Dash {

    private JLabel label;
    private JTextField textField;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Dash().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Smart Classroom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel for the first page with attendance button
        JPanel attendancePanel = new JPanel(new BorderLayout());
        JButton attendanceButton = new JButton("Attendance");
        attendanceButton.addActionListener(e -> {
            textField.setText(""); // Clear the text field
            label.setText("Please enter your name"); // Reset the label text
            cardLayout.show(cardPanel, "InteractionPage");
        });
        attendancePanel.add(attendanceButton, BorderLayout.CENTER);

        // Panel for the interaction page
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        label = new JLabel("Please enter your name", SwingConstants.CENTER);
        textField = new JTextField(20); // Set a column width for the text field

        JButton button = new JButton("Please confirm attendance!");
        button.addActionListener(e -> sayHello());

        // Button to go back to the first page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            textField.setText(""); // Clear the text field
            label.setText("Please enter your name"); // Reset the label text
            cardLayout.show(cardPanel, "AttendancePage");
        });


        interactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        interactionPanel.add(label);
        interactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        interactionPanel.add(textField);
        interactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        interactionPanel.add(button);
        interactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        interactionPanel.add(backButton); // Add the back button to the interaction panel

        // Add both panels to the card panel
        cardPanel.add(attendancePanel, "AttendancePage");
        cardPanel.add(interactionPanel, "InteractionPage");

        // Add card panel to the frame
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    private void sayHello() {
        String name = textField.getText();
        if (!name.isEmpty()) {
            label.setText("Congratulation, " + name + ", successfully checked in!");
        }
    }
}
