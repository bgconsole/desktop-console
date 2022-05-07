package com.bgconsole.desktop_ui.ui.profile;

import com.bgconsole.desktop_ui.AppData;
import com.bgconsole.desktop_ui.MainWindow;
import com.bgconsole.desktop_ui.ui.SimpleTrigger;
import com.bgconsole.platform.engine.profile.ProfileRedux;
import com.bgconsole.platform.store.Store;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ProfileWindow {

    private Stage stage;

    private ProfileWindowController controller;

    private final Store store = AppData.instance.getStore();

    public ProfileWindow(SimpleTrigger trigger) {
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop_ui/profile_window.fxml");
            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            controller = loader.getController();

            stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop_ui/styles.css").toExternalForm());

            stage.setOnCloseRequest(event -> trigger.trigger());

            stage.setTitle("Profile manager");
            stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop_ui/img/logo.png"))));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void popUp() {
        refreshProfiles();
        if (stage.isIconified()) stage.setIconified(false);
        stage.show();
        stage.requestFocus();
        stage.toFront();
    }

    private void refreshProfiles() {
        store.dispatch(new ProfileRedux.LoadProfiles());
    }
}
