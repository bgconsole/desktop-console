package com.bgconsole.desktop_ui;

import com.bgconsole.desktop_ui.global_input.GlobalKeyListener;
import com.bgconsole.desktop_ui.main_window.MainWindowController;
import com.bgconsole.desktop_ui.main_window.MainWindowRedux;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        URL resource = getClass().getResource("/com/bgconsole/desktop_ui/main_window.fxml");
        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        MainWindowController controller = loader.getController();
        controller.setHostServices(getHostServices());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop_ui/styles.css").toExternalForm());

        stage.setTitle("BG Console");
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop_ui/img/logo.png"))));
        stage.setScene(scene);
        stage.show();

        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac")) {
            Platform.runLater(() -> {
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Terminal");
//                controller.getMenuBar().setUseSystemMenuBar(true);

            });
        }
        controller.setStage(stage);
//        controller.setProfileList(MainWindowData.instance.getProfiles());

        loadComponents();
    }

    private void loadComponents() {
        new MainWindowRedux();
        new GlobalKeyListener();
    }

    public static void main(String[] args) {
        launch(args);
    }

}