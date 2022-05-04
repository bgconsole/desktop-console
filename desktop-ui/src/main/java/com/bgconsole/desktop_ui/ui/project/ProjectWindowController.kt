package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.ENGINE_OPENED_AGGREGATE
import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateContent
import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateRedux
import com.bgconsole.desktop_engine.desktop_services.opened.version.ENGINE_OPENED_VERSION
import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionContent
import com.bgconsole.desktop_engine.store.Subscriber
import com.bgconsole.desktop_ui.AppData
import com.bgconsole.desktop_ui.CommandRunner
import com.bgconsole.desktop_ui.Config
import com.bgconsole.desktop_ui.command.Command
import com.bgconsole.desktop_ui.terminal.OpenerCallBack
import com.bgconsole.desktop_ui.terminal.Terminal
import com.bgconsole.desktop_ui.ui.terminal_window.TerminalWindow
import com.bgconsole.desktop_ui.utils.VersionObservableConverter
import com.bgconsole.domain.Instruction
import com.bgconsole.domain.Project
import com.bgconsole.domain.Version
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.StackPane
import java.nio.file.Path
import java.util.*

class ProjectWindowController : CommandRunner {
    @FXML
    private lateinit var projectDetailPane: StackPane

    @FXML
    private lateinit var instructionList: ListView<Instruction>

    @FXML
    private lateinit var versionSelector: ChoiceBox<Version>

    //    @FXML
    //    private Menu configMenu;
    @FXML
    var menuBar: MenuBar? = null
    private val appData: AppData = AppData.instance

    private var openedAggregates: OpenedAggregateContent? = null

    private val instructionObservableList = FXCollections.observableArrayList<Instruction>()

    //    private CommandService commandService;
    //    private TerminalService terminalService;
    //    private VariableService variableService;
    //    private ConfigService configService;
    private var tabPane: TabPane? = null
    private var configs: List<Config>? = null
    private var project: Project? = null
    private var projectWindow: ProjectWindow? = null
    private val terminalWindows: List<TerminalWindow> = ArrayList()
    private val store = AppData.instance.store
    fun setProject(project: Project?) {
        this.project = project
        //        appData.get(project.getId()).setCommandRunner(this);
//        configs = loadConfigs(new ArrayList<>(), appData.get(project.getId()).getEnvironment().getDir());
//        buildConfigMenu(configs, configMenu);
    }

    fun setProjectWindow(projectWindow: ProjectWindow?) {
        this.projectWindow = projectWindow
    }

    fun initialize() {
        terminalWindows
        tabPane = TabPane()
        projectDetailPane.children.add(tabPane)

        val versionList = FXCollections.observableArrayList<Version>()
        versionSelector.converter = VersionObservableConverter(versionList)
        versionSelector.items = versionList

        instructionList.items = instructionObservableList

        instructionList.setCellFactory {
            object : ListCell<Instruction?>() {
                override fun updateItem(item: Instruction?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) {
                        null
                    } else {
                        item?.name
                    }
                }
            }
        }

        versionList.addAll(
            Objects.requireNonNull(store.get(ENGINE_OPENED_VERSION) as OpenedVersionContent).versions[project?.id].orEmpty()
        )


        store.subscribe(ENGINE_OPENED_VERSION, object : Subscriber {
            override fun update(entity: Any) {
                versionList.setAll((entity as OpenedVersionContent).versions[project?.id].orEmpty())
            }
        })

