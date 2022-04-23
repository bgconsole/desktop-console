package com.bgconsole.desktop;

import com.bgconsole.desktop.command.Command;
import com.bgconsole.desktop.command.CommandList;
import com.bgconsole.desktop.command.CommandService;
import com.bgconsole.desktop.config.ConfigService;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.terminal.OpenerCallBack;
import com.bgconsole.desktop.terminal.Terminal;
import com.bgconsole.desktop.terminal.TerminalService;
import com.bgconsole.desktop.ui.ProjectWindow;
import com.bgconsole.desktop.ui.commandeditor.CommandEditorWindow;
import com.bgconsole.desktop.ui.terminal_window.TerminalWindow;
import com.bgconsole.desktop.ui.vareditor.VarEditorWindow;
import com.bgconsole.desktop.variable.Variable;
import com.bgconsole.desktop.variable.VariableList;
import com.bgconsole.desktop.variable.VariableService;
import com.kodedu.terminalfx.TerminalTab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FXMLController implements CommandRunner {

    @FXML
    private StackPane termPane;

    @FXML
    private ListView<String> commandList;

//    @FXML
//    private Menu configMenu;

    @FXML
    private MenuBar menuBar;

    private final AppData appData;
    private CommandService commandService;
    private TerminalService terminalService;
    private VariableService variableService;
    private ConfigService configService;

    private TabPane tabPane;

    private List<Config> configs;
    private List<Command> commandCache;

    private Project project;

    private ProjectWindow projectWindow;

    private List<TerminalWindow> terminalWindows;

    public FXMLController() {
        appData = AppData.instance;
        terminalWindows = new ArrayList<>();
    }

    public void setProject(Project project) {
        this.project = project;
        commandService = appData.get(project.getId()).getCommandService();
        terminalService = appData.get(project.getId()).getTerminalService();
        variableService = appData.get(project.getId()).getVariableService();
        configService = appData.get(project.getId()).getConfigService();
        commandCache = new ArrayList<>();
        appData.get(project.getId()).setCommandRunner(this);
        configs = loadConfigs(new ArrayList<>(), appData.get(project.getId()).getEnvironment().getDir());
//        buildConfigMenu(configs, configMenu);
    }

    public void setProjectWindow(ProjectWindow projectWindow) {
        this.projectWindow = projectWindow;
    }

    public void initialize() {
        tabPane = new TabPane();
        termPane.getChildren().add(tabPane);
    }

//    @FXML
//    public void onEnter(KeyEvent event) {
//        if (event.getCode() == KeyCode.ENTER) {
//            String command = commandField.getText();
//            runCommand(command);
//        }
//    }

    @FXML
    public void newTerminal(ActionEvent event) {
        openTerminal("term" + (tabPane.getTabs().size() + 1), "Terminal " + (tabPane.getTabs().size() + 1), (terminal, isNew) -> {
            tabPane.getTabs().add(terminal.getTerminalTab());
            tabPane.getSelectionModel().select(terminal.getTerminalTab());
        });
    }

    @FXML
    public void quit(ActionEvent event) {
        projectWindow.closeWindow();
    }

    @FXML
    public void reloadConfig(ActionEvent event) {
        reloadConfig();
    }

    @FXML
    public void editVars(ActionEvent event) {
        List<VariableList> variables = appData.get(project.getId()).getEnvironment().getVariableLists().stream().filter(var -> !var.getConfig().getConfig().equals("SYS_VAR")).map(variable -> variableService.loadVariables(variable.getConfig())).collect(Collectors.toList());
        new VarEditorWindow(variables, this::reloadConfig);
    }

    @FXML
    public void editCommands(ActionEvent event) {
        List<CommandList> commands = appData.get(project.getId()).getEnvironment().getCommandLists().stream().map(command -> commandService.loadCommands(command.getName(), command.getAbsolutePath())).collect(Collectors.toList());
        new CommandEditorWindow(appData.getWorkspaceService(), project, commands, this::reloadConfig);
    }

    public void runCommand(String command) {
        Optional<Command> alias = commandService.findAlias(appData.get(project.getId()).getEnvironment(), command);
        if (alias.isPresent()) {
            execCommandInRightTerminal(tabPane, alias.get());
//            commandField.setText("");
        } else {
            TerminalTab terminalTab = (TerminalTab) tabPane.getSelectionModel().getSelectedItem();
            if (terminalTab != null) {
                commandService.sendCommand(terminalTab, command, this::resolveVariable);
//                commandField.setText("");
            }
        }
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private void openTerminal(String id, String name, OpenerCallBack callBack) {
        terminalService.openTerminal(appData.get(project.getId()).getEnvironment(), id, name, (terminal, isNew) -> {
            addClosingEvent(terminal);
            callBack.openerCallBack(terminal, isNew);
        }, this::resolveVariable);
    }

    private void buildConfigMenu(List<Config> configs, Menu menu) {
        MenuItem item;
        Menu subMenu;
        for (Config config : configs) {
            if (config.getType().equals("file")) {
                item = new MenuItem(config.getConfig());
                item.setOnAction(t -> {
                    configService.loadConfig(appData.get(project.getId()).getEnvironment(), config);
                    //loadConfig();
                });
                menu.getItems().add(item);
            } else {
                subMenu = new Menu(config.getConfig());
                buildConfigMenu(config.getConfigs(), subMenu);
                menu.getItems().add(subMenu);
            }
        }
    }

    private void execCommandInRightTerminal(TabPane tabPane, Command command) {
        Optional<Variable> optTitle = variableService.findVar(appData.get(project.getId()).getEnvironment(), "TITLE:" + command.getConsoleId());
        String title = command.getConsoleId();
        if (optTitle.isPresent() && optTitle.get().getValue() != null && !optTitle.get().getValue().isBlank()) {
            title = optTitle.get().getValue();
        }

        openTerminal(command.getConsoleId(), title, (terminal, isNew) -> {
//            Terminal terminalTab = findTab(command.getConsoleId());
//            if (terminalTab != null) {
//                terminalTab.getTerminal().getSelectionModel().select(terminalTab);
//            } else {
//                tabPane.getTabs().add(terminal.getTerminalTab());
//                tabPane.getSelectionModel().select(terminal.getTerminalTab());
//            }
            if (terminal.getWindow() == null) {
                terminalWindows.add(new TerminalWindow(terminal));
            }
            terminal.getWindow().popUp();
            terminal.getWindow().selectTerminal(terminal);

            commandService.sendCommand(terminal.getTerminalTab(), command.getCommand(), this::resolveVariable);
        });
//        Terminal terminal = new Terminal(command.getConsoleId(), title);
//        new TerminalWindow(terminal);
//        commandService.sendCommand(terminal.getTerminalTab(), command.getCommand(), this::resolveVariable);
    }

    private void addClosingEvent(Terminal terminal) {
        terminal.getTerminalTab().setOnCloseRequest(event1 -> {
            terminalService.closeTerminal(appData.get(project.getId()).getEnvironment(), terminal);
        });
    }

    private Terminal findTab(String terminalId) {
        for (TerminalWindow terminalWindow : terminalWindows) {
            for (int i = 0; i < terminalWindow.getTerminals().size(); i++) {
                Terminal terminal = terminalWindow.getTerminals().get(i);
                if (terminal.getId() != null && terminal.getId().equals(terminalId)) {
                    return terminal;
                }
            }
        }
        return null;
    }

    private Optional<String> resolveVariable(String var) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Argument required");
        dialog.setHeaderText("An arugment is required, please enter the following value");
        dialog.setContentText("Value for \"" + var + "\"");
        return dialog.showAndWait();
    }

    private List<Config> loadConfigs(List<Config> configs, Path dir) {
        File currentDir = dir.toFile();
        File[] files = currentDir.listFiles();
        Config config;
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (!file.isHidden()) {
                    if (file.isDirectory()) {
                        config = new Config("dir", file.getName(), file.getAbsolutePath());
                        configs.add(config);
                        loadConfigs(config.getConfigs(), file.toPath());

                    } else {
                        configs.add(new Config("file", file.getName(), file.getAbsolutePath()));
                    }
                }
            }
        }
        return configs;
    }

    public void loadConfig(List<CommandList> commandLists) {
        commandCache.clear();
        commandList.getItems().clear();
        for (CommandList commands : commandLists) {
            commands.getCommands().forEach(command -> {
                commandList.getItems().add(command.getName() + " (" + command.getAlias() + ")");
                commandCache.add(command);
            });
            commandList.setOnMouseClicked(click -> {
                if (click.getClickCount() == 2) {
                    Command command = commandCache.get(commandList.getSelectionModel().getSelectedIndex());
                    execCommandInRightTerminal(tabPane, command);
                }
            });
        }
    }

    private void reloadConfig() {
        appData.get(project.getId()).reloadEnv();
        configs = loadConfigs(new ArrayList<>(), appData.get(project.getId()).getEnvironment().getDir());
//        configMenu.getItems().clear();
//        buildConfigMenu(configs, configMenu);
        loadConfig(appData.get(project.getId()).getEnvironment().getCommandLists());
    }

    @Override
    public void exec(String command) {
        runCommand(command);
    }
}
