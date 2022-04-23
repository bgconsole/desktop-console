module terminal {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.kodedu.terminalfx;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.apache.commons.lang3;
//    requires com.fasterxml.jackson.;
//    requires jnativehook;
    requires java.logging;
    requires simplenativehooks;
    requires javafx.base;

    opens com.bgconsole.desktop to javafx.fxml;
    opens com.bgconsole.desktop.ui.vareditor to javafx.fxml;
    opens com.bgconsole.desktop.ui.commandeditor to javafx.fxml;
    opens com.bgconsole.desktop.ui.global_window to javafx.fxml, javafx.base;
    opens com.bgconsole.desktop.workspace to com.fasterxml.jackson.databind;
    exports com.bgconsole.desktop;
    exports com.bgconsole.desktop.variable;
    exports com.bgconsole.desktop.command;
    exports com.bgconsole.desktop.config;
    exports com.bgconsole.desktop.terminal;
    exports com.bgconsole.desktop.environment;
    exports com.bgconsole.desktop.global_input;
    exports com.bgconsole.desktop.location;
    exports com.bgconsole.desktop.workspace;
    exports com.bgconsole.desktop.project;
    exports com.bgconsole.desktop.profile;
    opens com.bgconsole.desktop.global_input to javafx.fxml;
    exports com.bgconsole.desktop.ui.new_location;
    opens com.bgconsole.desktop.ui.new_location to javafx.fxml;
    opens com.bgconsole.desktop.project to com.fasterxml.jackson.databind;
}