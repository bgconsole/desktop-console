package com.bgconsole.desktop;

import com.bgconsole.desktop.command.CommandService;
import com.bgconsole.desktop.command.CommandServiceImpl;
import com.bgconsole.desktop.config.ConfigService;
import com.bgconsole.desktop.config.ConfigServiceImpl;
import com.bgconsole.desktop.environment.Environment;
import com.bgconsole.desktop.environment.EnvironmentService;
import com.bgconsole.desktop.environment.EnvironmentServiceImpl;
import com.bgconsole.desktop.profile.ProfileService;
import com.bgconsole.desktop.profile.ProfileServiceImpl;
import com.bgconsole.desktop.project.Project;
import com.bgconsole.desktop.project.ProjectService;
import com.bgconsole.desktop.project.ProjectServiceImpl;
import com.bgconsole.desktop.terminal.TerminalService;
import com.bgconsole.desktop.terminal.TerminalServiceImpl;
import com.bgconsole.desktop.variable.VariableService;
import com.bgconsole.desktop.variable.VariableServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProjectData {

    public static final Path DEFAULT_PROFILE_DIR = Paths.get(System.getProperty("user.home"), ".com.bgconsole", "profiles");

    private final Project project;

    private final EnvironmentService environmentService;

    private final CommandService commandService;

    private final TerminalService terminalService;

    private final VariableService variableService;


    private final ProjectService projectService;

    private final ProfileService profileService;

    private final ConfigService configService;

    private Environment environment;

    private CommandRunner commandRunner;

    public ProjectData(Project project) {
        this.project = project;
        commandService = new CommandServiceImpl();
        variableService = new VariableServiceImpl();
        terminalService = new TerminalServiceImpl(variableService, commandService);
        profileService = new ProfileServiceImpl();
        projectService = new ProjectServiceImpl();
        environmentService = new EnvironmentServiceImpl(variableService, projectService, commandService);
        configService = new ConfigServiceImpl(variableService, commandService);
        environment = environmentService.initEnv(project);
    }

    public void reloadEnv() {
        List<Config> configs = environment.getConfigs();
        environment = environmentService.initEnv(project);
        for (Config config : configs) {
            configService.loadConfig(environment, config);
        }
    }

    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }

    public void runCommand(String command) {
        if (commandRunner != null) {
            commandRunner.exec(command);
        }
    }

    public EnvironmentService getEnvironmentService() {
        return environmentService;
    }

    public ConfigService getConfigService() {
        return configService;
    }

    public CommandService getCommandService() {
        return commandService;
    }

    public TerminalService getTerminalService() {
        return terminalService;
    }

    public VariableService getVariableService() {
        return variableService;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getWorkspaceName() {
        return project.getName();
    }

}
