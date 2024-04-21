package dev.ln13.cse360project.frontend;

import dev.ln13.cse360project.backend.SQLInteraction;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageInstance {

    public Label messageRecipient;
    public static String recipient;
    public VBox messageList;
    public TextField messageInput;
    public Button sendButton;
    public Button backButton;
    public static int conversationID;
    private PauseTransition pause;

    public void sendMessage(ActionEvent actionEvent) {
        // Send message to recipient
        System.out.println("Sending message to " + messageRecipient.getText() + ": " + messageInput.getText());
        try {
            SQLInteraction.addMessage(conversationID, MedicalApp.patientName, messageInput.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageInput.clear();
        getMessages();
    }

    public void backButtonAction(ActionEvent actionEvent) {
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/messenger.fxml", "Messenger", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    // Get new messages every 5 seconds
    public void getMessages() {
        ArrayList<Integer> MessageIDs = new ArrayList<>();
        messageList.getChildren().clear();
        try {
            MessageIDs = SQLInteraction.getMessages(conversationID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer messageID : MessageIDs) {
            try {
                VBox msgContainer = new VBox();
                HBox msg = new HBox();
                ResultSet rs = SQLInteraction.getMessageByID(messageID);
                Label msgSender = new Label(rs.getString("sender") + ": ");
                Label timestamp = new Label(rs.getString("date"));
                Label msgText = new Label(rs.getString("message"));msg.getChildren().addAll(msgSender, msgText);
                msgContainer.getChildren().addAll(msg, timestamp);
                messageList.getChildren().add(msgContainer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize() {
        messageRecipient.setText(recipient);
        getMessages();
        pause = new PauseTransition(javafx.util.Duration.seconds(1));
        pause.setOnFinished(e -> {
            getMessages();
            pause.play();
        });
        pause.play();
    }
}
