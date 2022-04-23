package com.bgconsole.desktop.ui;

import com.bgconsole.desktop.AppData;
import com.bgconsole.desktop.FXMLController;
import com.bgconsole.desktop.MainWindow;
import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.project.Project;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class TerminalWindow {

    private final Stage stage;

    public TerminalWindow(Project project) throws IOException {
        URL resource = getClass().getResource("/com/bgconsole/desktop/scene.fxml");
        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        FXMLController controller = loader.getController();
        controller.setProject(project);
        controller.setTerminalWindow(this);

        stage = new Stage(StageStyle.DECORATED);
//        stage.initModality(Modality.);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());

        stage.setTitle(project.getName() + " | BG Console");
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop/img/logo.png"))));
        stage.setScene(scene);
        stage.show();

        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac")) {
            Platform.runLater(() -> {
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Terminal");
                controller.getMenuBar().setUseSystemMenuBar(true);
//                controller.loadConfig(AppData.instance.get(location.getId()).getEnvironment().getCommandLists());
            });
        }

        controller.loadConfig(AppData.instance.get(project.getId()).getEnvironment().getCommandLists());

//        try {
//            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
//            logger.setLevel(Level.OFF);
//            logger.setUseParentHandlers(false);
//
//            GlobalScreen.registerNativeHook();
//        } catch (NativeHookException ex) {
//            System.err.println("There was a problem registering the native hook.");
//            System.err.println(ex.getMessage());
//
//            System.exit(1);
//        }
//
//        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }

    public void closeWindow() {
        stage.close();
    }
}
