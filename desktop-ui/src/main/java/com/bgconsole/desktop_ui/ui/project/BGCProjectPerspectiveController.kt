package com.bgconsole.desktop_ui.ui.project

import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.ENGINE_OPENED_AGGREGATE
import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateContent
import com.bgconsole.desktop_engine.desktop_services.opened.aggregate.OpenedAggregateRedux
import com.bgconsole.desktop_engine.desktop_services.opened.environment.ENGINE_OPENED_ENVIRONMENT
import com.bgconsole.desktop_engine.desktop_services.opened.environment.OpenedEnvironmentContent
import com.bgconsole.desktop_engine.desktop_services.opened.environment.OpenedEnvironmentRedux
import com.bgconsole.desktop_engine.desktop_services.opened.variable.ENGINE_OPENED_RESOLVED_VARIABLES
import com.bgconsole.desktop_engine.desktop_services.opened.variable.ResolvedVariableContent
import com.bgconsole.desktop_engine.desktop_services.opened.version.ENGINE_OPENED_VERSION
import com.bgconsole.desktop_engine.desktop_services.opened.version.OpenedVersionContent
import com.bgconsole.desktop_ui.AppData
import com.bgconsole.desktop_ui.CommandRunner
import com.bgconsole.desktop_ui.Config
import com.bgconsole.desktop_ui.terminal.OpenerCallBack
import com.bgconsole.desktop_ui.terminal.Terminal
import com.bgconsole.desktop_ui.terminal.TerminalServiceImpl
import com.bgconsole.desktop_ui.ui.terminal_window.TerminalWindow
import com.bgconsole.desktop_ui.utils.VersionObservableConverter
import com.bgconsole.domain.Instruction
import com.bgconsole.domain.Variable
import com.bgconsole.domain.Version
import com.bgconsole.platform.domain.Project
import com.bgconsole.platform.store.Store
import com.bgconsole.platform.store.Subscriber
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.StackPane
import javafx.scene.web.WebView
import java.nio.file.Path
import java.util.*


class BGCProjectPerspectiveController : CommandRunner {
    @FXML
    private lateinit var projectDetailPane: StackPane

    @FXML
    private lateinit var instructionList: ListView<Instruction>

    @FXML
    private lateinit var versionSelector: ChoiceBox<Version>

    @FXML
    private lateinit var docTab: Tab

    @FXML
    private lateinit var resVarTab: Tab

    @FXML
    private lateinit var webDoc: WebView

    @FXML
    private lateinit var resVarTable: TableView<Variable>

    //    @FXML
    //    private Menu configMenu;
    @FXML
    var menuBar: MenuBar? = null
    private val appData: AppData = AppData.instance

    private var openedAggregates: OpenedAggregateContent? = null
    private var openedEnvironments: OpenedEnvironmentContent? = null
    private var resolvedVariables: ResolvedVariableContent? = null

    private val instructionObservableList = FXCollections.observableArrayList<Instruction>()

    //    private CommandService commandService;
    //    private TerminalService terminalService;
    //    private VariableService variableService;
    //    private ConfigService configService;
    private var configs: List<Config>? = null
    private var project: Project? = null
    private val terminalWindows: List<TerminalWindow> = mutableListOf()
    private lateinit var globalStore: Store
    private lateinit var projectStore: Store

    private lateinit var terminalService: TerminalServiceImpl

    fun setProject(project: Project?) {
        this.project = project
        //        appData.get(project.getId()).setCommandRunner(this);
//        configs = loadConfigs(new ArrayList<>(), appData.get(project.getId()).getEnvironment().getDir());
//        buildConfigMenu(configs, configMenu);
    }

    fun setStore(globalStore: Store, projectStore: Store) {
        this.globalStore = globalStore
        this.projectStore = projectStore
        terminalService = TerminalServiceImpl(globalStore)
    }

