package com.bgconsole.desktop.ui.profile;

import com.bgconsole.desktop.MainWindow;
import com.bgconsole.desktop.ProjectData;
import com.bgconsole.desktop.profile.Profile;
import com.bgconsole.desktop.profile.ProfileService;
import com.bgconsole.desktop.ui.SimpleTrigger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class ProfileWindow {

    private Stage stage;

    private final ProfileService profileService;

    private ProfileWindowController controller;

    public ProfileWindow(ProfileService profileService, SimpleTrigger trigger) {
        this.profileService = profileService;
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop/profile_window.fxml");
            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            controller = loader.getController();
            controller.setProfileService(profileService);

            stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());

            stage.setOnCloseRequest(event -> trigger.trigger());

            stage.setTitle("Profile manager");
            stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop/img/logo.png"))));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshProfiles();
    }

    public void popUp() {
        refreshProfiles();
        if (stage.isIconified()) stage.setIconified(false);
        stage.show();
        stage.requestFocus();
        stage.toFront();
    }

    private void refreshProfiles() {
        List<Profile> profiles = profileService.loadProfiles(ProjectData.DEFAULT_PROFILE_DIR.toString());
        controller.setProfiles(profiles);
    }
}
