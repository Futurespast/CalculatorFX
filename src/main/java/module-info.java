module com.example.calculatorfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.calculatorfx to javafx.fxml;
    exports com.example.calculatorfx;
}