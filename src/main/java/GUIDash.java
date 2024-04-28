import com.mingyan.smartClassroom.ChatService.ClassroomMessage;
import com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse;
import io.grpc.stub.StreamObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIDash {

    private JLabel label;
    private JTextField textField;
    private JTextArea chatArea;
    private JTextField chatInput;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AttendanceServerServiceClient attendanceClient; // gRPC client for attendance
    private ChatServerServiceClient chatClient; // gRPC client for chat

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

    /*
     * This function is used to create a gui dashboard
     * There are three buttons at the first page of the app.
     *
     */
    private void createAndShowGUI() {
        // Initialize gRPC attendanceClient
        attendanceClient = new AttendanceServerServiceClient("localhost", 8080);
        chatClient = new ChatServerServiceClient("localhost", 8080);

        // give the gui a name
        JFrame frame = new JFrame("Smart Classroom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // keep a exit choice
        frame.setSize(500, 400); // set size of gui dashboard

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel for the first page with three buttons
        // Rows, columns, horizontal gap, vertical gap
        JPanel firstPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton attendanceButton = new JButton("Attendance");
        JButton ChatButton = new JButton("Chat");
        JButton CCTVButton = new JButton("CCTV");

        // This is used to keep prompt default (in previous test, I found the lat record will be saved there)
        attendanceButton.addActionListener(e -> {
            textField.setText(""); // Clear the text field
            label.setText("Please enter your name"); // Reset the label text
            cardLayout.show(cardPanel, "attendancePage");
        });

        // Navigate to the chat page
        ChatButton.addActionListener(e -> {
            // Initialize the chat client if it's not already initialized
            if (chatClient == null) {
                chatClient = new ChatServerServiceClient("localhost", 8080); // Adjust host and port as needed
            }
            cardLayout.show(cardPanel, "chatPage");
        });

        // add to gui dashboard
        firstPanel.add(attendanceButton);
        firstPanel.add(ChatButton);
        firstPanel.add(CCTVButton);

        /*
          * Panel for the attendance page
          *
         */

        JPanel attendancePanel = new JPanel();
        attendancePanel.setLayout(new BoxLayout(attendancePanel, BoxLayout.Y_AXIS)); // vertical layout

        label = new JLabel("Please enter your name", SwingConstants.CENTER); // keep in the center
        textField = new JTextField(20); // Set a column width for the text field

        JButton button = new JButton("Please confirm attendance!");
        button.addActionListener(e -> clockIn());// when click the button, run the clockIn() function

        // Button to go back to the first page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            textField.setText(""); // Clear the text field
            label.setText("Please enter your name"); // Reset the label text
            cardLayout.show(cardPanel, "firstPage");
        });

        // layout
        attendancePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        attendancePanel.add(label);
        attendancePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        attendancePanel.add(textField);
        attendancePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        attendancePanel.add(button);
        attendancePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        attendancePanel.add(backButton); // Add the back button to the attendance panel




        /*
        * Panel for the chat service page
        * chatPanel from here
         */

        JPanel chatPanel = new JPanel(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size for scrollPane
        chatPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a sub-panel for user input and the send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        chatInput = new JTextField();

        JButton sendButton =     new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());

        // Button to go back to the first page
        // Create a sub-panel for the "Back" button in the north
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton chatBackButton = new JButton("Back");
        chatBackButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "firstPage");
        });
        topPanel.add(chatBackButton, BorderLayout.EAST); // This puts the button in the top-right corner



        inputPanel.add(chatInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);
        chatPanel.add(topPanel, BorderLayout.NORTH); // Add the top panel to the chat panel at the top






        //*************************************************
        // Add both panels to the card panel
        cardPanel.add(firstPanel, "firstPage");
        cardPanel.add(attendancePanel, "attendancePage");
        cardPanel.add(chatPanel, "chatPage");

        // Add card panel to the frame
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    /*
     * This function is used to send name to server and get response
     */
    private void clockIn() {
        String name = textField.getText();
        if (!name.isEmpty()) {
            // Create an instance of the response handler
            UnaryResponseHandler responseHandler = new UnaryResponseHandler();
            // Use the attendanceClient to send the unary request with the handler
            attendanceClient.sendUnaryRequest(name, responseHandler);
        }
    }


    /*
     * This is used to get response from server and pass it to the text filed
     *
     */
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

        // show error information if attendanceClient does not connect to server successfully
        @Override
        public void onError(Throwable t) {
            SwingUtilities.invokeLater(() -> {
                label.setText("Error: " + t.getMessage());
                textField.setText("");
            });
            System.err.println("Error in unary request: " + t.getMessage());
        }

        // show some notification when thread finished
        @Override
        public void onCompleted() {
            System.out.println("Unary request completed");
            // Optionally reset the text field when the stream is completed
            SwingUtilities.invokeLater(() -> textField.setText(""));
        }
    }


    /*
    * for chat service
     */

    private void sendMessage() {
        String message = chatInput.getText();
        if (!message.isEmpty()) {
            // Assuming you have a method to get the user (hardcoded or otherwise)
            ClassroomMessage msg = ClassroomMessage.newBuilder()
                    .setUser("Student")
                    .setMessage(message)
                    .setTimestamp(System.currentTimeMillis())
                    .build();

            // Display the message in the chat area with a prefix to denote the user's message
            chatArea.append("You: " + message + "\n"); // Add the user's message to the chat area


            chatClient.startChat(new StreamObserver<ClassroomMessage>() {
                @Override
                public void onNext(ClassroomMessage msg) {
                    SwingUtilities.invokeLater(() -> chatArea.append("Server: " + msg.getMessage() + "\n"));
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Chat error: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Chat completed.");
                }
            }).onNext(msg);
            chatInput.setText(""); // Clear input after sending
        }
    }

}