package com.bgconsole.desktop_ui.ui.commandeditor;

import com.bgconsole.platform.domain.Project;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CommandEditorController {

    @FXML
    private TabPane commandTab;

//    private List<CommandList> commandLists;

    private Project project;

    public void initialize() {
    }

    @FXML
    public void add() {
        Dialog<Result> dialog = new Dialog<>();
        dialog.setTitle("New command list");
        dialog.setHeaderText("Please specify…");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField textField = new TextField("Name");
        ObservableList<String> options =
                FXCollections.observableArrayList("In workspace", "Locally");
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
        dialogPane.setContent(new VBox(8, textField, comboBox));
        Platform.runLater(textField::requestFocus);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Result(textField.getText(),
                        comboBox.getValue().equals("Locally"));
            }
            return null;
        });
        dialog.showAndWait().ifPresent(result -> {
            if (result.createLocally) {
//                workspaceService.createCommandListLocally(project.getWorkspaceLocation(), result.name);
            } else {
//                workspaceService.createCommandList(project.getWorkspaceLocation(), result.name);
            }
        });
    }

    @FXML
    public void modify() {
//        CommandList currentCommand = commandLists.get(commandTab.getSelectionModel().getSelectedIndex());
//        TextInputDialog inputDialog = new TextInputDialog("Change command list name");
//        inputDialog.setContentText("Name: ");
//        inputDialog.setHeaderText("Set the new name of the command list");
//        inputDialog.getEditor().setText(currentCommand.getName());
//        inputDialog.showAndWait().ifPresent(newName -> {
//            if (!newName.equals(currentCommand.getName())) {
//
//            }
//        });
    }

    @FXML
    public void delete() {

    }

//    public void setCommandLists(List<CommandList> commandLists) {
//        this.commandLists = commandLists;
//        try {
//            for (CommandList commandList : commandLists) {
//                URL resource = getClass().getResource("/com/bgconsole/desktop_ui/command_editor_tab.fxml");
//                FXMLLoader loader = new FXMLLoader(resource);
//                Parent root = loader.load();
//                CommandEditorTabController controller = loader.getController();
//                controller.setCommandList(commandList);
//
//                Tab tab = new Tab(commandList.getName(), root);
//
//                commandTab.getTabs().add(tab);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void setWorkspaceService(WorkspaceService workspaceService) {
//        this.workspaceService = workspaceService;
//    }

    public void setProject(Project project) {
        this.project = project;
    }

    private static class Result {
        String name;
        boolean createLocally;

        public Result(String name, boolean createLocally) {
            this.name = name;
            this.createLocally = createLocally;
        }
    }
}