        store.subscribe(ENGINE_OPENED_AGGREGATE, object : Subscriber {
            override fun update(entity: Any) {
                openedAggregates = entity as OpenedAggregateContent
                updateInstructionInList()
            }
        })
    }

    private fun updateInstructionInList() {
        val version = versionSelector.selectionModel?.selectedItem
        val instructions = version?.let { versions ->
            openedAggregates?.aggregates?.get(versions.id)?.let {
                it.flatMap { aggregate -> aggregate.instructions.orEmpty() }
            }
        }.orEmpty()
        instructionObservableList.setAll(instructions)
    }

    //    @FXML
    //    public void onEnter(KeyEvent event) {
    //        if (event.getCode() == KeyCode.ENTER) {
    //            String command = commandField.getText();
    //            runCommand(command);
    //        }
    //    }
    @FXML
    fun newTerminal(event: ActionEvent?) {
        openTerminal(
            "term" + (tabPane!!.tabs.size + 1),
            "Terminal " + (tabPane!!.tabs.size + 1)
        ) { terminal: Terminal, isNew: Boolean ->
            tabPane!!.tabs.add(terminal.terminalTab)
            tabPane!!.selectionModel.select(terminal.terminalTab)
        }
    }

    @FXML
    fun quit(event: ActionEvent?) {
        projectWindow!!.closeWindow()
    }

    @FXML
    fun reloadConfig(event: ActionEvent?) {
        reloadConfig()
    }

    @FXML
    fun editVars(event: ActionEvent?) {
//        List<VariableList> variables = appData.get(project.getId()).getEnvironment().getVariableLists().stream().filter(var -> !var.getConfig().getConfig().equals("SYS_VAR")).map(variable -> variableService.loadVariables(variable.getConfig())).collect(Collectors.toList());
//        new VarEditorWindow(variables, this::reloadConfig);
    }

    @FXML
    fun editCommands(event: ActionEvent?) {
//        List<CommandList> commands = appData.get(project.getId()).getEnvironment().getCommandLists().stream().map(command -> commandService.loadCommands(command.getName(), command.getAbsolutePath())).collect(Collectors.toList());
    }

    fun runCommand(command: String?) {
//        Optional<Command> alias = commandService.findAlias(appData.get(project.getId()).getEnvironment(), command);
//        if (alias.isPresent()) {
//            execCommandInRightTerminal(tabPane, alias.get());
//        } else {
//            TerminalTab terminalTab = (TerminalTab) tabPane.getSelectionModel().getSelectedItem();
//            if (terminalTab != null) {
//                commandService.sendCommand(terminalTab, command, this::resolveVariable);
//            }
//        }
    }

    private fun openTerminal(id: String, name: String, callBack: OpenerCallBack) {
//        terminalService.openTerminal(appData.get(project.getId()).getEnvironment(), id, name, (terminal, isNew) -> {
//            addClosingEvent(terminal);
//            callBack.openerCallBack(terminal, isNew);
//        }, this::resolveVariable);
    }

    private fun buildConfigMenu(configs: List<Config>, menu: Menu) {
//        MenuItem item;
//        Menu subMenu;
//        for (Config config : configs) {
//            if (config.getType().equals("file")) {
//                item = new MenuItem(config.getConfig());
//                item.setOnAction(t -> {
//                    configService.loadConfig(appData.get(project.getId()).getEnvironment(), config);
//                });
//                menu.getItems().add(item);
//            } else {
//                subMenu = new Menu(config.getConfig());
//                buildConfigMenu(config.getConfigs(), subMenu);
//                menu.getItems().add(subMenu);
//            }
//        }
    }

    private fun execCommandInRightTerminal(tabPane: TabPane, command: Command) {
//        Optional<Variable> optTitle = variableService.findVar(appData.get(project.getId()).getEnvironment(), "TITLE:" + command.getConsoleId());
//        String title = command.getConsoleId();
//        if (optTitle.isPresent() && optTitle.get().getValue() != null && !optTitle.get().getValue().isBlank()) {
//            title = optTitle.get().getValue();
//        }
//
//        openTerminal(command.getConsoleId(), title, (terminal, isNew) -> {
//            if (terminal.getWindow() == null) {
//                terminalWindows.add(new TerminalWindow(terminal));
//            }
//            terminal.getWindow().popUp();
//            terminal.getWindow().selectTerminal(terminal);
//
//            commandService.sendCommand(terminal.getTerminalTab(), command.getCommand(), this::resolveVariable);
//        });
    }

    private fun addClosingEvent(terminal: Terminal) {
//        terminal.getTerminalTab().setOnCloseRequest(event1 -> {
//            terminalService.closeTerminal(appData.get(project.getId()).getEnvironment(), terminal);
//        });
    }

    private fun findTab(terminalId: String): Terminal? {
        for (terminalWindow in terminalWindows) {
            for (i in terminalWindow.getTerminals().indices) {
                val terminal = terminalWindow.getTerminals()[i]
                if (terminal.id != null && terminal.id == terminalId) {
                    return terminal
                }
            }
        }
        return null
    }

    private fun resolveVariable(`var`: String): Optional<String> {
        val dialog = TextInputDialog()
        dialog.title = "Argument required"
        dialog.headerText = "An arugment is required, please enter the following value"
        dialog.contentText = "Value for \"$`var`\""
        return dialog.showAndWait()
    }

    private fun loadConfigs(configs: MutableList<Config>, dir: Path): List<Config> {
        val currentDir = dir.toFile()
        val files = currentDir.listFiles()
        var config: Config
        if (files != null && files.size > 0) {
            for (file in files) {
                if (!file.isHidden) {
                    if (file.isDirectory) {
                        config = Config("dir", file.name, file.absolutePath)
                        configs.add(config)
                        loadConfigs(config.configs, file.toPath())
                    } else {
                        configs.add(Config("file", file.name, file.absolutePath))
                    }
                }
            }
        }
        return configs
    }

    //    public void loadConfig(List<CommandList> commandLists) {
    //        commandCache.clear();
    //        commandList.getItems().clear();
    //        for (CommandList commands : commandLists) {
    //            commands.getCommands().forEach(command -> {
    //                commandList.getItems().add(command.getName() + " (" + command.getAlias() + ")");
    //                commandCache.add(command);
    //            });
    //            commandList.setOnMouseClicked(click -> {
    //                if (click.getClickCount() == 2) {
    //                    Command command = commandCache.get(commandList.getSelectionModel().getSelectedIndex());
    //                    execCommandInRightTerminal(tabPane, command);
    //                }
    //            });
    //        }
    //    }
    private fun reloadConfig() {
        appData[project!!.id].reloadEnv()
        configs = loadConfigs(ArrayList(), appData[project!!.id].environment.dir)
        //        configMenu.getItems().clear();
//        buildConfigMenu(configs, configMenu);
//        loadConfig(appData.get(project.getId()).getEnvironment().getCommandLists());
    }

    override fun exec(command: String) {
        runCommand(command)
    }

    fun changeVersion(event: ActionEvent?) {
        val version = versionSelector.selectionModel?.selectedItem
        version?.let { store.dispatch(OpenedAggregateRedux.LoadAggregates(it)) }
    }

}