<<<<<<< HEAD
package dev.ln13.cse360project.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Messenger {
    private String messageSent;
    private String messageReceived;
    private String messageSender;
    private String messageRecipient;

    public String getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(String messageSent) {
        this.messageSent = messageSent;
    }

    public String getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(String messageReceived) {
        this.messageReceived = messageReceived;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(String messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public void deliverMessage() {
    /*    try {
            SQLInteraction.conn = SQLInteraction.setupConnection() ;
            String sql = "INSERT INTO messages(sender, recipient, message) VALUES(?,?,?)";

            PreparedStatement pstmt = SQLInteraction.conn.prepareStatement(sql);
            pstmt.setString(1, this.messageSender);
            pstmt.setString(2, this.messageRecipient);
            pstmt.setString(3, this.messageSent);
            pstmt.executeUpdate();
            SQLInteraction.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
    	//figuring out sql
    }
}
=======
package dev.ln13.cse360project.backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Messenger {
    private String messageSent;
    private String messageReceived;
    private String messageSender;
    private String messageRecipient;

    public String getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(String messageSent) {
        this.messageSent = messageSent;
    }

    public String getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(String messageReceived) {
        this.messageReceived = messageReceived;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(String messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public void deliverMessage() {
    /*    try {
            SQLInteraction.conn = SQLInteraction.setupConnection() ;
            String sql = "INSERT INTO messages(sender, recipient, message) VALUES(?,?,?)";

            PreparedStatement pstmt = SQLInteraction.conn.prepareStatement(sql);
            pstmt.setString(1, this.messageSender);
            pstmt.setString(2, this.messageRecipient);
            pstmt.setString(3, this.messageSent);
            pstmt.executeUpdate();
            SQLInteraction.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
    	//figuring out sql
    }
}
>>>>>>> 31ebfcf3c13794012c36ff411ddf2c54c9244e47
