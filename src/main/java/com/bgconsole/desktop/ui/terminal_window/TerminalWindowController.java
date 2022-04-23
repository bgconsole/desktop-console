package com.bgconsole.desktop.ui.terminal_window;

import com.kodedu.terminalfx.TerminalTab;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class TerminalWindowController {

    @FXML
    private StackPane terminalTabPane;

    private TabPane tabPane;

    public void initialize() {
        tabPane = new TabPane();
        terminalTabPane.getChildren().add(tabPane);
    }

    public void addTerminalTab(TerminalTab terminalTab) {
        tabPane.getTabs().add(terminalTab);
        tabPane.getSelectionModel().select(terminalTab);
    }

    public void selectTab(TerminalTab terminalTab) {
        tabPane.getSelectionModel().select(terminalTab);
    }
}
