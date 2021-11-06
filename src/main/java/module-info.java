module com.dassoop.javafxlist_dragdropreorder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dassoop.javafxlist_dragdropreorder to javafx.fxml;
    exports com.dassoop.javafxlist_dragdropreorder;
}