module com.example.listlang {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.listlang to javafx.fxml;
    exports com.example.listlang;
}