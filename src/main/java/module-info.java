module com.example.teamupclienteescritorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.teamupclienteescritorio to javafx.fxml;
    opens com.example.teamupclienteescritorio.Controladores to javafx.fxml;
    exports com.example.teamupclienteescritorio;
}