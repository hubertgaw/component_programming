module pl.co.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires pl.co.model;
    requires org.apache.logging.log4j;
    requires java.persistence;

    opens pl.comp to javafx.fxml;
    exports pl.comp;
}