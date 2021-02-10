open module pl.co.model {
    requires org.apache.commons.lang3;
    requires javafx.base;
    requires org.apache.logging.log4j;
    requires java.persistence;

    exports pl.comp.model;
    exports pl.comp.model.exceptions;
}