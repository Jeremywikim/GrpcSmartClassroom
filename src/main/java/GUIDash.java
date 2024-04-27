import com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse;
import io.grpc.stub.StreamObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIDash {

    private JLabel label;
    private JTextField textField;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AttendanceServerServiceClient client; // gRPC client instance

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new GUIDash().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        // Initialize gRPC client
        client = new AttendanceServerServiceClient("localhost", 8080);

        JFrame frame = new JFrame("Smart Classroom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel for the first page with attendance button
        JPanel attendancePanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Rows, columns, horizontal gap, vertical gap
        JButton attendanceButton = new JButton("Attendance");
        JButton ventilationButton = new JButton("Ventilation");
        JButton lightButton = new JButton("Light");

//        JPanel attendancePanel = new JPanel(new BorderLayout());
//        JButton attendanceButton = new JButton("Attendance");
//        JButton ventilationButton = new JButton("Ventilation");
        attendanceButton.addActionListener(e -> {
            textField.setText(""); // Clear the text field
            label.setText("Please enter your name"); // Reset the label text
            cardLayout.show(cardPanel, "InteractionPage");
        });
        attendancePanel.add(attendanceButton);
        attendancePanel.add(ventilationButton);
        attendancePanel.add(lightButton);

        // Panel for the interaction page
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        label = new JLabel("Please enter your name", SwingConstants.CENTER);
        textField = new JTextField(20); // Set a column width for the text field

        JButton button = new JButton("Please confirm attendance!");
        button.addActionListener(e -> clockIn());

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


    private void clockIn() {
        String name = textField.getText();
        if (!name.isEmpty()) {
            // Create an instance of the response handler
            UnaryResponseHandler responseHandler = new UnaryResponseHandler();
            // Use the client to send the unary request with the handler
            client.sendUnaryRequest(name, responseHandler);
        }
    }



    private class UnaryResponseHandler implements StreamObserver<AttendanceResponse> {
        @Override
        public void onNext(AttendanceResponse response) {
            // Use SwingUtilities.invokeLater to update the label on the GUI thread
            SwingUtilities.invokeLater(() -> {
                label.setText(response.getMessage());
                // Optionally reset the text field after showing the response
                textField.setText("");
            });
        }

        @Override
        public void onError(Throwable t) {
            SwingUtilities.invokeLater(() -> {
                label.setText("Error: " + t.getMessage());
                textField.setText("");
            });
            System.err.println("Error in unary request: " + t.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("Unary request completed");
            // Optionally reset the text field when the stream is completed
            SwingUtilities.invokeLater(() -> textField.setText(""));
        }
    }



}
