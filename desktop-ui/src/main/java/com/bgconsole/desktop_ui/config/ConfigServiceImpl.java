package com.bgconsole.desktop_ui.config;

import com.bgconsole.desktop_ui.command.CommandList;
import com.bgconsole.desktop_ui.command.CommandService;
import com.bgconsole.desktop_ui.environment.Environment;
import com.bgconsole.desktop_ui.Config;
import com.bgconsole.desktop_ui.variable.VariableList;
import com.bgconsole.desktop_ui.variable.VariableService;

public class ConfigServiceImpl implements ConfigService {

    private final VariableService variableService;

    private final CommandService commandService;

    public ConfigServiceImpl(VariableService variableService, CommandService commandService) {
        this.variableService = variableService;
        this.commandService = commandService;
    }

    @Override
    public Environment loadConfig(Environment environment, Config config) {
        VariableList variables = variableService.loadVariables(config);
        environment.getVariableLists().add(variables);
//        for (Variable variable : variableService.findVars(environment, "COMMAND_FILE")) {
//            String commandFile = variable.getValue();
//            if (commandFile != null && !commandFile.isBlank()) {
//                CommandList commands = commandService.loadCommands(commandFile);
//                environment.getCommandLists().add(commands);
//            }
//        }
        for (VariableList variableList : environment.getVariableLists()) {
            for (CommandList commandList : environment.getCommandLists()) {
//                commandList.setNewList(commandService.replaceVars(variableList.getVariables(), commandList.getCommands()));
            }
        }
        environment.getConfigs().clear();
        environment.getConfigs().add(config);
        return environment;
    }
}
