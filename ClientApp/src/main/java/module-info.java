module com.client.clientapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.client.clientapp to javafx.fxml;
    exports com.client.clientapp;
}