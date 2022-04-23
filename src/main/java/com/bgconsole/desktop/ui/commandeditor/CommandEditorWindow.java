package com.bgconsole.desktop.ui.commandeditor;

import com.bgconsole.desktop.MainWindow;
import com.bgconsole.desktop.command.CommandList;
import com.bgconsole.desktop.location.Location;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.ui.SimpleTrigger;
import com.bgconsole.desktop.workspace.Workspace;
import com.bgconsole.desktop.workspace.WorkspaceService;
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

public class CommandEditorWindow {

    public CommandEditorWindow(WorkspaceService workspaceService, Project project, List<CommandList> lists, SimpleTrigger trigger) {
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop/command_editor.fxml");
            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            CommandEditorController controller = loader.getController();
            controller.setCommandLists(lists);
            controller.setWorkspaceService(workspaceService);
            controller.setProject(project);

            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());

            stage.setOnCloseRequest(event -> trigger.trigger());

            stage.setTitle("Command Editor");
            stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop/img/logo.png"))));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
