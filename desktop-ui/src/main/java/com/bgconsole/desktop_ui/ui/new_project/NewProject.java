package com.bgconsole.desktop_ui.ui.new_project;

import com.bgconsole.desktop_ui.workspace.Workspace;
import com.bgconsole.desktop_ui.AppData;
import com.bgconsole.desktop_ui.main_window.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class NewProject {

    public NewProject(MainWindowController mainWindowController, Workspace workspace) {
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop_ui/new_project.fxml");
            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            NewProjectController controller = loader.getController();
            controller.setMainWindowController(mainWindowController);
            controller.setWorkspace(workspace);
//            controller.setCommandLists(lists);

            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop_ui/styles.css").toExternalForm());

//            stage.setOnCloseRequest(event -> trigger.trigger());

            stage.setTitle("New project");
            stage.setScene(scene);
            stage.show();

            controller.setStage(stage);
            controller.setProjectService(AppData.instance.getProjectService());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
