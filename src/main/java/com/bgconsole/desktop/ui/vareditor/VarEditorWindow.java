package com.bgconsole.desktop.ui.vareditor;

import com.bgconsole.desktop.MainWindow;
import com.bgconsole.desktop.ui.SimpleTrigger;
import com.bgconsole.desktop.variable.VariableList;
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

public class VarEditorWindow {

    public VarEditorWindow(List<VariableList> lists, SimpleTrigger trigger) {
        try {
            URL resource = getClass().getResource("/com/bgconsole/desktop/var_editor.fxml");
            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            VarEditorController controller = loader.getController();
            controller.setVariableLists(lists);

            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/com/bgconsole/desktop/styles.css").toExternalForm());

            stage.setOnCloseRequest(event -> trigger.trigger());

            stage.setTitle("Variable Editor");
            stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("/com/bgconsole/desktop/img/logo.png"))));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
