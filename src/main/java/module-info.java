module com.filmlistia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;



    exports com.filmlistia;
    opens com.filmlistia to javafx.fxml;
}