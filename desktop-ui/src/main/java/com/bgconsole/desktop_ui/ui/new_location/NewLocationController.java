package com.bgconsole.desktop_ui.ui.new_location;

import com.bgconsole.desktop_ui.profile.ProfileService;
import com.bgconsole.desktop_ui.workspace.Workspace;
import com.bgconsole.desktop_ui.workspace.WorkspaceService;
import com.bgconsole.desktop_ui.main_window.MainWindowController;
import com.bgconsole.desktop_ui.MainWindowData;
import com.bgconsole.domain.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewLocationController {

    @FXML
    private TextField name;

    @FXML
    private TextField path;

    private Stage stage;

    private MainWindowController mainWindowController;

    private WorkspaceService workspaceService;

    private ProfileService profileService;

    private Profile profile;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setWorkspaceService(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @FXML
    public void openDialog(ActionEvent event) {
        final DirectoryChooser directoryChooser =
                new DirectoryChooser();
        final File selectedDirectory =
                directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            path.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    public void closeWindow() {
        stage.close();
    }

    @FXML
    public void createLocation(ActionEvent event) {
        String strPath = path.getText();
        String strName = name.getText();

        Workspace workspace = workspaceService.createWorkspace(profile.getId(), strName, strPath);
        profileService.addWorkspace(profile.getId(), workspace, strPath);

        MainWindowData.instance.reloadLocations();
//        mainWindowController.setProfileList(MainWindowData.instance.getProfiles());
        stage.close();
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
