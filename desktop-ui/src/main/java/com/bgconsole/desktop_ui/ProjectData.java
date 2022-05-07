package com.bgconsole.desktop_ui;

import com.bgconsole.platform.domain.Project;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectData {

    public static final Path DEFAULT_PROFILE_DIR = Paths.get(System.getProperty("user.home"), ".com.bgconsole", "profiles");

    private final Project project;

    private CommandRunner commandRunner;

    public ProjectData(Project project) {
        this.project = project;
    }

    public void reloadEnv() {
    }

    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }

    public void runCommand(String command) {
        if (commandRunner != null) {
            commandRunner.exec(command);
        }
    }

    public String getWorkspaceName() {
        return project.getName();
    }

}
