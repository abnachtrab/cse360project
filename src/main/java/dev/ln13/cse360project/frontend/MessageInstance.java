package dev.ln13.cse360project.frontend;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class MessageInstance {

    public Label messageRecipient;
    public static String recipient;
    public VBox messageList;
    public TextField messageInput;
    public Button sendButton;
    public Button backButton;

    public void sendMessage(ActionEvent actionEvent) {
        // Send message to recipient
        System.out.println("Sending message to " + messageRecipient.getText() + ": " + messageInput.getText());
        VBox msgContainer = new VBox();
        HBox msg = new HBox();
        Label msgSender = new Label("You: ");
        Label timestamp = new Label(LocalDateTime.now().toString());
        Label msgText = new Label(messageInput.getText());
        msg.getChildren().addAll(msgSender, msgText);
        msgContainer.getChildren().addAll(msg, timestamp);
        messageList.getChildren().add(msgContainer);
        messageInput.setText("");
    }

    public void backButtonAction(ActionEvent actionEvent) {
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/messenger.fxml", "Messenger", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    // Get new messages every 5 seconds
    public void getMessages() {
        // Get messages from recipient
        System.out.println("Getting messages from " + messageRecipient.getText());
        VBox msgContainer = new VBox();
        HBox msg = new HBox();
        Label msgSender = new Label(messageRecipient.getText() + ": ");
        Label timestamp = new Label(LocalDateTime.now().toString());
        Label msgText = new Label("Hello!");
        msg.getChildren().addAll(msgSender, msgText);
        msgContainer.getChildren().addAll(msg, timestamp);
        messageList.getChildren().add(msgContainer);
    }
    
    public void initialize() {
        messageRecipient.setText(recipient);
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(5));
        Thread refreshThread = new Thread(() -> {
            while (true) {
                getMessages();
                pause.play();
            }
        });
        refreshThread.setDaemon(true);
        refreshThread.start();
        
    }
}
