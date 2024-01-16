module dev.ln13.cse360project {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.ln13.cse360project to javafx.fxml;
    exports dev.ln13.cse360project;
}