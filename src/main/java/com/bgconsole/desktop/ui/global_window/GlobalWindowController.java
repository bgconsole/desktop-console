package com.bgconsole.desktop.ui.global_window;

import com.bgconsole.desktop.AppData;
import com.bgconsole.desktop.environment.Environment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
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

    private List<TableCommand> allCommands;

    private Stage stage;

    public void initialize() {
        commands = FXCollections.observableArrayList();
        allCommands = new ArrayList<>();
        AppData.instance.getLocations().forEach(locationData -> {
            Environment environment = locationData.getEnvironment();
            environment.getCommandLists().forEach(cmds -> allCommands.addAll(cmds.getCommands().stream().map(command -> new TableCommand(command, locationData)).collect(Collectors.toList())));
        });

        commands.addAll(allCommands);

        var workspace = new TableColumn<TableCommand, String>("Workspace");
        workspace.setCellValueFactory(new PropertyValueFactory<>("workspace"));
        var command = new TableColumn<TableCommand, String>("Name");
        command.setCellValueFactory(new PropertyValueFactory<>("name"));
        var alias = new TableColumn<TableCommand, String>("Alias");
        alias.setCellValueFactory(new PropertyValueFactory<>("alias"));

        commandTable.getColumns().add(command);
        commandTable.getColumns().add(workspace);
        commandTable.getColumns().add(alias);

        commandTable.setRowFactory(tableView -> {
            TableRow<TableCommand> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    TableCommand tableCommand = row.getItem();
                    tableCommand.getLocationData().runCommand(tableCommand.getCommand());
                    stage.close();
                }
            });
            return row;
        });

        commandTable.setItems(commands);
        if (!commands.isEmpty()) {
            commandTable.getSelectionModel().select(0);
        }

        commandTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        searchField.textProperty().addListener((observableValue, s, t1) -> {
            commands.clear();
            allCommands.forEach(command1 -> {
                if (StringUtils.containsIgnoreCase(command1.getAlias(), t1)
                        || StringUtils.containsIgnoreCase(command1.getCommand(), t1)
                        || StringUtils.containsIgnoreCase(command1.getWorkspace(), t1)) {
                    commands.add(command1);
                }
            });
            commandTable.setItems(commands);
        });

        searchField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!commands.isEmpty() && !searchField.getText().isEmpty()) {
                    TableCommand tableCommand = commandTable.getSelectionModel().getSelectedItem();
                    if (tableCommand == null) {
                        tableCommand = commandTable.getItems().get(0);
                    }
                    tableCommand.getLocationData().runCommand(tableCommand.getCommand());
                    stage.close();
                }
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
