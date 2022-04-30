package com.bgconsole.desktop_ui.environment;

import com.bgconsole.desktop_ui.Config;
import com.bgconsole.desktop_ui.command.CommandList;
import com.bgconsole.desktop_ui.command.CommandService;
import com.bgconsole.desktop_ui.project.Project;
import com.bgconsole.desktop_ui.project.ProjectCommand;
import com.bgconsole.desktop_ui.project.ProjectService;
import com.bgconsole.desktop_ui.project.ProjectVariable;
import com.bgconsole.desktop_ui.variable.VariableList;
import com.bgconsole.desktop_ui.variable.VariableService;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentServiceImpl implements EnvironmentService {

    private final VariableService variableService;

    private final CommandService commandService;

    private final ProjectService projectService;

    public EnvironmentServiceImpl(VariableService variableService, ProjectService projectService, CommandService commandService) {
        this.variableService = variableService;
        this.projectService = projectService;
        this.commandService = commandService;
    }

    @Override
    public Environment initEnv(Project project) {
        Environment environment = new Environment(project);
        VariableList sysVar = new VariableList(new Config("file", "SYS_VAR", ""));
        sysVar.setNewVariables(variableService.getSystemVariable());
        environment.getVariableLists().add(sysVar);
        environment.getVariableLists().addAll(loadVariables(project));
        environment.getCommandLists().addAll(loadCommands(environment, project));
        return environment;
    }

    private List<CommandList> loadCommands(Environment environment, Project project) {
        List<CommandList> commands = new ArrayList<>();
        for (ProjectCommand command : project.getCommands()) {
            String path = Paths.get(project.getWorkspace().getPath(), "projects", command.getPath()).toString();
            CommandList commandList = commandService.loadCommands(command.getName(), path);
            commandList.setNewList(commandService.replaceAllVars(environment.getVariableLists(), commandList.getCommands()));
            commands.add(commandList);
        }
        return commands;
    }

    private List<VariableList> loadVariables(Project project) {
        List<VariableList> variables = new ArrayList<>();
        for (ProjectVariable variable : project.getVariables()) {
            String path = Paths.get(project.getWorkspace().getPath(), "projects", variable.getPath()).toString();
            variables.add(variableService.loadVariables(new Config("file", variable.getId(), path)));
        }
        return variables;
    }

}
