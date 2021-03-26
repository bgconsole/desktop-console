package cloud.dest.terminal.ui.vareditor;

import cloud.dest.terminal.variable.VariableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VarEditorController {

    @FXML
    private AnchorPane varTab;

    private List<VariableList> variableLists;

    public void initialize() {
    }

    public void setVariableLists(List<VariableList> variableLists) {
        this.variableLists = variableLists;
        URL resource = getClass().getResource("/cloud/dest/terminal/var_editor_tab.fxml");
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(resource);
            root = loader.load();
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
            VarEditorTabController controller = loader.getController();
            controller.setVariableList(variableLists.get(1));

            varTab.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
