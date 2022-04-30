package com.bgconsole.desktop_ui.ui.new_project;

import com.bgconsole.desktop_ui.project.ProjectService;
import com.bgconsole.desktop_ui.workspace.Workspace;
import com.bgconsole.desktop_ui.main_window.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewProjectController {

    @FXML
    private TextField name;

    private Stage stage;

    private MainWindowController mainWindowController;

    private ProjectService projectService;

    private Workspace workspace;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    @FXML
    public void closeWindow() {
        stage.close();
    }

    @FXML
    public void createProject(ActionEvent event) {
        String strName = name.getText();

        projectService.createProject(workspace, strName);
        stage.close();
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
