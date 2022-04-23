package com.bgconsole.desktop.ui.new_location;

import com.bgconsole.desktop.MainWindowController;
import com.bgconsole.desktop.MainWindowData;
import com.bgconsole.desktop.workspace.WorkspaceService;
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setWorkspaceService(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
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

        workspaceService.createWorkspace(strName, strPath);

        MainWindowData.instance.reloadLocations();
        mainWindowController.setProfileList(MainWindowData.instance.getProfiles());
        stage.close();
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
