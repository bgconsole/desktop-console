package com.bgconsole.desktop.ui.global_window;

import com.bgconsole.desktop.ProjectData;
import com.bgconsole.desktop.command.Command;

public class TableCommand {

    private Command command;
    private ProjectData projectData;

    public TableCommand(Command command, ProjectData projectData) {
        this.command = command;
        this.projectData = projectData;
    }

    public String getName() {
        return command.getName();
    }

    public String getCommand() {
        return command.getCommand();
    }

    public String getWorkspace() {
        return projectData.getWorkspaceName();
    }

    public String getAlias() {
        return command.getAlias();
    }

    public ProjectData getLocationData() {
        return projectData;
    }
}
