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
    requires com.fasterxml.jackson.databind;

    opens com.example.teamupclienteescritorio to javafx.fxml;
    opens com.example.teamupclienteescritorio.controladores to javafx.fxml;
    opens com.example.teamupclienteescritorio.clasesMensajes to com.fasterxml.jackson.databind;
    exports com.example.teamupclienteescritorio;
}