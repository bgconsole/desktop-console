package com.bgconsole.desktop_ui.ui.new_location;

import com.bgconsole.desktop_ui.main_window.MainWindowController;
import com.bgconsole.platform.domain.Profile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class NewLocation {

    public NewLocation(MainWindowController mainWindowController, Profile profile) {
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop_ui/new_location.fxml");
            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            NewLocationController controller = loader.getController();
            controller.setMainWindowController(mainWindowController);
            controller.setProfile(profile);
//            controller.setCommandLists(lists);

            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop_ui/styles.css").toExternalForm());

//            stage.setOnCloseRequest(event -> trigger.trigger());

            stage.setTitle("New location");
            stage.setScene(scene);
            stage.show();

            controller.setStage(stage);
//            controller.setWorkspaceService(AppData.instance.getWorkspaceService());
//            controller.setProfileService(AppData.instance.getProfileService());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
