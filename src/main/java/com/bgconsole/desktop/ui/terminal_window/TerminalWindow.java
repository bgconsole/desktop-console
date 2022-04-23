package com.bgconsole.desktop.ui.terminal_window;

import com.bgconsole.desktop.MainWindow;
import com.bgconsole.desktop.terminal.Terminal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TerminalWindow {

    private List<Terminal> terminals;

    private TerminalWindowController controller;

    private Stage stage;

    public TerminalWindow(Terminal terminal) {
        terminal.setWindow(this);
        terminals = new ArrayList<>();
        terminals.add(terminal);
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop/terminal_window.fxml");
            FXMLLoader loader = new FXMLLoader(resource);


            Parent root = loader.load();


            controller = loader.getController();
            controller.addTerminalTab(terminal.getTerminalTab());

            stage = new Stage();
//            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());

            stage.setTitle(terminal.getName());
            stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop/img/logo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTerminal(Terminal terminal) {
        terminal.setWindow(this);
        terminals.add(terminal);
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void popUp() {
        if (stage.isIconified()) stage.setIconified(false);
        stage.show();
        stage.requestFocus();
        stage.toFront();
    }

    public void selectTerminal(Terminal terminal) {
        controller.selectTab(terminal.getTerminalTab());
    }
}
