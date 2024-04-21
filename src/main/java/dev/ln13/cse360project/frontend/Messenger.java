package dev.ln13.cse360project.frontend;

import dev.ln13.cse360project.backend.SQLInteraction;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class Messenger {
    public Button backButton;
    public Label menuTitle;
    public VBox messageList;
    private PauseTransition pause;

    // Shows every person who has sent/received a message in a list, and when one is clicked opens a MessageInstance for that person
    public void initialize() {
        menuTitle.setText("Messenger");
        getMessages();
        pause = new PauseTransition(javafx.util.Duration.seconds(5));
        pause.setOnFinished(e -> {
            getMessages();
            pause.play();
        });
        pause.play();
    }

    public void backButtonAction() {
        pause.stop();
        if (MedicalApp.userType.equals("patient")) {
            MedicalApp.switchView("/dev/ln13/cse360project/layouts/patient-portal.fxml", "Patient Health Portal", (Stage) backButton.getScene().getWindow());
        } else {
            MedicalApp.switchView("/dev/ln13/cse360project/layouts/provider-portal.fxml", "Provider Access Portal", (Stage) backButton.getScene().getWindow());
        }
    }

    public void threadSelectAction(ActionEvent actionEvent) {
        pause.stop();
        Button selectedThread = (Button) actionEvent.getSource();
        MessageInstance.recipient = selectedThread.getText();
        try {
            MessageInstance.conversationID = SQLInteraction.getConversationID(MessageInstance.recipient, MedicalApp.patientName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        menuTitle.setText(selectedThread.getText());
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/message-instance.fxml", "Message Instance", (Stage) selectedThread.getScene().getWindow());
    }

    public void getMessages() {
        messageList.getChildren().clear();
        try {
            ArrayList<Integer> conversationIDs = SQLInteraction.getConversations(MedicalApp.patientName);
            for (Integer conversationID : conversationIDs) {
                ResultSet rs = SQLInteraction.getConversation(conversationID);
                String threadlabel;
                if (rs.getString("sender").equals(MedicalApp.patientName)) {
                    threadlabel = rs.getString("recipient");
                } else {
                    threadlabel = rs.getString("sender");
                }
                if (threadlabel.equals(MedicalApp.patientName)) {
                    continue;
                }
                Button threadButton = new Button(threadlabel);
                threadButton.setOnAction(this::threadSelectAction);
                messageList.getChildren().add(threadButton);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
