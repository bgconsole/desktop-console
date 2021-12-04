package com.bgconsole.desktop.ui.commandeditor;

import com.bgconsole.desktop.command.CommandList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CommandEditorController {

    @FXML
    private TabPane commandTab;

    private List<CommandList> commandLists;

    public void initialize() {
    }

    public void setCommandLists(List<CommandList> commandLists) {
        this.commandLists = commandLists;
        try {
            for (CommandList commandList : commandLists) {
                URL resource = getClass().getResource("/com/bgconsole/desktop/command_editor_tab.fxml");
                FXMLLoader loader = new FXMLLoader(resource);
                Parent root = loader.load();
                CommandEditorTabController controller = loader.getController();
                controller.setCommandList(commandList);

                Tab tab = new Tab(commandList.getName(), root);


                commandTab.getTabs().add(tab);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
