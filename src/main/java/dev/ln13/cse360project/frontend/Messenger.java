package dev.ln13.cse360project.frontend;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Messenger {
    public Button backButton;
    public Label menuTitle;
    public VBox messageList;

    // Shows every person who has sent/received a message in a list, and when one is clicked opens a MessageInstance for that person
    public void initialize() {
        ArrayList<String> threads = new ArrayList<>();
        threads.add("Dr. Smith");
        threads.add("Dr. Johnson");
        threads.add("Dr. Patel");
        threads.add("Dr. Lee");
        threads.add("Dr. Kim");

        for (String thread : threads) {
            Button threadButton = new Button(thread);
            threadButton.setOnAction(this::threadSelectAction);
            messageList.getChildren().add(threadButton);
        }

        menuTitle.setText("Messenger");

         PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(5));
         Thread refreshThread = new Thread(() -> {
             while (true) {
                 getMessages();
                 pause.play();
             }
         });
         refreshThread.start();
    }

    public void backButtonAction() {
        if (MedicalApp.userType.equals("patient")) {
            MedicalApp.switchView("/dev/ln13/cse360project/layouts/patient-portal.fxml", "Patient Health Portal", (Stage) backButton.getScene().getWindow());
        } else {
            MedicalApp.switchView("/dev/ln13/cse360project/layouts/provider-portal.fxml", "Provider Access Portal", (Stage) backButton.getScene().getWindow());
        }
    }

    public void threadSelectAction(ActionEvent actionEvent) {
        Button selectedThread = (Button) actionEvent.getSource();
        MessageInstance.recipient = selectedThread.getText();MedicalApp.switchView("/dev/ln13/cse360project/layouts/message-instance.fxml", "Message Instance", (Stage) selectedThread.getScene().getWindow());
    }

    public void getMessages() {
        // Get messages from recipient
        System.out.println("Getting messages from " + MessageInstance.recipient);
        VBox msgContainer = new VBox();
        Label msgSender = new Label(MessageInstance.recipient + ": ");
        Label timestamp = new Label("12:00 PM");
        Label msgText = new Label("Hello!");
        msgContainer.getChildren().addAll(msgSender, msgText, timestamp);
        messageList.getChildren().add(msgContainer);
    }
}
