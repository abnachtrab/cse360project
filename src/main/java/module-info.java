module cse360project {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    opens dev.ln13.cse360project.frontend to javafx.fxml;
    exports dev.ln13.cse360project.frontend;
    exports dev.ln13.cse360project.backend;
}