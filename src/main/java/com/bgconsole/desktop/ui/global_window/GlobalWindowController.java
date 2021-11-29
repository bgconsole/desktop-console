package com.bgconsole.desktop.ui.global_window;

import com.bgconsole.desktop.AppData;
import com.bgconsole.desktop.command.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalWindowController {

    @FXML
    private TableView<TableCommand> commandTable;

    @FXML
    private TextField searchField;

    private ObservableList<TableCommand> commands;

    private List<Command> allCommands;

    public void initialize() {
        commands = FXCollections.observableArrayList();
        allCommands = new ArrayList<>();
        AppData.instance.getLocations().forEach(locationData -> locationData.getEnvironment().getCommandLists().forEach(cmds -> allCommands.addAll(cmds.getCommands())));

        commands.addAll(allCommands.stream().map(command -> new TableCommand(command.getName(), command.getCommand(), command.getAlias())).collect(Collectors.toList()));

        var command = new TableColumn<TableCommand, String>("Command");
        command.setCellValueFactory(new PropertyValueFactory<>("command"));
        var workspace = new TableColumn<TableCommand, String>("Workspace");
        workspace.setCellValueFactory(new PropertyValueFactory<>("workspace"));
        var alias = new TableColumn<TableCommand, String>("Alias");
        alias.setCellValueFactory(new PropertyValueFactory<>("alias"));

        commandTable.getColumns().add(command);
        commandTable.getColumns().add(workspace);
        commandTable.getColumns().add(alias);

        commandTable.setItems(commands);

        searchField.textProperty().addListener((observableValue, s, t1) -> {
            commands.clear();
            allCommands.forEach(command1 -> {
                if (StringUtils.containsIgnoreCase(command1.getAlias(), t1)
                        || StringUtils.containsIgnoreCase(command1.getCommand(), t1)
                        || StringUtils.containsIgnoreCase(command1.getName(), t1)) {
                    commands.add(new TableCommand(command1.getName(), command1.getCommand(), command1.getAlias()));
                }
            });
            commandTable.setItems(commands);
        });
    }
}