    fun init() {
        webDoc.engine.loadContent("<html><body bgcolor='#141414'></body></html>")

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
        instructionList.setOnMouseClicked {
            if (it.clickCount == 2) {
                val instruction = instructionList.selectionModel.selectedItem
//                execCommandInRightTerminal(tabPane!!, instruction);
            }
        }

        versionList.addAll(
            Objects.requireNonNull(projectStore.get(ENGINE_OPENED_VERSION) as OpenedVersionContent).versions[project?.id].orEmpty()
        )

        val resolvedVarsObservableList = FXCollections.observableArrayList<Variable>()
        val name = TableColumn<Variable, String>("Name")
        name.cellValueFactory = PropertyValueFactory("Name")
        val value = TableColumn<Variable, String>("Value")
        value.cellValueFactory = PropertyValueFactory("Value")
        resVarTable.columns.add(name)
        resVarTable.columns.add(value)
        resVarTable.columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY
        resVarTable.items = resolvedVarsObservableList

        projectStore.subscribe(ENGINE_OPENED_VERSION, object : Subscriber {
            override fun update(entity: Any) {
                versionList.setAll((entity as OpenedVersionContent).versions[project?.id].orEmpty())
            }
        })

        projectStore.subscribe(ENGINE_OPENED_AGGREGATE, object : Subscriber {
            override fun update(entity: Any) {
                openedAggregates = entity as OpenedAggregateContent
                updateInstructionInList()
                updateDoc()
            }
        })

        projectStore.subscribe(ENGINE_OPENED_ENVIRONMENT, object : Subscriber {
            override fun update(entity: Any) {
                openedEnvironments = entity as OpenedEnvironmentContent
            }
        })

        projectStore.subscribe(ENGINE_OPENED_RESOLVED_VARIABLES, object : Subscriber {
            override fun update(entity: Any) {
                resolvedVariables = entity as ResolvedVariableContent
                resolvedVariables?.variables.let { resolvedVarsObservableList.setAll(it) }
            }
        })
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
//        openTerminal(
//            "term" + (tabPane!!.tabs.size + 1),
//            "Terminal " + (tabPane!!.tabs.size + 1)
//        ) { terminal: Terminal, isNew: Boolean ->
//            tabPane!!.tabs.add(terminal.terminalTab)
//            tabPane!!.selectionModel.select(terminal.terminalTab)
//        }
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

    private fun updateInstructionInList() {
        val version = versionSelector.selectionModel?.selectedItem
        val instructions = version?.let { versions ->
            openedAggregates?.aggregates?.get(versions.id)?.let {
                it.flatMap { aggregate -> aggregate.instructions.orEmpty() }
            }
        }.orEmpty()
        instructionObservableList.setAll(instructions)
    }

    private fun updateDoc() {
        val version = versionSelector.selectionModel?.selectedItem
        val instructions = version?.let { versions ->
            openedAggregates?.aggregates?.get(versions.id)?.let {
                it.flatMap { aggregate -> aggregate.instructions.orEmpty() }
            }
        }.orEmpty()
        val str = StringBuilder("<html><body bgcolor='#141414' style=\"font-size:12px;color:#F3F3F3\">")
        instructions.forEach {
            str.append("<h2>").append(it.name).append("</h2>")
            str.append("<div>").append(it.description.orEmpty()).append("</div>")
            str.append("<div>").append(it.instruction).append("</div>")
            str.append("<div>").append(it.alias).append("</div>")
        }
        str.append("</body></html>")
        webDoc.engine.loadContent(str.toString())
    }

    private fun openTerminal(id: String, name: String, callBack: OpenerCallBack) {
//        terminalService.openTerminal(id, name, (terminal, isNew) -> {
//            addClosingEvent(terminal);
//            callBack.openerCallBack(terminal, isNew);
//        }, this::resolveVariable);
        terminalService.openTerminal(id, name) { terminal, isNew ->
            run {
                addClosingEvent(terminal);
                callBack.openerCallBack(terminal, isNew);
            }
        }
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

    private fun execCommandInRightTerminal(tabPane: TabPane, command: Instruction) {
//        val optTitle: Optional<Variable> =
//            variableService.findVar(appData[project!!.id].environment, "TITLE:" + command.getConsoleId())
//        var title: String = command.consoleId.orEmpty()
//        if (optTitle.isPresent() && optTitle.get().getValue() != null && !optTitle.get().getValue().isBlank()) {
//            title = optTitle.get().getValue()
//        }
        val variables = resolvedVariables?.variables
        var title: String =
            variables?.find { it.name == "TITLE:" + command.consoleId }?.value.orEmpty()
        if (title.isEmpty()) {
            title = command.consoleId.orEmpty()
        }

        command.consoleId?.let {
            openTerminal(it, title) { terminal, isNew ->
                if (terminal.window == null) {
                    terminalWindows + TerminalWindow(terminal)
                }
                terminal.window.popUp()
                terminal.window.selectTerminal(terminal)
//                terminalService.commandService.sendCommand(terminal.terminalTab, command.instruction, variables)
            }
        }
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
//        configs = loadConfigs(ArrayList(), appData[project!!.id].environment.dir)
        //        configMenu.getItems().clear();
//        buildConfigMenu(configs, configMenu);
//        loadConfig(appData.get(project.getId()).getEnvironment().getCommandLists());
    }

    override fun exec(command: String) {
        runCommand(command)
    }

    fun changeVersion(event: ActionEvent?) {
        val version = versionSelector.selectionModel?.selectedItem
        version?.let {
            projectStore.dispatch(OpenedAggregateRedux.LoadAggregates(it))
            projectStore.dispatch(OpenedEnvironmentRedux.LoadEnvironments(it))
        }
    }

}