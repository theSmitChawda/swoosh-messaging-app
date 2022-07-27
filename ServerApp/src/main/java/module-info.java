module com.server.serverapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.server.serverapp to javafx.fxml;
    exports com.server.serverapp;
}